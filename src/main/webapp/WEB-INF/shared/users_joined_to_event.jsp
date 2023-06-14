<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html>

    <head>
      <link rel="stylesheet" type="text/css" href="styles/style.css">
      <link rel="stylesheet" type="text/css" href="styles/header_style.css">
      <link rel="stylesheet" type="text/css"
        href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
      <title>Conference site</title>
    </head>

    <body>
      <%@include file="../shared/choose_header.jspf" %>
      <h3>Users who will go to this event</h3>
      <table class="table table-striped">
          <thead>
            <tr>
              <th>Name</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="user" items="${users_joined_to_event}">
              <tr>
                <td>${user}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>

      <%@include file="../shared/footer.jspf" %>

    </body>

    </html>