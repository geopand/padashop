@param java.util.List<gr.padashop.models.Category> categories
@param gr.padashop.web.models.PageInfo page
@param String message = null
@param String error = null

@template.layout.main(
page = page,
content = @`
    <div>
        <body class="bg-gray-100 p-6">
        <div class="max-w-3xl mx-auto bg-white rounded-xl shadow-md overflow-hidden">
            <form class="p-6" action="/product/save" method="post" enctype="multipart/form-data">
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
                            <label for="price" class="block text-sm font-medium leading-6 text-gray-900">
                                Τιμή μονάδος<span class="text-red-500">*</span>
                            </label>
                            <div class="mt-2">
                                <input type="number" step=".01"
                                       name="price"
                                       id="price"
                                       value=""
                                       required
                                       class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                            </div>
                        </div>

                        <div class="sm:col-span-3">
                            <label for="file" class="block text-sm font-medium leading-6 text-gray-900">
                                Εικόνα προϊόντος<span class="text-red-500">*</span>
                            </label>
                            <div class="mt-2">
                                <input type="file" name="file" id="file" required
                                       class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6"
                                />
                            </div>
                        </div>

                        <div class="sm:col-span-3">
                            <label for="brand" class="block text-sm font-medium leading-6 text-gray-900">
                                Εταιρία<span class="text-red-500">*</span>
                            </label>
                            <div class="mt-2">
                                <input type="text"
                                       name="brand"
                                       id="brand"
                                       value=""
                                       required
                                       class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                            </div>
                        </div>

                        <div class="sm:col-span-3">
                            <label for="stock" class="block text-sm font-medium leading-6 text-gray-900">
                                Τεμάχια σε απόθεμα<span class="text-red-500">*</span>
                            </label>
                            <div class="mt-2">
                                <input type="number"
                                       name="stock"
                                       id="stock"
                                       value=""
                                       required
                                       class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                            </div>
                        </div>

                    </div>
                </div>

                <div class="sm:col-span-2">
                    <label for="parent" class="block text-sm font-medium leading-6 text-gray-900">
                        Κατηγορία Προϊόντος
                    </label>
                    <div class="mt-2">
                        <select id="category" name="category"
                                class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:max-w-xs sm:text-sm sm:leading-6">
                            <option value="">Επιλογή κατηγορίας</option>
                            @for(gr.padashop.models.Category cat: categories)
                                <option value="${cat.getId()}">
                                    ${cat.getName()}
                                </option>
                            @endfor
                        </select>
                    </div>
                </div>

                <div class="mt-6 flex items-center justify-end gap-x-6">
                    <button type="button" class="text-sm font-semibold leading-6 text-gray-900"><a href="/product/">Άκυρο</a>
                    </button>
                    <button type="submit"
                            class="rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">
                        Αποθήκευση
                    </button>
                </div>
            </form>
        </div>
        </body>
    </div>
`)