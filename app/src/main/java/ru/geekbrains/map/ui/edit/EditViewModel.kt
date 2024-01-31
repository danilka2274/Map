package ru.geekbrains.map.ui.edit

import ru.geekbrains.map.domain.AppState
import ru.geekbrains.map.domain.models.MarkerDomain
import ru.geekbrains.map.domain.usecases.GetMarkerByIdUseCase
import ru.geekbrains.map.domain.usecases.UpdateMarkerUseCase
import ru.geekbrains.map.ui.edit.base.BaseEditViewModel
import kotlinx.coroutines.launch


class EditViewModel(
    private val updateMarkerUseCase: UpdateMarkerUseCase,
    private val getMarkerByIdUseCase: GetMarkerByIdUseCase,
) : BaseEditViewModel() {

    override fun handleError(throwable: Throwable) {
        getOperationLiveData().postValue(AppState.Error(throwable))
    }

    override fun updateMarker(marker: MarkerDomain) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            getOperationLiveData()
                .postValue(
                    updateMarkerUseCase.execute(marker)
                )
        }

    override fun getMarker(markerId: Int) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            getOperationLiveData()
                .postValue(getMarkerByIdUseCase.execute(markerId))
        }
}