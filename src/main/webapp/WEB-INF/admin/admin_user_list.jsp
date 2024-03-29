<%@ page contentType="text/html; charset=UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <html>
      <head>
        <!-- <link rel="stylesheet" href="styles/style.css"> -->
        <link rel="stylesheet" href="styles/header_style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <title>Manage users</title>
      </head>
      <body>
          <%@include file="admin_header.jspf" %>
<table class="table table-striped">
          <thead>
            <tr>
              <th>First name</th>
              <th>Last name</th>
              <th>Email(login)</th>
              <th>Role</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="item" items="${filteredUserList}">
              <tr>
                <td>${item.firstName}</td>
                <td>${item.lastName}</td>
                <td>${item.login}</td>
                <td>${item.role}</td>
                <c:if test="${param.type eq 'regular_user'}">
                 <td>
                  <form action="main?command=set_admin" method="POST">
                    <input type="hidden" name="userId" value="${item.id}" />
                    <input type="submit" class="btn btn-warning" value="Set user as admin" />
                  </form>
                 </td>
                 <td>
                  <form action="main?command=set_manager" method="POST">
                    <input type="hidden" name="userId" value="${item.id}" />
                    <input type="submit" class="btn btn-warning" value="Set user as manager" />
                  </form>
                 </td>
                </c:if>
                <c:if test="${param.type eq 'admin'}">
                 <td>
                  <form action="main?command=delete_admin" method="POST">
                    <input type="hidden" name="userId" value="${item.id}" />
                    <input type="submit" class="btn btn-warning" value="Delete admin" />
                  </form>
                 </td>
                </c:if>
                <c:if test="${param.type eq 'manager'}">
                 <td>
                  <form action="main?command=delete_manager" method="POST">
                    <input type="hidden" name="userId" value="${item.id}" />
                    <input type="submit" class="btn btn-warning" value="Delete manager" />
                  </form>
                 </td>
                </c:if>
                <c:choose>
                 <c:when test="${param.type != 'banned_user'}">
                 <td>
                  <form action="main?command=block_user&from=${param.type}" method="POST">
                    <input type="hidden" name="userId" value="${item.id}" />
                    <input type="submit" class="btn btn-danger" value="Block user" />
                  </form>
                 </td>
                 </c:when>
                 <c:when test="${param.type == 'banned_user'}">
                 <td>
                  <form action="main?command=unblock_user" method="POST">
                    <input type="hidden" name="userId" value="${item.id}" />
                    <input type="submit" class="btn btn-danger" value="Unblock user" />
                  </form>
                 </td>
                 </c:when>
                </c:choose>

              </tr>
            </c:forEach>
          </tbody>
        </table>
        </body>
        </html>