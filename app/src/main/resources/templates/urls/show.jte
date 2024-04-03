@import hexlet.code.dto.urls.UrlPage
@param UrlPage page

@template.layout.page(
page = page,
content = @`
<div class="container-lg mt-5">
    <h2>Page ${page.getName()}</h2>
    <table class="table mt-3">
        <tbody>
        <tr>
            <th>ID</th>
            <td>${page.getId()}</td>
        </tr>
        <tr>
            <th>Name</th>
            <td>${page.getName()}</td>
        </tr>
        <tr>
            <th>Created at</th>
            <td>${page.getCreatedAt().toString()}</td>
        </tr>
        </tbody>
    </table>
</div>
`,
)