package com.example.jobapplication.ui.view.acccionesUsuario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import com.example.jobapplication.R
import com.example.jobapplication.data.model.Persona
import com.example.jobapplication.data.provider.PersonaProvider
import com.example.jobapplication.databinding.ActivityCambiarContraBinding

class CambiarContra : AppCompatActivity() {

    private lateinit var binding: ActivityCambiarContraBinding
    private val personas = PersonaProvider.instance
    private var step = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCambiarContraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            /*
             * Botón para buscar al usuario en el paso 1, si se encuentra se bloquea el campo del nombre de usuario, se
             * habilita el campo de la nueva contraseña y si no se encontró se muestra el error respectivo.
             */
            accionUsuario.setOnClickListener {
                textErrorCamposVacios.visibility = View.GONE
                textErrorUsuarioNoEncontrado.visibility = View.GONE

                when (step) {
                    1 -> {
                        /*
                         * Se valida que el campo no esté vacío y si lo está se muestra el error de que son requeridos. Si
                         * no se verifica si el usuario con ese nombre de usuario existe y si no se muestra el error.
                         */
                        if (userName.text.isNotEmpty()) {
                            if (personas.getPersona(userName.text.toString()) != null) {
                                userName.isEnabled = false
                                passwordNew.visibility = View.VISIBLE
                                step = 2
                            } else {
                                textErrorUsuarioNoEncontrado.visibility = View.VISIBLE
                            }
                        } else {
                            textErrorCamposVacios.visibility = View.VISIBLE
                        }
                    }
                    2 -> {
                        /*
                         * Se verifica que el campo de la contraseña no esté vacío. Si no lo está nada más se cambia la
                         * contraseña con los valores de ambos inputs y se finaliza la actividad; se muestra un toast
                         * para notificar que se cambió.
                         */
                        if (passwordNew.text.isNotEmpty()) {
                            personas.editPersona(Persona(userName.text.toString(), passwordNew.text.toString(), false))

                            Toast.makeText(this@CambiarContra, "Contraseña cambiada con éxito.", Toast.LENGTH_LONG)
                                .show()
                            finish()
                        } else {
                            textErrorCamposVacios.visibility = View.VISIBLE
                        }
                    }
                }
            }

            /*
             * El botón de ir a atrás que solo termina esta actividad y vuelve al login.
             */
            irAtras.setOnClickListener {
                finish()
            }
        }
    }
}