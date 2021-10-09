<%-- 
    Document   : inforPay
    Created on : Sep 13, 2021, 2:58:01 PM
    Author     : TanNV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <font color="red">${error}</font></br>
        Enter information to pay : </br>
        <form action="PayController" method="POST">
            Name : <input type="text" name="nameCustomer" required=""></br>
            Phone number : <input type="number" min="10000000" max="1000000000000" required="" name="phone"></br>
            Address : <input type="text" name="address" required=""></br>
            Total cost : <font>${totalCost}</font></br>
            Method : <font>Payment on delivery</font> </br>
            <input type="submit" value="Pay">
        </form>
    </body>
</html>
