package com.example.lab91

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.squareup.picasso.Picasso

class Adapter(context: Context, items: MutableList<Item>): BaseAdapter() {
    var ctx: Context = context
    var objects: MutableList<Item> = items
    var inflater: LayoutInflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    // Формирование разметки, содержащей строку данных
    override fun getView(
        position: Int, convertView: View?,
        parent: ViewGroup?): View {

        // Если разметка ещё не существует, создаём её по шаблону
        var view = convertView
        if (view == null)
            view = inflater.inflate(R.layout.list_fragment,
                parent, false)

        // inflate - метод, который умеет из содержимого layout-файла создать View-элемент
        // Получение объекта с информацией о продукте
        val s = objects[position]

        // Заполнение элементов данными из объекта
        var v = view?.findViewById(R.id.title_ad) as TextView
        v.text = s.title

        v = view?.findViewById(R.id.first_ad) as TextView
        v.text = s.first

        v = view?.findViewById(R.id.last_ad) as TextView
        v.text = s.last

        v = view?.findViewById(R.id.city_ad) as TextView
        v.text = s.city

        v = view?.findViewById(R.id.state_ad) as TextView
        v.text = s.state

        v = view?.findViewById(R.id.country_ad) as TextView
        v.text = s.country

        v = view?.findViewById(R.id.number_st_ad) as TextView
        v.text = s.number

        v = view?.findViewById(R.id.name_st_ad) as TextView
        v.text = s.name

        v = view?.findViewById(R.id.phone_ad) as TextView
        v.text = s.phone


        val ava: ImageView = view.findViewById(R.id.avatar_ad)

        if(s.avatar !== "") {

            // Загрузите изображение аватара с использованием библиотеки Picasso
            Picasso.get().load(s.avatar).into(ava)
        }

        return view
    }
    // Получение элемента данных в указанной строке
    override fun getItem(position: Int): Any {
        return objects[position]
    }

    // Получение идентификатора элемента в указанной строке
    // Часто вместо него возвращается позиция элемента
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    // Получение количества элементов в списке
    override fun getCount(): Int {
        return objects.size
    }
}