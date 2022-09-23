<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body">
    <div class="container has-text-centered">
        <div class="container has-text-centered">
            <p class="title">
                My projects<br>
                <br>
            </p>
        </div>
        <c:if test="${empty userProjects}">
            <div class="container has-text-centered">
                <p class="title">
                    Lack of projects<br>
                    <br>
                </p>
            </div>
        </c:if>


        <c:if test="${not empty userProjects}">
            <table class="table is-fullwidth is-bordered" style="width:100%">
                <thead>

                <tr>
                    <th style="width:10%">Num</th>
                    <th style="width:15%">Picture</th>
                    <th style="width:20%">Title</th>
                    <th>Description</th>
                    <th style="width:25%">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="userProject" items="${userProjects}" varStatus="loop">
                    <tr>
                        <td>${loop.count}</td>
                        <th>
                            <img src="/user/project/${userProject.id}/showImage" width="150" height="150">
                        </th>
                        <td>${userProject.title}</td>
                        <td>${userProject.description}</td>
                        <td>
                            <nav class="navbar">
                                <div class="container">
                                    <div class="navbar-menu">
                                        <a href="/user/project/${userProject.id}/show">
                                            <button type="submit" class="button button is-primary">Show Details</button>
                                        </a>
                                    </div>
                                    <div class="navbar-menu">
                                        <a href="/user/project/${userProject.id}/edit">
                                            <button type="submit" class="button button is-primary">Edit</button>
                                        </a>
                                    </div>
                                    <div class="navbar-menu">
                                        <a href="/user/project/${userProject.id}/delete">
                                            <button type="submit" class="button button is-primary">Delete</button>
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
        <div class="container has-text-centered">
            <div>
                <a href="/user/project/add">
                    <button type="submit" class="button button is-primary"><br>Add new project<br></button>
                </a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>