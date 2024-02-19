package com.example.mysql_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mysql_app.databinding.ActivityRegistrarBinding
import com.google.firebase.firestore.FirebaseFirestore

class RegistrarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.BotonEnviar.setOnClickListener {
            if(binding.Identificador.text.isNotBlank() && binding.name.text.isNotBlank() &&
                binding.aPaterno.text.isNotBlank() && binding.aMaterno.text.isNotBlank() &&
                binding.edad.text.isNotBlank() && binding.dir.text.isNotBlank() &&
                binding.municipios.text.isNotBlank()){

                val dato = hashMapOf( "id" to binding.Identificador.text.toString(),
                    "Nombre" to binding.name.text.toString(),
                    "Apellido Paterno" to binding.aPaterno.text.toString(),
                    "Apellido Materno" to binding.aMaterno.text.toString(),
                    "Edad" to binding.edad.text.toString(),
                    "Dirección" to binding.dir.text.toString(),
                    "Municipios" to binding.municipios.text.toString())

                db.collection("Usuarios")
                    .document(binding.Identificador.text.toString()).set(dato)
                    .addOnSuccessListener {
                        binding.mensaje.text  = "Dato agregado correctamente"
                    }
                    .addOnFailureListener{
                        binding.mensaje.text = "No se pudo agregar el datos"
                    }
            }
        }

        //Cambiar a la Vista Principal
        binding.botonRegresar.setOnClickListener{
            val abrir = Intent(this, MainActivity::class.java)
            startActivity(abrir)
        }
    }
}