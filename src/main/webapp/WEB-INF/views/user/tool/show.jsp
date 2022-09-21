<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body">
    <div class="container">
        <div class="tile is-ancestor">
            <div class="tile is-4 is-vertical is-parent">
                <div class="tile is-child box">
                    <p class="title">
                        Power Tool Details<br>
                    </p>
                    <div class="container has-text-centered">
                        <img src="/user/${tool.user.id}/tool/${tool.id}/showPicture"
                             width="200" height="200">
                    </div>
                </div>
            </div>
            <div class="tile is-parent">
                <div class="tile is-child box">
                    <div class="field">
                        <label class="label">Name:</label>
                        <div>
                            ${tool.name}
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Description:</label>
                        <div>
                            ${tool.description}
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Link:</label>
                        <div>
                            ${tool.link}
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="tile is-ancestor">
            <div class="tile is-parent">
                <div class="tile is-child box">
                    <nav class="navbar">
                        <div class="tabs is-right">
                            <div class="navbar-menu">
                                <a href="/user/tools/">
                                    <button type="submit" class="button button is-primary">Show all tools</button>
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
                </div>
            </div>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/views/static/footer.jsp"/>
