<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>



<div class="hero-body">
    <div class="container has-text-centered">
        <div class="container has-text-centered">
            <p class="title">
                Your Favorite Projects<br>
                <br>
            </p>
        </div>
    <c:if test="${empty favoriteProjects}">
        <div class="container has-text-centered">
            <p class="title">
                Lack of favorite projects<br>
                <br>
            </p>
        </div>
    </c:if>

    <c:if test="${not empty favoriteProjects}">
        <table class="table is-fullwidth is-bordered" style="width:100%">
            <thead>
            <tr>
                <th style="width:10%">Num</th>
                <th style="width:15%">Picture</th>
                <th style="width:20%">Title</th>
                <th>Description</th>
                <th style="width:10%">Author</th>
                <th style="width:20%">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="favoriteProject" items="${favoriteProjects}" varStatus="loop">
                <tr>
                    <td>${loop.count}</td>
                    <th>
                        <img src="/user/project/${favoriteProject.favoriteProject.id}/showImage" width="150" height="150">
                    </th>
                    <td>${favoriteProject.favoriteProject.title}</td>
                    <td>${favoriteProject.favoriteProject.description}</td>
                    <td>${favoriteProject.favoriteProject.user.username}</td>
                    <td>
                        <nav class="navbar">
                            <div class="container">
                                <div class="navbar-menu">
                                    <a href="/user/project/${favoriteProject.favoriteProject.id}/show">
                                        <button type="submit" class="button button is-primary">Show Details</button></a>
                                </div>
                                <div class="navbar-menu">
                                    <a href="/user/favoriteProjects/${favoriteProject.id}/removeFromFavorite">
                                        <button type="submit" class="button button is-primary">Remove from favorite</button></a>
                                </div>
                            </div>
                        </nav>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
        <div class="container has-text-centered">
            <div>
                <a href="/user/projects/all">
                    <button type="submit" class="button button is-primary"><br>Show all projects<br></button>
                </a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>