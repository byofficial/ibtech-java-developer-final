<%@ page import="com.ibtech.mall.database.entity.Account" %>

<%
    Account auth = (Account) request.getSession().getAttribute("auth");
    if (auth != null) {
        response.sendRedirect("index.jsp");
    }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="theme/account/css/owl.carousel.min.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="theme/account/css/bootstrap.min.css">

    <!-- Style -->
    <link rel="stylesheet" href="theme/account/css/style.css">

</head>
<body>

<div class="d-lg-flex half">
    <div class="bg order-1 order-md-2" style="background-image: url('theme/account/images/bg_1.jpg');"></div>
    <div class="contents order-2 order-md-1">

        <div class="container">
            <div class="row align-items-center justify-content-center">
                <div class="col-md-7">
                    <h3>Login to <strong>Shopping Mall</strong></h3>
                    <p class="mb-4">Lorem ipsum dolor sit amet elit. Sapiente sit aut eos consectetur adipisicing.</p>
                    <form action="login-account" method="POST">
                        <div class="form-group first">
                            <label for="username">Kullanıcı Adı</label>
                            <input type="text" class="form-control" placeholder="Kullanıcı adınız" name="accountName"
                                   id="username">
                        </div>
                        <div class="form-group last mb-3">
                            <label for="password">Parola</label>
                            <input type="password" class="form-control" placeholder="Parolanız" name="accountPassword"
                                   id="password">
                        </div>

                        <input type="submit" value="Giriş Yap" class="btn btn-block btn-primary">

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
