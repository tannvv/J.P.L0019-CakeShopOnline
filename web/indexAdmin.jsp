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
                height: 400vh;
            }
            .img{
                text-align: center;
                border : 1px solid green;
                padding: 5px;
                margin: 5px;
                height: 700px;
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
        <a href="MainController?action=create">Create new cake</a>
        <form action="MainController" method="POST">
            <input type="hidden" value="searchCategory" name="action">
            <label class="search">Search with categories cake : </label>
            <select name="category">
                <c:forEach items="${listCategory}" var="category">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
            <input type="submit" value="Search">
        </form>


        <font color="red">${errorName}</font>
        <form action="MainController" method="POST">
            <input type="hidden" value="searchName" name="action">
            <label class="search">Search with name : </label>
            Name : <input type="text" name="name" required="">
            <input type="submit" value="Search">
        </form>
        <font color="green">${notifi}</font>
        <section id="main">
            <c:forEach items="${listCake}" var="cake">
                <div class="img">
                    <form action="UpdateCakeController" method="POST" enctype="multipart/form-data">
                       
                        Cake ID :<input type="text" value="${cake.cakeID}" readonly="" name="cakeID"></br>
                        </br>Cake Name :<input type="text" value="${cake.cakeName}" name="cakeName"></br>
                        <img id="watch" src="images/${cake.image}" width="120" height="150"></br>
                        <td><input type="file" accept=".jpg" name="photo" required=""></td>
                        </br> Description : <input type="text" value="${cake.description}" name="description" required="" minlength="1" maxlength="100"></br>
                        </br> Price : <input type="number" value="${cake.price}" name="price" required="" min="0" step="1"></br>
                        </br> Amount : <input type="number" value="${cake.amount}" name="amount" required="" min="0" step="1"></br>
                        </br> Create date : <input type="date" id="createDate" value="${cake.createDate}" name="createDate" required="" ></br>
                        </br> Expiration date : <input type="date" id="expDate" value="${cake.expDate}" name="expDate" required="" ></br>
                        </br> Category : <select name="category">
                                        <c:forEach items="${listCategory}" var="category">
                                            <option value="${category.id}" ${catk.categoryID eq category.id ? "selected" : ""}>${category.name}</option>
                                        </c:forEach>
                                    </select> </br>
                        Status : <select name="status">
                                    <option value="true">Active</option>
                                    <option value="false" ${cake.status eq false ? "selected":""}>Inactive</option>
                                </select> </br></br>
                                <input type="submit" value="Update">
                    </form>
                </div>
            </c:forEach>
        </section>
        <div width="100%">
            <c:forEach begin="1" end="${numberPage}" var="i">
                <a href="LoadListController?indexPage=${i}">${i}</a>
            </c:forEach>
        </div>
        </br></br></br>
        
        <footer>
            <div class="container" style="background-color:#f1f1f1">
                </br>${userData.fullName}-<a href="MainController?action=loadList">View list product</a>-<a href="TrakingOrderController"> Tracking order</a>
                - <a href="MainController?action=logout">Log out</a>   </br></br>
            </div>
        </footer>
    </body>
      <script>
        var createDate = document.getElementById('createDate');
        createDate.max = new Date().toISOString().split("T")[0];
        var expDate = document.getElementById('expDate');
        expDate.min = new Date().toISOString().split("T")[0];
    </script>
</html>
