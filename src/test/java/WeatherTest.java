import com.github.tomakehurst.wiremock.WireMockServer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

public class WeatherTest {
    int port = 8080;
    private WireMockServer wireMockServer;
    OkHttpClient httpClient = new OkHttpClient.Builder().build();
    int delay = 3530;

    @BeforeClass
    public void setUp() {
        wireMockServer = new WireMockServer(); // Создание экземпляра сервера WireMock
        wireMockServer.start(); // Запуск сервера
        // Настройка мок-ответа
        configureFor("localhost" , port);
        stubFor(get(urlEqualTo("/example"))
                .willReturn(aResponse()
                        .withStatus(225)
                        .withHeader("Content-Type" , "text/plain")
                        .withBody("Hello, WireMock!")
                        .withFixedDelay(delay)
                ));
    }

    @AfterClass
    public void tearDown() {
        wireMockServer.stop(); // Остановка сервера
    }

    @Test
    public void testWireMockServer() throws Exception {
        Instant beforeRequest = Instant.now();
        // Выполнение HTTP-запроса к мок-серверу
        String response = makeRequest("http://localhost:" + port + "/example");
        Instant afterRequest = Instant.now();
        int aDelay = (int) Duration.between(beforeRequest , afterRequest).toMillis();
        System.out.println(response + "expected resault");
        // Проверка ответа
        Assert.assertTrue(aDelay >= delay);
        System.out.println(aDelay);
    }

    @Test
    public void testWireMockServerWithHttpClient() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:" + port + "/example") // Укажите URL-адрес вашего GET-запроса
                .build();
        try
            // Отправка запроса и получение ответа
            (Response response = httpClient.newCall(request).execute()){
            // Проверка статуса ответа
            Assert.assertEquals(response.code() , 225 , "HTTP запрос не выполнен успешно.");
            // Извлечение содержимого ответа
            String stringBody = response.body().string();
            Assert.assertEquals("text/plain",response.header("Content-Type"));
            String contentType = response.headers().get("Content-Type");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void checkResponse() throws Exception {
        var Url = ("http://localhost:" + port + "/example");
        String responseBody = urlEqualTo(Url).toString();
        // Проверка тела ответа
        System.out.println("Response Body: " + responseBody);
        // Assert.assertEquals(responseBody,"Hello, WireMock!");
        System.out.println(makeRequest(Url));
    }

    //----------------------------------------------------
    // Метод для выполнения HTTP-запроса и получения ответа в виде строки
    private String makeRequest(String url) throws Exception {
        // Используем HttpURLConnection для выполнения HTTP-запроса
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        // Получение ответа
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder response = new StringBuilder();
            try (java.io.BufferedReader in = new java.io.BufferedReader(
                    new java.io.InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            return response.toString();
        } else {
            throw new Exception("HTTP запрос не выполнен. Код ответа: " + responseCode);
        }
    }
}


