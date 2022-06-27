package kr.pe.ssun.androiddemo.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kr.pe.ssun.androiddemo.data.domain.usecase.GetShopParam
import kr.pe.ssun.androiddemo.data.domain.usecase.GetShopUseCase
import kr.pe.ssun.androiddemo.data.model.ShopItem
import kr.pe.ssun.androiddemo.util.onMain
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val getShopUseCase: GetShopUseCase
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _items = MutableStateFlow<List<ShopItem>>(listOf())
    val items: StateFlow<List<ShopItem>> = _items

    fun getShop(query: String) = onMain {
        _isLoading.emit(true)
        getShopUseCase(GetShopParam(query)).collect { result ->
            _isLoading.emit(false)
            when {
                result.isSuccess -> {
                    _items.emit(result.getOrDefault(listOf()))
                }
                result.isFailure -> {

                }
            }
        }
    }
}