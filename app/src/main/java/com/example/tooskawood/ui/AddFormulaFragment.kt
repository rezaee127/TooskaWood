package com.example.tooskawood.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tooskawood.Formula
import com.example.tooskawood.Material
import com.example.tooskawood.R
import com.example.tooskawood.databinding.FragmentAddFormulaBinding
import com.example.tooskawood.viewModel.MainViewModel

class AddFormulaFragment : Fragment() {
    lateinit var binding : FragmentAddFormulaBinding
    private val vModel: MainViewModel by activityViewModels()
    var listOfEditTextMaterialNames=ArrayList<TextView>()
    var listOfEditTextMaterialValues=ArrayList<TextView>()
    val listOfMaterials= arrayListOf<Material>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddFormulaBinding.inflate (inflater, container, false)
        return binding.root

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_formula, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        listOfEditTextMaterialNames=arrayListOf(binding.editTextTextMaterialName1,
            binding.editTextTextMaterialName2,binding.editTextTextMaterialName3,
            binding.editTextTextMaterialName4,binding.editTextTextMaterialName5,
            binding.editTextTextMaterialName6,binding.editTextTextMaterialName7,
            binding.editTextTextMaterialName8,binding.editTextTextMaterialName9,
            binding.editTextTextMaterialName10)

        listOfEditTextMaterialValues= arrayListOf(binding.editTextMaterialValue1,
            binding.editTextMaterialValue2,binding.editTextMaterialValue3,
            binding.editTextMaterialValue4,binding.editTextMaterialValue5,
            binding.editTextMaterialValue6,binding.editTextMaterialValue7,
            binding.editTextMaterialValue8,binding.editTextMaterialValue9,
            binding.editTextMaterialValue10)

        binding.buttonSave.setOnClickListener {
            when{
                binding.editTextTextFormulaCode.text.isNullOrBlank()-> binding.editTextTextFormulaCode.error="کد فرمول را وارد کنید"
                vModel.searchFormula(binding.editTextTextFormulaCode.text.toString())!=0->binding.editTextTextFormulaCode.error="این کد قبلا وارد شده است"
                binding.editTextTextMaterialName1.text.isNullOrBlank()-> binding.editTextTextMaterialName1.error="یک ماده وارد کنید"
                binding.editTextMaterialValue1.text.isNullOrBlank()-> binding.editTextMaterialValue1.error="مقدار ماده را وارد کنید"

                else ->{
                    for (i in listOfEditTextMaterialNames.indices){
                        if (listOfEditTextMaterialNames[i].text!=""){
                            listOfMaterials.add(Material(i+1,
                                listOfEditTextMaterialNames[i].text.toString(),
                                listOfEditTextMaterialValues[i].text.toString().toInt()))
                        }
                    }

                    vModel.insert(
                        Formula(0,binding.editTextTextFormulaCode.text.toString(),listOfMaterials)
                    )
                    Toast.makeText(requireActivity(),"فرمول ذخیره شد", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_addFormulaFragment_to_showFormulaFragment)

                }
            }
        }

    }
}