package com.example.jobapplication.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.jobapplication.R
import com.example.jobapplication.data.model.Persona
import com.example.jobapplication.data.provider.JobFormProvider
import com.example.jobapplication.databinding.ActivityMainBinding
import com.example.jobapplication.ui.view.fragments.InicioFragment
import com.example.jobapplication.ui.view.fragments.JobFormFragment
import com.example.jobapplication.ui.view.fragments.ListJobApplicationFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    /*
     * View binding para acceder a los componentes de la navdraw con más facilidad.
     */
    private lateinit var binding: ActivityMainBinding

    /*
     * Variable que viene siendo lo que abre la navdraw desde el toolbar. Es lo de alguna manera permite funcionar la nav,
     * hay dudas porque malfunciona un poco.
     */
    private lateinit var toggle: ActionBarDrawerToggle

    /*
     * Usuario que se pretende setear con lo que se manda desde el Login. No tiene mucha utilidad por el momento, quizá
     * para la hora de crear aplicaciones del job para linkearlo a dicho usuario.
     */
    private var userLogged: Persona? = null

    /*
     *
     */
    private val jobApplications = JobFormProvider.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
         * Obtención y set del usuario loggeado.
         */
        userLogged = intent.getSerializableExtra("Login") as Persona

        binding.apply {
            /*
             * Inicialización del toggle, técnicamente lo que hace es que recibe la actividad, la navdraw como tal y textos
             * como un alt en caso de que no carguen las imagenes.
             */
            toggle = ActionBarDrawerToggle(
                this@MainActivity, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
            )

            /*
             * Le añade al navdraw el listener del toggle, lo que abre o cierra el navdraw. Lo sincroniza por alguna razón
             * y sin eso no funca.
             */
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            /*
             * Le añade el listener a la navdraw para que haga una cosa u otra cuando se seleccione cada item de la nav.
             */
            navView.setNavigationItemSelectedListener(this@MainActivity)

            navView.getHeaderView(0).findViewById<TextView>(R.id.nombreUsuarioHeader).text = userLogged!!.userName

            /*
             * Oculta la opción que no debería ver el admin o estándar.
             */
            if (userLogged!!.tipoUsuario) {
                navView.menu.getItem(1).isVisible = false
            } else {
                navView.menu.getItem(2).isVisible = false
            }
        }

        /*
         * Detalles de la custom navdraw.
         */
        setupToolbar()
    }

    private fun setupToolbar() {
        /*
         * La primera línea se puede quitar y pondría la toolbar por defecto que si le funcionan bien los íconos,
         * pero hace que la nav se abra por atrás entonces como que no. Se le quita el título y permite que se muestre
         * lo que viene siendo el toggle con los últimos dos métodos.
         */
        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /*
     * Método que reemplaza los fragments dentro del main body de la actividad que tiene el navdraw. Aquí solo se realiza
     * la acción que reemplaza un framelayout por el fragment. El método se usa donde se escucha cuando se clickea un item
     */
    private fun replaceFragments(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.appBarMain.contentMain.id, fragment)
        fragmentTransaction.commit()
    }

    /*
     * Ni idea qué hace la verdad.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                binding.drawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /*
     * El método que escucha cuando se clickea un item de la navdraw.
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        /*
         * Hace que se resalte el item seleccionado, debe de estar dentro de un grupo para que solo se seleccione un item
         * a la vez.
         */
        item.isChecked = true

        /*
         * Cierra la navdraw para enseñar el contenido principal.
         */
        binding.drawerLayout.closeDrawers()

        /*
         * Según el id del item clickeado hace el cambio respectivo, faltaría crear los fragments nuevos para el job y
         * para el recycler view. Luego de eso se podría ver para consultar uno.
         */
        when (item.itemId) {
            /*
             * Según si es inicio, reemplaza con una nueva instancia que recibe los parámeros para meterlos en el bundle.
             * Se pueden pasar serializables como el jobForm en cuestión para hacerle display en caso de que sea un usuario
             * estándar y ya haya registrado uno, esto para hacer el editar.
             */
            R.id.inicio -> {
                replaceFragments(
                    InicioFragment.newInstance(
                        userLogged!!.userName,
                        userLogged!!.password
                    )
                )
            }
            R.id.form -> {
                val form = jobApplications.getApplication(userLogged!!.userName)

                replaceFragments(JobFormFragment.newInstance(form, userLogged!!))
            }
            R.id.conForm -> {
                replaceFragments(
                    ListJobApplicationFragment.newInstance(
                        userLogged!!
                    )
                )
            }
            R.id.logout -> {
                userLogged = null
                finish()
                startActivity(Intent(this@MainActivity, Login::class.java))
            }
        }

        return true
    }

    /*
     * Esto infla unos tres puntos que no tienen utilidad pero podrían servir de algo.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}