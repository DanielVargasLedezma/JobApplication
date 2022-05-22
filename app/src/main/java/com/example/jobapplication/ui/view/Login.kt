package com.example.jobapplication.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.jobapplication.databinding.ActivityLoginBinding
import com.example.jobapplication.data.model.JobForm
import com.example.jobapplication.data.provider.PersonaProvider
import com.example.jobapplication.ui.view.acccionesUsuario.CambiarContra
import com.example.jobapplication.ui.view.acccionesUsuario.CrearUsuario
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Login : AppCompatActivity() {

    /*
     * View binding, accesibilidad directa a los diferentes componentes pertinentes a esta actividad
     */
    private lateinit var binding: ActivityLoginBinding

    /*
     * Los datos quemados de los usuarios a validar para el login. Se le llama provider porque es quien en teoría provee
     * los datos, esto mismo lo sigue una estructura seria.
     */
    private val personas = PersonaProvider.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
         * binding.apply para evitar tener que poner el binding. cada vez que se quiera acceder a un componente de la
         * vista.
         */
        binding.apply {
            val intent = Intent(this@Login, MainActivity::class.java)

            /*
             * Texto para crear un nuevo usuario. Solo inicia la actividad de crear usuario sin terminar esta dado que
             * tiene que pasar por aquí para seguir a otra actividad.
             */
            crearUsuario.setOnClickListener {
                startActivity(Intent(this@Login, CrearUsuario::class.java))
            }

            /*
             * Texto para cambiar la contraseña. Solo inicia la actividad de cambiar contraseña sin terminar esta dado que
             * tiene que pasar por aquí para seguir a otra actividad.
             */
            cambiarContra.setOnClickListener {
                startActivity(Intent(this@Login, CambiarContra::class.java))
            }

            loginButton.setOnClickListener {
                /*
                 * Se esconde el error para efectos visuales.
                 */
                errorLogin.visibility = View.GONE

                /*
                 * Método de la colección de usuarios a loggear para validar con los valores dados en los campos de la
                 * vista. Retorna true si hace match y false si no. Si es false, solo se pone el error en la UI visible.
                 * Si es true, buscamos el objeto que hizo match con un método que hace lo mismo, pero retorna el objeto
                 * en lugar del boolean y se pone en intent. Se pasa al navdraw.
                 */
                if (personas.login(usuarioALoggear.text.toString(), passALoggear.text.toString())) {
                    intent.putExtra(
                        "Login",
                        personas.loginPersona(usuarioALoggear.text.toString(), passALoggear.text.toString())
                    )
                    finish()
                    startActivity(intent)
                } else {
                    errorLogin.visibility = View.VISIBLE
                }
            }
        }
    }
}