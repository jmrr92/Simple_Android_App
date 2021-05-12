package com.example.myapplication

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_2.*

class Activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        //Toast para mostrar la Activity actual
        Toast.makeText(this, "Estás actualmente en Activity 2", Toast.LENGTH_LONG).show()

        //Añadimos la funcionalidad del boton para crear la tabla
        botonCrear.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "BaseDatos", null, 1)
            val bd = admin.writableDatabase
            bd.execSQL("DROP TABLE jugador")
            bd.execSQL("CREATE TABLE jugador (id int primary key, nombre text, edad int)")
            Toast.makeText(this, "Tabla jugador añadida", Toast.LENGTH_SHORT).show()

        }

        //Añadimos la funcionalidad del boton insertar los datos a la tabla
        botonInsertar.setOnClickListener() {
            val admin = AdminSQLiteOpenHelper(this, "BaseDatos", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            registro.put("id", idj.getText().toString())
            registro.put("nombre", nj.getText().toString())
            registro.put("edad", ej.getText().toString())
            bd.insert("jugador", null, registro)
            bd.close()
            idj.setText("")
            nj.setText("")
            ej.setText("")
            Toast.makeText(this, "Datos del jugador añadidos", Toast.LENGTH_SHORT).show()
        }

        //Añadimos la funcionalidad del boton para consultar los datos introducidos
        botonConsultar.setOnClickListener() {
            val admin = AdminSQLiteOpenHelper(this, "BaseDatos", null, 1)
            val bd = admin.writableDatabase
            val arrayList = ArrayList<String>()
            val adaptList = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
            lv.adapter = adaptList
            val cursor: Cursor = bd.rawQuery("SELECT * FROM jugador", null)
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getString(0)
                    val nombre = cursor.getString(1)
                    val edad = cursor.getString(2)
                    arrayList.add("ID: "+ id + " NOMBRE: " + nombre + " EDAD: " + edad)

                    for(posicion in arrayList.indices){

                    }


                } while (cursor.moveToNext())
            }

        }

        //Volvemos a la Activity Main
        botonVolver2.setOnClickListener {
            finish()
        }
    }
}