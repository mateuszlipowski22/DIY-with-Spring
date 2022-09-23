<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body">
    <div class="container">
        <div class="tile is-ancestor">
            <div class="tile is-4 is-vertical is-parent">
                <div class="tile is-child box">

                    <p class="title">
                        User: ${user.username} Details<br>
                    </p>
                    <div class="container has-text-centered">
                        <img src="/user/details/${user.id}/showAvatar"
                             width="200" height="200">
                    </div>
                </div>
            </div>
            <div class="tile is-parent">
                <div class="tile is-child box">
                    <div class="field">
                        <label class="label">City:</label>
                        <div>
                            ${user.userDetails.city}
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Account creation date:</label>
                        <div>
                            ${user.userDetails.createdOn}
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">About Me:</label>
                        <div>
                            ${user.userDetails.aboutMe}
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
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.enabled}</td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="container has-text-centered">
                        <form method="get" action="/admin/user/${user.id}/edit">
                            <button type="submit" class="button is-link">Edit user details</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>
