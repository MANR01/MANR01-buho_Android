package com.example.mysql_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysql_app.databinding.ActivitySearchBinding
import com.google.firebase.firestore.FirebaseFirestore

class SearchActivity : AppCompatActivity() {

    //Realizando enlace entre la parte grafica y la parte logica
    lateinit var binding: ActivitySearchBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BotonBuscar.setOnClickListener {
            db.collection("Usuarios").document(binding.Identificador.text.toString())
                .get().addOnSuccessListener {
                binding.name.setText(it.get("Nombre") as String?)
                binding.aPaterno.setText(it.get("Apellido Paterno") as String?)
                binding.aMaterno.setText(it.get("Apellido Materno") as String?)
                binding.edad.setText(it.get("Edad") as String?)
                binding.dir.setText(it.get("Dirección") as String?)
                binding.municipios.setText(it.get("Municipios") as String?)
            }
                .addOnFailureListener {
                    binding.mensaje.text = "No se ha podido conectar a la Base de Datos"
                }
        }

        //Cambiar a la Vista Principal
        binding.botonRegresar.setOnClickListener{
            val abrir = Intent(this, MainActivity::class.java)
            startActivity(abrir)
        }
    }
}