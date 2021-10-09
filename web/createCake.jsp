<%-- 
    Document   : watchForm
    Created on : Jul 5, 2021, 6:48:26 PM
    Author     : Tan Nguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create form</title>
    </head>
    <body>
        <h2>Create new cake</h2>
        
        <font color="red">${requestScope.error}</font>
        <form action="CreateController" method="POST" enctype="multipart/form-data">
            <table>
                <tr>
                    <td>Cake ID :</td>
                    <td><input type="text" value="${cake.cakeID}" name="cakeID" required="" minlength="3" maxlength="10"></td>
                </tr>
                <tr>
                    <td>Cake name :</td>
                    <td><input type="text" value="${cake.cakeName}" name="cakeName" required="" minlength="5" maxlength="30"></td>
                </tr>
                <tr>
                    <td>Description :</td>
                    <td><input type="text" value="${cake.description}" name="description" required="" minlength="1" maxlength="100"></td>
                </tr>
                <tr>
                    <td>Amount :</td>
                    <td><input type="number" value="${cake.amount}" name="amount" required="" min="0" step="1"></td>
                </tr>
                <tr>
                    <td>Price :</td>
                    <td><input type="number" value="${cake.price}" name="price" required="" min="0" step="1"></td>
                </tr>
                <tr>
                    <td>Create date :</td>
                    <td><input type="date" id="createDate" value="${cake.createDate}" name="createDate" required="" ></td>
                </tr>
                
                <tr>
                    <td>Expiration date :</td>
                    <td><td><input type="date" id="expDate" value="${cake.expDate}" name="expDate" required="" ></td>
                    </td>
                </tr>
                <tr>
                    <td>Status :</td>
                    <td><select name="status">
                            <option value="true">Active</option>
                            <option value="false" ${cake.status eq false ? "selected":""}>Inactive</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Category :</td>
              
                    <td>
                        <select name="category">
                            <c:forEach items="${listCategory}" var="category">
                                <option value="${category.id}" ${catk.categoryID eq category.id ? "selected" : ""}>${category.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Url image :</td>
                    <td><input type="file" accept=".jpg" name="photo" required=""></td>
                </tr>
            </table>
            <input type="submit" value="Create">
        </form>
    </body>
    
    <script>
        var createDate = document.getElementById('createDate');
        createDate.max = new Date().toISOString().split("T")[0];
        var expDate = document.getElementById('expDate');
        expDate.min = new Date().toISOString().split("T")[0];
    </script>
</html>
