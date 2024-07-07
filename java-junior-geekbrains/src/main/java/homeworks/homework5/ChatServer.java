package homeworks.homework5;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import seminars.seminar_5.AbstractRequest;
import seminars.seminar_5.BroadcastMessageRequest;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

// Socket

/**
 * Порядок взаимодействия:
 * 1. Клиент подключается к серверу
 * 2. Клиент посылает сообщение, в котором указан логин. Если на сервере уже есть подключенный клиент с таким логином,
 * то соединение разрывается.
 * 3. Клиент может посылать 3 типа команд:
 * 3.1 list - получить логины других клиентов
 * 3.2 send @login message - отправить личное сообщение с содержимым message другому клиенту с логином login
 * 3.3 send message - отправить сообщение всем с содержимым message
 */
public class ChatServer {

    private final static ObjectMapper objectMapper = new ObjectMapper();


    public static void main(String[] args) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Socket - абстракция, к которой можно подключиться
        // IP -address + port = socket
        // network - сеть - набор соединенных устройств
        // ip-address - это адрес устройства в какой-то сети
        // 8080 - http
        // 443 - https
        // 5432 - postgres
        // клиент подключается к серверу

        Map<String, ClientHandler> clients = new ConcurrentHashMap<>();

        try(ServerSocket server = new ServerSocket(8888)) {
            System.out.println("Сервер запущен");

            while (true) {
                System.out.println("Ждем клиентского подключения...");
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client, clients);

                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Ошибка во время работы сервера: " + e.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {

        private final Socket client;
        private final Scanner in;
        private final PrintWriter out;
        private final Map<String, ClientHandler> clients;
        private String clientLogin;

        public ClientHandler(Socket client, Map<String, ClientHandler> clients) throws IOException {
            this.client = client;
            this.clients = clients;

            this.in = new Scanner(client.getInputStream());
            this.out = new PrintWriter(client.getOutputStream(), true);
        }

        @Override
        public void run() {
            System.out.println("Подключен новый клиент!");

            try {
                String loginRequest = in.nextLine();
                LoginRequest request = objectMapper.reader().readValue(loginRequest, LoginRequest.class);
                this.clientLogin = request.getLogin();
            } catch (IOException e) {
                System.err.println("Не удалось прочитать сообщение от клиента [" + clientLogin + "]: " + e.getMessage());
                String unSuccessfulResponse = createLoginResponse(false);
                out.println(unSuccessfulResponse);
                doClose();
                return;
            }

            System.out.println("Запрос от клиента: " + clientLogin);
            // проверка, что логин клиента не занят
            if (clients.containsKey(clientLogin)) {
                String unSuccessfulResponse = createLoginResponse(false);
                out.println(unSuccessfulResponse);
                doClose();
                return;
            }

            clients.put(clientLogin, this);
            String successfulLoginResponse = createLoginResponse(true);
            out.println(successfulLoginResponse);

            while (true) {
                String msgFromClient = in.nextLine();

                final String type;
                try {
                    seminars.seminar_5.AbstractRequest abstractRequest = objectMapper.reader().readValue(msgFromClient, AbstractRequest.class);
                    type = abstractRequest.getType();
                } catch (IOException e) {
                    System.err.println("Не удалось прочитать сообщение от клиента [" + clientLogin + "]: " + e.getMessage());
                    sendMessage("Не удалось прочитать сообщение: " + e.getMessage());
                    continue;
                }

                if (SendMessageRequest.TYPE.equals(type)) {
                    // Клиент прислал SendMessageRequest

                    final SendMessageRequest sendMessageRequest;
                    try {
                        sendMessageRequest = objectMapper.reader().readValue(msgFromClient, SendMessageRequest.class);
                    } catch (IOException e) {
                        System.err.println("Не удалось прочитать сообщение от клиента [" + clientLogin + "]: " + e.getMessage());
                        sendMessage("Не удалось прочитать сообщение SendMessageRequest: " + e.getMessage());
                        continue;
                    }

                    ClientHandler clientTo = clients.get(sendMessageRequest.getRecipient());
                    if(clientTo == null) {
                        sendMessage("Клиент с логином: [" + sendMessageRequest + "] не найден");
                        continue;
                    }
                    // TODO: Проверить, что клиент с таким логином есть, если нет - ошибку в сторону клиента
                    clientTo.sendMessage(sendMessageRequest.getMessage());
                    // BroadcastRequest.TYPE.equals(type)
                } else if(seminars.seminar_5.BroadcastMessageRequest.TYPE.equals(type)) {
                    final seminars.seminar_5.BroadcastMessageRequest broadcastMessageRequest;
                    try {
                        broadcastMessageRequest = objectMapper.reader().readValue(msgFromClient, BroadcastMessageRequest.class);
                    } catch (IOException e) {
                        System.err.println("Не удалось прочитать сообщение: " + e.getMessage());
                        sendMessage("Не удалось прочитать сообщение BroadcastMessageRequest: " + e.getMessage());
                        continue;
                    } broadcastMessage(broadcastMessageRequest.getUserName(), broadcastMessageRequest.getMessage());
                    // DisconnectRequest.TYPE.equals(type)
                } else if(DisconnectRequest.TYPE.equals(type)) {
                    clients.remove(clientLogin);
                    broadcastMessage("Клиент с логином: ",clientLogin + "вышел из беседы!");
                    break;
                } else {
                    System.err.println("Неизвестный тип сообщения");
                    sendMessage("Неизвестный тип сообщения: " + type);
                }
            }
            doClose();
        }

        private void broadcastMessage(String username, String message) {
            for (ClientHandler client : clients.values()) {
                client.sendMessage("Сообщение отправлено от " + username + ": " + message);
            }
        }

        private void doClose() {
            try {
                in.close();
                out.close();
                client.close();
            } catch (IOException e) {
                System.err.println("Ошибка во время отсоединения клиента: " + e.getMessage());
            }
        }

        public void sendMessage(String message) {
            //TODO: Нужно придумать тип и структуру сообщения
            out.println(message);


        }

        private String createLoginResponse(boolean success) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setConnected(success);
            try {
                return objectMapper.writer().writeValueAsString(loginResponse);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Не удалось создать loginResponse: " + e.getMessage());
            }
        }
    }

}
