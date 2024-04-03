@import hexlet.code.dto.urls.UrlsPage
@import hexlet.code.util.NamedRoutes
@param UrlsPage page

@template.layout.page(

page = page,
content = @`
    <h1>Все товары</h1>
    <div class="mb-3">
        <a href="/">Добавить новый URL</a>
    </div>
    <table class="table table-striped">
        @for(var Url : page.getUrls())
            <tr>
                <td>
                    ${Url.getId()}
                </td>
                <td>
                    <a href="${NamedRoutes.urlPath(Url.getId())}">${Url.getName()}</a>
                </td>
            </tr>
        @endfor
    </table>
`,
)