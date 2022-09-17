<%@ page import="com.ibtech.mall.database.entity.Menu" %>
<%@ page import="com.ibtech.mall.database.manager.MenuManager" %>
<%@ page import="com.ibtech.mall.xml.MenuXml" %>
<%@ page pageEncoding="UTF-8" %>
<%
    String menuAddress = String.format(System.getenv("SITE_URL") + "api/menus");
    MenuManager menuMenager = new MenuManager();
    InputStream menuInputStream = WebHelper.get(menuAddress);
    Document menuDocument = XmlHelper.parse(menuInputStream);
    List<Menu> menuList = MenuXml.parseList(menuDocument);
%>
<nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
    <a href="" class="text-decoration-none d-block d-lg-none">
        <h1 class="m-0 display-5 font-weight-semi-bold"><span
                class="text-success font-weight-bold border px-3 mr-1">I</span>Ibtech</h1>
    </a>
    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
        <div class="navbar-nav mr-auto py-0">
            <% for (Menu menu : menuList) {
                if (menu.getStatus() == Status.ACTIVE) {
            %>
            <a href="<%=menu.getMenuUrl()%>" class="nav-item nav-link <%=menu.getMenuActive()%>"><%=menu.getMenuName()%>
            </a>
            <% }
            } %>
        </div>
        <div class="navbar-nav ml-auto py-0">
            <%if (auth == null) {%>
            <a href="login.jsp" class="nav-item nav-link">Giriş Yap</a>
            <a href="register.jsp" class="nav-item nav-link">Kayıt Ol</a>
            <%
            } else {
            %>
            <li class="nav-item">Merhaba, <strong><%= auth.getAccountName()%>!</strong></li>
            <li class="nav-item"><a class="nav-link" href="logout">Çıkış Yap</a></li>
            <%
                }
            %>
        </div>
    </div>
</nav>
