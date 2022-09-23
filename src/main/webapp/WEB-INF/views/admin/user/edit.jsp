<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body">
    <div class="container has-text-centered">
        <div class="container has-text-centered">
            <p class="title">
                Edit User
                <br>
                <br>
            </p>
        </div>
        <form:form modelAttribute="user" method="post" action="/admin/user/${user.id}/edit">
            <div class="field">
                <label class="label">User Name:</label>
                <div>
                    <form:input path="username" cssClass="input"></form:input>
                </div>
                <div>
                    <form:errors path="username"></form:errors>
                </div>
            </div>
            <div class="field">
                <label class="label">Email address:</label>
                <div>
                    <form:input path="email" cssClass="input"></form:input>
                </div>
                <div>
                    <form:errors path="email"></form:errors>
                </div>
            </div>
            <br>
            <div>
                <input type="submit" class="button is-link" value="Edit"/>
            </div>
        </form:form>
    </div>
</div>


<jsp:include page="/WEB-INF/views/static/footer.jsp"/>
