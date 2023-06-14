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
      <h3>All event from the ${param.event_category.toUpperCase()} category</h3>
      <table class="table table-striped">
          <thead>
            <tr>
              <th>Theme</th>
              <th>Category</th>
              <th>Date</th>
              <th>Address</th>
              <th>Description</th>
              <th>Status</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="event" items="${eventList}">
              <tr>
                <td>${event.theme}</td>
                <td>${event.category}</td>
                <td>${event.date}</td>
                <td>${event.address}</td>
                <td>${event.description}</td>
                <td>${event.status}</td>

                <c:if test="${user.role eq 'ADMIN'}">
                  <td>
                  <form action="main?command=delete_event" method="POST">
                    <input type="hidden" name="eventId" value="${event.id}" />
                    <input type="submit" class="btn btn-danger" value="Delete event" />
                  </form>
                  </td>
                </c:if>
                <c:if test="${user.role eq 'MANAGER'}">
                  <td>
                    <a href="main?command=show_change_event_info_page&eventId=${event.id}&event_category=${param.event_category}"><div class="btn btn-warning">Change information about the event</div></a>
                  <!-- <form action="main" method="GET">
                    <input type="hidden" name="command" value="show_change_event_info_page" />
                    <input type="hidden" name="eventId" value="${event.id}" />
                    <input type="hidden" name="event_category" value="${param.event_category}" />
                    <input type="submit" class="btn btn-warning" value="Change information about the event" />
                  </form> -->
                  </td>
                  <td>
                  <form action="main?command=delete_event" method="POST">
                    <input type="hidden" name="eventId" value="${event.id}" />
                    <input type="hidden" name="event_category" value="${param.event_category}" />
                    <input type="submit" class="btn btn-danger" value="Delete event" />
                  </form>
                  </td>
                </c:if>
                <c:if test="${user.role eq 'SPEAKER'}">
                  <td>
                  <form action="main?command=suggest_report_for_event" method="POST">
                    <input type="hidden" name="eventId" value="${event.id}" />
                    <input type="hidden" name="event_category" value="${param.event_category}" />
                    <input type="submit" class="btn btn-success" value="Suggest report for the event" />
                  </form>
                  </td>
                </c:if>
                <c:if test="${user.role eq 'USER'}">
                 <td>
                 <form action="main?command=leave_feedback" method="POST">
                    <input type="hidden" name="eventId" value="${event.id}" />
                    <input type="hidden" name="event_category" value="${param.event_category}" />
                    <input type="submit" class="btn btn-primary" value="Leave comment" />
                  </form>
                  </td>
                  <td>
                  <form action="main?command=register_for_event" method="POST">
                    <input type="hidden" name="eventId" value="${event.id}" />
                    <input type="hidden" name="event_category" value="${param.event_category}" />
                    <input type="submit" class="btn btn-primary" value="Register" />
                  </form>
                </td>
                </c:if>
              </tr>
            </c:forEach>
          </tbody>
        </table>

      <%@include file="../shared/footer.jspf" %>

    </body>

    </html>