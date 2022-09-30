const url = 'http://localhost:8080';
let stompClient;
let selectedUser;
let newMessages = new Map();
let tempChatHistory;

document.addEventListener("DOMContentLoaded", function () {
    registration();
    fetchAll()
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
            createdOn: messageOn
        }));
}

function registration() {
    let userName = document.getElementById("userName").value;
    connectToChat(userName);
}

function selectUser(userName) {
    console.log("selecting users: " + userName);

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
                if(history.fromLogin===userName){
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
    let isNew = document.getElementById("newMessage_" + userName) !== null;
    if (isNew) {
        let element = document.getElementById("newMessage_" + userName);
        element.parentNode.removeChild(element);
        render(newMessages.get(userName), userName);
    }
    $('#selectedUserId').html('');
    $('#selectedUserId').append('Chat with ' + userName);
}

function fetchAll() {
    $.get(url + "/fetchAllUsers", function (response) {
        let users = response;
        let usersTemplateHTML = "";
        for (let i = 0; i < users.length; i++) {
            usersTemplateHTML = usersTemplateHTML + '<a href="#" onclick="selectUser(\'' + users[i] + '\')" class="panel-block">\n' +
                '                                    <div id="userNameAppender_\' + users[i] + \'" class="name">' + users[i] + '</div>\n' +
                '                                </a>';
        }
        $('#usersList').html(usersTemplateHTML);
    });
}


async function displayChatHistory(userName) {
    let obj;
    const res = await fetch('http://localhost:8080/user/chat/' + userName);
    obj = await res.json();
    // console.log(obj)
    return obj;
}


