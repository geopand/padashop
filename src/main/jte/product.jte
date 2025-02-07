@param java.util.List<gr.padashop.models.ProductCategory> categories
@param gr.padashop.models.Product product
@param String message = null
@param String error = null

@template.layout.main(content = @`
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Φόρμα δημιουργίας προϊόντος</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 p-6">
<div class="max-w-3xl mx-auto bg-white rounded-xl shadow-md overflow-hidden">
    <form class="p-6" action="/api/category/save" method="post">
        <div class="border-b border-gray-900/10 pb-12">
            <h2 class="text-base font-semibold leading-7 text-gray-900">Εισαγωγή νέου προϊόντος</h2>
            <p class="mt-1 text-sm leading-6 text-gray-500">Τα πεδία με (*) είναι υποχρεωτικά</p>

            @if(message != null)
                <div class="mt-4 p-4 bg-green-100 text-green-700 rounded">
                    ${message}
                </div>
            @endif


            @if(error != null)
                <div class="mt-4 p-4 bg-red-100 text-red-700 rounded">
                    ${error}
                </div>
            @endif

            <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                <div class="sm:col-span-4">
                    <label for="name" class="block text-sm font-medium leading-6 text-gray-900">
                        Όνομα <span class="text-red-500">*</span>
                    </label>
                    <div class="mt-2">
                        <input type="text"
                               name="name"
                               id="name"
                               value=""
                               required
                               class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

                <div class="sm:col-span-3">
                    <label for="description" class="block text-sm font-medium leading-6 text-gray-900">
                        Περιγραφή <span class="text-red-500">*</span>
                    </label>
                    <div class="mt-2">
                        <input type="text"
                               name="description"
                               id="description"
                               value=""
                               required
                               class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

                <div class="sm:col-span-3">
                    <label for="slug" class="block text-sm font-medium leading-6 text-gray-900">
                        URL slug <span class="text-red-500">*</span>
                    </label>
                    <div class="mt-2">
                        <input type="text"
                               name="slug"
                               id="slug"
                               value=""
                               required
                               class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

                <div class="sm:col-span-3">
                    <label for="slug" class="block text-sm font-medium leading-6 text-gray-900">
                        Τιμή μονάδος<span class="text-red-500">*</span>
                    </label>
                    <div class="mt-2">
                        <input type="number"
                               name="price"
                               id="price"
                               value=""
                               required
                               class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

            </div>
        </div>

        <div class="sm:col-span-2">
            <label for="parent" class="block text-sm font-medium leading-6 text-gray-900">Κατηγορία Γονέας</label>
            <div class="mt-2">
                <select id="category" name="category"
                        class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:max-w-xs sm:text-sm sm:leading-6">
                    <option value="">Επιλογή κατηγορίας</option>
                    @for(gr.padashop.models.ProductCategory cat: categories)
                        <option value="${cat.getId()}"
                                selected="${product.getCategory() != null && product.getCategory().equals(cat.getId())}">
                            ${cat.getName()}
                        </option>
                    @endfor
                </select>
            </div>
        </div>

        <div class="mt-6 flex items-center justify-end gap-x-6">
            <button type="button" class="text-sm font-semibold leading-6 text-gray-900"><a href="/api/category/">Άκυρο</a></button>
            <button type="submit"
                    class="rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">
                Αποθήκευση
            </button>
        </div>
    </form>
</div>
</body>
</html>
`)