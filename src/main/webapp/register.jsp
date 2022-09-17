<%@ page import="com.ibtech.mall.database.entity.Account" %>

<%
    Account auth = (Account) request.getSession().getAttribute("auth");
    if (auth != null) {
        response.sendRedirect("index.jsp");
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Kayıt Ol</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="theme/account/css/owl.carousel.min.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="theme/account/css/bootstrap.min.css">

    <!-- Style -->
    <link rel="stylesheet" href="theme/account/css/style.css">

</head>
<body>

<div class="d-lg-flex half">
    <div class="bg orders-1 orders-md-2" style="background-image: url('theme/account/images/bg_1.jpg');"></div>
    <div class="contents orders-2 orders-md-1">

        <div class="container">
            <div class="row align-items-center justify-content-center">
                <div class="col-md-7">
                    <h3>Kayıt Ol | Ibtech <strong>Shopping Mall</strong></h3>
                    <p class="mb-4">Ibtech Java Developer Bootcamp | E-Ticaret Sitesi</p>
                    <p class="alert-danger">${param.message}</p>
                    <form action="register-account" method="POST">
                        <div class="form-group first">
                            <label for="username">Kullanıcı Adı:</label>
                            <input type="text" class="form-control" placeholder="Kullanıcı adınız" name="accountName"
                                   id="username">
                        </div>
                        <div class="form-group first">
                            <label for="username">Mail Adresi:</label>
                            <input type="email" class="form-control" placeholder="Mail Adresiniz" name="accountEmail"
                                   id="email">
                        </div>
                        <div class="form-group last mb-3">
                            <label for="password">Parola:</label>
                            <input type="password" class="form-control" placeholder="Parolanız" name="accountPassword"
                                   id="password">
                        </div>
                        <input type="submit" value="Kayıt Ol" class="btn btn-block btn-primary">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="theme/account/js/jquery-3.3.1.min.js"></script>
<script src="theme/account/js/popper.min.js"></script>
<script src="theme/account/js/bootstrap.min.js"></script>
<script src="theme/account/js/main.js"></script>
</body>
</html>
