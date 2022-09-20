<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DIY with Spring</title>
    <link rel="shortcut icon" href="../images/fav_icon.png" type="image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <!-- Bulma Version 0.9.0-->
    <link rel="stylesheet" href="https://unpkg.com/bulma@0.9.0/css/bulma.min.css"/>
    <style type="text/css">
        html,
        body {
            font-family: 'Open Sans';
        }

        img {
            padding: 5px;
            border: 1px solid #ccc;
        }
    </style>
</head>

<body>
<section class="hero is-fullheight is-default is-bold">
    <div class="hero-head">
        <nav class="navbar">
            <div class="container">
                <div class="navbar-brand">
                    <a class="navbar-item" href="/">
                        <strong>DIY with Spring</strong>
                    </a>
                    <span class="navbar-burger burger" data-target="navbarMenu">
                            <span></span>
                            <span></span>
                            <span></span>
                        </span>
                </div>
                <div id="navbarMenu" class="navbar-menu">
                    <div class="navbar-end">
                        <div class="tabs is-right">
                            <ul>
                                <li><a href="/">Home</a></li>
                                <sec:authorize access="hasRole('ADMIN')">
                                    <li><a href="/admin/book/list">Book List</a></li>
                                    <li><a href="/admin/book/add">Add Book</a></li>
                                    <li><a href="/admin/user/list">User List</a></li>
                                </sec:authorize>
                                <li><a href="/about">About</a></li>

                                <sec:authorize access="hasRole('USER')">
                                    <li><a href="/user/book/list">Book List</a></li>
                                    <li><a href="/user/projects/">My projects</a></li>
                                    <li><a href="/user/tools/">My tools</a></li>
                                </sec:authorize>

                                <sec:authorize access="isAnonymous()">
                                    <li><a href="/register">Register</a></li>
                                    <li><a href="/login">Login</a></li>
                                </sec:authorize>

                                <sec:authorize access="isAuthenticated()">
                                    <li>
                                        <a href="/user/details/show">
                                            Logged as: <sec:authentication property="principal.username"/>
                                        </a>
                                    </li>
                                    <li>
                                        <a>
                                            <form action="<c:url value="/logout"/>" method="post">
                                                <input type="submit" value="Logout" class="button is-link">
                                                <input type="hidden" name="${_csrf.parameterName}"
                                                       value="${_csrf.token}"/>
                                            </form>
                                        </a>
                                    </li>
                                </sec:authorize>

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
    </div>
