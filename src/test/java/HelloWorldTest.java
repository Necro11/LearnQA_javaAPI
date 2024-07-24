import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloWorldTest {

    @Test
    public void testRestAssured() {
        Map<String, String> headers = new HashMap<>();
        headers.put("myHeader1", "myValue1");
        headers.put("myHeader2", "myValue2");

        String url = "https://playground.learnqa.ru/api/long_redirect";
        int redirectCount = 0;
        Response response = null;
        List<String> urls = new ArrayList<>();
        List<Integer> statusCodes = new ArrayList<>();

        while (true) {
            response = RestAssured
                    .given()
                    .redirects()
                    .follow(false)
                    .when()
                    .get(url)
                    .andReturn();

            int statusCode = response.getStatusCode();
            urls.add(url);
            statusCodes.add(statusCode);

            if (statusCode == 200) {
                break;
            }

            String location = response.getHeader("Location");
            if (location == null) {
                System.out.println("No more redirects, but status code is not 200");
                break;
            }

            url = location;
            redirectCount++;
        }

        // Вывод всех URL и статус-кодов
        System.out.println("Redirect chain:");
        for (int i = 0; i < urls.size(); i++) {
            System.out.print(urls.get(i) + " (" + statusCodes.get(i) + "\n)");
            if (i < urls.size() - 1) {
                System.out.print(" -> ");
            }
        }

        int finalStatusCode = response.getStatusCode();
        String finalUrl = response.getHeader("Location") != null ? response.getHeader("Location") : url;

        System.out.println("\n\nNumber of redirects: " + redirectCount);
        System.out.println("Final status code: " + finalStatusCode);
        System.out.println("Final URL: " + finalUrl);
    }
}
