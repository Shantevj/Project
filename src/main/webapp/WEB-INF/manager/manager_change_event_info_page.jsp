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
                    <form action="main?command=change_event&eventId=${event_to_be_changed.id}" method="POST">
                        <c:if test="${is_new_event_info_valid eq 'not_valid'}">
                            <div id="failed">Incorrect event information</div>
                        </c:if>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><label for="status">Status</label> <select name="status"
                                    id="status">
                                    <option>Scheduled</option>
                                    <option>Held on</option>
                                    <option>Finished</option>
                                    <option>Canceled</option>
                                </select></li>
                            <li class="list-group-item"><label for="theme">Theme</label> <input type="text" name="theme"
                                    id="theme" value="${event_to_be_changed.theme}" required /></li>
                            <li class="list-group-item"><label for="address">Address</label> <input type="text"
                                    name="address" id="address" value="${event_to_be_changed.address}" required /></li>
                            <li class="list-group-item"><label for="description">Description</label><br> <textarea
                                    name="description" id="description" rows="5" cols="50">${event_to_be_changed.description}</textarea></li>
                            <li class="list-group-item"><label for="datetime">Date and time</label> <input
                                    type="datetime-local" name="datetime" id="datetime" required value="${event_to_be_changed.date}" /></li>
                        </ul>
                        <div class="card-body text-right">
                            <input type="submit" class="btn btn-success" value="Change event info" />
                            <a href="main?command=get_events&event_category=${event_to_be_changed.category}" class="btn btn-danger">Cancel</a>
                        </div>
                    </form>
                </div>
        </body>

        </html>