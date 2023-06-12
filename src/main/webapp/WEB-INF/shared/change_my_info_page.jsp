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
      <c:if test="${is_changed_data_valid eq 'not_valid'}">
        <div id="failed">Incorrect login data</div>
      </c:if>
        <div class="card" style="width: 30%;">
          <form action="main?command=change_my_info" method="POST">
          <ul class="list-group list-group-flush">
            <li class="list-group-item"><label for="firstName">First name</label> <input type="text" name="firstName"
                id="firstName" value="${user.firstName}" /></li>
            <li class="list-group-item"><label for="lastName">Last name</label> <input type="text" name="lastName"
              id="lastName" value="${user.lastName}" /></li>           
            <li class="list-group-item"><label for="password">Password</label> <input type="password" name="password"
              id="password" /></li>            
          </ul>
          <div class="card-body text-right">
            <input type="submit" class="btn btn-success" value="Confirm" />
            <a href="main?command=show_my_account_info_page" class="btn btn-danger">Cancel</a>
          </div>
          </form>
        </div>
        <%@include file="../shared/footer.jspf" %>
    </body>

    </html>