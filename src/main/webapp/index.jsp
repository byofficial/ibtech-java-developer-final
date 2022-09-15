<%@ page import="com.ibtech.mall.database.entity.Account" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Account auth = (Account) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("person", auth);
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Ibtech Shopping Mall</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">
    <%@include file="components/common/common_js_css.jsp" %>
</head>
<body>
<!-- Topbar Start -->
<%@include file="components/common/topbar.jsp" %>
<!-- Topbar End -->


<!-- Navbar Start -->
<div class="container-fluid mb-5">
    <div class="row border-top px-xl-5">
        <div class="col-lg-3 d-none d-lg-block">
            <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100"
               data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                <h6 class="m-0">Kategoriler</h6>
                <i class="fa fa-angle-down text-dark"></i>
            </a>
            <%@include file="components/home/nav_category.jsp" %>
        </div>
        <div class="col-lg-9">
            <%@include file="components/common/navigation.jsp" %>
            <%@include file="components/home/slider.jsp" %>
        </div>
    </div>
</div>

<!-- Navbar End -->


<!-- Featured Start -->

<!-- Featured End -->


<!-- Categories Start -->

<!-- Categories End -->


<!-- Offer Start -->

<!-- Offer End -->


<!-- Trendy Products Start -->
<%@include file="components/home/trendy_products.jsp" %>

<!-- Trendy Products End -->


<!-- Subscribe Start -->

<!-- Subscribe End -->


<!--Just Arrived Products Start -->

<!--Just Arrived Products End -->


<!-- Vendor Start -->

<!-- Vendor End -->


<!-- Footer Start -->
<%@include file="components/common/footer.jsp" %>
<!-- Footer End -->


<!-- Back to Top -->


</body>
</html>