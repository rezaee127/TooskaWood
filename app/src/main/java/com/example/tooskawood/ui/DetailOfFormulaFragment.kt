package com.example.tooskawood.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tooskawood.R
import com.example.tooskawood.databinding.FragmentDetailOfFormulaBinding
import com.example.tooskawood.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar

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
        //return inflater.inflate(R.layout.fragment_detail_of_formula, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        val id=requireArguments().getInt("id")
        val sumValue = vModel.convertValue(id)
        var numberForConvert= 0L
        binding.buttonConvert.setOnClickListener {
            if (binding.editTextNumberForConvert.text.isNullOrBlank()){
                binding.editTextNumberForConvert.error="یک عدد وارد کنید"
            }else{
                numberForConvert=binding.editTextNumberForConvert.text.toString().toLong()
                binding.MaterialRecyclerView.adapter=MaterialAdapter(vModel.getFormula(id).materials,sumValue,numberForConvert)
            }
        }

        binding.textViewFormulaCodeInDetailFragment.text=vModel.getFormula(id).code
        binding.MaterialRecyclerView.adapter=MaterialAdapter(vModel.getFormula(id).materials,sumValue,numberForConvert)



        binding.buttonEdit.setOnClickListener {
            edit(id)
        }

        binding.buttonDelete.setOnClickListener {
            delete(id)
        }

    }



    private fun delete(id:Int){
        val snkbr = Snackbar.make(binding.detailOfFormulaFragment, "Do you want to delete the formula?",
            Snackbar.LENGTH_LONG)

        snkbr.duration = 9000

        snkbr.setAction("yes",  View.OnClickListener {
            vModel.deleteById(id)
            Toast.makeText(requireContext(),"حذف فرمول انجام شد", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_detailOfFormulaFragment_to_showFormulaFragment)
            snkbr.dismiss()
        })

            .setTextColor(ContextCompat.getColor(requireContext(),(R.color.whiteBack2)))
            .setActionTextColor(ContextCompat.getColor(requireContext(),(R.color.white)))
            .setBackgroundTint(ContextCompat.getColor(requireContext(),(R.color.gray)))
            .show()
    }


    private fun edit(id:Int){
        val bundle = bundleOf("id" to id,"edit" to true)
        findNavController().navigate(R.id.action_detailOfFormulaFragment_to_addFormulaFragment, bundle)
    }


}