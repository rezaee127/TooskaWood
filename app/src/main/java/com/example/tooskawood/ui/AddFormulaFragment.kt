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
    ): View {
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
        listOfEditTextMaterialNames=arrayListOf(binding.editTextMaterialName1,
            binding.editTextMaterialName2,binding.editTextMaterialName3,
            binding.editTextMaterialName4,binding.editTextMaterialName5,
            binding.editTextMaterialName6,binding.editTextMaterialName7,
            binding.editTextMaterialName8,binding.editTextMaterialName9,
            binding.editTextMaterialName10)

        listOfEditTextMaterialValues= arrayListOf(binding.editTextMaterialValue1,
            binding.editTextMaterialValue2,binding.editTextMaterialValue3,
            binding.editTextMaterialValue4,binding.editTextMaterialValue5,
            binding.editTextMaterialValue6,binding.editTextMaterialValue7,
            binding.editTextMaterialValue8,binding.editTextMaterialValue9,
            binding.editTextMaterialValue10)

        val id=requireArguments().getInt("id")
        if(id==0){
            save(id)
        }else{
            edit(id)
        }


    }

    private fun edit(id:Int) {

        vModel.getFormula(id).let {
            for(i in 0 until it.materials.size){
                binding.editTextFormulaCode2.setText(it.code)
                listOfEditTextMaterialNames[i].text = it.materials[i].name
                listOfEditTextMaterialValues[i].text = it.materials[i].value.toString()
            }
        }

        save(id)
    }


    private fun save(id:Int) {
        binding.buttonSave.setOnClickListener {
            when{
                binding.editTextFormulaCode2.text.isNullOrBlank()-> binding.editTextFormulaCode2.error="کد فرمول را وارد کنید"
                id==0 && vModel.searchFormula(binding.editTextFormulaCode2.text.toString())!=0->binding.editTextFormulaCode2.error="این کد قبلا وارد شده است"
                binding.editTextMaterialName1.text.isNullOrBlank()-> binding.editTextMaterialName1.error="یک ماده وارد کنید"
                binding.editTextMaterialValue1.text.isNullOrBlank()-> binding.editTextMaterialValue1.error="مقدار ماده را وارد کنید"

                else ->{
                    for (i in listOfEditTextMaterialNames.indices){
                        if (listOfEditTextMaterialValues[i].text.toString()!=""){
                            listOfMaterials.add(Material(i+1,
                                listOfEditTextMaterialNames[i].text.toString(),
                                listOfEditTextMaterialValues[i].text.toString().toLong()))
                        }
                    }
                    if (id==0)
                        vModel.insert(Formula(id,binding.editTextFormulaCode2.text.toString(),listOfMaterials))
                    else
                        vModel.update(Formula(id,binding.editTextFormulaCode2.text.toString(),listOfMaterials))
                    Toast.makeText(requireActivity(),"فرمول ذخیره شد", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_addFormulaFragment_to_showFormulaFragment)

                }
            }
        }
    }
}