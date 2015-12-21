<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>

</head>

<body>

<div class="container">
    <div class="wrapper">
        <form action="/login" method="post" name="loginForm" class="form-signin">

            <h3 class="form-signin-heading">Sign in</h3>
            <c:if test="${not empty param}">
                <div class="error">Бядаааа</div>
            </c:if>
            <br>

            <input type="text" name="username" class="form-control" placeholder="login" required=""
                   autofocus=""/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button class="btn btn-lg btn-primary btn-block" name="Submit" type="Submit">Login</button>


        </form>
    </div>
</div>
</body>
</html>