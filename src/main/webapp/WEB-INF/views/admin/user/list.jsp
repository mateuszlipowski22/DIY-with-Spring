<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body">
    <div class="container has-text-centered">
        <div class="container has-text-centered">
            <p class="title">
                All books<br>
                <br>
            </p>
        </div>
        <table class="table is-fullwidth is-bordered">
            <thead>
            <tr>
                <th>Num</th>
                <th>User name</th>
                <th>Email</th>
                <th>Active</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}" varStatus="loop">
                <tr>
                    <td>${loop.count}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>${user.enabled}</td>
                    <td>
                        <nav class="navbar">
                            <div class="container">
                                <div class="navbar-menu">
                                    <a href="${user.id}/show">
                                        <button type="submit" class="button button is-primary">Show Details</button>
                                    </a>
                                </div>
                                <div class="navbar-menu">
                                    <a href="${user.id}/edit">
                                        <button type="submit" class="button button is-primary">Edit</button>
                                    </a>
                                </div>
                                <div class="navbar-menu">
                                    <a href="${user.id}/delete">
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
    </div>
</div>


<jsp:include page="/WEB-INF/views/static/footer.jsp"/>