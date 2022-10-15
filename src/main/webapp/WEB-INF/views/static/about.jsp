<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body ">
    <div class="container has-text-centered">
        <div class="container has-text-centered">
            <p class="title">
                About
                <br>
                <br>
            </p>
        </div>
        <div>
            <table class="table is-fullwidth is-bordered">
                <tr>
                    <td>
                        <div class="container has-text-centered">
                            <table class="container has-text-centered is-bordered">
                                DIY with Spring <br><br>
                                DIY application with usage of Spring<br>
                                Web application for sharing your “Do it yourself” projects with other users.<br>
                                Application written in Spring framework uses following modules:<br>
                                -MVC<br>
                                -Security<br>
                                -JPA<br>
                                -Web Socket<br><br>

                                Communication with database by the usage of Hibernate.<br>
                                Front-end based on JSP websites and JavaScript (combined with websocket application for<br>
                                live user chat).<br>
                                Implementation of Bulma CSS framework.<br>
                                Simplification of the written JAVA code by the usage of Project Lombok.<br>
                                <br>
                            </table>
                            <br>Additional features after login into website<br><br>
                            http://localhost:8080/create-user creation of user account:<br>
                            login: user<br>
                            password: user<br>
                            <br>
                            http://localhost:8080/create-admin creation of admin account:<br>
                            login: admin<br>
                            password: admin<br>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>