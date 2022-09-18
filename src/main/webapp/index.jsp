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
            <a class="btn shadow-none d-flex align-items-center justify-content-between bg-success text-white w-100"
               data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                <h6 class="m-0 text-dark">Kategoriler</h6>
                <i class="fa fa-angle-down text-dark"></i>
            </a>
            <%@include file="components/home/nav_category.jsp" %>
        </div>
        <div class="col-lg-9">
<%--            <jsp:include page="components/common/navigation.jsp" />--%>
            <%@include file="components/common/navigation.jsp" %>
            <%@include file="components/home/slider.jsp" %>
        </div>
    </div>
</div>

<!-- Navbar End -->


<!-- Featured Start -->
<%@include file="components/home/featured.jsp" %>
<!-- Featured End -->


<!-- Categories Start -->
<%@include file="components/home/categories.jsp" %>
<!-- Categories End -->


<!-- Offer Start -->
<%@include file="components/home/offer.jsp" %>
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