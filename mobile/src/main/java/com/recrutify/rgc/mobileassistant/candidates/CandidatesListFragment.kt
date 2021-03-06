package com.recrutify.rgc.mobileassistant.candidates

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
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
import com.recrutify.rgc.mobileassistant.databinding.FragmentCandidatesListBinding
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
 * [CandidatesListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CandidatesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CandidatesListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var labelsAdapter: LabelsAdapter

    lateinit var candidatesListViewModel: CandidatesListViewModel

    lateinit var dataBindingComponent: DataBindingComponent

    var binding by autoCleared<FragmentCandidatesListBinding>()

    var adapter by autoCleared<CandidatesListAdapter>()
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

        binding = DataBindingUtil.inflate<FragmentCandidatesListBinding>(
                inflater,
                R.layout.fragment_candidates_list,
                container,
                false,
                dataBindingComponent
        )

        return binding.root
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        candidatesListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CandidatesListViewModel::class.java)

        candidatesListViewModel.results.observe(this, Observer {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        Log.d("CANDIDATE_L", "SUCCESS")
                        adapter.submitList(it.data)
                        binding.executePendingBindings()
                    }
                    Status.LOADING -> {
                        Log.d("CANDIDATE_L", "LOADING")
                    }
                    Status.ERROR -> {
                        Log.d("CANDIDATE_L", "ERROR ${it.message}")
                    }
                }

            }
        })

        val _adapter = CandidatesListAdapter (
                dataBindingComponent = dataBindingComponent,
                appExecutors = appExecutors) { project ->

            Log.d("PROJECT_L", "on: ${project.id}")
        }

        binding.candidateList.adapter = _adapter

        adapter = _adapter

        candidatesListViewModel.setQuery("-")
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
         * @return A new instance of fragment CandidatesListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                CandidatesListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
