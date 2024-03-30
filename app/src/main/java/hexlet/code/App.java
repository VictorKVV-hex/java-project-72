package hexlet.code;

import io.javalin.Javalin;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.SQLException;
@Slf4j
public class App {
/*    private static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.parseInt(port);
    }*/
    public static Javalin getApp() {
        // Создаем приложение
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        // Описываем, что загрузится по адресу /
        app.get("/", ctx -> ctx.result("Hello World"));
        return app;
    }
    public static void main(String[] args) throws IOException, SQLException {
        var app = getApp();
        app.start(7070);
    }
}
