@import hexlet.code.dto.urls.UrlsPage
@import hexlet.code.util.NamedRoutes
@param UrlsPage page

@template.layout.page(

page = page,
content = @`
    <h1>Страницы</h1>
    <div class="mb-3">
        <a href="/">Добавить новый URL</a>
    </div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Наименовние</th>
            <th scope="col">Последняя проверка</th>
            <th scope="col">Код ответа</th>
        </tr>
        </thead>
        <tbody>
        @for(var url: page.getUrls())
            <tr>
                <th scope="row">${url.getId()}</th>
                <td>
                    <a href="${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a>
                </td>
                <td>${page.getChecks().isEmpty() ? "" : page.getChecks().get(url.getId()).getCreatedAt().toString()}</td>
                <td>${page.getChecks().isEmpty() || page.getChecks().get(url.getId()) == null ? "" : page.getChecks().get(url.getId()).getStatusCode().toString()}</td>
            </tr>
        @endfor
        </tbody>
    </table>
`,
)