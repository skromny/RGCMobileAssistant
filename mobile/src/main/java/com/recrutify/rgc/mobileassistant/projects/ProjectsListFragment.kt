package com.recrutify.rgc.mobileassistant.projects

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.recrutify.rgc.mobileassistant.AppExecutors
import com.recrutify.rgc.mobileassistant.Model.Status

import com.recrutify.rgc.mobileassistant.R
import com.recrutify.rgc.mobileassistant.common.FragmentDataBindingComponent
import com.recrutify.rgc.mobileassistant.common.LabelsAdapter
import com.recrutify.rgc.mobileassistant.common.OnFragmentInteractionListener
import com.recrutify.rgc.mobileassistant.common.autoCleared
import com.recrutify.rgc.mobileassistant.databinding.FragmentProjectsListBinding
import com.recrutify.rgc.mobileassistant.injection.Injectable
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProjectsListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ProjectsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ProjectsListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var labelsAdapter: LabelsAdapter

    lateinit var projectListViewModel: ProjectsListViewModel

    lateinit var dataBindingComponent: DataBindingComponent

    var binding by autoCleared<FragmentProjectsListBinding>()

    var adapter by autoCleared<ProjectsListAdapter>()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        dataBindingComponent = FragmentDataBindingComponent(this, labelsAdapter)

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_projects_list, container, false)
        binding = DataBindingUtil.inflate<FragmentProjectsListBinding>(
                inflater,
                R.layout.fragment_projects_list,
                container,
                false,
                dataBindingComponent
        )

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        projectListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ProjectsListViewModel::class.java)

        projectListViewModel.results.observe(this, Observer {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        Log.d("PROJECT_L", "SUCCESS")
                        adapter.submitList(it.data)
                        binding.executePendingBindings()
                    }
                    Status.LOADING -> {
                        Log.d("PROJECT_L", "LOADING")
                    }
                    Status.ERROR -> {
                        Log.d("PROJECT_L", "ERROR ${it.message}")
                    }
                }

            }
        })

        val _adapter = ProjectsListAdapter(
                dataBindingComponent = dataBindingComponent,
                appExecutors = appExecutors) { project ->

                    val intent = Intent(context, ProjectDetailActivity::class.java)
                    startActivity(intent);
                    Log.d("PROJECT_L", "on: ${project.id}")
                }

        binding.projectList.adapter = _adapter

        adapter = _adapter


        projectListViewModel.setQuery("-")
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProjectsListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ProjectsListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }







    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */

    //!!!! Przeniesony do common

//    interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        fun onFragmentInteraction(uri: Uri)
//    }


}
