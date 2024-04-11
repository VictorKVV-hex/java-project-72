package hexlet.code.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.repository.CheckRepository;
import io.javalin.http.Context;
import hexlet.code.repository.UrlRepository;
import java.net.URI;
//import java.net.URL;
import hexlet.code.model.Url;
import io.javalin.http.NotFoundResponse;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;


public class UrlController {
    public static void create(Context ctx) throws SQLException {
        var input = ctx.formParamAsClass("url", String.class)
                .get()
                .toLowerCase()
                .trim();

        String obrezokURL;

        try {
//            normalizedURL = String.valueOf(new URI(input).toURL());
            URL url = new URI(input).toURL();
//            obrezokURL = getObrezokURL(url);
            obrezokURL = String.format("%s://%s", url.getProtocol(), url.getAuthority());
        } catch (Exception e) {
            ctx.sessionAttribute("flash", "Incorrect URL");
            ctx.sessionAttribute("flash-type", "warning");
            ctx.redirect("/");
            return;
        }

        if (UrlRepository.findByName(obrezokURL) != null) {
            ctx.sessionAttribute("flash", "Уже есть такой URL");
            ctx.sessionAttribute("flash-type", "info");
            ctx.redirect("/urls");
        } else {
            var url = new Url(obrezokURL, new Timestamp(System.currentTimeMillis()));
            UrlRepository.save(url);
            ctx.sessionAttribute("flash", "Страница успешно добавлена");
            ctx.sessionAttribute("flash-type", "success");
            ctx.redirect("/urls");
        }
    }

    public static void index(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        var page = new UrlsPage(urls);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("urls/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        var urlChecks = CheckRepository.getEntitiesById(id);
        var page = new UrlPage(id, url.getName(), url.getCreatedAt(), urlChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("urls/show.jte", model("page", page));
    }
}
