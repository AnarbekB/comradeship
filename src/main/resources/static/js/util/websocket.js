import { Client, Message } from '@stomp/stompjs';

let client = null;
const handlers = [];

export function connect() {
    client = new Client({
        brokerURL: 'ws://localhost:9000/comradeship-websocket',
        connectHeaders: {
            // login: 'user',
            // passcode: 'password',
        },
        debug: function (str) {
            // console.log(str);
        },
        reconnectDelay: 5000,
        heartbeatIncoming: 4000,
        heartbeatOutgoing: 4000,
    });

    client.activate();
    client.onConnect = function (frame) {
        client.subscribe('/topic/activity', message =>  {
            handlers.forEach(handler =>
                handler(JSON.parse(message.body)));
        });
    }
}

export function addHandler(handler) {
    handlers.push(handler);
}

export function disconnect() {
    if (client !== null) {
        client.disconnect();
    }
}

export function sendMessage(message) {
    client.publish({
        destination: '/app/changeMessage',
        body: JSON.stringify(message)
    });
}