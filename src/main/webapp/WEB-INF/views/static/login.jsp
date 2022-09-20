<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body container has-text-centered">
    <div>
        <table class="table is-fullwidth is-bordered">
            <tr>
                <td>
                    <div class="container has-text-centered">
                        <p class="title">
                            Login<br>
                            <br>
                        </p>
                    </div>

                    <form method="post">
                        <div class="field">
                            <label class="label">User Name:</label>
                            <div>
                                <input type="text" name="username" class="input"/>
                            </div>
                            <div>
                                <%--                        <form:errors path="title"></form:errors>--%>
                            </div>
                        </div>
                        <div class="field">
                            <label class="label">Password:</label>
                            <div>
                                <input type="password" name="password" class="input"/>
                            </div>
                            <div>
                                <%--                        <form:errors path="title"></form:errors>--%>
                            </div>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <br>
                        <div>
                            <input type="submit" class="button is-link" value="Submit"/>
                        </div>
                    </form>
                </td>
            </tr>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>