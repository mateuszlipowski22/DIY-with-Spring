<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body">
  <div class="container">
    <div class="tile is-ancestor">
      <div class="tile is-parent">
        <div class="tile is-child box">
          <div class="field">
            <label class="label">Comments</label>
          </div>

          <c:if test="${empty project.comments}">
            <div>
              No comments added for project
            </div>
          </c:if>

          <c:if test="${not empty project.comments}">
            <c:forEach var="comment" items="${project.comments}" varStatus="loop">
              <table class="table is-fullwidth is-bordered" style="width:100%">
                <tbody>
                <tr>
                  <td style="width:20%">
                    User: ${comment.user.username}<br>
                    Written at<br>
                      ${comment.createdOn}</td>
                  <td>${comment.content}</td>
                </tr>
              </table>
            </c:forEach>
          </c:if>
        </div>
      </div>
    </div>
    <div class="tile is-ancestor">
      <div class="tile is-parent">
        <div class="tile is-child box">
          <form:form modelAttribute="comment" method="post" action="/user/project/comment/add">
            <div class="field">
              <label class="label">Name</label>
              <div>
                <form:input path="content" cssClass="input"></form:input>
              </div>
              <div>
                <form:errors path="content"></form:errors>
                <input type="hidden" name="projectId" value="${project.id}"/>
              </div>
            </div>
            <div class="control">
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              <button type="submit" class="button is-link">Add comment</button>
            </div>
          </form:form>
        </div>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/views/static/footer.jsp"/>