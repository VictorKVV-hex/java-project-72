package hexlet.code;

import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import hexlet.code.model.Url;
import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.sql.Timestamp;
import java.util.Date;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import hexlet.code.repository.CheckRepository;


public final class AppTest {
    private Javalin app;
    private static MockWebServer testServer;
    private static String site;
    private static String adres;

    @BeforeEach
    public void setUp() throws Exception {
        app = App.getApp();
    }
    @BeforeAll
    public static void setServer() throws Exception {
        testServer = new MockWebServer();
        Path path = Paths.get("src/test/resources/test.html").toAbsolutePath().normalize();
        String content = Files.readString(path);
        testServer.enqueue(new MockResponse().setBody(content));
        testServer.start();

        site = testServer.url("").toString();
        adres = String.format("http://localhost:%s", testServer.getPort());
    }
    @AfterAll
    public static void closeServer() throws IOException {
        testServer.shutdown();
    }

    @AfterEach
    public void close() {
        app.stop();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }
    @Test
    public void testURL1() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://some-domain.org";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://some-domain.org");
            assertThat(UrlRepository.getEntities()).hasSize(1);
        });
    }
    @Test
    public void testURL2() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://some-domain.org:8080/example/path";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://some-domain.org:8080");
            assertThat(UrlRepository.getEntities()).hasSize(1);
        });
    }
    @Test
    public void testURL3() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://some-domain.org";
            var response = client.post(NamedRoutes.urlsPath(), requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("https://some-domain.org");
            assertThat(UrlRepository.getEntities()).hasSize(1);
            var response2 = client.get(NamedRoutes.urlPath("1"));
            assertThat(response2.code()).isEqualTo(200);
            assertThat(response2.body().string()).contains("https://some-domain.org");
        });
    }
    @Test
    public void testUrlNotFound() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlPath(99999999L));
            assertThat(response.code()).isEqualTo(404);
        });
    }
    @Test
    public void testCheck() {
        JavalinTest.test(app, (server, client) -> {
/*            client.post("/urls", "url=" + site);
            var urlId = UrlRepository.findByName(adres).getId();   */ // --> Или добавить сайт post'ом
//            var url = new Url(site, new Timestamp(System.currentTimeMillis()));
            var url = new Url(site);
            UrlRepository.save(url);
            var urlId = UrlRepository.findByName(site).getId();
            client.post(String.format("/urls/%s/checks", urlId));

            var check = CheckRepository.getEntitiesById(urlId).get(0);
            assertThat(check.getStatusCode()).isEqualTo(200);
            assertThat(check.getH1()).isEqualTo("Hello, world!");
            assertThat(check.getTitle()).isEqualTo("Test");
            assertThat(check.getDescription()).isEqualTo("Test Webpage");
            assertThat(check.getCreatedAt()).isBeforeOrEqualTo(new Date(System.currentTimeMillis()));
        });
    }
}
