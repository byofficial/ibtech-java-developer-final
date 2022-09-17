<%@ page pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
    <a href="" class="text-decoration-none d-block d-lg-none">
        <h1 class="m-0 display-5 font-weight-semi-bold"><span
                class="text-danger font-weight-bold border px-3 mr-1">E</span>Ibtech</h1>
    </a>
    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
        <div class="navbar-nav mr-auto py-0">
            <a href="index.html" class="nav-item nav-link active">sad</a>
            <a href="shop.html" class="nav-item nav-link">Shop</a>
            <a href="detail.html" class="nav-item nav-link">Shop Detail</a>
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                <div class="dropdown-menu rounded-0 m-0">
                    <a href="cart.html" class="dropdown-item">Shopping Cart</a>
                    <a href="checkout.html" class="dropdown-item">Checkout</a>
                </div>
            </div>
            <a href="contact.html" class="nav-item nav-link">Contact</a>
        </div>
        <div class="navbar-nav ml-auto py-0">
            <%if (auth == null) {%>
            <a href="login.jsp" class="nav-item nav-link">Giriş Yap</a>
            <a href="register.jsp" class="nav-item nav-link">Kayıt Ol</a>
            <%
            } else {
            %>
            <li class="nav-item">Merhaba, <strong><%= auth.getAccountName()%>!</strong></li>
            <li class="nav-item"><a class="nav-link" href="logout">Çıkış Yap</a></li>
            <%
                }
            %>
        </div>
    </div>
</nav>
