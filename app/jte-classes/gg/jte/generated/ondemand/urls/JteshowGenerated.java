package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.urls.UrlPage;
public final class JteshowGenerated {
	public static final String JTE_NAME = "urls/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,3,3,6,6,8,8,8,13,13,13,17,17,17,21,21,21,26,26,26,26,26,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n<div class=\"container-lg mt-5\">\n    <h2>Page ");
				jteOutput.setContext("h2", null);
				jteOutput.writeUserContent(page.getName());
				jteOutput.writeContent("</h2>\n    <table class=\"table mt-3\">\n        <tbody>\n        <tr>\n            <th>ID</th>\n            <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getId());
				jteOutput.writeContent("</td>\n        </tr>\n        <tr>\n            <th>Name</th>\n            <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getName());
				jteOutput.writeContent("</td>\n        </tr>\n        <tr>\n            <th>Created at</th>\n            <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getCreatedAt().toString());
				jteOutput.writeContent("</td>\n        </tr>\n        </tbody>\n    </table>\n</div>\n");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
