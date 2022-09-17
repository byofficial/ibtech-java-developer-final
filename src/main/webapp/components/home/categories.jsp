<%@ page import="com.ibtech.mall.database.entity.enums.FeaturedCategory" %>
<%@ page import="com.ibtech.mall.database.manager.ProductManager" %>
<%@ page pageEncoding="UTF-8" %>
<%
    ProductManager productManagerCategory = new ProductManager();
    String noPhotoCategory = "https://res.cloudinary.com/ibtbcm/image/upload/v1663288077/product_picture/nophoto_ftkwas.jpg";
    String featuredCategoryListAddress = System.getenv("SITE_URL") + "api/categories";
    InputStream featuredCategoryListIN = WebHelper.get(featuredCategoryListAddress);
    Document documentFeaturedCategoryList = XmlHelper.parse(featuredCategoryListIN);
    List<Category> featuredCategoryList = CategoryXml.parseList(documentFeaturedCategoryList);
%>
<div class="container-fluid pt-5">
    <div class="text-center mb-4">
        <h2 class="section-title px-5"><span class="px-2">Seçilmiş Kategoriler</span></h2>
    </div>
    <div class="row px-xl-5 pb-3">
        <% for (Category category : featuredCategoryList) {
            if (category.getStatus() == Status.ACTIVE && category.getFeaturedCategory() == FeaturedCategory.ACTIVE) {
        %>
        <div class="col-lg-4 col-md-6 pb-1">
            <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                <p class="text-right"><%=
                productManagerCategory.findByCategoryId(category.getCategoryId()).size()
                %> ürün
                </p>
                <a href="category.jsp?id=<%=category.getCategoryId()%>"
                   class="cat-img position-relative overflow-hidden mb-3">
                    <img class="img-fluid" src="<%=!category.getCategoryImage().equals("")
                    ? category.getCategoryImage() : noPhotoCategory%>" alt="">
                </a>
                <a href="category.jsp?id=<%=category.getCategoryId()%>"><h5
                        class="font-weight-semi-bold m-0"><%=category.getCategoryName()%>
                </h5></a>
            </div>
        </div>
        <% }
        } %>
    </div>
</div>