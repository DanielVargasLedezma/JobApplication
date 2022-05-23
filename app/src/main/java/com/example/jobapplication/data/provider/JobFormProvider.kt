package com.example.jobapplication.data.provider

import com.example.jobapplication.data.model.JobForm
import com.example.jobapplication.data.model.Persona

class JobFormProvider private constructor() {

    private var jobForms: ArrayList<JobForm> = ArrayList()

    init {
        addJobForm(
            JobForm(
                "Valeria",
                "Trejos",
                "Juan Pablo",
                "Casa con puertas y ventanas",
                "Poás",
                "Alajuela",
                "42001",
                "Costa Rica",
                "vtrejosch@gmail.com",
                "+506",
                "1010101010",
                "Intol Master",
                "14/07/1999",
                "vtrejosch"
            )
        )
        addJobForm(
            JobForm(
                "Daniel",
                "Vargas",
                "La Ribera",
                "300m Norte del Cementerio de la Ribera",
                "Belén",
                "La Ribera",
                "42001",
                "Costa Rica",
                "danilevl229@gmail.com",
                "+506",
                "60729328",
                "Intol Master",
                "18/07/2000",
                "Dan"
            )
        )
        addJobForm(
            JobForm(
                "Daniel",
                "Vargas",
                "La Ribera",
                "300m Norte del Cementerio de la Ribera",
                "Belén",
                "La Ribera",
                "42001",
                "Costa Rica",
                "danilevl229@gmail.com",
                "+506",
                "60729328",
                "Intol Master",
                "18/07/2000",
                "Dan_VL"
            )
        )
    }

    private object HOLDER {
        val INSTANCE = JobFormProvider()
    }

    companion object {
        val instance: JobFormProvider by lazy {
            HOLDER.INSTANCE
        }
    }

    fun addJobForm(jobForm: JobForm): Boolean {
        if (getApplication(jobForm.usuarioLinkeado) == null) {
            jobForms.add(jobForm)
            return true
        }
        return false
    }

    fun getApplications(): ArrayList<JobForm> {
        return this.jobForms
    }

    fun getApplication(userName: String): JobForm? {
        for (p: JobForm in jobForms) {
            if (p.usuarioLinkeado == userName) {
                return p;
            }
        }
        return null
    }

    fun deleteJobApplication(position: Int) {
        jobForms.removeAt(position)
    }

    fun deleteForm (p: JobForm){
        jobForms.remove(p)
    }

    fun editJobForm(jobForm: JobForm) {
        val aux = getApplication(jobForm.usuarioLinkeado)

        if (aux != null) {
            aux.first_name = jobForm.first_name
            aux.last_name = jobForm.last_name
            aux.street_address = jobForm.street_address
            aux.street_address_2 = jobForm.street_address_2
            aux.city = jobForm.city
            aux.state = jobForm.state
            aux.zip_code = jobForm.zip_code
            aux.country = jobForm.country
            aux.email = jobForm.email
            aux.area_code = jobForm.area_code
            aux.phone_number = jobForm.phone_number
            aux.position = jobForm.position
            aux.date = jobForm.date
        }
    }
}