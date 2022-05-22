package com.example.jobapplication.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jobapplication.databinding.FragmentInicioBinding

/**
 * A simple [Fragment] subclass.
 * Use the [InicioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InicioFragment() : Fragment() {
    private val ARG_PARAM1 = "userName"
    private val ARG_PARAM2 = "passUser"

    /*
     * Al igual que las actividades, pueden usar view binding, pero se debe usar diferente.
     */
    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!

    /*
     * Se declaran los parámetros que el fragment espera recibir, debe coincidir con los nombres a ser posible en el
     * método estático de abajo.
     */
    private var userName: String? = null
    private var passUser: String? = null

    /*
     * Método que se ejecuta para setear los valores que recibe el método estático.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userName = it.getString(ARG_PARAM1)
            passUser = it.getString(ARG_PARAM2)
        }
    }

    /*
     * El método que infla la vista en la aplicación. Aquí se metería toda la lógica necesaria para hacer cositas. Como es
     * una clase, se pueden añadir más métodos para lógica aparte y es capaz de implementar interfaces.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInicioBinding.inflate(inflater, container,false)

        binding.apply {
            nombreUsuario.text = userName
            passUsuario.text = passUser
        }

        return binding.root
    }

    /*
     * Destructor, no entiendo mucho para qué sirve pero bueno.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*
     * Método estático para crear instancias, recibe los parámetros que se van a usar en el fragment. Pueden ser objetos
     * complejos, pero tienen que ser serializables y en ese caso se cambiaría la línea 87 y 88, haciendo el put al bundle
     * del respectivo tipo.
     */
    companion object {


        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param userName Parameter 1.
         * @param passUser Parameter 2.
         * @return A new instance of fragment InicioFragment.
         */
        @JvmStatic
        fun newInstance(userName: String, passUser: String) =
            InicioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, userName)
                    putString(ARG_PARAM2, passUser)
                }
            }
    }
}