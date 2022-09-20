<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body">
    <div class="container has-text-centered">
        <div class="container has-text-centered">
            <p class="title">
                Add New Toolk<br>
            </p>
        </div>
        <table class="table is-fullwidth is-bordered">
            <form:form modelAttribute="tool" method="post" action="/user/tool/add">
                <div class="field">
                    <label class="label">Name</label>
                    <div>
                        <form:input path="name" cssClass="input"></form:input>
                    </div>
                    <div>
                        <form:errors path="name"></form:errors>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Description</label>
                    <div>
                        <form:input path="description" cssClass="input"></form:input>
                    </div>
                    <div>
                        <form:errors path="description"></form:errors>
                    </div>
                </div>
                <div class="field">
                    <label class="label">Link</label>
                    <div>
                        <form:input path="link" cssClass="input"></form:input>
                    </div>
                    <div>
                        <form:errors path="link"></form:errors>
                    </div>
                </div>
<%--                <div class="field">--%>
<%--                    <label class="label">Picture</label>--%>
<%--                    <div>--%>
<%--                        <form:input path="image" cssClass="input"></form:input>--%>
<%--                    </div>--%>
<%--                    <div>--%>
<%--                        <form:errors path="image"></form:errors>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div class="control">
                    <button type="submit" class="button is-link">Add Tool</button>
                </div>
            </form:form>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>