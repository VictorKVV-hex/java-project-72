@import hexlet.code.dto.BasePage
@import hexlet.code.util.NamedRoutes
@param BasePage page

@template.layout.page(
page = page,
content = @`
    <main class="flex-grow-1">
        <div class="px-4 py-5 my-5 text-center">
            <h1 class="display-5 fw-bold text-body-emphasis">
                Анализатор страниц
            </h1>
            <div class="col-lg-6 mx-auto">
                <p class="lead mb-4">
                    Check websites for SEO suitability for free
                </p>
                <form action="${NamedRoutes.urlsPath()}" method="post" class="rss-form text-body">
                    <div class="row">
                        <div class="col">
                            <div class="form-floating">
                                <input
                                        id="url-input"
                                        autofocus type="text"
                                        required name="url"
                                        aria-label="url"
                                        class="form-control w-100"
                                        placeholder="RSS link"
                                        autocomplete="off">
                                <label for="url-input">Введите ссылку</label>
                            </div>
                        </div>
                        <div class="col-auto">
                            <button type="submit" class="h-100 btn btn-lg btn-primary px-sm-5">Проверить</button>
                        </div>
                    </div>
                </form>
                <p class="mt-2 mb-0">Example: https://www.example.com</p>
            </div>
        </div>
    </main>
`
)