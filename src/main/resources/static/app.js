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

stompClient.onConnect = (frame) => {
	console.log(frame.command);

	stompClient.subscribe('/topic/users', (count) => {
		$('#usersOnlineText').html('Users online: ' + count.body);
	});
};

window.onload = async () => {
	stompClient.activate();

	const usersFetch = await fetch('http://localhost:8080/api/usersCount');
	const response = (await usersFetch.json()) + 1;
	$('#usersOnlineText').html('Users online: ' + response);
};
