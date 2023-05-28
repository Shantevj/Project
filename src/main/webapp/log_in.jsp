<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="styles/login_style.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <title>Login page</title>
    </head>

    <body>
        <div id="login">
            <div class="container">
                <div id="login-row" class="row justify-content-center align-items-center">
                    <div id="login-column" class="col-md-6">
                        <div id="login-box" class="col-md-12">
                            <form id="login-form" class="form" action="main?command=log_in" method="POST">
                                <h3 class="text-center text-info">Login</h3>
                                <c:if test="${login_failed eq 'failed'}">
                                    <div id="failed">Incorrect login data</div>
                                </c:if>
                                <div class="form-group">
                                    <label for="login" class="text-info">Email</label>
                                    <input type="email" name="login" id="email" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="text-info">Password</label>
                                    <input type="password" name="password" id="password" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <input type="submit" class="btn btn-info btn-md" value="Login" />
                                </div>
                                <br>
                                <div id="register-link" class="text-right">
                                    <a href="sign_up.jsp" class="text-info">Don`t have account yet? Create new one.</a>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

    </html>