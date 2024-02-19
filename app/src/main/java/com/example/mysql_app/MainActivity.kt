package com.example.mysql_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysql_app.databinding.ActivityMainBinding
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Analytic Event
        val analisis: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integración Completa")
        analisis.logEvent("InitScreen", bundle)

        //Cambiar a la Vista Registrar Cliente
        binding.botonAgregarCli.setOnClickListener{
            val abrir = Intent(this, RegistrarActivity::class.java)
            startActivity(abrir)
        }

        //Cambiar a la Vista Buscar Cliente
        binding.botonBuscarCli.setOnClickListener{
            val abrir = Intent(this, SearchActivity::class.java)
            startActivity(abrir)
        }
    }
}