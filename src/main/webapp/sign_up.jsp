<%@ page contentType="text/html; charset=UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="styles/signup_style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <title>Sign up page</title>
    </head>

    <body>
        <div id="signup">
            <div class="container">
                <div id="signup-row" class="row justify-content-center align-items-center">
                    <div id="signup-column" class="col-md-6">
                        <div id="signup-box" class="col-md-12">
                            <form id="signup-form" class="form" action="main?command=sign_up" method="POST">
                                <h3 class="text-center text-info">Sign up</h3>
                                <div class="form-group">
                                    <label for="firstName" class="text-info">First Name</label>
                                    <input type="text" name="firstName" id="firstName" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="lastName" class="text-info">Last name</label>
                                    <input type="text" name="lastName" id="lastName" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="email" class="text-info">Email</label>
                                    <input type="text" name="email" id="email" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="password1" class="text-info">Password</label>
                                    <input type="password" name="password1" id="password1" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label for="password2" class="text-info">Confirm password</label>
                                    <input type="password" name="password2" id="password2" class="form-control">
                                </div>
                                <div class="form-group">
                                    <input type="submit" class="btn btn-info btn-md" value="Create new account" />
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

    </html>