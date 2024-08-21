package com.example.rolldice

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

      val lanzarButton: Button = findViewById(R.id.lanzarButton)
      lanzarButton.setOnClickListener{
          rollDice()

          Handler(Looper.getMainLooper()).postDelayed({
              val toast = Toast.makeText(applicationContext, "Lanzamiento Exitoso", Toast.LENGTH_SHORT)// Objeto de tipo Toast.
              toast.show() //Mostramos el mensaje
              // Configura un Handler para cancelar el Toast después de 500ms (0.5 segundos)
              Handler(Looper.getMainLooper()).postDelayed({toast.cancel()}, 1200) // Cambia 500 a cualquier valor en milisegundos para ajustar la duración
          },1000)
      }
    }
    private fun rollDice(){
        showGIF()
        var numero: Int = lanzar(6)
        // Toast.makeText(this,"Resultado: $numero",Toast.LENGTH_SHORT).show()
        val txtResultado: TextView = findViewById(R.id.txtResultado)
        Handler(Looper.getMainLooper()).postDelayed({txtResultado.text = "Resultado: ${numero}"},1000)
        }

    private fun lanzar (max:Int):Int{
        return (1..max).random()
    }

    private fun showGIF (){
        val imagenGIF: ImageView = findViewById(R.id.imgDado)
        Glide.with(this).load(R.drawable.dado_girando).into(imagenGIF)
        Handler(Looper.getMainLooper()).postDelayed({Glide.with(this).clear(imagenGIF)},1000)

    }

}