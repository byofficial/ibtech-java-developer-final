<%@ page import="com.ibtech.mall.database.entity.Account" %>
<%@ page import="com.ibtech.mall.database.manager.CategoryManager" %>
<%@ page import="com.ibtech.mall.database.entity.Product" %>
<%@ page import="com.ibtech.mall.xml.ProductXml" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CategoryManager categoryCategoryManager = new CategoryManager();

    long categoryId = Long.parseLong(request.getParameter("id"));
    String categoryDetailAddress = String.format(System.getenv("SITE_URL") + "api/products/category?id=%d", categoryId);
    String noPhotoAddress = "https://res.cloudinary.com/ibtbcm/image/upload/v1663288077/product_picture/nophoto_ftkwas.jpg";
    InputStream categoryInputStream = WebHelper.get(categoryDetailAddress);
    Document categoryDocument = XmlHelper.parse(categoryInputStream);
    List<Product> productCategoryList = ProductXml.parseList(categoryDocument);

    Account auth = (Account) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("person", auth);
    }
%>
<html>
<head>
    <title><%=categoryCategoryManager.findById(categoryId).getCategoryName() %>
    </title>
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
            <%@include file="components/category/nav_category.jsp" %>
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
        <h1 class="font-weight-semi-bold text-uppercase mb-3"><%=categoryCategoryManager.findById(categoryId).getCategoryName() %>
        </h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="/">Ana Sayfa</a></p>
            <p class="m-0 px-2">/</p>
            <p class="m-0 px-2"><%=categoryCategoryManager.findById(categoryId).getCategoryName() %>
            </p>
            <p class="m-0 px-2">/</p>
            <p class="m-0 px-2">Tüm Ürünler</p>

        </div>
    </div>
</div>
<!-- Page Header End -->

<!-- Shop Start -->
<div class="container-fluid pt-5">
    <div class="row px-xl-5">


        <!-- Shop Product Start -->
        <div class="col-lg-12 col-md-12">
            <div class="row pb-3">
                <div class="col-12 pb-1">

                </div>

                <% for (Product product : productCategoryList) { %>
                <div class="col-lg-4 col-md-6 col-sm-12 pb-1">
                    <div class="card product-item border-0 mb-4">
                        <div class="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                            <a href="product.jsp?id=<%=product.getProductId()%>"> <img class="img-fluid w-100" src="<%=!product.getImagePath().equals("")
                    ? product.getImagePath() : noPhotoAddress %>" alt=""></a>
                        </div>
                        <div class="card-body border-left border-right text-center p-0 pt-4 pb-3">
                            <a href="product.jsp?id=<%=product.getProductId()%>"><h6
                                    class="text-truncate mb-3"><%= product.getProductName()%>
                            </h6></a>
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
                            <a href="api/cart/create?id=<%=product.getProductId()%>" id="cart"
                               class="btn btn-sm text-dark p-0"><i
                                    class="fas fa-shopping-cart text-primary mr-1"></i>Sepete Ekle</a>
                        </div>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
        <!-- Shop Product End -->
    </div>
</div>
<!-- Shop End -->

<!-- Footer Start -->
<%@include file="components/common/footer.jsp" %>
<!-- Footer End -->
</body>
</html>
