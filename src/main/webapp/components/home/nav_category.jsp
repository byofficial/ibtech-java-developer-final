<%@ page import="com.ibtech.mall.core.WebHelper" %>
<%@ page import="com.ibtech.mall.core.XmlHelper" %>
<%@ page import="com.ibtech.mall.xml.CategoryXml" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.w3c.dom.Document" %>
<%@ page import="com.ibtech.mall.database.entity.Category" %>
<%@ page import="java.util.List" %>
<nav class="collapse show navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0"
     id="navbar-vertical">
    <div class="navbar-nav w-100 overflow-hidden" style="height: 410px">
        <%
            String address = System.getenv("SITE_URL") + "api/categories";
            InputStream in = WebHelper.get(address);
            Document document = XmlHelper.parse(in);
            List<Category> categories = CategoryXml.parseList(document);
        %>

        <%for (Category category : categories) { %>
        <a href="category.jsp?id=<%=category.getCategoryId()%>"
           class="nav-item nav-link"><%=category.getCategoryName()%>
        </a>
        <% }%>

    </div>
</nav>