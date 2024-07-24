import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloWorldTest {

    @Test
    public void testRestAssured() {
        // Отправляем запрос и получаем ответ
        JsonPath response = RestAssured
                .get("https://playground.learnqa.ru/api/get_json_homework")
                .jsonPath();

        // Извлекаем список сообщений
        List<Map<String, Object>> messages = response.getList("messages");

        // Проверяем, что список не пустой
        if (!messages.isEmpty()) {
            // Выводим все сообщения и их временные метки
            for (Map<String, Object> message : messages) {
                String msg = (String) message.get("message"); // Предполагаем, что поле называется "message"
                String timestamp = (String) message.get("timestamp"); // Предполагаем, что поле называется "timestamp"
                System.out.println("Сообщение: " + msg + ", Время: " + timestamp);
            }

            // Если нужно вывести только второе сообщение
            if (messages.size() > 1) {
                Map<String, Object> secondMessage = messages.get(1);
                String secondMsg = (String) secondMessage.get("message");
                String secondTimestamp = (String) secondMessage.get("timestamp");
                System.out.println("\nВторое сообщение: " + secondMsg + ", Время: " + secondTimestamp);
            } else {
                System.out.println("Сообщений меньше двух.");
            }
        } else {
            System.out.println("Список сообщений пуст.");
        }
    }
}