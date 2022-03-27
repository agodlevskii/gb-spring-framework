<%@ page import="ru.gb.model.Product" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: A200028696
  Date: 27.03.2022
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Products</title>
    <style>
      table {
        border: 1px solid black;
        border-collapse: collapse;
      }

      table th,
      table td {
        padding: 0.5rem;
        border: 1px solid black;
      }
    </style>
</head>
<body>
  <header>
    <h1>Products</h1>
  </header>
  <main>
    <% if (request.getAttribute("products") != null) %>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Cost</th>
        </tr>
      </thead>
      <tbody>
        <% for (Product product: (List<Product>)request.getAttribute("products")) { %>
        <tr>
          <td><%=product.getId()%></td>
          <td><%=product.getName()%></td>
          <td><%=product.getCost()%></td>
        </tr>
        <% } %>
      </tbody>
    </table>
  </main>
</body>
</html>
