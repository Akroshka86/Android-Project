package com.example.lab91

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment


class LeftFragment() : Fragment() {

    private lateinit var list: MutableList<Item>
    private var listString: ArrayList<String> = arrayListOf()



    companion object {

        // принимаем значения списка
        fun newInstance(list: MutableList<Item>): LeftFragment {
            val Listfragment = LeftFragment()

            // Создаем класс для передачи данных между компонентами
            val comp = Bundle()

            // помещаем в него переданный список элементов под ключом ItemList
            comp.putParcelableArrayList("ItemList", ArrayList(list))

            // Далее устанавливаем этот Bundle в качестве аргументов для созданного фрагмента
            Listfragment.arguments = comp
            return Listfragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // извлекаем список элементов из аргументов фрагмента и сохраняет его в переменной list
        list = arguments?.getParcelableArrayList<Item>("ItemList")?.toMutableList() ?: mutableListOf()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Создаем элемент view - который представляет визуальные элементы фрагмента
        val view = inflater.inflate(R.layout.fragment_left, container, false)

        // Создаем цикл в котором вызывается метод Info который возвращает строку, которая добавляется в список listString
        for(i in 0 until list.size)
        {
            listString.add(list[i].Info())
        }

        // Подключаем адаптер
        val listOptions = view.findViewById<ListView>(R.id.list)
        listOptions.adapter = Adapter(requireContext(), list)

        // Реакция на нажатие
        listOptions.setOnItemClickListener { parent, view, position, id ->

            // Вызывается метод onData, который передает позицию в главную активность
            (mainContext as OnDataListener).onData(list[position].first, list[position].title, list[position].last, list[position].city, list[position].state, list[position].country, list[position].number, list[position].name, list[position].phone, list[position].gender, list[position].postcode, list[position].latitude, list[position].longitude, list[position].offset, list[position].description, list[position].email, list[position].username, list[position].password, list[position].date, list[position].age, list[position].nat, list[position].avatar)
        }

        return view
    }

    // используется для передачи фрагментов между активностями
    interface OnDataListener {
        fun onData(DataFirst: String, DataAva: String, DataLast: String, DataCity: String, DataState: String, DataCountry: String, DataNumber: String, DataName: String, DataPhone: String, DataGender: String, DataPostcode: String, DataLatitude: String, DataLongitude: String, DataOffset: String, DataDescription: String, DataEmail: String, DataUsername: String, DataPassword: String, DataDate: String, DataAge: String, DataNat: String,DataTitle: String)
    }

    // onAttach – вызывается при подключении фрагмента к активности.
    // Сохраняем контекст главной активности (ссылка)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainContext = context
    }
    private lateinit var mainContext: Context
}