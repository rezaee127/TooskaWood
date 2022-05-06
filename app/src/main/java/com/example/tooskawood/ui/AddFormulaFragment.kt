package com.example.tooskawood.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
    var arrayOfEditTextRows11to15= arrayListOf<EditText>()
    var arrayOfTextViewRows11to15= arrayListOf<TextView>()
    var arrayOfEditTextRows16to20= arrayListOf<EditText>()
    var arrayOfTextViewRows16to20= arrayListOf<TextView>()
    var j=0
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
        initArrays()
        initView()
    }


    private fun initView() {
        goneRows11to15()
        goneRows16to20()

        val id=requireArguments().getInt("id")
        addRow()

        if(id==0){
            save(id)
        }else{
            edit(id)
        }
    }

    private fun addRow() {
        binding.buttonAdd5Rows.setOnClickListener {
            if (j==0){
                visibleRows11to15()
                j=1
            }else if(j==1){
                visibleRows16to20()
            }
        }
    }


    private fun edit(id:Int) {

        vModel.getFormula(id).let {
            if(it.materials.size>15){
                visibleRows11to15()
                visibleRows16to20()
            }else if (it.materials.size>10){
                visibleRows11to15()
                j=1
            }
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




    private fun initArrays() {
        arrayOfEditTextRows11to15= arrayListOf(
            binding.editTextMaterialName11,binding.editTextMaterialName12,
            binding.editTextMaterialName13,binding.editTextMaterialName14,
            binding.editTextMaterialName15,binding.editTextMaterialValue11,
            binding.editTextMaterialValue12,binding.editTextMaterialValue13,
            binding.editTextMaterialValue14,binding.editTextMaterialValue15)

        arrayOfTextViewRows11to15=arrayListOf(binding.textView11,binding.textView12,
            binding.textView13,binding.textView14,binding.textView15)

        arrayOfEditTextRows16to20= arrayListOf(
            binding.editTextMaterialName16,binding.editTextMaterialName17,
            binding.editTextMaterialName18,binding.editTextMaterialName19,
            binding.editTextMaterialName20,binding.editTextMaterialValue16,
            binding.editTextMaterialValue17,binding.editTextMaterialValue18,
            binding.editTextMaterialValue19,binding.editTextMaterialValue20)

        arrayOfTextViewRows16to20=arrayListOf(binding.textView16,binding.textView17,
            binding.textView18,binding.textView19,binding.textView20)


        listOfEditTextMaterialNames=arrayListOf(binding.editTextMaterialName1,
            binding.editTextMaterialName2,binding.editTextMaterialName3,
            binding.editTextMaterialName4,binding.editTextMaterialName5,
            binding.editTextMaterialName6,binding.editTextMaterialName7,
            binding.editTextMaterialName8,binding.editTextMaterialName9,
            binding.editTextMaterialName10,binding.editTextMaterialName11,
            binding.editTextMaterialName12,binding.editTextMaterialName13,
            binding.editTextMaterialName14,binding.editTextMaterialName15,
            binding.editTextMaterialName16,binding.editTextMaterialName17,
            binding.editTextMaterialName18,binding.editTextMaterialName19,
            binding.editTextMaterialName20)

        listOfEditTextMaterialValues= arrayListOf(binding.editTextMaterialValue1,
            binding.editTextMaterialValue2,binding.editTextMaterialValue3,
            binding.editTextMaterialValue4,binding.editTextMaterialValue5,
            binding.editTextMaterialValue6,binding.editTextMaterialValue7,
            binding.editTextMaterialValue8,binding.editTextMaterialValue9,
            binding.editTextMaterialValue10,binding.editTextMaterialValue11,
            binding.editTextMaterialValue12,binding.editTextMaterialValue13,
            binding.editTextMaterialValue14,binding.editTextMaterialValue15,
            binding.editTextMaterialValue16,binding.editTextMaterialValue17,
            binding.editTextMaterialValue18,binding.editTextMaterialValue19,
            binding.editTextMaterialValue20)

    }




    private fun goneRows11to15() {
        for (i in arrayOfTextViewRows11to15.indices)
            arrayOfTextViewRows11to15[i].visibility=View.GONE
        for (i in arrayOfEditTextRows11to15.indices)
            arrayOfEditTextRows11to15[i].visibility=View.GONE
    }

    private fun goneRows16to20() {
        for (i in arrayOfTextViewRows16to20.indices)
            arrayOfTextViewRows16to20[i].visibility=View.GONE
        for (i in arrayOfEditTextRows16to20.indices)
            arrayOfEditTextRows16to20[i].visibility=View.GONE
    }

    private fun visibleRows11to15() {
        for (i in arrayOfTextViewRows11to15.indices)
            arrayOfTextViewRows11to15[i].visibility=View.VISIBLE
        for (i in arrayOfEditTextRows11to15.indices)
            arrayOfEditTextRows11to15[i].visibility=View.VISIBLE
    }

    private fun visibleRows16to20() {
        for (i in arrayOfTextViewRows16to20.indices)
            arrayOfTextViewRows16to20[i].visibility=View.VISIBLE
        for (i in arrayOfEditTextRows16to20.indices)
            arrayOfEditTextRows16to20[i].visibility=View.VISIBLE
        binding.buttonAdd5Rows.isEnabled=false
    }


}