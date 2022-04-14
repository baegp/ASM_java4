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

    </head>

    <body class="g-sidenav-show  bg-gray-200">

        <c:import url="http://localhost:8080/ASM_java4/Header.jsp" />

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
