<%-- 
    Document   : trackingOrder
    Created on : Sep 13, 2021, 8:12:15 PM
    Author     : TanNV
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            <input type="hidden" value="tracking" name="action">
            Enter order to tracking : <input type="number" min="1" step="1" name="orderID" required=""><input type="submit" value="Search">
        </form>
        </br></br>
        <font color="red">${error}</font>
        <table border="1px solid">
            <tr>
                <td>User name  </td>
                <td>Order ID  </td>
                <td>Order date  </td>
                <td>List of cake  </td>
                <td>Payment method  </td>
                <td>Shipping address  </td>
                <td>Total cost </td>
            </tr>
             <tr>
                <td>${order.customerName}</td>
                <td>${order.orderID}</td>
                <td>${order.orderDate}</td>
                <td>
                    <c:forEach items="${requestScope.listOrderDetail}" var="orderDetail">
                         ${orderDetail.cakeID.cakeName} - (${orderDetail.quantity})</br>
                    </c:forEach>
                </td>
                <td><c:if test="${order.method == 1}">
                        Payment upon delivery
                </c:if></td>
                <td>${order.address}</td>
                <td>${order.totalCost}</td>
            </tr>
        </table>
            
            
        <footer>
            <div class="container" style="background-color:#f1f1f1">
                </br>${userData.fullName}-<a href="LoadListController">View list product</a> - <a href="ListCartController?action=view">View shopping cart</a> - <a href="TrakingOrderController">Tracking order</a>
                - <a href="LogoutController">Log out</a>   </br></br>
            </div>
        </footer>
    </body>
</html>
