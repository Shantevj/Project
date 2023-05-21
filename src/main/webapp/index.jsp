<%@ page contentType="text/html; charset=UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
      <sql:query var="rs" dataSource="jdbc/ConferenceDB">
        SELECT e.id, e.theme, c.name, ed.date, ed.address, ed.description, es.description
        FROM Events AS e, Categories AS c, Event_statuses AS es, Event_details AS ed
        WHERE ed.event_id=e.id AND c.id=e.category_id AND es.id=e.event_status_id
      </sql:query>
      <html>

      <head>
        <link rel="stylesheet" href="styles/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <title>Conference site (Welcome page)</title>
      </head>

      <body>
        <c:if test="${empty user}">
          <%@include file="WEB-INF/GuestHeader.jspf" %>
        </c:if>
        <c:if test="${not empty user}">
          <%@include file="WEB-INF/Header.jspf" %>
        </c:if>

        <c:if test="${empty user}">
          <h3>Hello, guest! To register on the conference, please consider logging to your account</h3>
        </c:if>
        <table class="table table-striped">
          <thead>
            <tr>
              <th>Id</th>
              <th>Theme</th>
              <th>Category</th>
              <th>Date</th>
              <th>Address</th>
              <th>Description</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="row" items="${rs.rowsByIndex}">
              <tr>
                <td>${row[1]}</td>
                <td>${row[2]}</td>
                <td>${row[3]}</td>
                <td>${row[4]}</td>
                <td>${row[5]}</td>
                <td>${row[6]}</td>
                <td>
                  <form action="main" method="POST">
                    <input type="hidden" name="eventID" value="${row[0]}" />
                    <input type="submit" class="btn btn-primary" value="Зареєструватися" />
                  </form>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <%@include file="WEB-INF/Footer.jspf" %>
      </body>

      </html>