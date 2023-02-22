<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<link rel="stylesheet" href="styles/styles.css">
<body>
    <h2>Hello World!</h2>
    <form action="main" method="GET">
        <input type="text" name="name" value="user" />
        <input type="submit" value="Submit" />
    </form>
   <c:if test="${not empty name}">
    <hr>
    ${name}
   </c:if>
</body>
</html>
