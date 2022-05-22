package com.example.jobapplication.ui.view.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jobapplication.data.model.JobForm
import com.example.jobapplication.data.model.Persona
import com.example.jobapplication.data.provider.JobFormProvider
import com.example.jobapplication.databinding.FragmentJobFormBinding

/**
 * A simple [Fragment] subclass.
 * Use the [JobFormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JobFormFragment : Fragment() {

    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"

    private var _binding: FragmentJobFormBinding? = null
    private val binding get() = _binding!!

    private var param1: JobForm? = null
    private var param2: Persona? = null

    private val jobApplications = JobFormProvider.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

            param1 = it.getSerializable(ARG_PARAM1) as JobForm?
            param2 = it.getSerializable(ARG_PARAM2) as Persona
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentJobFormBinding.inflate(inflater, container, false)

        binding.apply {

            if(param2!!.tipoUsuario) {
                descartar.visibility = View.GONE
                guardar.visibility = View.GONE
                eliminar.visibility = View.GONE
                firstName.isEnabled = false
                lastName.isEnabled = false
                streetAddress.isEnabled = false
                streetAddressLineTwo.isEnabled = false
                city.isEnabled = false
                state.isEnabled = false
                postal.isEnabled = false
                country.isEnabled = false
                email.isEnabled = false
                areaCode.isEnabled = false
                phoneNumber.isEnabled = false
                position.isEnabled = false
                date.isEnabled = false

            }

            if (param1 != null) {

                firstName.setText(param1?.first_name)
                lastName.setText(param1?.last_name)
                streetAddress.setText(param1?.street_address)
                streetAddressLineTwo.setText(param1?.street_address_2)
                city.setText(param1?.city)
                state.setText(param1?.state)
                postal.setText(param1?.zip_code)
                country.setText(param1?.country)
                email.setText(param1?.email)
                areaCode.setText(param1?.area_code)
                phoneNumber.setText(param1?.phone_number)
                position.setText(param1?.position)
                date.setText(param1?.date)
            }
            descartar.setOnClickListener{
                descartar()
            }

            if(param1==null){
                eliminar.visibility = View.GONE
            }else{
                eliminar.setOnClickListener{
                    jobApplications.deleteForm(param1!!)
                }

            }

        }

        return binding.root
    }

    private fun descartar(

    ) {
        binding.apply {
            if (param1 != null) {
                firstName.setText(param1?.first_name)
                lastName.setText(param1?.last_name)
                streetAddress.setText(param1?.street_address)
                streetAddressLineTwo.setText(param1?.street_address_2)
                city.setText(param1?.city)
                state.setText(param1?.state)
                postal.setText(param1?.zip_code)
                country.setText(param1?.country)
                email.setText(param1?.email)
                areaCode.setText(param1?.area_code)
                phoneNumber.setText(param1?.phone_number)
                position.setText(param1?.position)
                date.setText(param1?.date)
            } else {
                firstName.text.clear()
                lastName.text.clear()
                streetAddress.text.clear()
                streetAddressLineTwo.text.clear()
                city.text.clear()
                state.text.clear()
                postal.text.clear()
                country.text.clear()
                email.text.clear()
                areaCode.text.clear()
                phoneNumber.text.clear()
                position.text.clear()
                date.text.clear()

            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JobFormFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: JobForm?, param2: Persona?) =
            JobFormFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                    putSerializable(ARG_PARAM2, param2)
                }
            }
    }
}