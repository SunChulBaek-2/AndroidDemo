package kr.pe.ssun.androiddemo.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kr.pe.ssun.androiddemo.data.ShopRepository
import kr.pe.ssun.androiddemo.domain.FlowUseCase
import kr.pe.ssun.androiddemo.data.model.ShopItem
import kr.pe.ssun.androiddemo.di.IoDispatcher
import javax.inject.Inject

data class GetShopParam(
    val query: String
)

class GetShopUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: ShopRepository
) : FlowUseCase<GetShopParam, List<ShopItem>>(dispatcher) {

    override fun execute(parameters: GetShopParam): Flow<Result<List<ShopItem>>> = flow {
        try {
            emit(Result.success(repository.getShop(parameters.query, 100, 1, "sim").items))
        } catch (e: Exception) {
            emit(Result.failure(Throwable(e)))
        }
    }
}