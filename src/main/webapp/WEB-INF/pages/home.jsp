<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authentication var="user" property="principal"/>
Email:${user.email}
<br>
Name:${user.name}

<table border="1px">
    <thead>
    <tr>
        <td>id</td>
        <td>title</td>
        <td>description</td>
        <td>price</td>
        <td>amount</td>
        <td>buy</td>
    </tr>
    </thead>
    <c:forEach items="${userProducts}" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.title}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td>${product.amount}</td>
        </tr>
    </c:forEach>
</table>

