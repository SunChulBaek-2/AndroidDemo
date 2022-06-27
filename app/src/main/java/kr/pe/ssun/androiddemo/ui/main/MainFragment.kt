package kr.pe.ssun.androiddemo.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.pe.ssun.androiddemo.databinding.FragmentMainBinding

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false).apply {
            btn.setOnClickListener {
                viewModel.getShop(et.text.toString())
                et.text = null
            }
            rv.adapter = ShopAdapter()
            rv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        }
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        lifecycleScope.launch {
            viewModel.items.collect { items ->
                (binding.rv.adapter as ShopAdapter).submitList(items)
            }
        }
        lifecycleScope.launch {
            viewModel.isLoading.collect {
                binding.progress.visibility = if (it) View.VISIBLE else View.GONE
            }
        }
    }
}