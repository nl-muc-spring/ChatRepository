var stompClient = null;
var username = '';

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
	
	if ($( "#username" ).val() === "") {
		$( "#username-error" ).removeClass('hidden');
		console.log('USERNAME is empty');
		return;
	} else {
		$( "#username-error" ).addClass('hidden');
	}
	
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function (response) {
            showMessage(JSON.parse(response.body));
        });
    stompClient.send("/app/connected", {}, JSON.stringify({'userName': $( "#username" ).val()}));
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
    stompClient.send("/app/meassageroom", {}, JSON.stringify({'user':{'userName': $("#username").val()}, 'message': $("#message").val()}));
}

function showMessage(message) {
	var date = new Date(message.date);
    $("#messages").append(
    		"<tr><td><b>" + message.user.userName 
    		+ "</b> <small>" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() 
    		+ "</small> | " + message.message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMessage(); });
});