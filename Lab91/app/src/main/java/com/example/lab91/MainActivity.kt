package com.example.lab91

import android.content.Context
import android.content.SharedPreferences
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.fragment.app.FragmentTransaction
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity(), LeftFragment.OnDataListener {

    // Определение ориентации экрана
    private var isTwoPane = false




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Если у нас уже есть список, то
        // Загружаем список из SharedPreferences
        val savedList = getList()

        // Проверка на содержимость
        if (savedList.isNotEmpty())
        {
            // запускаем функции по созданию фрагмента
            createFragment(savedList.toMutableList())
        }
    }




    // Функция при нажатии на кнопку
    fun buttonSearchClick(view: View) {

            // Создание списка Item
            val arr: MutableList<Item> = mutableListOf()

            // Запуск корутины
            GlobalScope.launch {

                // Получаем файл из сети
                val url = URL("https://randomuser.me/api").readText()

                // выполним код в основном потоке
                MainScope().launch {

                    // Создаем объект для работы с файлами JSON
                    val json = JSONObject(url)

                    // Работаем с массивом объектов items
                    val array = json.getJSONArray("results")

                    // Создаем цикл по каждому объекту
                    for (i in 0 until array.length()) {

                        // Получаем i элемент массива
                        val o = array.getJSONObject(i)

                        // Получаем значения
                        val owner = o.getJSONObject("name")
                        val title = owner.getString("title")
                        val first = owner.getString("first")
                        val last = owner.getString("last")
                        val picture = o.getJSONObject("picture")
                        val avatar = picture.getString("large")

                        val location = o.getJSONObject("location")
                        val city = location.getString("city")
                        val state = location.getString("state")
                        val country = location.getString("country")

                        val street = location.getJSONObject("street")
                        val number = street.getString("number")
                        val name = street.getString("name")

                        val phone = o.getString("phone")

                        val gender = o.getString("gender")
                        val postcode = location.getString("postcode")

                        val coordinates = location.getJSONObject("coordinates")
                        val latitude = coordinates.getString("latitude")
                        val longitude = coordinates.getString("longitude")

                        val timezone = location.getJSONObject("timezone")
                        val offset = timezone.getString("offset")
                        val description = timezone.getString("description")

                        val email = o.getString("email")
                        val login = o.getJSONObject("login")
                        val username = login.getString("username")
                        val password = login.getString("password")

                        val dob = o.getJSONObject("dob")
                        val date = dob.getString("date")
                        val age = dob.getString("age")

                        val nat = o.getString("nat")

                        // добавляем переменную со списком и помещаем его в массив
                        val reqInfo = Item(first, title, last, city, state, country, number, name, phone, gender, postcode, latitude, longitude, offset, description, email, username, password, date, age, nat, avatar)
                        arr.add(reqInfo)
                    }


                    // Вызываем функцию с данным массивом
                    createFragment(arr)
                }
            }

    }













    private fun createFragment(arr: MutableList<Item>)
    {

        saveList(arr)
        // Проверка ориентации экрана
        isTwoPane = findViewById<View>(R.id.list_fragment) != null

        // Реализуем метод транзакций
        if (isTwoPane) {

            // findFragmentById - поиск фрагмента
            if (supportFragmentManager.findFragmentById(R.id.list_fragment) == null)
            {
                // создаем транзакцию для добавления фрагмента в контейнер list_fragment
                supportFragmentManager.beginTransaction().add(R.id.list_fragment, LeftFragment.newInstance(arr)).commit()
            }
            else
            {
                // создаем транзакцию для добавления фрагмента в контейнер list_fragment и обновляем данные
                supportFragmentManager.beginTransaction().replace(R.id.list_fragment, LeftFragment.newInstance(arr)).commit()
            }

            // findFragmentById - поиск фрагмента
            if (supportFragmentManager.findFragmentById(R.id.details_fragment) == null)
            {

                supportFragmentManager.beginTransaction().add(R.id.details_fragment,
                    RightFragment(
                        arr[0].first,
                        arr[0].title,
                        arr[0].last,
                        arr[0].city,
                        arr[0].state,
                        arr[0].country,
                        arr[0].number,
                        arr[0].name,
                        arr[0].phone,
                        arr[0].gender,
                        arr[0].postcode,
                        arr[0].latitude,
                        arr[0].longitude,
                        arr[0].offset,
                        arr[0].description,
                        arr[0].email,
                        arr[0].username,
                        arr[0].password,
                        arr[0].date,
                        arr[0].age,
                        arr[0].nat,
                        arr[0].avatar

                    )).commit()
            }
            else
            {
                supportFragmentManager.beginTransaction().replace(R.id.details_fragment,
                    RightFragment(
                        arr[0].first,
                        arr[0].title,
                        arr[0].last,
                        arr[0].city,
                        arr[0].state,
                        arr[0].country,
                        arr[0].number,
                        arr[0].name,
                        arr[0].phone,
                        arr[0].gender,
                        arr[0].postcode,
                        arr[0].latitude,
                        arr[0].longitude,
                        arr[0].offset,
                        arr[0].description,
                        arr[0].email,
                        arr[0].username,
                        arr[0].password,
                        arr[0].date,
                        arr[0].age,
                        arr[0].nat,
                        arr[0].avatar
                    )
                ).commit()
            }
        }
        else
        {
            if (supportFragmentManager.findFragmentById(R.id.container) == null) {
                supportFragmentManager.beginTransaction().add(R.id.container,LeftFragment.newInstance(arr)).commit()
            }
            else
            {
                supportFragmentManager.beginTransaction().replace(R.id.container,LeftFragment.newInstance(arr)).commit()
            }
        }
    }

    // Получаем данные из активности
    override fun onData(DataFirst: String, DataTitle: String, DataLast: String, DataCity: String, DataState: String, DataNumber: String, DataName: String, DataCountry: String, DataPhone: String, DataGender: String, DataPostcode: String, DataLatitude: String, DataLongitude: String, DataOffset: String, DataDescription: String, DataEmail: String, DataUsername: String, DataPassword: String, DataDate: String, DataAge: String, DataNat: String, DataAva: String) {
        isTwoPane = findViewById<View>(R.id.list_fragment) != null

        // замена активностей
        supportFragmentManager.beginTransaction()
            .replace(
                if (isTwoPane)
                    R.id.details_fragment
                else
                    R.id.container,
                RightFragment(DataFirst, DataTitle, DataLast, DataCity, DataState, DataCountry, DataNumber, DataName, DataPhone, DataGender, DataPostcode, DataLatitude, DataLongitude, DataOffset, DataDescription, DataEmail, DataUsername, DataPassword, DataDate, DataAge, DataNat,DataAva))

            // Плавный переход
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

            // Возвращение к другой активности
            .addToBackStack(null)
            .commit()

    }




    // Функция сохранения списка
    private fun saveList(list: List<Item>)
    {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()

        // Преобразуем строку в JSON-строку
        val json = gson.toJson(list)

        // Записываем JSON строку в ItemList
        editor.putString("ItemList", json)
        editor.apply()
    }


    // Функция загрузки списка
    private fun getList(): List<Item>
    {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val gson = Gson()

        // Получаем строку JSON
        val json = sharedPreferences.getString("ItemList", "")

        // Указываем тип данных для восстановления
        val type = object : TypeToken<List<Item>>() {}.type

        // ПОлучаем список объектов, при ошибке возвращаем пустой список
        return gson.fromJson(json, type) ?: emptyList()
    }
}

