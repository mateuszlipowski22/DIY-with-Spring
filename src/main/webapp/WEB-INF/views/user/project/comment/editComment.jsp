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

          <c:if test="${not empty project.comments}">
            <table class="table is-fullwidth is-bordered" style="width:100%">
              <tbody>
              <c:forEach var="comment" items="${project.comments}" varStatus="loop">

                <tr>
                  <td style="width:20%">
                    User: <strong>${comment.user.username}</strong><br>
                    <div class="container has-text-centered">
                      <img src="/user/details/${comment.user.id}/showAvatar"
                           width="150" height="150">
                    </div>
                    Written at<br>
                      ${comment.createdOn}<br>
                    <c:if test="${not empty comment.updatedOn}">
                      Edited on<br>
                      ${comment.updatedOn}
                    </c:if></td>
                  <td>${comment.content}</td>
                </tr>

              </c:forEach>
              </tbody>
            </table>
          </c:if>
        </div>
      </div>
    </div>
    <div class="tile is-ancestor">
      <div class="tile is-parent">
        <div class="tile is-child box">
          <form:form modelAttribute="comment" method="post" action="/user/project/comment/edit">
            <div class="field">
              <label class="label">Content</label>
              <div>
                <form:input path="content" cssClass="input"></form:input>
              </div>
              <div>
                <input type="hidden" name="projectId" value="${project.id}"/>
                <input type="hidden" name="commentId" value="${comment.id}"/>
              </div>
            </div>
            <div class="control">
              <button type="submit" class="button is-link">Edit comment</button>
            </div>
          </form:form>
        </div>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/views/static/footer.jsp"/>