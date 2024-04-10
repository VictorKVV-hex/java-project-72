package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.util.NamedRoutes;
public final class JteshowGenerated {
	public static final String JTE_NAME = "urls/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,4,4,7,7,9,9,9,14,14,14,18,18,18,22,22,22,29,29,29,29,29,29,29,29,29,44,44,46,46,46,47,47,47,48,48,48,49,49,49,50,50,50,51,51,51,53,53,57,57,57,57,57,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n<div class=\"container-lg mt-5\">\n    <h2>Сайт: ");
				jteOutput.setContext("h2", null);
				jteOutput.writeUserContent(page.getName());
				jteOutput.writeContent("</h2>\n    <table class=\"table mt-3\">\n        <tbody>\n        <tr>\n            <th>ID</th>\n            <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getId());
				jteOutput.writeContent("</td>\n        </tr>\n        <tr>\n            <th>Имя</th>\n            <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getName());
				jteOutput.writeContent("</td>\n        </tr>\n        <tr>\n            <th>Создано</th>\n            <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getCreatedAt().toString());
				jteOutput.writeContent("</td>\n        </tr>\n        </tbody>\n    </table>\n</div>\n<div class=\"container-lg mt-5\">\n    <h2 class=\"mt-5\">Проверки</h2>\n    <form method=\"post\"");
				var __jte_html_attribute_0 = NamedRoutes.urlChecksPath(page.getId());
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n        <button type=\"submit\" class=\"btn btn-primary\">Проверить</button>\n    </form>\n    <table class=\"table mt-3\">\n    <thead>\n    <tr>\n        <th class=\"col\">ID</th>\n        <th class=\"col\">Код ответа</th>\n        <th class=\"col\">Title</th>\n        <th class=\"col\">h1</th>\n        <th class=\"col\">Description</th>\n        <th class=\"col\">Дата проверки</th>\n    </tr>\n    </thead>\n    <tbody>\n    ");
				for (var urlCheck: page.getUrlChecks()) {
					jteOutput.writeContent("\n        <tr>\n            <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getId());
					jteOutput.writeContent("</td>\n            <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getStatusCode());
					jteOutput.writeContent("</td>\n            <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getTitle());
					jteOutput.writeContent("</td>\n            <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getH1());
					jteOutput.writeContent("</td>\n            <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getDescription());
					jteOutput.writeContent("</td>\n            <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck.getCreatedAt().toString());
					jteOutput.writeContent("</td>\n        </tr>\n    ");
				}
				jteOutput.writeContent("\n    </tbody>\n    </table>\n</div>\n");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
