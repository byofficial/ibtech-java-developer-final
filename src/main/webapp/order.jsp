<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ibtech.mall.database.entity.Account" %>
<%@ page import="com.ibtech.mall.database.manager.CategoryManager" %>
<%@ page import="com.ibtech.mall.database.entity.Orders" %>
<%@ page import="com.ibtech.mall.database.manager.OrderManager" %>
<%@ page import="com.ibtech.mall.database.entity.Orders" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CategoryManager categoryManager = new CategoryManager();
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
    Account auth = (Account) request.getSession().getAttribute("auth");
    List<Orders> orders = null;
    if (auth != null) {
        request.setAttribute("person", auth);
        OrderManager orderManager = new OrderManager();
        orders = orderManager.accountOrders(auth.getAccountId());
    } else {
        response.sendRedirect("/login.jsp");
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Siparişlerim</title>
    <%@include file="components/common/common_js_css.jsp" %>
</head>
<body>
<!-- Topbar Start -->
<%@include file="components/common/topbar.jsp" %>
<!-- Topbar End -->

<!-- Navbar Start -->
<div class="container-fluid">
    <div class="row border-top px-xl-5">
        <div class="col-lg-3 d-none d-lg-block">
            <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100"
               data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                <h6 class="m-0">Kategoriler</h6>
                <i class="fa fa-angle-down text-dark"></i>
            </a>
            <%@include file="components/order/nav_category.jsp" %>
        </div>
        <div class="col-lg-9">
            <%@include file="components/common/navigation.jsp" %>
        </div>
    </div>
</div>

<!-- Navbar End -->


<!-- Page Header Start -->
<div class="container-fluid bg-secondary mb-5">
    <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Siparişlerim</h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="/">Ana Sayfa</a></p>
            <p class="m-0 px-2">/</p>
            <p class="m-0 px-2">Siparişlerim</p>

        </div>
    </div>
</div>
<!-- Page Header End -->

<div class="container">
    <div class="card-header my-3">Tüm Siparişler</div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Ürün Adı</th>
            <th scope="col">Kategori</th>
            <th scope="col">Miktar</th>
            <th scope="col">Fiyat</th>
            <th scope="col">Aksiyon</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (orders != null) {
                for (Orders orederItem : orders) {%>
        <tr>

            <td><%=orederItem.getProductName() %>
            </td>
            <td><%=categoryManager.findById(orederItem.getCategoryId()).getCategoryName() %>
            </td>
            <td><%=orederItem.getQuantity() %>
            </td>
            <td><%=dcf.format(orederItem.getSalesPrice()) %>
            </td>
            <td><a class="btn btn-sm btn-danger" href="api/order/remove?id=<%=orederItem.getOrderId()%>">Siparişi İptal
                Et</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
</div>
<!-- Footer Start -->
<%@include file="components/common/footer.jsp" %>
<!-- Footer End -->

</body>
</html>
