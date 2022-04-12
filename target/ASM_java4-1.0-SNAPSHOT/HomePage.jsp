<%-- 
    Document   : HomePage
    Created on : Mar 26, 2022, 10:37:02 PM
    Author     : tranh
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <%
//            String username = (String) session.getAttribute("username");
//            boolean value = (boolean) session.getAttribute("login");
//            String role = (String) session.getAttribute("role");
//
//            if (value == false) {
//                response.sendRedirect(request.getContextPath() + "/Login.jsp");
//            }
%> 

        <%--<%=username%>--%>
        <%--<c:import url="http://localhost:8080/ASM_java4/Header.jsp" />--%>
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

        <main id="main">
            <section id="slide-show">
                <div class="slide">
                    <img src="./img/mixitet.png" alt="">
                </div>
            </section>

            <div class="title">
                <div class="sap-xep">
                    <ul class=" menu-sap-xep">
                        <a href="#">Sắp xếp sản phẩm <i class="fas fa-angle-down"></i></a>
                        <li onclick="sapXepTangDan()"><a href="#">Tăng dần theo giá</a></li>
                        <li onclick="sapXepGiamDan()"><a href="#">Giảm dần theo giá</a></li>
                    </ul>
                </div>
                <div class="content-title-sp">
                    <h1>DANH SÁCH SẢN PHẨM</h1>
                </div>
            </div>
            <section id="xuatSanPham">
                <c:forEach items="${listPro}" var="pro">
                    <div class="sanPham ">     
                        <a href="${pageContext.request.contextPath}/HomePageController?id=${pro.getId()}">
                            <div class="img">
                                <img src="${pro.getImage()}" alt="">
                            </div>
                        </a>
                        <div class="ten">
                            ${pro.getName()}
                        </div>
                        <div class="sao">
                            <i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i><i class="fas fa-star"></i>
                        </div>
                        <div class="gia">
                            <%--<fmt:formatNumber type="number" maxFractionDigits="2" value="${pro.getPrice()}" />--%>
                            <fmt:formatNumber type="number" maxFractionDigits="2" value="${pro.getPrice()}"/>
                            VND
                        </div>
                        <form action="CartController" method="post">
                            <input type="hidden" name="id" value="${pro.getId()}">
                            <button value="add" name="cart" type="submit" class="btn btnPrimary">Thêm vào giỏ hàng</button>
                        </form>
                    </div>
                </c:forEach>

            </section>

            <section class="contact">
                <div data-aos="zoom-in-right" data-aos-duration="1000" id="particles-js" class="container-contact">
                    <div class="content-contact">
                        <h1>GP Shop</h1>
                        <p>Cập nhật thông tin về sản phẩm mới</p>
                        <div class="lienHe">
                            <a class="page" href="#"><span>FANFAGE</span></a>
                            <a class="ins" href="#"><span>INSTAGRAM</span></a>
                        </div>
                    </div>
                </div>
            </section>

            <c:import url="http://localhost:8080/ASM_java4/Footer.jsp" />

            <script src="./js/main.js"></script>
            <script src="./js/cart.js"></script>
    </body>
</html>
