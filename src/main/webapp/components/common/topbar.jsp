<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.ibtech.mall.database.entity.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%
    ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cartList != null) {
        request.setAttribute("cart_list", cartList);
    }
%>

<div class="container-fluid">
    <div class="row bg-secondary py-2 px-xl-5">
        <div class="col-lg-6 d-none d-lg-block">
            <div class="d-inline-flex align-items-center">
                <a class="text-dark" href="">SSS</a>
                <span class="text-muted px-2">|</span>
                <a class="text-dark" href="">Yardım</a>
                <span class="text-muted px-2">|</span>
                <a class="text-dark" href="">Destek</a>
            </div>
        </div>
        <div class="col-lg-6 text-center text-lg-right">
            <div class="d-inline-flex align-items-center">
                <a class="text-dark px-2" href="">
                    <i class="fab fa-facebook-f"></i>
                </a>
                <a class="text-dark px-2" href="">
                    <i class="fab fa-twitter"></i>
                </a>
                <a class="text-dark px-2" href="">
                    <i class="fab fa-linkedin-in"></i>
                </a>
                <a class="text-dark px-2" href="">
                    <i class="fab fa-instagram"></i>
                </a>
                <a class="text-dark pl-2" href="">
                    <i class="fab fa-youtube"></i>
                </a>
            </div>
        </div>
    </div>
    <div class="row align-items-center py-3 px-xl-5">
        <div class="col-lg-3 d-none d-lg-block">
            <a href="/" class="text-decoration-none">
                <h1 class="m-0 display-5 font-weight-semi-bold"><span
                        class="text-success font-weight-bold border px-3 mr-1">I</span>btech</h1>
            </a>
        </div>
        <div class="col-lg-6 col-6 text-left">
            <p class="text-dark text-center" style="margin-top: revert !important;">The Shopping Mall!</p>
        </div>
        <div class="col-lg-3 col-6 text-right">
            <a href="" class="btn border">
                <i class="fas fa-heart text-primary"></i>
                <span class="badge badge-primary">0</span>
            </a>
            <a href="cart.jsp" class="btn border">
                <i class="fas fa-shopping-cart text-primary"></i>
                <span class="badge badge-danger">${cart_list.size() > 0 ? cart_list.size() : 0}</span>
            </a>
        </div>
    </div>
</div>