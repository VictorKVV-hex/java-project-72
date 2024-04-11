package hexlet.code.controller;

import hexlet.code.model.UrlCheck;
import hexlet.code.repository.CheckRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import hexlet.code.repository.UrlRepository;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import java.sql.SQLException;
import java.sql.Timestamp;
import hexlet.code.util.NamedRoutes;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.Optional;

public class CheckController {
    public static void create(Context ctx) throws SQLException {
        var urlId = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(urlId)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + urlId + " not found"));

        try {
            HttpResponse<String> response = Unirest.get(url.getName()).asString();
            Document doc = Jsoup.parse(response.getBody());

            var statusCode = response.getStatus();
            var title = doc.title();
/*            var h1Doc = doc.selectFirst("h1");
            var h1 = h1Doc == null ? "" : h1Doc.text();*/
            var h1Doc = doc.select("h1").first();
            Optional<String> h1Opt = Optional.of(h1Doc.text());
            var h1 = h1Opt.get();

            var descriptionDoc = doc.selectFirst("[name=description]");
            var description = descriptionDoc == null ? "" : descriptionDoc.attr("content");

            var createdAt = new Timestamp(System.currentTimeMillis());
            var urlCheck = new UrlCheck(statusCode, title, h1, description, urlId, createdAt);
            CheckRepository.save(urlCheck);

            ctx.sessionAttribute("flash", "Страница успешно проверена");
            ctx.sessionAttribute("flash-type", "success");
        } catch (Exception e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.sessionAttribute("flash-type", "danger");
        }

        ctx.redirect(NamedRoutes.urlPath(urlId));
    }
}
