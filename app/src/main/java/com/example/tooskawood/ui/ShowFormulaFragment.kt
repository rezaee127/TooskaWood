package com.example.tooskawood.ui

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tooskawood.R
import com.example.tooskawood.databinding.FragmentShowFormulaBinding
import com.example.tooskawood.viewModel.MainViewModel


class ShowFormulaFragment : Fragment() {
    lateinit var binding: FragmentShowFormulaBinding
    val vModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowFormulaBinding.inflate(inflater, container, false)
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

        vModel.getFormulaListLiveData().observe(requireActivity()) {
            formulaAdapter.submitList(it)
        }
    }


    @SuppressLint("SetTextI18n")
    private fun initView() {

        binding.floatingActionButton.setOnClickListener {
            val bundle = bundleOf("id" to 0, "edit" to false)
            findNavController().navigate(
                R.id.action_showFormulaFragment_to_addFormulaFragment,
                bundle
            )
        }

        vModel.getCountFormulaLiveData().observe(requireActivity()) {
            binding.textViewCountFormula.text = "تعداد فرمولها: $it"
            if (it == 0) {
                binding.buttonSearch.isEnabled = false
                binding.editTextSearch.isEnabled = false
                binding.linearLayout3.visibility = View.GONE
                binding.textViewCountFormula.visibility=View.INVISIBLE
            }
        }

        search()

    }

    private fun search() {

        binding.buttonSearch.setOnClickListener {
            if (binding.editTextSearch.text.isNullOrBlank())
                binding.editTextSearch.error = "یک کد فرمول وارد کنید"
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
        findNavController().navigate(
            R.id.action_showFormulaFragment_to_detailOfFormulaFragment,
            bundle
        )
    }
}