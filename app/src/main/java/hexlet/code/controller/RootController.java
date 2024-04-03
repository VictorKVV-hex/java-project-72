package hexlet.code.controller;
import io.javalin.http.Context;
import hexlet.code.dto.BasePage;
import java.util.Collections;
public class RootController {
    public static void index(Context ctx) {
        var page = new BasePage();
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("build.jte", Collections.singletonMap("page", page));
    }
}
