<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body container has-text-centered">
    <div>
        <table class="table is-fullwidth is-bordered">
            <tr>
                <th colspan="2">
                    <div class="container has-text-centered">
                        <p class="title">
                            Register new user<br>
                            <br>
                        </p>
                    </div>
                </th>
            </tr>
            <tr>
                <td>
                    <form:form modelAttribute="userDTO" method="post" action="/register">
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
                        <div class="field">
                            <label class="label">Password:</label>
                            <div>
                                <form:password path="password" cssClass="input"></form:password>
                            </div>
                            <div>
                                <form:errors path="password"></form:errors>
                            </div>
                        </div>
                </td>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="field">
                        <label class="label"><form:checkbox path="confirmation" cssClass="checkbox"></form:checkbox>I agree to the terms and conditions </label>
                        <div>
                            <form:errors path="confirmation"></form:errors>
                        </div>
                    </div>
                        <div>
                            <input type="submit" class="button is-link" value="Register"/>
                        </div>
                    </form:form>
                </td>
            </tr>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>