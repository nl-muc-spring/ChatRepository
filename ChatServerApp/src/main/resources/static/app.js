var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (response) {
            showMessage(JSON.parse(response.body).message);
        });
    stompClient.send("/app/connected", {}, JSON.stringify({'userName': 'userName'}));
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.send("/app/disconnected", {}, JSON.stringify({'userName': 'userName'}));
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    stompClient.send("/app/meassageroom", {}, JSON.stringify({'message': $("#message").val()}));
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});