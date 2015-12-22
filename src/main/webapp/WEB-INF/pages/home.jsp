<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="<c:url value="/resources/js/jquery-1.11.3.min.js" />"></script>

<sec:authentication var="user" property="principal"/>
Email:${user.email}
<br>
Name:${user.name}

<br>
<a href="<c:url value="/logout"/>">Logout</a>
<br>


<table border="1px">
    <thead>
    <tr>
        <td>id</td>
        <td>title</td>
        <td>description</td>
        <td>price</td>
        <td>amount</td>
        <td>info</td>
    </tr>
    </thead>
    <c:forEach items="${userProducts}" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.title}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td>${product.amount}</td>
            <td>
                <button onclick="onClick(${product.id});">Click me</button>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<form:form action="/add-product" method="post" commandName="product">
    <label>Title</label>
    <form:input path="title" type="text"/>
    <form:errors path="title"/>
    <br>
    <label>Description</label>
    <form:input path="description" type="text"/>
    <form:errors path="description"/>
    <br>
    <label>Price</label>
    <form:input path="price" type="number" min="0" step="1"/>
    <form:errors path="price"/>
    <br>
    <label>Amount</label>
    <form:input path="amount" type="number" min="0" step="1"/>
    <form:errors path="amount"/>
    <br>
    <form:hidden path="userId" value="${user.id}"/>
    <form:button>Submit</form:button>

</form:form>

<br>

<div id="placeholder"></div>


<script>
    function onClick(id) {
        $.post("/info/" + id, function (data) {
                    $("#placeholder").html(data);
                })
                .fail(function () {
                    alert("fail");
                });
    }

</script>