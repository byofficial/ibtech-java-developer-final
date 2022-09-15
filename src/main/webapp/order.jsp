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
