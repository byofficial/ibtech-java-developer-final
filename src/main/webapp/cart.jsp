<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ibtech.mall.database.entity.Account" %>
<%@ page import="com.ibtech.mall.database.manager.CartManager" %>
<%@ page import="com.ibtech.mall.database.manager.CategoryManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CategoryManager categoryManager = new CategoryManager();
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf", dcf);
    Account auth = (Account) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("person", auth);
    }
    ArrayList<Cart> cartCartList = (ArrayList<Cart>) session.getAttribute("cart-list");
    List<Cart> cartCartProduct = null;
    if (cartCartList != null) {
        CartManager cartCartManager = new CartManager();
        cartCartProduct = cartCartManager.getCartAllProducts(cartCartList);
        double total = cartCartManager.getCartTotalPriceCalculate(cartCartList) + 10.0;
        request.setAttribute("total", total);
        request.setAttribute("cart_list", cartCartList);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Sepet</title>
    <%@include file="components/common/common_js_css.jsp" %>
</head>
<body>
<!-- Topbar Start -->
<%@include file="components/common/topbar.jsp" %>
<!-- Topbar End -->

<!-- Cart Start -->
<div class="container-fluid pt-5">
    <form action="api/cart/checkout" method="POST">
        <div class="row px-xl-5">

            <div class="col-lg-8 table-responsive mb-5">
                <table class="table table-bordered text-center mb-0">
                    <thead class="bg-secondary text-dark">
                    <tr>
                        <th>Ürün Adı</th>
                        <th>Kategori</th>
                        <th>Fiyat</th>
                        <th>Adet</th>
                        <th>Kaldır</th>
                    </tr>
                    </thead>
                    <tbody class="align-middle">
                    <%
                        if (cartCartList != null) {
                            for (Cart cartItem : cartCartProduct) {
                    %>
                    <tr>
                        <td class="align-middle"><img src="theme/img/product-1.jpg" alt=""
                                                      style="width: 50px;"> <%=cartItem.getProductName()%>
                        </td>
                        <td class="align-middle"><%=categoryManager.findById(cartItem.getCategoryId()).getCategoryName()%>
                        </td>
                        <td class="align-middle"><%= dcf.format(cartItem.getSalesPrice())%>
                        </td>
                        <td class="align-middle">

                            <input type="hidden" name="productId" value="<%= cartItem.getProductId()%>"
                                   class="form-input">
                            <div class="input-group quantity mx-auto" style="width: 100px;">
                                <div class="input-group-btn">
                                    <a class="btn btn-sm btn-primary btn-minus"
                                       href="api/cart/qty?operation=dec&id=<%=cartItem.getProductId()%>">
                                        <i class="fa fa-minus"></i>
                                    </a>
                                </div>
                                <input name="quantity" type="text"
                                       class="form-control form-control-sm bg-secondary text-center"
                                       value="<%=cartItem.getQuantity()%>" readonly>
                                <div class="input-group-btn">
                                    <a class="btn btn-sm btn-primary btn-plus"
                                       href="api/cart/qty?operation=inc&id=<%=cartItem.getProductId()%>">
                                        <i class="fa fa-plus"></i>
                                    </a>
                                </div>
                            </div>
                        </td>
                        <td class="align-middle"><a class="btn btn-sm btn-primary"
                                                    href="api/cart/remove?id=<%=cartItem.getProductId() %>">
                            <i class="fa fa-times"></i></a></td>
                    </tr>

                    <% }
                    }%>
                    </tbody>
                </table>
            </div>
            <div class="col-lg-4">

                <div class="input-group">
                    <input type="text" class="form-control p-4" placeholder="Kupon Kodu">
                    <div class="input-group-append">
                        <a class="btn btn-primary">Kuponu Uygula</a>
                    </div>
                </div>

                <div class="card border-secondary mb-5">
                    <div class="card-header bg-secondary border-0">
                        <h4 class="font-weight-semi-bold m-0">Diğer Ücretler</h4>
                    </div>
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <h6 class="font-weight-medium">Sepet Toplamı</h6>
                            <h6 class="font-weight-medium">₺ ${(total>0)?dcf.format(total-10.0):0}</h6>
                        </div>
                        <div class="d-flex justify-content-between">
                            <h6 class="font-weight-medium">Standart Kargo</h6>
                            <% if (cartCartList != null) { %>
                            <h6 class="font-weight-medium">₺ 10</h6
                            <% } else {%>
                            <h6 class="font-weight-medium">₺ 0</h6
                            <% }%>

                        </div>
                    </div>
                    <div class="card-footer border-secondary bg-transparent">
                        <div class="d-flex justify-content-between mt-2">
                            <h5 class="font-weight-bold">Toplam</h5>
                            <% if (cartCartList != null) { %>
                            <h5 class="font-weight-bold">₺ ${(total>0)?dcf.format(total):0}</h5>
                            <% } else {%>
                            <h5 class="font-weight-bold">₺ 0</h5>
                            <% }%>

                        </div>
                        <button type="submit" class="btn btn-block btn-primary my-3 py-3">Ödemeye Geç</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- Cart End -->


<!-- Footer Start -->
<%@include file="components/common/footer.jsp" %>
<!-- Footer End -->

</body>
</html>
