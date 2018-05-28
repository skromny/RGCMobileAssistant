package com.recrutify.rgc.mobileassistant.projects

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
import com.recrutify.rgc.mobileassistant.databinding.FragmentGeneralProjectDetailBinding
import com.recrutify.rgc.mobileassistant.injection.Injectable
import kotlinx.android.synthetic.main.fragment_general_project_detail.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PROJECT_ID = "projectId"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [GeneralProjectDetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [GeneralProjectDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class GeneralProjectDetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    @Inject
    lateinit var labelsAdapter: LabelsAdapter

    lateinit var projectViewModel: GeneralProjectDetailViewModel

    lateinit var dataBindingComponent: DataBindingComponent

    var binding by autoCleared<FragmentGeneralProjectDetailBinding>()


    // TODO: Rename and change types of parameters
    private var projectId: Int? = null

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("ProjectDetail", "onCreate")
        super.onCreate(savedInstanceState)
        arguments?.let {
            projectId = it.getInt(ARG_PROJECT_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.d("ProjectDetail", "onCreateView")
        dataBindingComponent = FragmentDataBindingComponent(this, labelsAdapter)

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_projects_list, container, false)
        binding = DataBindingUtil.inflate<FragmentGeneralProjectDetailBinding>(
                inflater,
                R.layout.fragment_general_project_detail,
                container,
                false,
                dataBindingComponent
        )

//        binding.project = Project(1, 0, 1, "companyname", "", null, 0,0,0,
//                "2017-08-10T14:37:59", "PLN", null, null, "mock", null, null, null, 7, null,null,
//                false, false, listOf(), null, null, "project-name", null, null, "123456009", 5,
//                "Nowak", null, null, null, 4, null,null,null)

        return binding.root
        //return inflater.inflate(R.layout.fragment_general_project_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("ProjectDetail", "onActivityCreated")
        projectViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(GeneralProjectDetailViewModel::class.java)

        projectViewModel.results.observe(this, Observer {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        Log.d("PROJECT_L", "SUCCESS")
                        Log.d("PROJECT_L", it.data?.name)

                        binding.project = it.data
                        //binding.lProjectGenerals?.project = it.data
//                        binding.notifyPropertyChanged(R.id.ic_status)
//                        binding.notifyPropertyChanged(R.id.tv_project_status)
//                        binding.notifyPropertyChanged(R.id.tv_project_name)
//                        binding.notifyPropertyChanged(R.id.tv_company_name)
//                        binding.invalidateAll()
//                        binding.project?.name = it.data?.name!!
//                        binding.project?.companyName = it.data?.companyName
                        //binding.invalidateAll()
                        //binding.notifyChange()
                        wv_project_desc.loadData(it.data?.description, "text/html", "UTF8")
                        //adapter.submitList(it.data)
                        //binding.executePendingBindings()
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

        projectViewModel.setProjectId(projectId!!)
    }
    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        Log.d("ProjectDetail", "onAttach")
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        Log.d("ProjectDetail", "onDetach")
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
         * @return A new instance of fragment GeneralProjectDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(projectId: Int) =
                GeneralProjectDetailFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_PROJECT_ID, projectId)
                    }
                }
    }
}
