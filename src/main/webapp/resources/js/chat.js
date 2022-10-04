const url = 'http://localhost:8080';
let stompClient;
let stompClientChatRoom;
let selectedUser;
let newMessages = new Map();
let tempChatHistory;
let selectedChatRoom;

document.addEventListener("DOMContentLoaded", function () {
    registration();
    fetchAll();
    setInterval(fetchAll,60*1000);
    fetchAllChatRoom();
    setInterval(fetchAllChatRoom, 60*1000)
});


function connectToChat(userName) {
    console.log("connecting to chat...")
    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to: " + frame);
        stompClient.subscribe("/topic/messages/" + userName, function (response) {
            let data = JSON.parse(response.body);
            console.log(data)
            if (selectedUser === data.fromLogin) {
                render(data.message, data.fromLogin);
            } else {
                newMessages.set(data.fromLogin, data.message);
                $('#userNameAppender_' + data.fromLogin).append('<span id="newMessage_' + data.fromLogin + '" style="color: red">+1</span>');
            }
        });
    });
}

function sendMsg(from, text, to, messageOn) {
    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify(
        {
            fromLogin: from,
            message: text,
            toLogin: to,
            createdOn: messageOn,
        }));
}

function sendMsgChatRoom(from, text, chatRoomName, messageOn) {
    stompClient.send("/app/chat/chatroom/" + selectedChatRoom, {}, JSON.stringify(
        {
            fromLogin: from,
            message: text,
            createdOn: messageOn,
            chatName: chatRoomName
        }));
}

function registration() {
    let userName = document.getElementById("userName").value;
    connectToChat(userName);
}

function selectUser(userName) {
    console.log("selecting users: " + userName);
    // connectToChat(userName);
    tempChatHistory = document.getElementById('chat-history').firstChild;
    tempChatHistory.innerHTML = ""
    console.log(tempChatHistory);

    fetch('http://localhost:8080/user/chat/' + userName)
        .then(function(resp) {
        if (resp.ok) {
            return resp.json();
        } else {
            throw new Error("Błąd sieci");
        }
    }).then(function(data) {
         data.forEach(function(history) {
                if(history.fromLogin!==$('#userName').val()){
                    renderFromDBFeedback(history.message, history.fromLogin, history.createdOn)
                }else{
                    renderFromDB(history.message, history.createdOn)
                }
                console.log(history.fromLogin);
            });


        }).catch(function(err) {
        console.log(err);
    });


    selectedUser = userName;
    selectedChatRoom = null;

    let isNew = document.getElementById("newMessage_" + userName) !== null;
    if (isNew) {
        let element = document.getElementById("newMessage_" + userName);
        element.parentNode.removeChild(element);
        render(newMessages.get(userName), userName);
    }
    $('#selectedChatRoomId').html('');
    $('#selectedUserId').html('');
    $('#selectedUserId').append('Chat with ' + userName);
}

function fetchAll() {
    let currentLoggedUsers=[];
    $.get(url + "/fetchAllLoggedUsers", function (response) {
        let loggedUsers = response;
        for (let i = 0; i < loggedUsers.length; i++) {
            currentLoggedUsers.push(loggedUsers[i])
        }
    });

    $.get(url + "/fetchAllUsers", function (response) {
        let users = response;
        let usersTemplateHTML = "";
        for (let i = 0; i < users.length; i++) {
            if(currentLoggedUsers.includes(users[i])){
                usersTemplateHTML = usersTemplateHTML + '<a href="#" onclick="selectUser(\'' + users[i] + '\')" class="panel-block">\n' +
                    '                                    <div id="userNameAppender_\' + users[i] + \'" class="name">&#128994 ' + users[i] + '</div>\n' +
                    '                                </a>';
            }else{
                usersTemplateHTML = usersTemplateHTML + '<a href="#" onclick="selectUser(\'' + users[i] + '\')" class="panel-block">\n' +
                    '                                    <div id="userNameAppender_\' + users[i] + \'" class="name">&#128308 ' + users[i] + '</div>\n' +
                    '                                </a>';
            }
        }
        $('#usersList').html(usersTemplateHTML);
    });
}

function fetchAllChatRoom() {

    $.get(url + "/fetchAllChatRoom", function (response) {
        let chatRoomList = response;
        let chatRoomTemplateHTML = "";
        for (let i = 0; i < chatRoomList.length; i++) {
            chatRoomTemplateHTML = chatRoomTemplateHTML + '<a href="#" onclick="selectChatRoom(\'' + chatRoomList[i] + '\')" class="panel-block">\n' +
                    '                                    <div id="chatRoomNameAppender_\' + chatRoomList[i] + \'" class="name">' + chatRoomList[i] + '</div>\n' +
                    '                                </a>';
        }
        $('#charRoomList').html(chatRoomTemplateHTML);
    });
}

function registrationChatRoom() {
    let chatName = document.getElementById("userName").value;
    connectToChat(chatName);
}

function creationChatRoom(){
    let chatRoomName = document.getElementById("chatRoomName").value;
    $.get(url + "/registration/" + chatRoomName, function (response) {
        fetchAllChatRoom();
        connectToChatRoom(chatRoomName);
    }).fail(function (error) {
        if (error.status === 400) {
            alert("ChatRoom is already busy!")
        }
    })
}

function connectToChatRoom(chatRoomName) {
    console.log("connecting to chat...")
    let socket = new SockJS(url + '/chat/chatroom/');
    stompClientChatRoom = Stomp.over(socket);
    stompClientChatRoom.connect({}, function (frame) {
        console.log("connected to: " + frame);
        stompClientChatRoom.subscribe("/chatroom/messages/" + chatRoomName, function (response) {
            let data = JSON.parse(response.body);
            console.log(data)
            if (chatRoomName === data.chatName) {
                render(data.message, data.fromLogin);
            } else {
                newMessages.set(data.fromLogin, data.message);
                $('#userNameAppender_' + data.fromLogin).append('<span id="newMessage_' + data.fromLogin + '" style="color: red">+1</span>');
            }
        });
    });
}

function selectChatRoom(chatRoomName) {
    console.log("selecting chat room: " + chatRoomName);
    if (stompClientChatRoom===undefined) {
        connectToChatRoom(chatRoomName);
    }
    tempChatHistory = document.getElementById('chat-history').firstChild;
    tempChatHistory.innerHTML = ""
    console.log(tempChatHistory);

    fetch('http://localhost:8080/user/chatroom/' + chatRoomName)
        .then(function(resp) {
            if (resp.ok) {
                return resp.json();
            } else {
                throw new Error("Błąd sieci");
            }
        }).then(function(data) {
        data.forEach(function(history) {
            if(history.fromLogin!==$('#userName').val()){
                renderFromDBFeedback(history.message, history.fromLogin, history.createdOn)
            }else{
                renderFromDB(history.message, history.createdOn)
            }
            console.log(history.fromLogin);
        });


    }).catch(function(err) {
        console.log(err);
    });

    selectedChatRoom = chatRoomName;
    selectedUser = null
    let isNew = document.getElementById("newMessage_" + chatRoomName) !== null;
    if (isNew) {
        let element = document.getElementById("newMessage_" + chatRoomName);
        element.parentNode.removeChild(element);
        render(newMessages.get(chatRoomName), chatRoomName);
    }
    $('#selectedUserId').html('');
    $('#selectedChatRoomId').html('');
    $('#selectedChatRoomId').append('Chat Room Name ' + chatRoomName);
}