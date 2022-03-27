<jsp:useBean id="cat" scope="request" type="ru.gb.model.Cat"/>
<%@ page import="ru.gb.model.Cat" %>
<%--
  Created by IntelliJ IDEA.
  User: A200028696
  Date: 27.03.2022
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Cat</title>
</head>
<body>
    <h1>Cat</h1>
    <p>Name: ${cat.name}</p>
    <p>Age: ${cat.age}</p>
    <p>
        <span>Children</span>
        <ul>
            <% if (((Cat)request.getAttribute("cat")).getChildren() != null) %>
            <% for (Cat val: ((Cat) request.getAttribute("cat")).getChildren()) { %>
                <li>Name: <%=val.getName()%></li>
                <li>Age: <%=val.getAge()%></li>
            <% }%>
        </ul>
    </p>
</body>
</html>
