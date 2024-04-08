package hexlet.code;

import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public final class AppTest {
    private Javalin app;

    private static String website;
    private static String name;

    @BeforeEach
    public void setUp() throws Exception {
        app = App.getApp();
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
}
