package com.example.jobapplication.ui.view.acccionesUsuario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.jobapplication.data.model.Persona
import com.example.jobapplication.data.provider.PersonaProvider
import com.example.jobapplication.databinding.ActivityCrearUsuarioBinding

class CrearUsuario : AppCompatActivity() {

    private lateinit var binding: ActivityCrearUsuarioBinding
    private val personas = PersonaProvider.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCrearUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            insertarUsuario.setOnClickListener {
                /*
                 * Visibilidad de los errores reiniciados para evitar mal-funcionamiento.
                 */
                textErrorCamposVacios.visibility = View.GONE
                textErrorUsurioYaCreado.visibility = View.GONE

                /*
                 * Validar para ver si los campos no están vacíos. Si no se cumple muestra el error de que los campos
                 * son necesarios
                 */
                if (usernameNew.text.isNotEmpty() && passwordNew.text.isNotEmpty()) {
                    /*
                     * Intenta insertar, retorna false si existe algún userName ya en los datos quemados y muestra el
                     * error de nombre de usuario ya utilizado. El valor del switch se obtiene con el isChecked que
                     * retorna true si está seleccionado y false si no.
                     */
                    if (tryInsertNewUser(
                            usernameNew.text.toString(),
                            passwordNew.text.toString(),
                            tipoUsuario.isChecked
                        )
                    ) {
                        /*
                         * Un toast para informar y terminamos la actividad lo que retorna al login.
                         */
                        Toast.makeText(this@CrearUsuario, "Usuario creado con éxito.", Toast.LENGTH_LONG).show()
                        finish()
                    } else {
                        textErrorUsurioYaCreado.visibility = View.VISIBLE
                    }
                } else {
                    textErrorCamposVacios.visibility = View.VISIBLE
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

    private fun tryInsertNewUser(userName: String, password: String, tipoUsuario: Boolean) =
        personas.addPersona(Persona(userName, password, tipoUsuario))
}