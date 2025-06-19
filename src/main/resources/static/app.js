let username = '';

const stompClient = new StompJs.Client({
	brokerURL: 'ws://localhost:8080/gs-guide-websocket',
});

stompClient.onWebSocketError = (error) => {
	console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
	console.error('Broker reported error: ' + frame.headers['message']);
	console.error('Additional details: ' + frame.body);
};

// Receive connected users
stompClient.onConnect = (frame) => {
	console.log(frame.command);

	stompClient.subscribe('/topic/users', (count) => {
		$('#usersOnlineText').html('Users online: ' + count.body);
	});

	// Receive messages
	stompClient.subscribe('/topic/messages', (message) => {
		const body = JSON.parse(message.body);
		const date = new Date(body.date);

		$('#messages').append(`
				<div class="message">
					<div class="message-content">
						<p class="message-text">${body.author}: ${body.message}</p>
					</div>
					<div class="time">
						<p class="time-text">${date.getHours()}:${date.getMinutes()}</p>
					</div>
				</div>
			`);
	});
};

// Send message to server
const sendMessage = () => {
	const textMessage = $('#messageInput').val();
	stompClient.publish({
		destination: '/app/new_message',
		body: JSON.stringify({
			message: textMessage,
			author: username,
			date: new Date(),
		}),
	});

	$('#messageInput').val('');
};

const sendLogin = () => {
	// Set username
	username = $('#usernameInput').val();
	$('#username').html('Username: ' + username);

	// Activate server connection
	stompClient.activate();

	// Remove registration form
	setChatMode();
};

const setLoginMode = () => {
	$('#login').show();
	$('#chat').hide();
};

const setChatMode = () => {
	$('#login').hide();
	$('#chat').show();
};

$(() => {
	// Activate registration form
	setLoginMode();

	// Request number of users connected to the server
	fetch('http://localhost:8080/api/usersCount').then((e) => {
		e.json().then((response) => {
			const n = response + 1;
			$('#usersOnlineText').html('Users online: ' + n);
		});
	});

	$('#sendButton').click(() => sendMessage());
	$('#loginButton').click(() => sendLogin());
	$('form').on('submit', (e) => e.preventDefault());
});
