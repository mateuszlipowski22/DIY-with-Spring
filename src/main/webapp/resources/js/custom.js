let $chatHistory;
let $button;
let $textarea;
let $chatHistoryList;



function init() {
    cacheDOM();
    bindEvents();
}

function bindEvents() {
    $button.on('click', addMessage.bind(this));
    $textarea.on('keyup', addMessageEnter.bind(this));
}

function cacheDOM() {
    $chatHistory = $('.chat-history');
    $button = $('#sendBtn');
    $textarea = $('#message-to-send');
    $chatHistoryList = $chatHistory.find('ul');
}

function render(message, userName) {
    scrollToBottom();
    // responses
    var templateResponse = Handlebars.compile($("#message-response-template").html());
    var contextResponse = {
        response: message,
        time: getCurrentTime(),
        userName: userName
    };

    setTimeout(function () {
        $chatHistoryList.append(templateResponse(contextResponse));
        scrollToBottom();
    }.bind(this), 1500);
}

function sendMessage(message) {
    let username = $('#userName').val();
    console.log(username)
    let currentDate=getCurrentDateAndTime();
    let toLogin = selectedUser;
    let chatRoomName = selectedChatRoom;
    if(selectedUser===null && selectedChatRoom!==null){
        sendMsgChatRoom(username, message, chatRoomName, currentDate)
    }else{
        sendMsg(username, message, toLogin, currentDate);
    }
    scrollToBottom();
    if (message.trim() !== '') {
        var template = Handlebars.compile($("#message-template").html());
        var context = {
            messageOutput: message,
            time: getCurrentTime(),
            toUserName: selectedUser
        };

        $chatHistoryList.append(template(context));
        scrollToBottom();
        $textarea.val('');
    }
}

function scrollToBottom() {
    $chatHistory.scrollTop($chatHistory[0].scrollHeight);
}

function getCurrentTime() {
    return new Date().toLocaleTimeString().replace(/([\d]+:[\d]{2})(:[\d]{2})(.*)/, "$1$3");
}

function getCurrentDateAndTime(){
    let currentDate = new Date()
    const options = {year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit' };
    return currentDate.toLocaleDateString(undefined,options);
    // return new Date().toLocaleString();
}


function addMessage() {
    sendMessage($textarea.val());
}

function addMessageEnter(event) {
    // enter was pressed
    if (event.keyCode === 13) {
        addMessage();
    }
}

init();


function renderFromDBFeedback(message, userName, time) {
    scrollToBottom();
    // responses
    var templateResponse = Handlebars.compile($("#message-response-template-db").html());
    var contextResponse = {
        response: message,
        time: time,
        userName: userName
    };

    setTimeout(function () {
        $chatHistoryList.append(templateResponse(contextResponse));
        scrollToBottom();
    }.bind(this), 1500);
}

function renderFromDB(message, time) {
    scrollToBottom();
    // responses
    var templateResponse = Handlebars.compile($("#message-template-db").html());
    var contextResponse = {
        messageOutput: message,
        time: time,
    };

    setTimeout(function () {
        $chatHistoryList.append(templateResponse(contextResponse));
        scrollToBottom();
    }.bind(this), 1500);
}