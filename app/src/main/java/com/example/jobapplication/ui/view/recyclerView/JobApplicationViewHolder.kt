package com.example.jobapplication.ui.view.recyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.jobapplication.data.model.JobForm
import com.example.jobapplication.databinding.JobApplicationItemBinding
import com.example.jobapplication.ui.view.JobApplication

/*
 * La clase que sostiene o maneja cada item singular del recycler view. Recibe una vista por parámetros para poder saber
 * con qué está trabajando y para poder obtener el view binding.
 */
class JobApplicationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    /*
     * View binding a uno de los item del recycler view. Para acceder a los campos con mayor facilidad. Al no ser una
     * actividad se obtiene de una forma diferente, no se infla la vista solo se bindea con la vista que recibe en los
     * parámetros.
     */
    private val binding = JobApplicationItemBinding.bind(view)

    fun render(jobApplication: JobForm, onClickListener: (JobForm) -> Unit) {
        binding.apply {

            applicantName.text = "${jobApplication.first_name} ${jobApplication.last_name}"
            applicantEmail.text = jobApplication.email
            applicantProfesion.text = jobApplication.position
            applicantPhone.text = "${jobApplication.area_code} ${jobApplication.phone_number}"
            applicantUserName.text = jobApplication.usuarioLinkeado

            /*
             * Setea el evento que va a tener cada item por separado lo que le pasa el objeto de este item, esto para
             * poder mostrarlo en pantalla completa con todos los datos.
             */
            itemView.setOnClickListener {
                onClickListener(jobApplication)
            }
        }
    }
}