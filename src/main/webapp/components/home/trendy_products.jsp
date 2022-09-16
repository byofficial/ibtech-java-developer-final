<%@ page import="com.ibtech.mall.database.entity.Product" %>
<%@ page import="com.ibtech.mall.xml.ProductXml" %>
<%@ page import="com.ibtech.mall.core.XmlHelper" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.w3c.dom.Document" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ibtech.mall.core.WebHelper" %>
<%@ page pageEncoding="UTF-8" %>
<%
    String productListAddress = System.getenv("SITE_URL") + "api/products";
    InputStream productListIN = WebHelper.get(productListAddress);
    Document documentProductList = XmlHelper.parse(productListIN);
    List<Product> trandyProductList = ProductXml.parseList(documentProductList);
%>
<div class="container-fluid pt-5">
    <div class="text-center mb-4">
        <h2 class="section-title px-5"><span class="px-2">Trend Ürünler</span></h2>
    </div>
    <div class="row px-xl-5 pb-3">

        <% for (Product product : trandyProductList) { %>
        <div class="col-lg-3 col-md-6 col-sm-12 pb-1">
            <div class="card product-item border-0 mb-4">
                <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                    <img class="img-fluid w-100" src="theme/img/product-1.jpg" alt="">
                </div>
                <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                    <h6 class="text-truncate mb-3"><%= product.getProductName()%>
                    </h6>
                    <div class="d-flex justify-content-center">
                        <h6><%=product.getSalesPrice()%>
                        </h6>
                        <h6 class="text-muted ml-2">
                            <del><%=product.getSalesPrice()%>
                            </del>
                        </h6>
                    </div>
                </div>
                <div class="card-footer d-flex justify-content-between bg-light border">
                    <a href="product.jsp?id=<%=product.getProductId()%>" class="btn btn-sm text-dark p-0"><i
                            class="fas fa-eye text-primary mr-1"></i>Ürünü İncele</a>
                    <a href="api/cart/create?id=<%=product.getProductId()%>" id="cart" class="btn btn-sm text-dark p-0"><i
                            class="fas fa-shopping-cart text-primary mr-1"></i>Sepete Ekle</a>
                </div>
            </div>
        </div>
        <% } %>


    </div>
</div>