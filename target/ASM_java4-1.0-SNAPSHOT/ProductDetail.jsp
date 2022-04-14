<!--
=========================================================
* Material Dashboard 2 - v3.0.1
=========================================================

* Product Page: https://www.creative-tim.com/product/material-dashboard
* Copyright 2022 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://www.creative-tim.com/license)
* Coded by Creative Tim
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
-->
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>
            Product Detail
        </title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="./css/productDetail.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fyuck.css">

    </head>

    <body class="g-sidenav-show  bg-gray-200">

        <header id="header">
            <nav class="nav-bar">
                <div class="container">
                    <div class="logo">
                        <a href="#">
                            <h1><span>GP</span> SHOP</h1>
                        </a>
                    </div>
                    <div class="menu">
                        <ul class="menu-level-1">
                            <li class="item-menu-level-1">
                                <i id="popup-btn"> <i class="fas fa-search"></i></i>
                                <div class="popup">
                                    <div class="popup-content">
                                        <a href="#"><span class="close-btn">&times;</span></a>
                                        <div class="khoitim">
                                            <form method="post"> 
                                                <input name="searchName" id="search" type="search" placeholder="Search">
                                                <button type="submit" onclick="clickTimKiem()"> <i class="fas fa-search"> </i></button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="item-menu-level-1">
                                <a href="${pageContext.request.contextPath}/HomePageController" id="home" class="title-item-menu-level-1">HOME</a>
                            </li>
                            <li onclick="showMenu2()" class="item-menu-level-1">
                                <span class="title-item-menu-level-1">DANH MỤC SẢN PHẨM</span>
                                <i class="fas fa-angle-down"></i>
                                <ul id="menu2-dssp" class="menu-level-2">
                                    <div class="khoi-1">
                                        <h3>DANH MỤC SẢN PHẨM</h3>
                                        <c:forEach items="${listCat}" var="cat">
                                            <li class="item-menu-level-2">
                                                <i class="fas fa-angle-right"></i>
                                                <a href="${pageContext.request.contextPath}/HomePageController?idCat=${cat.getId()}" class="title-item-menu-level-2">${cat.getName()}</a>
                                            </li>
                                        </c:forEach>
                                    </div>
                                    <div class="hang">
                                        <div class="img-hang">
                                            <img src="https://shop.mixigaming.com/wp-content/uploads/2021/01/VUVU7648-2048x1365.jpg"
                                                 alt="">
                                        </div>
                                        <div class="img-hang">
                                            <img src="https://shop.mixigaming.com/wp-content/uploads/2021/01/binh-giu-nhiet-mixi-4-2048x1365.jpg"
                                                 alt="">
                                        </div>
                                        <div class="img-hang">
                                            <img src="https://shop.mixigaming.com/wp-content/uploads/2021/01/2-2048x1366.jpg"
                                                 alt="">
                                        </div>
                                    </div>
                                </ul>
                            </li>
                            <li class="item-menu-level-1">
                                <a href="#" class="title-item-menu-level-1">THÔNG BÁO</a>
                            </li>
                        </ul>
                    </div>

                    <div class="mua-hang">
                        <div class="sdt">
                            <i class="fas fa-user-cog">
                                <div class="avbcs" >
                                    <form action="UserController" method="post">          
                                        <button style="border-radius: 3px;padding: 3px; margin-top: 5px; background-color: #007784; outline: none; color: #FFF; border: none" class="btn bg-gradient-info w-100" >Log out</button>
                                    </form>
                                </div>

                            </i>

                        </div>      
                        <span>xin chao: ${username}</span>

                        <div style="border-left: 2px solid #555" id="cc" class="gio-hang">
                            <a href="./Cart.jsp">GIỎ HÀNG</a>
                            <a href="./Cart.jsp"><i class="fas fa-shopping-cart"></i></a>
                        </div>
                    </div>
                </div>
            </nav>
        </header>

        <div class='productDetail'>
            <div style="margin-top: 100px" class='container-images-productDetail'>
                <div class="imagesProductDetail">
                    <img style="width: 60%; margin-left: 100px" src=${detail.getImage()} alt="" />
                </div>
            </div>

            <div style="" class='main-item-productDetail'>    

                <div>
                    <div style="margin-top: 40px" class="nameProductDetail">${detail.getName()}</div>

                    <div class="priceProductDetail"> 
                        <fmt:formatNumber type="number" maxFractionDigits="2" value="${detail.getPrice()}" /> VNĐ 
                    </div>
                    <div class="">${detail.getDescription()}</div>
                </div>
                <div class="item-sp">
                    Số lượng: 
                    <input style="width: 50px; padding:  0px 5px; outline: none" value="1" type="number" name="" id="" min="1" max="10">
                </div>   
                <form action="CartController" method="post">
                    <input type="hidden" name="id" value="${detail.getId()}">
                    <button name="cart" value="add" style="margin-top: 20px; color: #ffffff ; background-color: #007784; border-radius: 7px" type="submit" class="btn btn-success">Thêm vào giỏ hàng</button>
                </form>
            </div>  


        </div>
        <c:import url="http://localhost:8080/ASM_java4/Footer.jsp" />        

    </body>
</html>
