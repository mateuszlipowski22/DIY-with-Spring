<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body">
    <div class="container">
        <div class="tile is-ancestor">
            <div class="tile is-4 is-vertical is-parent">
                <div class="tile is-child box">

                    <p class="title">
                        User: ${currentUser.username} Details<br>
                    </p>
                    <div class="container has-text-centered">
                        <img src="/user/details/showAvatar"
                             width="200" height="200">
                    </div>
<%--                    <div class="container has-text-centered">--%>
<%--                        <form method="get" action="/user/details/image">--%>
<%--                            <button type="submit" class="button is-link">Change profile picture</button>--%>
<%--                        </form>--%>
<%--                    </div>--%>

                </div>
            </div>
            <div class="tile is-parent">
                <div class="tile is-child box">
                    <div class="field">
                        <label class="label">City:</label>
                        <div>
                            ${userDetails.city}
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Account creation date:</label>
                        <div>
                            ${userDetails.createdOn}
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">About Me:</label>
                        <div>
                            ${userDetails.aboutMe}
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="tile is-ancestor">
            <div class="tile is-parent">
                <div class="tile is-child box is-vertical">
                    <table class="table is-fullwidth is-bordered">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>User name</th>
                            <th>Email</th>
                            <th>Active</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${currentUser.id}</td>
                            <td>${currentUser.username}</td>
                            <td>${currentUser.email}</td>
                            <td>${currentUser.enabled}</td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="container has-text-centered">
                        <form method="get" action="/user/details/edit">
                            <button type="submit" class="button is-link">Edit user details</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>
