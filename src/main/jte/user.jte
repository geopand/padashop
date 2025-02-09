@param gr.padashop.models.User user
@param gr.padashop.web.models.PageInfo page
@param String message = null
@param String error = null

@template.layout.main(
page = page,
content = @`
<div class="bg-gray-100 p-6">
<div class="max-w-3xl mx-auto bg-white rounded-xl shadow-md overflow-hidden">
    <form class="p-6" action="/user/save" method="post">
        <div class="border-b border-gray-900/10 pb-12">
            <h2 class="text-base font-semibold leading-7 text-gray-900">Φόρμα Εισαγωγής Χρήστη</h2>
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
                <div class="sm:col-span-3">
                    <label for="fName" class="block text-sm font-medium leading-6 text-gray-900">
                        Όνομα <span class="text-red-500">*</span>
                    </label>
                    <div class="mt-2">
                        <input type="text"
                               name="fName"
                               id="fName"
                               value=""
                               class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

                <div class="sm:col-span-3">
                    <label for="lName" class="block text-sm font-medium leading-6 text-gray-900">
                        Επίθετο <span class="text-red-500">*</span>
                    </label>
                    <div class="mt-2">
                        <input type="text"
                               name="lName"
                               id="lName"
                               value="${user.getsName()}"
                               class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

                <div class="sm:col-span-4">
                    <label for="email" class="block text-sm font-medium leading-6 text-gray-900">
                        E-mail
                    </label>
                    <div class="mt-2">
                        <input type="email" name="email" id="email" value="${user.getEmail()}"
                               class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

                <div class="sm:col-span-3">
                    <label for="country" class="block text-sm font-medium leading-6 text-gray-900">Χώρα</label>
                    <div class="mt-2">
                        <select id="country" name="country"
                                class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:max-w-xs sm:text-sm sm:leading-6">
                            <option value="">Επιλογή χώρας</option>
                            <option value="US"
                                    selected="${user.getCountry() != null && user.getCountry().equals("GR")}">Greece
                            </option>
                            <!-- Add more countries as needed -->
                        </select>
                    </div>
                </div>

                <div class="col-span-full">
                    <label for="strAddress" class="block text-sm font-medium leading-6 text-gray-900">
                        Οδός
                    </label>
                    <div class="mt-2">
                        <input type="text" name="strAddress" id="strAddress" value="${user.getStrAddress()}"
                               class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

                <div class="sm:col-span-2 sm:col-start-1">
                    <label for="city" class="block text-sm font-medium leading-6 text-gray-900">Πόλη</label>
                    <div class="mt-2">
                        <input type="text" name="city" id="city" value="${user.getCity()}"
                               class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>

                <div class="sm:col-span-2">
                    <label for="region" class="block text-sm font-medium leading-6 text-gray-900">State /
                        Province</label>
                    <div class="mt-2">
                        <select id="region" name="region"
                                class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:max-w-xs sm:text-sm sm:leading-6">
                            <option value="">Select a state</option>
                            <option value="GR" selected="${user.getRegion() != null && user.getRegion().equals("GR")}">
                                Greece
                            </option>
                            <!-- Add more states as needed -->
                        </select>
                    </div>
                </div>

                <div class="sm:col-span-2">
                    <label for="zipCode" class="block text-sm font-medium leading-6 text-gray-900">Τ.Κ</label>
                    <div class="mt-2">
                        <input type="text" name="zipCode" id="zipCode" value="${user.getZipCode()}"
                               class="block w-full rounded-md border-0 px-3 py-2 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                    </div>
                </div>
            </div>
        </div>

        <div class="mt-6 flex items-center justify-end gap-x-6">
            <button type="button" class="text-sm font-semibold leading-6 text-gray-900"><a href="/user/">Άκυρο</a></button>
            <button type="submit"
                    class="rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">
                Αποθήκευση
            </button>
        </div>
    </form>
</div>
</div>
`)