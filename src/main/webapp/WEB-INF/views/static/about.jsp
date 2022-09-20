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
                                <br>DIY with Spring <br><br>
                                <thead>
                                <tr>
                                    <th>Method HTTP</th>
                                    <th>ADDRESS</th>
                                    <th>Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>GET</td>
                                    <td>/books</td>
                                    <td>Returns the book list</td>
                                </tr>
                                </tbody>
                            </table>
                            <br>Additional features after login into website<br><br>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/static/footer.jsp"/>