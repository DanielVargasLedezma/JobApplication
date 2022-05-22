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
    }

    private object HOLDER {
        val INSTANCE = JobFormProvider()
    }

    companion object {
        val instance: JobFormProvider by lazy {
            HOLDER.INSTANCE
        }
    }

    fun addJobForm(persona: JobForm) {
        jobForms.add(persona)
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

}