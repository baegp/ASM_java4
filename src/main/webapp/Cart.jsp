<%-- 
    Document   : Cart
    Created on : Mar 28, 2022, 10:27:37 AM
    Author     : tranh
--%>
<%@page import="DTO.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DTO.Cart"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://kit.fontawesome.com/f20d460fd9.js" crassarigin="anonymou"></script>
        <link rel="stylesheet" href="./css/cart.css">

    </head>
    <body>

        <%
//            Cart cart = (Cart) session.getAttribute("cart");
//            if (cart != null) {
//                ArrayList<Item> ds = cart.getCart();
//                for (Item mh : ds) {
//                    Car sp = CarDao.findProductById(mh.getMaSp());
        %>

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
                            <li onclick="showSearch()" class="item-menu-level-1">
                                <a href="#"><i class="fas fa-search"></i></a>
                                <div id="mark">
                                    <input type="search" name="" id="search">
                                    <button onclick="timKiemSp()"><i class="fas fa-search"></i></button>
                                </div>
                            </li>
                            <li class="item-menu-level-1">
                                <a href="HomePage.jsp" id="home" class="title-item-menu-level-1">HOME</a>
                            </li>
                            <li onclick="showMenu2()" class="item-menu-level-1">
                                <a href="#" class="title-item-menu-level-1">DANH MỤC SẢN PHẨM</a>
                                <i class="fas fa-angle-down"></i>
                                <ul id="menu2-dssp" class="menu-level-2">
                                    <div class="khoi-1">
                                        <h3>DANH MỤC SẢN PHẨM</h3>
                                        <li class="item-menu-level-2">
                                            <i class="fas fa-angle-right"></i>
                                            <a href="#" class="title-item-menu-level-2">APPLE</a>
                                        </li>
                                        <li class="item-menu-level-2">
                                            <i class="fas fa-angle-right"></i>
                                            <a href="#" class="title-item-menu-level-2">SAMSUNG</a>
                                        </li>
                                        <li class="item-menu-level-2">
                                            <i class="fas fa-angle-right"></i>
                                            <a href="#" class="title-item-menu-level-2">XIAOMI</a>
                                        </li>
                                        <li class="item-menu-level-2">
                                            <i class="fas fa-angle-right"></i>
                                            <a href="#" class="title-item-menu-level-2">VSMART</a>
                                        </li>
                                        <li class="item-menu-level-2">
                                            <i class="fas fa-angle-right"></i>
                                            <a href="#" class="title-item-menu-level-2">LAPTOP</a>
                                        </li>
                                    </div>
                                    <div class="hang">
                                        <div class="img-hang">
                                            <img src="https://cdn.tgdd.vn/Products/Images/42/228743/iphone-12-pro-max-vang-new-600x600-600x600.jpg"
                                                 alt="">
                                        </div>
                                        <div class="img-hang">
                                            <img src="https://cdn.tgdd.vn/Products/Images/42/226264/xiaomi-mi-11-xanhduong-600x600-600x600.jpg"
                                                 alt="">
                                        </div>
                                        <div class="img-hang">
                                            <img src="https://cdn.tgdd.vn/Products/Images/42/234308/samsung-galaxy-s21-ultra-256gb-den-600x600-1-600x600.jpg"
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
                            <a href="../html/admin.html">ADMIN</a>
                        </div>
                        <div id="cc" class="gio-hang">
                            <a href="#">GIỎ HÀNG</a>
                            <i class="fas fa-shopping-cart"></i>
                        </div>
                    </div>
                </div>
            </nav>
        </header>

        <main>
            <div class="danh-sach-sp">
                <div class="container-sp">
                    <div class="content-product">
                        <h1>Giỏ hàng của bạn</h1>
                        <div class="list-item-product">
                            <div class="item-product">
                                <h3>Hình ảnh</h3>
                            </div>
                            <div class="item-product">
                                <h3>Sản phẩm</h3>
                            </div>
                            <div class="item-product">
                                <h3>Đơn giá</h3>
                            </div>
                            <div class="item-product">
                                <h3>Số lượng</h3>
                            </div>
                            <div class="item-product">
                                <h3>Tổng tiền</h3>
                            </div>
                            <div class="item-product">
                                <h3>Thao tác</h3>
                            </div>
                        </div>
                    </div>

                    <div id="thong-tin-sp">
                        <div class="san-pham-mua">
                            <div class="item-sp">
                                <div class="anh-san-pham">
                                    <img src="https://shop.mixigaming.com/wp-content/uploads/2022/02/VUVU4714-300x400.jpg" alt="" />

                                </div>
                            </div>
                            <div class="item-sp">
                                <h3>aa</h3>
                            </div>
                            <div class="item-sp tien">
                                <h3 id="don-gia">3423</h3>
                            </div>
                            <div class="item-sp">
                                <input type="number" name id="so-luong-${i}" value="1" min="1" max="10"/>
                            </div>
                            <div class="item-sp ">
                                <h3>212</h3>
                            </div>
                            <div class="item-sp">
                                xoa
                            </div>
                        </div>

                        <div class="thanh-toan">
                            <div class="tt-thanh-toan" ></div>
                            <div class="tt-thanh-toan" ></div>
                            <div class="tt-thanh-toan" ></div>
                            <div class="tt-thanh-toan">
                                <h3>Tổng</h3>
                            </div>
                            <div class="tt-thanh-toan tien">
                                <h3>{formatMoney(Number(sum))}</h3>
                            </div>
                            <div class="tt-thanh-toan"> </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="infor-khach-hang">
                <div class="container-khach-hang">
                    <div class="content-khach-hang">
                        <h1>Mua hàng</h1>
                        <div class="form-khach-hang">
                            <form action="" id="form-infor">
                                <div class="thong-tin ">
                                    <label for="ho-ten">Họ tên</label>
                                    <input placeholder="Nhập họ và tên" type="text" name="" id="ho-ten">
                                    <span class="form-messsage"></span>
                                </div>
                                <div class="thong-tin">
                                    <label for="sdt">Số điện thoại</label>
                                    <input placeholder="Nhập số điện thoại" type="number" name="" id="sdt">
                                    <span class="form-messsage"></span>
                                </div>
                                <div class="thong-tin">
                                    <label for="email">Email</label>
                                    <input placeholder="Nhập email" type="email" name="" id="email">
                                    <span class="form-messsage"></span>
                                </div>
                                <div class="thong-tin">
                                    <label for="address">Địa chỉ</label>
                                    <input placeholder="Nhập địa chỉ" type="text" name="" id="address">
                                    <span class="form-messsage"></span>
                                </div>
                                <div class="thong-tin">
                                    <label for="time-nhan-hang">Thời gian nhận hàng trong ngày 8-18h</label>
                                    <input placeholder="Nhập thời gian nhận hàng trong ngày" type="text" name=""
                                           id="time-nhan-hang">
                                    <span class="form-messsage"></span>
                                </div>
                                <button type="submit" class="btn btnPrimary">Mua</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <%--<c:import url="http://localhost:8080/ASM_java4/Footer.jsp" />--%>

    </body>
</html>
