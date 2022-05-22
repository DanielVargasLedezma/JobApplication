package com.example.jobapplication.data.model

/*
 * data class, una clase que solo tiene como objetivo hacer placeholder de datos. Dado que la contraseña es editable, se
 * declara como variable en lugar de val. Es una clase serializable.
 */
data class Persona(val userName: String, var password: String, val tipoUsuario: Boolean) : java.io.Serializable
