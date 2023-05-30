<%@ page contentType="text/html; charset=UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
      <sql:query var="rs" dataSource="jdbc/ConferenceDB">
        SELECT u.id, u.first_name, u.last_name, u.login
        FROM Users AS u, Roles AS r
        WHERE u.role_id=r.id AND r.name='${param.type}'
      </sql:query>
      <html>
      <head>
        <link rel="stylesheet" href="styles/header_style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <title>Manage users</title>
      </head>
      <body>
          <%@include file="manager_header.jspf" %>
<table class="table table-striped">
          <thead>
            <tr>
              <th>First name</th>
              <th>Last name</th>
              <th>Email(login)</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="row" items="${rs.rowsByIndex}">
              <tr>
                <td>${row[1]}</td>
                <td>${row[2]}</td>
                <td>${row[3]}</td>
                <c:if test="${param.type eq 'speaker'}">
                 <td>
                  <form action="main?command=delete_speaker" method="POST">
                    <input type="hidden" name="userId" value="${row[0]}" />
                    <input type="submit" class="btn btn-danger" value="Delete speaker" />
                  </form>
                 </td>
                </c:if>
                <c:if test="${param.type eq 'regular_user'}">
                 <td>
                  <form action="main?command=set_speaker" method="POST">
                    <input type="hidden" name="userId" value="${row[0]}" />
                    <input type="submit" class="btn btn-warning" value="Add speaker" />
                  </form>
                 </td>
                </c:if>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        </body>
        </html>