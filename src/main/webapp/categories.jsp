<%@ page import="com.ibtech.mall.core.WebHelper" %>
<%@ page import="com.ibtech.mall.core.XmlHelper" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.w3c.dom.Document" %>
<%@ page import="com.ibtech.mall.database.entity.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ibtech.mall.xml.CategoryXml" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String address = "http://localhost:8080/api/categories";
    InputStream in = WebHelper.get(address);
    Document document = XmlHelper.parse(in);
    List<Category> categories = CategoryXml.parseList(document);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
