@param gr.padashop.web.models.PageInfo page
@param String message = null
@param String error = null

@template.layout.main(
page = page,
content = @`
    <div class="bg-gray-100 p-6">
        <div class="max-w-3xl mx-auto bg-white rounded-xl shadow-md v-screen">
            Καλωσήρθατε στο διαχειριστικό του PadaShop
        </div>
    </div>
`)