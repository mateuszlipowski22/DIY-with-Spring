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
                            Update user Details<br>
                            <br>
                        </p>
                    </div>
                </th>
            </tr>
            <tr>
                <td>
                    <form:form modelAttribute="userDetailsDTO" method="post" action="/user/details/edit"
                               enctype="multipart/form-data">

                        <div class=" field">
                            <label class="label">City:</label>
                            <div>
                                <form:input path="city" cssClass="input"></form:input>
                            </div>
                            <div>
                                <form:errors path="city"></form:errors>
                            </div>
                        </div>

                        <div class="field">
                            <label class="label">About Me:</label>
                            <div>
                                <form:input path="aboutMe" cssClass="input"></form:input>
                            </div>
                            <div>
                                <form:errors path="aboutMe"></form:errors>
                            </div>
                        </div>
                        <div class="field">
                            <label class="control-label">Select your avatar</label>
                            <input id="imageFile" name="imageFile" type="file" class="file">
                        </div>
                        <div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="submit" class="button is-link" value="Update User Details"/>
                        </div>
                    </form:form>
                </td>
            </tr>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>