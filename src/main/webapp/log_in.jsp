<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--add here links to login_style.css and bootstrap 4-->
    <title>Login page</title>
</head>

<body>
    <div id="login">
        <div class="container">
            <div id="login-row" class="row justify-content-center align-items-center">
                <div id="login-column" class="col-md-6">
                    <div id="login-box" class="col-md-12">
                        <form id="login-form" class="form" action="/main?command=log_in" method="POST">
                            <h3 class="text-center text-info">Login</h3>
                            <div class="form-group">
                                <label for="login" class="text-info">Email</label>
                                <input type="text" name="login" id="email" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="password" class="text-info">Password</label>
                                <input type="password" name="password" id="password" class="form-control">
                            </div>
                            <div class="form-group">
                                <input type="submit" class="btn btn-info btn-md" value="Login" />
                            </div>
                            <div id="register-link" class="text-right">
                                <a href="sing_up.jsp" class="text-info">Don`t have account yet, create new one</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>     
    </div>
</body>
</html>