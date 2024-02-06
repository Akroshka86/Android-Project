package com.example.lab91

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class RightFragment() : Fragment() {

    private var first: String = ""
    private var title: String = ""
    private var last: String = ""
    private var city: String = ""
    private var state: String = ""
    private var country: String = ""
    private var number: String = ""
    private var name: String = ""
    private var phone: String = ""
    private var gender: String = ""
    private var postcode: String = ""
    private var latitude: String = ""
    private var longitude: String = ""
    private var offset: String = ""
    private var description: String = ""
    private var email: String = ""
    private var username: String = ""
    private var password: String = ""
    private var date: String = ""
    private var age: String = ""
    private var nat: String = ""
    private var ava: String = ""

    constructor(First: String, Title: String, Last: String, City: String, State: String, Country: String, Number: String, Name: String, Phone: String, Gender: String, Postcode: String, Latitude: String, Longitude: String, Offset: String, Description: String, Email: String, Username: String, Password: String, Date: String, Age: String, Nat: String, Ava: String) : this() {
        first = First
        title = Title
        last = Last
        city = City
        state = State
        country = Country
        number = Number
        name = Name
        phone = Phone
        gender = Gender
        postcode = Postcode
        latitude = Latitude
        longitude = Longitude
        offset = Offset
        description = Description
        email = Email
        username = Username
        password = Password
        date = Date
        age = Age
        nat = Nat
        ava = Ava
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Проверяем сохранены ли значения
        if (savedInstanceState != null)
        {
            // Если значения сохранены, то присваиваются значениям
            first = savedInstanceState.getString("SaveFirst", "")
            title = savedInstanceState.getString("SaveTitle", "")
            last = savedInstanceState.getString("SaveLast", "")
            city = savedInstanceState.getString("SaveCity", "")
            state = savedInstanceState.getString("SaveState", "")
            country = savedInstanceState.getString("SaveCountry", "")
            number = savedInstanceState.getString("SaveNumber", "")
            name = savedInstanceState.getString("SaveName", "")
            phone = savedInstanceState.getString("SavePhone", "")
            gender = savedInstanceState.getString("SaveGender", "")
            postcode = savedInstanceState.getString("SavePostcode", "")
            latitude = savedInstanceState.getString("SaveLatitude", "")
            longitude = savedInstanceState.getString("SaveLongitude", "")
            offset = savedInstanceState.getString("SaveOffset", "")
            description = savedInstanceState.getString("SaveDescription", "")
            email = savedInstanceState.getString("SaveEmail", "")
            username = savedInstanceState.getString("SaveUsername", "")
            password = savedInstanceState.getString("SavePassword", "")
            date = savedInstanceState.getString("SaveDate", "")
            age = savedInstanceState.getString("SaveAge", "")
            nat = savedInstanceState.getString("SaveNat", "")
            ava = savedInstanceState.getString("SaveAvatar", "")
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // создается и возвращается разметка фрагмента, заданная в файле fragment_right
        return inflater.inflate(R.layout.fragment_right, container, false)


    }

    // Вызывается сразу после onCreateView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvava: ImageView = view.findViewById(R.id.avatar_image)
        val tvfirst: TextView = view.findViewById(R.id.first_text)
        val tvtitle: TextView = view.findViewById(R.id.title_text)
        val tvlast: TextView = view.findViewById(R.id.last_text)
        val tvcity: TextView = view.findViewById(R.id.city_text)
        val tvstate: TextView = view.findViewById(R.id.state_text)
        val tvcountry: TextView = view.findViewById(R.id.country_text)
        val tvnumber: TextView = view.findViewById(R.id.number_text)
        val tvname: TextView = view.findViewById(R.id.name_text)
        val tvphone: TextView = view.findViewById(R.id.phone_text)
        val tvgender: TextView = view.findViewById(R.id.gender_text)
        val tvpostcode: TextView = view.findViewById(R.id.postcode_text)
        val tvlatitude: TextView = view.findViewById(R.id.latitude_text)
        val tvlongitude: TextView = view.findViewById(R.id.longitude_text)
        val tvoffset: TextView = view.findViewById(R.id.offset_text)
        val tvdescription: TextView = view.findViewById(R.id.description_text)
        val tvemail: TextView = view.findViewById(R.id.email_text)
        val tvusername: TextView = view.findViewById(R.id.username_text)
        val tvpassword: TextView = view.findViewById(R.id.password_text)
        val tvdate: TextView = view.findViewById(R.id.date_text)
        val tvage: TextView = view.findViewById(R.id.age_text)
        val tvnat: TextView = view.findViewById(R.id.nat_text)


        tvfirst.text = first
        tvtitle.text = title
        tvlast.text = last
        tvstate.text = state
        tvcountry.text = country
        tvnumber.text = number
        tvname.text = name
        tvcity.text = city
        tvphone.text = phone
        tvgender.text = gender
        tvpostcode.text = postcode
        tvlatitude.text = latitude
        tvlongitude.text = longitude
        tvoffset.text = offset
        tvdescription.text = description
        tvemail.text = email
        tvusername.text = username
        tvpassword.text = password
        tvdate.text = date
        tvage.text = age
        tvnat.text = nat

        if(ava !== "") {

            // Загрузите изображение аватара с использованием библиотеки Picasso
            Picasso.get().load(ava).into(tvava)
        }

        val clickListener = View.OnClickListener {
            openMap(tvlatitude.text.toString(), tvlongitude.text.toString())
        }

        tvstate.setOnClickListener(clickListener)
        tvnumber.setOnClickListener(clickListener)
        tvname.setOnClickListener(clickListener)
        tvcountry.setOnClickListener(clickListener)
        tvcity.setOnClickListener(clickListener)
        tvlatitude.setOnClickListener(clickListener)
        tvlongitude.setOnClickListener(clickListener)

        tvphone.setOnClickListener {
            makePhoneCall(tvphone.text.toString())
        }


    }

    // сохраняем значения
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("SaveFirst", first)
        outState.putString("SaveTitle", title)
        outState.putString("SaveLast", last)
        outState.putString("SaveCity", city)
        outState.putString("SaveState", state)
        outState.putString("SaveCountry", country)
        outState.putString("SaveNumber", number)
        outState.putString("SaveName", name)
        outState.putString("SavePhone", phone)
        outState.putString("SaveGender", gender)
        outState.putString("SavePostcode", postcode)
        outState.putString("SaveGender", latitude)
        outState.putString("SaveGender", longitude)
        outState.putString("SaveGender", offset)
        outState.putString("SaveGender", description)
        outState.putString("SaveGender", email)
        outState.putString("SaveGender", username)
        outState.putString("SaveGender", password)
        outState.putString("SaveGender", date)
        outState.putString("SaveGender", age)
        outState.putString("SaveGender", nat)
        outState.putString("SaveAvatar", ava)
    }

    // Функция для открытия карты
    private fun openMap(latitude: String, longitude: String) {
        val uri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)

        try {
            startActivity(mapIntent)
        } catch (e: Exception) {
            Log.e("OpenMapError", "Ошибка открытия карты", e)
        }
    }

    // Функция для открытия телефона
    private fun makePhoneCall(phoneNumber: String) {
        val uri = Uri.parse("tel:$phoneNumber")
        val dialIntent = Intent(Intent.ACTION_DIAL, uri)

        try {
            startActivity(dialIntent)
        } catch (e: Exception) {
            Log.e("MakePhoneCallError", "Ошибка вызова номера", e)
        }
    }

}