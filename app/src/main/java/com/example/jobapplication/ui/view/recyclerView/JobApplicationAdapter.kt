package com.example.jobapplication.ui.view.recyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.jobapplication.R
import com.example.jobapplication.data.model.JobForm
import java.util.*
import kotlin.collections.ArrayList

/*
 * El adaptador del recycler view, este es el que crea cada celda. Es diferente al ejemplo del UI Examples, pero el
 * funcionamiento es el mismo solo que está separado por orden y tiene una mejor funcionalidad por el onCLickListener
 * para controlar el comportamiento.
 */
class JobApplicationAdapter(
    private val jobApplicationList: ArrayList<JobForm>,
    private val onClickListener: (JobForm) -> Unit
) : RecyclerView.Adapter<JobApplicationViewHolder>(), Filterable {

    /*
     * ArrayList privada que sirve para imprimir en la pantalla, también se usa para filtrar sobre este y no sobre los
     * datos que se obtienen del provider; esto para no recortar la lista original.
     */
    private var itemsList: ArrayList<JobForm> = jobApplicationList

    /*
     * Cuando se crea el view holder de cada celda, que es cuando el recycler view estará iterando sobre cada vista o no
     * sé, alta fumada. Lo que hace es inflar o bindear el view holder, lo que llama al método de abajo y setea los
     * elementos de cada JobForm en la vista de cada item o celda.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobApplicationViewHolder {
        return JobApplicationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.job_application_item, parent, false)
        )
    }

    /*
     * Método que se va ejecutando por cada elemento del array que recibe por parámetros. Lo que hace es ejecutar el método
     * de render del view holder con el elemento en la posición que recibe. Le setea el objeto así como el evento que va
     * a suceder cuando se clickee la vista (esto segundo solo en este segundo caso).
     */
    override fun onBindViewHolder(holder: JobApplicationViewHolder, position: Int) {
        holder.render(itemsList[position], onClickListener)
    }

    /*
     * Método que implementa la interfaz de Adapter, retorna la cantidad de elementos en el recycler view.
     */
    override fun getItemCount() = itemsList.size

    /*
     * El método que filtra; es un poco self-explanatory, pero es una alta fumada ngl. Retorna un objeto filter que está
     * implementando los métodos que debe tener para filtrar.
     */
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()

                itemsList = if (charSearch.isEmpty()) {
                    jobApplicationList
                } else {
                    val resultList = ArrayList<JobForm>()

                    for (row in jobApplicationList) {
                        if (row.usuarioLinkeado.lowercase(Locale.getDefault())
                                .contains(charSearch.lowercase(Locale.getDefault()))
                        ) {
                            resultList.add(row)
                        }
                    }

                    resultList
                }

                val filterResults = FilterResults()

                filterResults.values = itemsList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemsList = results?.values as ArrayList<JobForm>
                notifyDataSetChanged()
            }
        }
    }
}