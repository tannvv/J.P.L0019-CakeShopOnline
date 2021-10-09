<%-- 
    Document   : viewCart
    Created on : Jul 5, 2021, 9:03:40 PM
    Author     : Tan Nguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>
        <style type="text/css">
         
            section{
                padding-top: 30px;
            }
            .img{
                text-align: center;
                border: 1px solid green;
                padding:5px;
                margin : 5px;
                height: 250px;
                width: 200px;
                float: left;
            }
            footer{
                padding-top: 30px;
                clear: both;
                height: 120px;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <font color="red">${error}</font>
        <h2 align="center">List cart</h2>
        <section>
            <table border="1px solid green" align="center" width="90%">
                <tr>
                    <th>Status</th>
                    <th>Cake name </th>
                    <th>Amount </th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Action</th>
                </tr>

                <c:forEach items="${sessionScope.listCart}" var="cart">
                    <form action="ListCartController" method="POST">
                    <tr>
                        <input type="hidden" name="action" value="update">
                        <td><input type="checkbox" name="status" ${cart.status eq true ? "checked" : ""} value="true"></td>
                        <input type="hidden" name="cakeID" value="${cart.cakeID.cakeID}">
                        <th align="center">${cart.cakeID.cakeName}</th>
                        
                        <td><input type="number" min="1" step="1" value="${cart.quantity}" name="quantity" ></td>
             
                        <td>${cart.cakeID.price}</td>
                        <td>${cart.quantity * cart.cakeID.price}</td>
                        <td align="center">
                            <input type="submit" value="Update" >
                            <a href="ListCartController?action=remove&id=${cart.cakeID.cakeID}"
                                              onclick="return confirm('Are you sure to delete !!!')" >Remove</a>
                        </td>
                    </tr>
                    </form>
                </c:forEach>
                <form action="PayController" method="GET">
                <tr>
                    <th colspan="1">Total cost</th>
                    <th colspan="2"><input type="number" readonly="" name="totalCost" value="${totalCost}"></th>
                </tr>
                <input type="submit" value="Pay">
                </form>
            </table>
                <font color="red">${error}</font> </br>

        </section>
        <footer align="right">
            <div class="container" style="background-color:#f1f1f1">
                </br>Hello ${userData.fullName}- <a href="MainController?action=loadList">View products</a>
               
                <c:if test = "${sessionScope.userData != null}">
                     - <a href="MainController?action=logout">Log out</a>   </br></br>
                </c:if>
            </div>
        </footer>
    </body>
</html>
