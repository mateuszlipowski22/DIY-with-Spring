<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body">
    <div class="container has-text-centered">
        <div class="container has-text-centered">
            <p class="title">
                My tools<br>
                <br>
            </p>
        </div>

        <c:if test="${empty tools}">
        <div class="container has-text-centered">
            <p>
                Your tool list is empty<br>
                <br>
            </p>
        </div>

        </c:if>
        <c:if test="${not empty tools}">
            <table class="table is-fullwidth is-bordered" style="width:100%">
                <thead>
                <tr>
                    <th>Num</th>
                    <th style="width:70%">Name</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="tool" items="${tools}" varStatus="loop">
                    <tr>
                        <td>${loop.count}</td>
                        <td>${tool.name}</td>
                        <td>
                            <nav class="navbar">
                                    <div class="tabs is-right">
                                        <div class="navbar-menu">
                                            <a href="/user/${tool.user.id}/tool/${tool.id}/show">
                                                <button type="submit" class="button button is-primary">Show</button>
                                            </a>
                                        </div>
                                        <div class="navbar-menu">
                                            <a href="/user/${tool.user.id}/tool/${tool.id}/edit">
                                                <button type="submit" class="button button is-primary">Edit</button>
                                            </a>
                                        </div>
                                        <div class="navbar-menu">
                                            <a href="/user/${tool.user.id}/tool/${tool.id}/delete">
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
                <a href="/user/tool/add">
                    <button type="submit" class="button button is-primary"><br>Add new tool<br></button>
                </a>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/views/static/footer.jsp"/>
