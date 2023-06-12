<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="styles/header_style.css">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
            <title>Add new event</title>
        </head>

        <body>
            <%@include file="manager_header.jspf" %>
                <div class="card" style="width: 60%;">
                    <form action="main?command=add_event" method="POST">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><label for="category">Category</label> <select name="category"
                                    id="categories">
                                    <option>Science</option>
                                    <option>IT</option>
                                    <option>Tech</option>
                                    <option>Bussines</option>
                                    <option>Enviro</option>
                                    <option>Health</option>
                                    <option>Society</option>
                                    <option>Life</option>
                                    <option>Other</option>
                                </select></li>
                            <li class="list-group-item"><label for="theme">Theme</label> <input type="text" name="theme"
                                    id="theme" required /></li>
                            <li class="list-group-item"><label for="address">Address</label> <input type="text"
                                    name="address" id="address" required /></li>
                            <li class="list-group-item"><label for="description">Description</label><br> <textarea
                                    name="description" id="description" rows="5" cols="50"></textarea></li>
                            <li class="list-group-item"><label for="datetime">Date and time</label> <input
                                    type="datetime-local" name="datetime" id="datetime" required /></li>
                        </ul>
                        <div class="card-body text-right">
                            <input type="submit" class="btn btn-success" value="Add event" />
                            <a href="main?command=" class="btn btn-danger">Cancel</a>
                        </div>
                    </form>
                </div>
        </body>

        </html>