package com.example.myapplication

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_3.*

private lateinit var mediaPlayer: MediaPlayer
private var pause: Boolean = false

class Activity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        //Toast para mostrar la Activity actual
        Toast.makeText(this, "Estás actualmente en Activity 3", Toast.LENGTH_LONG).show()

        // Variable to hold service class name
        val mp = MediaPlayer::class.java

        // Initialize a new Intent instance
        val intent = Intent(applicationContext, mp)

        //Boton para iniciar el servicio
        crear_servicio.setOnClickListener {
            startService(intent)
            Toast.makeText(this, "Servicio en ejecucion", Toast.LENGTH_SHORT).show()

            //Una vez iniciado el servicion damos forma y funcionalidad al boton de reproducir "Play"
            reproducir.setOnClickListener {
                if (pause) {
                    mediaPlayer.seekTo(mediaPlayer.currentPosition)
                    mediaPlayer.start()
                    pause = false
                } else {
                    mediaPlayer = MediaPlayer.create(applicationContext, R.raw.audio)
                    mediaPlayer.start()
                    Toast.makeText(this, "Playing", Toast.LENGTH_SHORT).show()
                }

                //Boton de pausa y controlamos posibles errores
                pausar.setOnClickListener {
                    if (mediaPlayer.isPlaying) {
                        mediaPlayer.pause()
                        pause = true
                        reproducir.isEnabled = true
                        pausar.isEnabled = false
                        parar.isEnabled = true
                        Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show()
                    }
                }

                //Boton de parar y controlamos posibles errores
                parar.setOnClickListener {
                    if (mediaPlayer.isPlaying || pause.equals(true)) {
                        pause = false
                        mediaPlayer.stop()
                        mediaPlayer.reset()
                        mediaPlayer.release()
                        reproducir.isEnabled = true
                        pausar.isEnabled = false
                        parar.isEnabled = true
                        Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // Boton para detener el servicio
        parar_servicio.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.reset()
            mediaPlayer.release()
            stopService(intent)
            Toast.makeText(this, "Servicio detenido", Toast.LENGTH_SHORT).show()
        }

        //Volvemos a la Activity Main
        botonVolver3.setOnClickListener {
            finish()
        }

    }
}
