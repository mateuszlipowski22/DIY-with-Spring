<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/static/header.jsp"/>


<div class="hero-body container has-text-centered">
    <div class="table-container">
        <table class="table is-fullwidth is-bordered is-scrollable" style="width:1000px">
            <tbody style="height: 700px">
            <tr>
                <td style="width:30%" rowspan=3>
                    <nav class="panel">
                        <p class="panel-heading">
                            Chat with DIY users
                        </p>
                        <div id="people-list">
                            <div class="panel-block">
                                <p class="control has-icons-left">
                                    <input id="userName" type="hidden"
                                           value="<sec:authentication property="principal.username"/>"/>
                                </p>
                            </div>
                            <ul class="list" id="usersList">

                            </ul>
                        </div>
                        <p class="panel-heading">
                            Chatroom
                        </p>
                        <div class="chatRoom" id="chatRoom">
                            <div class="search">
                                <input id="chatRoomName" placeholder="Chat Room Name" type="text"/>
                                <button onclick="creationChatRoom()" class="button is-link">Create Chat Room</button>
                            </div>
                            <ul class="list" id="charRoomList">


                            </ul>
                        </div>

                    </nav>
                </td>

                <td style="height: 50px" colspan=2>
                    <div class="chat">
                        <div class="chat-header clearfix">
                            <div class="chat-about">
                                <div class="chat-with" id="selectedUserId">Select user for Chat</div>
                                <div class="chat-with" id="selectedChatRoomId"></div>
                                <div class="chat-num-messages"></div>
                            </div>
                        </div> <!-- end chat-header -->
                </td>
            </tr>
            <TR>
                <TD style="height: 550px" colspan=2>
                    <div class="chat-history" id="chat-history" style="overflow: auto; height: 550px">
                        <ul></ul>
                    </div> <!-- end chat-history -->
                </TD>
            </TR>
            <TR>
                <TD style="height: 100px" colspan=2>
                    <div class="chat-message clearfix">
                        <textarea id="message-to-send" name="message-to-send" placeholder="Type your message"
                                  rows="3" cols="75"></textarea>
                        <button id="sendBtn" class="button is-link">Send</button>
                    </div> <!-- end chat-message -->
                </TD>
            </TR>
                    </div> <!-- end chat -->

             </tbody>
        </table>

        <script id="message-template" type="text/x-handlebars-template">
            <article class="message is-info">
                <div class="message-header">
                    <span class="message-data-time">{{time}}, Today</span> &nbsp; &nbsp;
                    <span class="message-data-name">You</span> <i class="fa fa-circle me"></i>
                </div>
                <div class="message-body">
                    {{messageOutput}}
                </div>
            </article>
        </script>

        <script id="message-response-template" type="text/x-handlebars-template">
            <article class="message is-success">
                <div class="message-header">
                    <span class="message-data-name"><i class="fa fa-circle online"></i> {{userName}}</span>
                    <span class="message-data-time">{{time}}, Today</span>
                </div>
                <div class="message-body">
                    {{response}}
                </div>
            </article>
        </script>

        <script id="message-template-db" type="text/x-handlebars-template">
            <article class="message is-info">
                <div class="message-header">
                    <span class="message-data-time">{{time}}, </span> &nbsp; &nbsp;
                    <span class="message-data-name">You</span> <i class="fa fa-circle me"></i>
                </div>
                <div class="message-body">
                    {{messageOutput}}
                </div>
            </article>
        </script>

        <script id="message-response-template-db" type="text/x-handlebars-template">
            <article class="message is-success">
                <div class="message-header">
                    <span class="message-data-name"><i class="fa fa-circle online"></i> {{userName}} </span>
                    <span class="message-data-time">{{time}}, </span>
                </div>
                <div class="message-body">
                    {{response}}
                </div>
            </article>
        </script>
    </div>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.0/handlebars.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/list.js/1.1.1/list.min.js"></script>
<script src="<c:url value="/resources/js/custom.js" />"></script>
<script src="<c:url value="/resources/js/chat.js" />"></script>
<jsp:include page="/WEB-INF/views/static/footer.jsp"/>


<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>Custom messanger</title>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.0/handlebars.min.js"></script>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/list.js/1.1.1/list.min.js"></script>--%>
<%--    <!--    libs for stomp and sockjs-->--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>--%>
<%--    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>--%>
<%--    <!--    end libs for stomp and sockjs-->--%>
<%--    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" rel="stylesheet"--%>
<%--          type="text/css">--%>
<%--    <link href="/resources/css/style.css" rel="stylesheet">--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container clearfix">--%>
<%--    <div class="people-list" id="people-list">--%>
<%--        <div class="search">--%>
<%--            <input id="userName" placeholder="search" type="text"/>--%>
<%--            <button onclick="registration()">Enter the chat</button>--%>
<%--            <button onclick="fetchAll()">Refresh</button>--%>
<%--        </div>--%>
<%--        <ul class="list" id="usersList">--%>


<%--        </ul>--%>
<%--    </div>--%>

<%--    <div class="chat">--%>
<%--        <div class="chat-header clearfix">--%>
<%--            <img alt="avatar" height="55px"--%>
<%--                 src="https://rtfm.co.ua/wp-content/plugins/all-in-one-seo-pack/images/default-user-image.png"--%>
<%--                 width="55px"/>--%>

<%--            <div class="chat-about">--%>
<%--                <div class="chat-with" id="selectedUserId"></div>--%>
<%--                <div class="chat-num-messages"></div>--%>
<%--            </div>--%>
<%--            <i class="fa fa-star"></i>--%>
<%--        </div> <!-- end chat-header -->--%>

<%--        <div class="chat-history">--%>
<%--            <ul>--%>

<%--            </ul>--%>

<%--        </div> <!-- end chat-history -->--%>

<%--        <div class="chat-message clearfix">--%>
<%--            <textarea id="message-to-send" name="message-to-send" placeholder="Type your message" rows="3"></textarea>--%>

<%--            <i class="fa fa-file-o"></i> &nbsp;&nbsp;&nbsp;--%>
<%--            <i class="fa fa-file-image-o"></i>--%>

<%--            <button id="sendBtn">Send</button>--%>

<%--        </div> <!-- end chat-message -->--%>

<%--    </div> <!-- end chat -->--%>

<%--</div> <!-- end container -->--%>

<%--<script id="message-template" type="text/x-handlebars-template">--%>
<%--    <li class="clearfix">--%>
<%--        <div class="message-data align-right">--%>
<%--            <span class="message-data-time">{{time}}, Today</span> &nbsp; &nbsp;--%>
<%--            <span class="message-data-name">You</span> <i class="fa fa-circle me"></i>--%>
<%--        </div>--%>
<%--        <div class="message other-message float-right">--%>
<%--            {{messageOutput}}--%>
<%--        </div>--%>
<%--    </li>--%>
<%--</script>--%>

<%--<script id="message-response-template" type="text/x-handlebars-template">--%>
<%--    <li>--%>
<%--        <div class="message-data">--%>
<%--            <span class="message-data-name"><i class="fa fa-circle online"></i> {{userName}}</span>--%>
<%--            <span class="message-data-time">{{time}}, Today</span>--%>
<%--        </div>--%>
<%--        <div class="message my-message">--%>
<%--            {{response}}--%>
<%--        </div>--%>
<%--    </li>--%>
<%--</script>--%>

<%--<script src="/resources/js/custom.js"></script>--%>
<%--<script src="/resources/js/chat.js"></script>--%>
<%--</body>--%>
<%--</html>--%>