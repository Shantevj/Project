<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--<%@ page contentType="text/html; charset=UTF-8" %>--%>

<sql:query var="rs" dataSource="jdbc/ConferenceDB">
select * from Users
</sql:query>
<html>
  <head>
    <title>DB Test</title>
  </head>
  <body>
  <h2>Results</h2>
<c:forEach var="row" items="${rs.rows}">
    Name ${row.first_name}, Last name ${row.last_name}
    <c:if test="${userRole == 'admin'}">
        <a href="editUser?userId=${row.id}">Edit</a>
       </c:if>
       <br/>
</c:forEach>
  </body>
</html>