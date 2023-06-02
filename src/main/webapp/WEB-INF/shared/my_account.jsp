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
        <div class="card" style="width: 30%;">
          <ul class="list-group list-group-flush">
            <li class="list-group-item">First name: ${user.firstName}</li>
            <li class="list-group-item">Last name: ${user.lastName}</li>
            <li class="list-group-item">Email : ${user.login}</li>
            <li class="list-group-item">Password (hash): ${user.password}</li>
            <li class="list-group-item">Logged as: ${user.role}</li>
          </ul>
          <div class="card-body text-right">
            <a class="btn btn-primary" href="main?command=my_account&type=change">Edit</a>
            <a class="btn btn-danger" href="#">Delete account</a>
          </div>
        </div>
        <%@include file="../shared/footer.jspf" %>
    </body>

    </html>