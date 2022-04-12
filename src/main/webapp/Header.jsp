<%-- 
    Document   : Header
    Created on : Mar 28, 2022, 10:17:57 AM
    Author     : tranh
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://kit.fontawesome.com/f20d460fd9.js" crassarigin="anonymou"></script>
        <link rel="stylesheet" href="./css/style.css">
        <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
        <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
    </head>
    <body>
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
                                            <input id="search" type="search" placeholder="Search">
                                            <button onclick="clickTimKiem()"> <i class="fas fa-search"> </i></button>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="item-menu-level-1">
                                <a href="${pageContext.request.contextPath}/HomePageController" id="home" class="title-item-menu-level-1">HOME</a>
                            </li>
                            <li onclick="showMenu2()" class="item-menu-level-1">
                                <a href="#" class="title-item-menu-level-1">DANH MỤC SẢN PHẨM</a>
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
                            <i class="fas fa-user-cog"></i>
                            <span>xin chao: ${username}</span>

                        </div>
                        <div id="cc" class="gio-hang">
                            <a href="./Cart.jsp">GIỎ HÀNG</a>
                            <a href="./Cart.jsp"><i class="fas fa-shopping-cart"></i></a>
                        </div>
                    </div>
                </div>
            </nav>
        </header>
    </body>
</html>
