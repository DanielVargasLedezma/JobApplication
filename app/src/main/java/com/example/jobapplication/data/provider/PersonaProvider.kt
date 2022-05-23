package com.example.jobapplication.data.provider

import com.example.jobapplication.data.model.Persona

/*
 * Provee los datos por medio de un singleton lo cual hace que solo se tenga una instancia en toda la ejecución y así
 * evitarse problemas de duplicación o acceso a lo mismo en diferentes vistas.
 */
class PersonaProvider private constructor() {

    /*
     * Array private y constante para evitar oopsies
     */
    private val personas: ArrayList<Persona> = ArrayList()

    /*
     * Método que se ejecuta por defecto cuando se crea una instancia. O sea, la única vez que se crea inicia con esos
     * usuarios.
     */
    init {
        addPersona(Persona("DanVL", "asdf4321", true))
        addPersona(Persona("vtrejosch", "123", false))
        addPersona(Persona("Dan", "123", false))
        addPersona(Persona("Dan_VL", "123", false))
        addPersona(Persona("vtrejosc", "123", false))
    }

    /*
     * Un objeto que viene siendo la instancia única del provider.
     */
    private object HOLDER {
        val INSTANCE = PersonaProvider()
    }

    /*
     * La manera de obtener la instancia por fuera, un companion que viene siento el static en otros lenguajes. By lazy
     * hace que solo sea de lectura entonces no pueda ser sobreescrita.
     */
    companion object {
        val instance: PersonaProvider by lazy {
            HOLDER.INSTANCE
        }
    }

    /*
     * Método XD. Aquí se podrían meter validaciones para lo de crear usuarios desde el login.
     */
    fun addPersona(persona: Persona): Boolean {
        if(getPersona(persona.userName) == null) {
            personas.add(persona)
            return true
        }
        return false
    }

    /*
     * Método que se usa para obtener a un usuario único con el nombre de Usuario.
     */
    fun getPersona(nombreUsuario: String): Persona? {
        for (p: Persona in personas!!) {
            if (p.userName == nombreUsuario) {
                return p;
            }
        }
        return null;
    }

    /*
     * Método para obtener el arrayList sobre el que iteraría el RecyclerView.
     */
    fun getPersonas(): ArrayList<Persona> {
        return this.personas!!
    }

    /*
     * Método para loggear, retorna true si encuentra un match con el usuario y la contraseña.
     */
    fun login(userName: String, password: String): Boolean {
        for (p: Persona in personas!!) {
            if (p.userName == userName && p.password == password) {
                return true
            }
        }
        return false
    }

    /*
     * Método para retornar al usuario que encontró con el método de arriba. Se podría meter los dos a uno pero no sé, XD
     * profesor.
     */
    fun loginPersona(userName: String, password: String): Persona? {
        for (p: Persona in personas!!) {
            if (p.userName == userName && p.password == password) {
                return p
            }
        }
        return null
    }

    /*
     * Método para editar, me parece que solo sería para cambiar la contraseña desde la UI.
     */
    fun editPersona(p: Persona) {
        val aux = getPersona(p.userName)
        if (aux != null) aux.password = p.password
    }
}