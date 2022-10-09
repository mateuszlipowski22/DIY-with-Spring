<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body">
  <div class="container has-text-centered">
    <div class="container has-text-centered">
      <p class="title">
        Add New Project<br>
      </p>
    </div>
    <table class="table is-fullwidth is-bordered">
      <form:form modelAttribute="project" method="post" action="/user/project/add" enctype="multipart/form-data">
        <div class="field">
          <label class="label">Title</label>
          <div>
            <form:input path="title" cssClass="input"></form:input>
          </div>
          <div>
            <form:errors path="title"></form:errors>
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
          <label class="label">Category</label>
          <div>
            <form:input path="category" cssClass="input"></form:input>
          </div>
          <div>
            <form:errors path="category"></form:errors>
          </div>
        </div>
        <div class="field">
          <label class="label">Tools</label>
          <div>
            <form:select itemValue="id" itemLabel="name" path="tools" items="${project.tools}" />

<%--            <form:checkboxes items="${project.tools}" path="tools" itemLabel="name" itemValue="id"/>--%>
<%--            <form:select path="tools" cssClass="input"></form:select>--%>
          </div>
          <div>
            <form:errors path="tools"></form:errors>
          </div>
        </div>
        <div class="field">
          <label class="control-label">Project Picture</label>
          <input id="imageFile" name="imageFile" type="file" class="file">
        </div>
        <div class="control">
          <button type="submit" class="button is-link">Add Project</button>
        </div>
      </form:form>
    </table>
  </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>
