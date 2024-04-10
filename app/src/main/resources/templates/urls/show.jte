@import hexlet.code.dto.urls.UrlPage
@import hexlet.code.util.NamedRoutes
@param UrlPage page

@template.layout.page(
page = page,
content = @`
<div class="container-lg mt-5">
    <h2>Сайт: ${page.getName()}</h2>
    <table class="table mt-3">
        <tbody>
        <tr>
            <th>ID</th>
            <td>${page.getId()}</td>
        </tr>
        <tr>
            <th>Имя</th>
            <td>${page.getName()}</td>
        </tr>
        <tr>
            <th>Создано</th>
            <td>${page.getCreatedAt().toString()}</td>
        </tr>
        </tbody>
    </table>
</div>
<div class="container-lg mt-5">
    <h2 class="mt-5">Проверки</h2>
    <form method="post" action="${NamedRoutes.urlChecksPath(page.getId())}">
        <button type="submit" class="btn btn-primary">Проверить</button>
    </form>
    <table class="table mt-3">
    <thead>
    <tr>
        <th class="col">ID</th>
        <th class="col">Код ответа</th>
        <th class="col">Title</th>
        <th class="col">h1</th>
        <th class="col">Description</th>
        <th class="col">Дата проверки</th>
    </tr>
    </thead>
    <tbody>
    @for(var urlCheck: page.getUrlChecks())
        <tr>
            <td>${urlCheck.getId()}</td>
            <td>${urlCheck.getStatusCode()}</td>
            <td>${urlCheck.getTitle()}</td>
            <td>${urlCheck.getH1()}</td>
            <td>${urlCheck.getDescription()}</td>
            <td>${urlCheck.getCreatedAt().toString()}</td>
        </tr>
    @endfor
    </tbody>
    </table>
</div>
`,
)