<%@ page import="com.ibtech.mall.core.WebHelper" %>
<%@ page import="com.ibtech.mall.core.XmlHelper" %>
<%@ page import="com.ibtech.mall.database.entity.Product" %>
<%@ page import="com.ibtech.mall.xml.ProductXml" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.w3c.dom.Document" %>
<%@ page import="com.ibtech.mall.database.entity.Account" %>
<%@ page import="com.ibtech.mall.database.manager.CategoryManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CategoryManager productCategoryManager = new CategoryManager();
    String productURL = "https://res.cloudinary.com/ibtbcm/image/upload/v1663287587/product_picture/";
    String noPhoto = "https://res.cloudinary.com/ibtbcm/image/upload/v1663288077/product_picture/nophoto_ftkwas.jpg";
    long productId = Long.parseLong(request.getParameter("id"));
    String productDetailAddress = String.format(System.getenv("SITE_URL") + "api/product?id=%d", productId);
    InputStream productInputStream = WebHelper.get(productDetailAddress);
    Document productDocument = XmlHelper.parse(productInputStream);
    Product productDetail = ProductXml.parse(productDocument);
    Account auth = (Account) request.getSession().getAttribute("auth");
    if (auth != null) {
        request.setAttribute("person", auth);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title><%=productDetail.getProductName() %>
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
            <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
            <h6 class="m-0">Kategoriler</h6>
                <i class="fa fa-angle-down text-dark"></i>
            </a>
            <%@include file="components/product/nav_category.jsp" %>
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
        <h1 class="font-weight-semi-bold text-uppercase mb-3">Ürün Detayı</h1>
        <div class="d-inline-flex">
            <p class="m-0"><a href="/">Ana Sayfa</a></p>
            <p class="m-0 px-2">/</p>
            <p class="m-0 px-2"><%=productCategoryManager.findById(productDetail.getCategoryId()).getCategoryName() %>
            </p>
            <p class="m-0 px-2">/</p>
            <p class="m-0"><%=productDetail.getProductName() %>
            </p>
        </div>
    </div>
</div>
<!-- Page Header End -->


<!-- Shop Detail Start -->
<div class="container-fluid py-5">
    <div class="row px-xl-5">
        <div class="col-lg-5 pb-5">
            <div id="product-carousel" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner border">
                    <div class="carousel-item active">
                        <img class="w-100 h-100" src="<%= !productDetail.getImagePath().equals("")
                        ? productURL + productDetail.getImagePath() : noPhoto %>" alt="Image">
                    </div>
                </div>
                <a class="carousel-control-prev" href="#product-carousel" data-slide="prev">
                    <i class="fa fa-2x fa-angle-left text-dark"></i>
                </a>
                <a class="carousel-control-next" href="#product-carousel" data-slide="next">
                    <i class="fa fa-2x fa-angle-right text-dark"></i>
                </a>
            </div>
        </div>
        <div class="col-lg-7 pb-5">
            <h3 class="font-weight-semi-bold"><%=productDetail.getProductName() %>
            </h3>
            <div class="d-flex mb-3">
                <div class="text-primary mr-2">
                    <small class="fas fa-star"></small>
                    <small class="fas fa-star"></small>
                    <small class="fas fa-star"></small>
                    <small class="fas fa-star-half-alt"></small>
                    <small class="far fa-star"></small>
                </div>
                <small class="pt-1">(50 Değerlendirme)</small>
            </div>
            <h3 class="font-weight-semi-bold mb-4">₺ <%= productDetail.getSalesPrice()%>
            </h3>
            <p class="mb-4"><%= productDetail.getDescription() != null ? productDetail.getDescription()
                    : "Ürün hakkında bir açıklama yazılmamış. Satıcı ile doğrudan iletişim kurarak bilgi edinebilirsiniz!"%>
            </p>

            <div class="d-flex align-items-center mb-4 pt-2">
                <a class="btn btn-primary px-3" href="api/cart/create?id=<%=productDetail.getProductId()%>"><i
                        class="fa fa-shopping-cart mr-1"></i> Sepete Ekle</a>
            </div>
            <div class="d-flex pt-2">
                <p class="text-dark font-weight-medium mb-0 mr-2">Paylaş:</p>
                <div class="d-inline-flex">
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-linkedin-in"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-pinterest"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="row px-xl-5">
        <div class="col">
            <div class="nav nav-tabs justify-content-center border-secondary mb-4">
                <a class="nav-item nav-link active" data-toggle="tab" href="#tab-pane-1">Açıklama</a>
                <a class="nav-item nav-link" data-toggle="tab" href="#tab-pane-2">Bilgi</a>
                <a class="nav-item nav-link" data-toggle="tab" href="#tab-pane-3">Yorum (0)</a>
            </div>
            <div class="tab-content">
                <div class="tab-pane fade show active" id="tab-pane-1">
                    <h4 class="mb-3">Ürün Açıklaması</h4>
                    <p><%= productDetail.getLongDescription() != null ? productDetail.getLongDescription()
                            : "Ürün hakkında bir açıklama yazılmamış. Satıcı ile doğrudan iletişim kurarak bilgi edinebilirsiniz!"%>
                    </p>
                </div>
                <div class="tab-pane fade" id="tab-pane-2">
                    <h4 class="mb-3">Bilgilendirme</h4>
                    <p>Ürün hakkında bilgilendirme metni bulunamadı. Satıcı ile doğrudan iletişim kurarak bilgi
                        edinebilirsiniz!</p>
                </div>
                <div class="tab-pane fade" id="tab-pane-3">
                    <h4 class="mb-3">Yorum</h4>
                    <p>Ürün hakkında yorum bulunamadı!</p>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Shop Detail End -->

<!-- Footer Start -->
<%@include file="components/common/footer.jsp" %>
<!-- Footer End -->
</body>
</html>
