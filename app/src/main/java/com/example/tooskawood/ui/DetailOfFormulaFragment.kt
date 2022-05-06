package com.example.tooskawood.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tooskawood.R
import com.example.tooskawood.databinding.FragmentDetailOfFormulaBinding
import com.example.tooskawood.viewModel.MainViewModel

class DetailOfFormulaFragment : Fragment() {
    lateinit var binding:FragmentDetailOfFormulaBinding
    val vModel:MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailOfFormulaBinding.inflate (inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detial_of_formula, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        var id=requireArguments().getInt("id")
        binding.textViewFormulaCodeInDetailFragment.text=vModel.getFormula(id).code
        binding.MaterialRecyclerView.adapter=MaterialAdapter(vModel.getFormula(id).materials,id,{edit(id)})

        binding.buttonEdit.setOnClickListener {
            edit(id)
        }
    }

    private fun edit(id:Int){
        val bundle = bundleOf("id" to id,"edit" to true)
        findNavController().navigate(R.id.action_detailOfFormulaFragment_to_addFormulaFragment, bundle)
    }


}