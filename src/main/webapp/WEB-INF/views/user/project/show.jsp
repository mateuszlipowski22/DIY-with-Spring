<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body">
    <div class="container">
        <div class="tile is-ancestor">
            <div class="tile is-4 is-vertical is-parent">
                <div class="tile is-child box">

                    <p class="title">
                        Project ${project.title} Details<br>
                    </p>
                    <div class="container has-text-centered">
                        <img src="/user/project/${project.id}/showImage"
                             width="200" height="200">
                    </div>
                </div>
            </div>
            <div class="tile is-parent">
                <div class="tile is-child box">
                    <div class="field">
                        <label class="label">Author</label>
                        <div>
                            ${project.user.username}
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Description</label>
                        <div>
                            ${project.description}
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Category</label>
                        <div>
                            ${project.category}
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Component</label>
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
                                    </tr>

                                </c:forEach>
                                </tbody>
                            </table>
                            <nav class="navbar">
                                <div class="tabs is-right">
                                    <div class="navbar-menu">
                                        <form method="get" action="/user/project/component/downloadList">
                                            <input type="hidden" name="projectId" value="${project.id}"/>
                                            <button type="submit" class="button is-link">Generate Component List</button>
                                        </form>
                                    </div>
                                </div>
                            </nav>
                        </c:if>
                    </div>

                    <div class="field">
                        <label class="label">Tools</label>
                        <c:if test="${empty project.tools}">
                            <div>
                                No tools added for project
                            </div>
                        </c:if>

                        <c:if test="${not empty project.tools}">
                            <table class="table is-fullwidth is-bordered" style="width:100%">
                                <thead>
                                <tr>
                                    <th style="width:10%">Num</th>
                                    <th style="width:20%">Name</th>
                                    <th>Description</th>
                                    <th style="width:15%">Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="tool" items="${project.tools}" varStatus="loop">

                                    <tr>
                                        <td>${loop.count}</td>
                                        <td>${tool.name}</td>
                                        <td>${tool.description}</td>
                                        <td>
                                            <nav class="navbar">
                                                <div class="tabs is-right">
                                                    <div class="navbar-menu">
                                                        <a href="/user/${tool.user.id}/tool/${tool.id}/show">
                                                            <button type="submit" class="button button is-primary">Show
                                                            </button>
                                                        </a>
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
        </div>
        <div class="tile is-ancestor">
            <div class="tile is-parent">
                <div class="tile is-child box">
                    <div class="field">
                        <label class="label">Project steps</label>
                    </div>
                    <c:if test="${empty project.steps}">
                        <div>
                            No steps added for project
                        </div>
                    </c:if>

                    <c:if test="${not empty project.steps}">
                        <c:forEach var="step" items="${project.steps}" varStatus="loop">
                            <table>
                                <thead>
                                <tr>
                                    <th>Num</th>
                                    <th>Step</th>
                                    <th>Picture</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>${loop.count}</td>
                                    <td>${step.description}</td>
                                    <td>${step.description}</td>
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
                    <div class="field">
                        <label class="label">Comments</label>
                    </div>

                    <c:if test="${empty project.comments}">
                        <div>
                            No comments added for project
                        </div>
                    </c:if>

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
                                        Written on<br>
                                            ${comment.createdOn}<br>

                                        <c:if test="${not empty comment.updatedOn}">
                                        Edited on<br>
                                            ${comment.updatedOn}
                                        </c:if>

                                        <c:if test="${currentUserId==comment.user.id}">
                                            <nav class="navbar">
                                                <div class="tabs is-right">
                                                    <div class="navbar-menu">
                                                        <form method="get" action="/user/project/comment/edit">
                                                            <input type="hidden" name="projectId" value="${project.id}"/>
                                                            <input type="hidden" name="commentId" value="${comment.id}"/>
                                                            <button type="submit" class="button is-link">Edit</button>
                                                        </form>
                                                    </div>
                                                    <div class="navbar-menu">
                                                        <form method="get" action="/user/project/comment/delete">
                                                            <input type="hidden" name="commentId" value="${comment.id}"/>
                                                            <input type="hidden" name="projectId" value="${project.id}"/>
                                                            <button type="submit" class="button is-link">Delete</button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </nav>
                                        </c:if>
                                    </td>
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
                    <nav class="navbar">
                        <div class="tabs is-right">
                            <div class="navbar-menu">
                                <div>
                                    <form method="get" action="/user/project/comment/add">
                                        <input type="hidden" name="projectId" value="${project.id}"/>
                                        <div class="control">
                                            <button type="submit" class="button button is-primary"><br>Add comment<br>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                                <div>
                                    <div class="control">
                                        <form method="get" action="/user/tool/add">
                                            <button type="submit" class="button button is-primary"><br>Add to my
                                                favorite<br>
                                            </button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/static/footer.jsp"/>
