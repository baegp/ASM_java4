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
        <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fyuck.css">

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
                                    <!--                                    <div class="popup-content">
                                                                            <a href="#"><span class="close-btn">&times;</span></a>
                                                                            <div class="khoitim">
                                                                                <form method="post"> 
                                                                                    <input name="searchName" id="search" type="search" placeholder="Search">
                                                                                    <button type="submit" onclick="clickTimKiem()"> <i class="fas fa-search"> </i></button>
                                                                                </form>
                                                                            </div>
                                                                        </div>-->
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
        <%--<c:import url="http://localhost:8080/ASM_java4/Header.jsp" />--%>

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

                    <%
                        Cart cart = (Cart) session.getAttribute("cart");
                        if (cart != null) {
                            ArrayList<Item> items = cart.getCart();
                            for (Item item : items) {
                    %>
                    <form action="CartController" method="post">
                        <div id="thong-tin-sp">
                            <div class="san-pham-mua">
                                <input type="hidden" class="form-control" name="maSp" id="maSp">
                                <div class="item-sp">
                                    <div class="anh-san-pham">
                                        <img src="<%=item.getImage()%>" alt="" />
                                    </div>
                                </div>
                                <div class="item-sp">
                                    <h3><%=item.getName()%></h3>
                                </div>
                                <div class="item-sp tien">
                                    <h3 id="don-gia">
                                        <fmt:formatNumber type="number" maxFractionDigits="2" value="<%=item.getPrice()%>"/>
                                    </h3>
                                </div>
                                <div class="item-sp">
                                    <form action="CartController" method="post">
                                        <input type="hidden" name="id" value="<%=item.getMaSp()%>"/>
                                        <button style="padding: 2px 5px" type="submit" name="cart" value="dete">-</button>
                                        <button style="padding: 2px 5px" ><%=item.getSoLuong()%></button>
                                        <button style="padding: 2px 5px" type="submit" name="cart" value="add">+</button>
                                    </form>
                                </div>
                                <div class="item-sp ">
                                    <h3><fmt:formatNumber type="number" maxFractionDigits="2" value="<%= item.getPrice() * item.getSoLuong()%>"/></h3>
                                </div>
                                <div class="item-sp">
                                    <form action="CartController" method="post">
                                        <input type="hidden" name="id" value="<%=item.getMaSp()%>"/>
                                         
                                        <button style="padding: 2px 5px; border-radius: 8px" type="submit" name="cart" value="remove" class="btn btnPrimary btn-block">
                                           x
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </form>
                    <% }
                        }%>


                    <div class="thanh-toan">
                        <div class="tt-thanh-toan" ></div>
                        <div class="tt-thanh-toan" ></div>
                        <div class="tt-thanh-toan" ></div>
                        <div class="tt-thanh-toan">
                            <h3>Tổng</h3>
                        </div>
                        <div class="tt-thanh-toan tien">
                            <h3><fmt:formatNumber type="number" maxFractionDigits="2" value="<%= cart.getTongTien()%>"/></h3>
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
                                <input placeholder="Nhập họ và tên" type="text" name="" id="ho-ten"required>
                                <span class="form-messsage"></span>
                            </div>
                            <div class="thong-tin">
                                <label for="sdt">Số điện thoại</label>
                                <input placeholder="Nhập số điện thoại" type="number" name="" id="sdt"required>
                                <span class="form-messsage"></span>
                            </div>
                            <div class="thong-tin">
                                <label for="email">Email</label>
                                <input placeholder="Nhập email" type="email" name="" id="email"required>
                                <span class="form-messsage"></span>
                            </div>
                            <div class="thong-tin">
                                <label for="address">Địa chỉ</label>
                                <input placeholder="Nhập địa chỉ" type="text" name="" id="address"required>
                                <span class="form-messsage"></span>
                            </div>
                            <div class="thong-tin">
                                <label for="time-nhan-hang">Thời gian nhận hàng trong ngày 8-18h</label>
                                <input placeholder="Nhập thời gian nhận hàng trong ngày" type="text" name=""
                                       id="time-nhan-hang"required>
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
