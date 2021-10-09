/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.log4j.Logger;
import tannv.cake.CakeDAO;
import tannv.cake.CakeDTO;
import tannv.category.CategoryDAO;
import tannv.user.UserDTO;
import tannv.utils.Convert;

/**
 *
 * @author TanNV
 */
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100
)
public class CreateController extends HttpServlet {

    
    private final String CREATE_PAGE = "createCake.jsp";
    private final String ERROR_PAGE = "error.jsp";
    static final Logger LOGGER = Logger.getLogger(CreateController.class);
    private final String UPLOAD_DIR = "images";
    private final String LOGIN_PAGE = "login.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        UserDTO userData = (UserDTO) request.getSession().getAttribute("userData");
        if (userData == null || userData.getRole() != 2) {
            request.setAttribute("error", "You not have permission to to this fucntion");
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
        
        try {
            CategoryDAO dao = new CategoryDAO();
            request.setAttribute("listCategory", dao.getAllCategories());
            request.getRequestDispatcher(CREATE_PAGE).forward(request, response);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String cakeID = request.getParameter("cakeID");
            String cakeName = request.getParameter("cakeName");
            String description = request.getParameter("description");
            int amount = Integer.parseInt(request.getParameter("amount"));
            int price = Integer.parseInt(request.getParameter("price"));
            Date createDay = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("createDate"));
            Date expDay = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("expDate"));
            Boolean status = Boolean.parseBoolean(request.getParameter("status"));
            String category = request.getParameter("category");
            String image = uploadFile(request);
            System.out.println(createDay + "," + expDay);
            CakeDTO cake = new CakeDTO(cakeID, cakeName, image, description, category, status, Convert.utilDateToSqlDate(createDay), Convert.utilDateToSqlDate(expDay), price, amount);
            CakeDAO dao = new CakeDAO();
            CakeDTO checkCakeID = dao.getCakeByID(cakeID);
            if (checkCakeID != null) {
                request.setAttribute("error", "ID is existed");
                LOGGER.error("ID is existed");
                CategoryDAO categoryDAO = new CategoryDAO();
                request.setAttribute("listCategory", categoryDAO.getAllCategories());
                request.setAttribute("cake", cake);
                request.getRequestDispatcher(CREATE_PAGE).forward(request, response);
            }
            boolean check = dao.createCake(cake);
            if (check) {
                request.setAttribute("listCake",dao.getPageCakeAdmin(1));
                CategoryDAO categoryDAO = new CategoryDAO();
                request.setAttribute("listCategory", categoryDAO.getAllCategories());
                request.setAttribute("notifi", "Create new cake success");
                request.getRequestDispatcher("LoadListController").forward(request, response);
                LOGGER.info("Create success");
            }else{
                request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
                LOGGER.info("Create cake error");
            }
            
        } catch (Exception e) {
            LOGGER.error("Insert new cake is error : "+ e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
     private String uploadFile(HttpServletRequest request) throws IOException, ServletException {
        String fileName = "";
        try {
            Part filePart = request.getPart("photo");
            fileName = (String) getFileName(filePart);

            String applicationPath = request.getServletContext().getRealPath("");
            String project = applicationPath.substring(0, applicationPath.length() - 10) + "web";
            String basePath = project + File.separator + UPLOAD_DIR + File.separator;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                File outputFilePath = new File(basePath + fileName);
                inputStream = filePart.getInputStream();
                outputStream = new FileOutputStream(outputFilePath);
                int read = 0;
                final byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
            } catch (Exception e) {
                e.printStackTrace();
                fileName = "";
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }

        } catch (Exception e) {
            fileName = "";
            LOGGER.error("Upload file error" + e.getMessage());
        }
        return fileName;
    }

    private String getFileName(Part filePart) {
        for (String content : filePart.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}
