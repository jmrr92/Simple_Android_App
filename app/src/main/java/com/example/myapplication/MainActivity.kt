package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Ir a Activity 2
        boton2.setOnClickListener{
            val intent1 = Intent(this, Activity2::class.java)
            startActivity(intent1)
        }

        //Ir a Activity 3
        boton3.setOnClickListener{
            val intent2 = Intent(this, Activity3::class.java)
            startActivity(intent2)
        }
    }

}