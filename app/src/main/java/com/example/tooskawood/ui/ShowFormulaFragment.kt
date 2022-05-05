package com.example.tooskawood.ui

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tooskawood.Formula
import com.example.tooskawood.Material
import com.example.tooskawood.R
import com.example.tooskawood.databinding.FragmentShowFormulaBinding
import com.example.tooskawood.viewModel.MainViewModel


class ShowFormulaFragment : Fragment() {
    lateinit var binding : FragmentShowFormulaBinding
    val vModel: MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowFormulaBinding.inflate (inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_show_formula, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setRecyclerView()

    }

    private fun setRecyclerView() {
        val formulaAdapter = FormulaAdapter({ goToDetailFragment(it) })
        binding.formulaRecyclerView.adapter = formulaAdapter
        val formula1= Formula(1,"formula1",listOf(Material(1,"سرب", 120),Material(2,"نیکل", 150)))
        val formula2= Formula(2,"formula2",listOf(Material(1,"قلع", 140),Material(2,"آهن", 220)))
        vModel.insert(formula1)
        vModel.insert(formula2)

        formulaAdapter.submitList(vModel.getFormulaList())
    }


    @SuppressLint("SetTextI18n")
    private fun initView() {

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_showFormulaFragment_to_addFormulaFragment)
        }

        vModel.getCountFormulaLiveData().observe(requireActivity()) {
            if (it == 0) {
                binding.buttonSearch.isEnabled = false
                binding.editTextSearch.isEnabled = false
            }
        }

        search()

    }

    private fun search() {

        binding.buttonSearch.setOnClickListener {
            if (binding.editTextSearch.text.isNullOrBlank())
                binding.editTextSearch.error = "یک کد وارد کنید"
            else if (vModel.searchFormula(binding.editTextSearch.text.toString()) == 0) {
                val dialog = AlertDialog.Builder(requireContext())
                dialog.setMessage("The desired code does not exist")
                    .setPositiveButton("OK",
                        DialogInterface.OnClickListener { dialog, id ->
                        })
                dialog.create().show()
            } else if (vModel.searchFormula(binding.editTextSearch.text.toString()) != 0) {
                val id = vModel.searchFormula(binding.editTextSearch.text.toString())
                goToDetailFragment(id)
            }
        }
    }

    private fun goToDetailFragment(id: Int) {
        val bundle = bundleOf("id" to id)
       // findNavController().navigate(R.id.action_showFormulaFragment_to_detailFragment, bundle)
    }
}