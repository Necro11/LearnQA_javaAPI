import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelloWorldTest {

    @Test
    public void testRestAssured(){
        Map<String,String> headers = new HashMap<>();
        headers.put("myHeader1", "myValue1");
        headers.put("myHeader2", "myValue2");

      Response response = RestAssured
              .given()
              .redirects()
              .follow(false)
              .when()
              .get("https://playground.learnqa.ru/api/long_redirect")
              .andReturn();

      int statusCode = response.getStatusCode();
      Headers responseHeaders = response.getHeaders();

      String location = response.getHeader("Location");
      System.out.println(responseHeaders + "\n" + "\n" + "Ответ: \n" + location);
    }
}
