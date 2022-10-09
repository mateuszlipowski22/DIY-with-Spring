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
            <label class="label">Component</label>
          </div>

          <c:if test="${empty project.components}">
            <div>
              No Component added for project
            </div>
          </c:if>

          <c:if test="${not empty project.components}">
            <table class="table is-fullwidth is-bordered" style="width:100%">
              <thead>
              <tr>
                <th style="width:10%">Num</th>
                <th style="width:70%">Description</th>
                <th>Quantity</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="component" items="${project.components}" varStatus="loop">

                <tr>
                  <td>${loop.count}</td>
                  <td>${component.description}</td>
                  <td>${component.quantity}</td>
                  <td>
                    <nav class="navbar">
                      <div class="container">
                        <div class="navbar-menu">
                          <form method="post" action="/user/project/component/delete">
                            <input type="hidden" name="componentId" value="${component.id}"/>
                            <input type="hidden" name="projectId" value="${project.id}"/>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <div class="control">
                              <button type="submit" class="button is-link">Delete</button>
                            </div>
                          </form>
                        </div>
                      </div>
                    </nav>
                  </td>
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
          <form:form modelAttribute="componentDTO" method="post" action="/user/project/component/add">
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
              <label class="label">Quantity</label>
              <div>
                <form:input path="quantity" cssClass="input"></form:input>
              </div>
              <div>
                <form:errors path="quantity"></form:errors>
                <input type="hidden" name="projectId" value="${project.id}"/>
              </div>
            </div>
            <div class="control">
              <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
              <button type="submit" class="button is-link">Add Component</button>
            </div>
          </form:form>
          <form method="get" action="/user/projects/all">
            <button type="submit" class="button is-link">Finish</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<jsp:include page="/WEB-INF/views/static/footer.jsp"/>