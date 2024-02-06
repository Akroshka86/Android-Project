package com.example.lab91

import android.os.Parcel
import android.os.Parcelable

class Item(First: String, Title: String, Last: String, City: String, State: String, Country: String, Number: String, Name: String, Phone: String, Gender: String, Postcode: String, Latitude: String, Longitude: String, Offset: String, Description: String, Email: String, Username: String, Password: String, Date: String, Age: String, Nat: String, OwnerAvatarUrl: String): Parcelable {

    // Имя
    var first: String = First

    // Описание
    var title: String = Title

    // Имя
    var last: String = Last

    var city: String = City

    var state: String = State

    var country: String = Country

    var number: String = Number

    var name: String = Name

    var phone: String = Phone

    var gender: String = Gender


    var postcode: String = Postcode
    var latitude: String = Latitude
    var longitude: String = Longitude
    var offset: String = Offset
    var description: String = Description
    var email: String = Email
    var username: String = Username
    var password: String = Password
    var date: String = Date
    var age: String = Age
    var nat: String = Nat

    // Аватар
    var avatar: String = OwnerAvatarUrl

    // Возвращаем строку которая возвращает значения переменных
    fun Info(): String {
        return "$last,$title,$avatar"
    }

    // Считываем значения из Parcel в переменные
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    // Записываем значения переменных в Parcel (хранилище)
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(first)
        dest.writeString(title)
        dest.writeString(last)
        dest.writeString(city)
        dest.writeString(state)
        dest.writeString(country)
        dest.writeString(number)
        dest.writeString(name)
        dest.writeString(phone)
        dest.writeString(gender)
        dest.writeString(postcode)
        dest.writeString(latitude)
        dest.writeString(longitude)
        dest.writeString(offset)
        dest.writeString(description)
        dest.writeString(email)
        dest.writeString(username)
        dest.writeString(password)
        dest.writeString(date)
        dest.writeString(age)
        dest.writeString(nat)
        dest.writeString(avatar)
    }

    override fun describeContents(): Int {
        return 0
    }

    // создаем объект который реализует интерфейс
    // CREATOR - создает  экземпляры класса Item из объекта Parcel
    // newArray - создает массив объектов Item указанного размера.
    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}