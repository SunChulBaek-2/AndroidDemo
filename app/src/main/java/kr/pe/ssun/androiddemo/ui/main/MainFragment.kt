package kr.pe.ssun.androiddemo.ui.main

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.pe.ssun.androiddemo.databinding.FragmentMainBinding

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val navController = findNavController()
        binding = FragmentMainBinding.inflate(inflater, container, false).apply {
            et.setOnEditorActionListener { tv, actionId, keyEvent ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.getShop(et.text.toString())
                    et.text = null
                    true
                }
                false
            }
            et.setOnKeyListener { view, keyCode, keyEvent ->
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    viewModel.getShop(et.text.toString())
                    et.text = null
                    true
                }
                false
            }
            btn.setOnClickListener {
                viewModel.getShop(et.text.toString())
                et.text = null
            }
            rv.adapter = ShopAdapter { url ->
                val action = MainFragmentDirections.actionSearchToWebview(url)
                navController.navigate(directions = action)
            }
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