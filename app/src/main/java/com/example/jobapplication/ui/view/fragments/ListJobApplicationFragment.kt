package com.example.jobapplication.ui.view.fragments

import android.graphics.Canvas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobapplication.R
import com.example.jobapplication.data.model.JobForm
import com.example.jobapplication.data.model.Persona
import com.example.jobapplication.data.provider.JobFormProvider
import com.example.jobapplication.databinding.FragmentListJobApplicationBinding
import com.example.jobapplication.ui.view.recyclerView.JobApplicationAdapter
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.Collections

/**
 * A simple [Fragment] subclass.
 * Use the [ListJobApplicationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListJobApplicationFragment : Fragment() {

    private val ARG_PARAM2 = "param2"

    private var param2: Persona? = null

    /*
     * Al igual que las actividades, pueden usar view binding, pero se debe usar diferente.
     */
    private var _binding: FragmentListJobApplicationBinding? = null
    private val binding get() = _binding!!

    /*
     * Instancia del adapter que se sobreescribe cada vez que se hacen cambios en el recycler view de UI.
     */
    private lateinit var adapter: JobApplicationAdapter

    /*
     * Todos los forms a hacer display en el recycler view.
     */
    private val jobApplications = JobFormProvider.instance

    /*
     *  Primer método a ejecutar, setea los parámetros que se pasan por el newInstance.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param2 = it.getSerializable(ARG_PARAM2) as Persona
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListJobApplicationBinding.inflate(inflater, container, false)

        initRecyclerView()
        setSearchBar()
        setRecyclerViewsItemsTouchHelper()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*
     *
     */
    private fun initRecyclerView() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@ListJobApplicationFragment.context)
            adapter = JobApplicationAdapter(jobApplications.getApplications()) { onItemSelected(it) }
            recyclerView.adapter = adapter
            recyclerView.setHasFixedSize(true)
        }
    }

    /*
     *
     */
    private fun setSearchBar() {
        binding.apply {
            applicationSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter.filter(newText)
                    return false
                }
            })
        }
    }

    /*
     *
     */
    private fun setRecyclerViewsItemsTouchHelper() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val fromPosition: Int = viewHolder.adapterPosition
                val toPosition: Int = target.adapterPosition

                Collections.swap(jobApplications.getApplications(), fromPosition, toPosition)

                binding.apply {
                    recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)
                }

                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.LEFT) {
                    jobApplications.deleteJobApplication(position)
                    binding.apply {

                        recyclerView.adapter?.notifyItemRemoved(position)

                        adapter = JobApplicationAdapter(jobApplications.getApplications()) { onItemSelected(it) }

                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {

                RecyclerViewSwipeDecorator.Builder(
                    this@ListJobApplicationFragment.context,
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addSwipeLeftBackgroundColor(
                        ContextCompat.getColor(
                            this@ListJobApplicationFragment.context!!,
                            R.color.red
                        )
                    )
                    .addSwipeLeftActionIcon(R.drawable.delete_icon)
                    .create()
                    .decorate()

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        binding.apply {
            itemTouchHelper.attachToRecyclerView(recyclerView)
        }
    }

    /*
     *
     */
    private fun onItemSelected(jobForm: JobForm) {
        /*
         * TODO implementar lógica de iniciar la actividad de
         */
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
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListJobApplicationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param2: Persona) =
            ListJobApplicationFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM2, param2)
                }
            }
    }
}