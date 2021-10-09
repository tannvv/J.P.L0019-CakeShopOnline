<%-- 
    Document   : indexUser
    Created on : Sep 11, 2021, 9:39:09 PM
    Author     : TanNV
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            header{
                height: 120px;
                align-items: center;
            }
            section{
                padding-left: 5%;
                padding-bottom: 40px;
                padding-top: 10px;
                clear: both;
                height: 200vh;
            }
            .img{
                text-align: center;
                border : 1px solid green;
                padding: 5px;
                margin: 5px;
                height: 350px;
                width: 200px;
                float: left;
            }
            footer{
                padding-top: 50px;
                clear:both;
                height: 120px;
                align-items: center;
            }
            #watch{
                padding-bottom: 10px;
                padding-top: 10px;
            }
            .header{
                padding-left: 13%;
                padding-bottom: 30px;
            }
            h2{
                padding-left: 40%;
                padding-bottom: 30px;
            }
            .search{
                padding-left: 7%;
                padding-bottom: 20px;
            }
            font{
                padding-left: 70px;
                font-size: 20px;
            }
            #main{
                display: block;
          
            }
        </style>
    </head>
    <body>
        <font>Hello ${sessionScope.userData.fullName}</font>
        <img src="images/main.jpg" width="70%" height="150px" class="header">
        <h2>Yellow Moon Shop</h2>
        <font color="red">${errorCategory}</font>
        <form action="MainController" method="POST">
<!--            <input type="hidden" value="SearchCategoryController" name="action">-->
            <input type="hidden" name="action" value="searchCategory">
            <label class="search">Search with categories cake : </label>
            <select name="category">
                <c:forEach items="${listCategory}" var="category">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Search">
        </form>

<!--        <font color="red">${errorPrice}</font>
        <form action="SearchServlet" method="POST">
            <input type="hidden" value="price" name="action">
            <label class="search">Search with range of price : </label>
            Min : <input type="number" min="0" step="1" name="min">
            Max : <input type="number" min="0" step="1" name="max">
            <input type="submit" value="Search">
        </form>-->

        <font color="red">${errorName}</font>
         <form action="MainController" method="POST">

            <input type="hidden" name="action" value="searchName">
            <label class="search">Search with name : </label>
            Name : <input type="text" name="name" required="">
            <input type="submit" value="Search">
        </form>
        <font color="green">${notifi}</font>
        <section id="main">
            <c:forEach items="${listCake}" var="cake">
                <div class="img">
                    ${cake.cakeID}-${cake.cakeName}</br>
                    <img id="watch" src="images/${cake.image}" width="120" height="150"></br>
                    Description : ${cake.description}</br>
                    Create date : ${cake.createDate}</br>
                    Expiration date : ${cake.expDate}</br>
                    Amount : ${cake.amount}</br>
                    Price : ${cake.price}</br>
                    <a href="ListCartController?action=buy&id=${cake.cakeID}">Add to cart</a>
                </div>
            </c:forEach>
        </section>
        <div width="100%" clear="both">
            <c:forEach begin="1" end="${numberPage}" var="i">
                <a href="LoadListController?indexPage=${i}">${i}</a>
            </c:forEach>
        </div>
        </br></br></br>

        <footer>
            <div class="container" style="background-color:#f1f1f1">
                </br>${userData.fullName}-<a href="LoadListController">View list product</a> - <a href="ListCartController?action=view">View shopping cart</a> - <a href="TrakingOrderController">Tracking order</a>
                - <a href="LogoutController">Log out</a>   </br></br>
            </div>
        </footer>
    </body>
</html>
