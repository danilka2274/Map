package ru.geekbrains.map.ui.markers

import ru.geekbrains.map.domain.AppState
import ru.geekbrains.map.domain.usecases.GetMarkersUseCase
import ru.geekbrains.map.domain.usecases.RemoveMarkerUseCase
import ru.geekbrains.map.ui.markers.base.BaseMarkersViewModel
import kotlinx.coroutines.launch

class MarkersViewModel(
    private val getMarkersUseCase: GetMarkersUseCase,
    private val removeMarkerUseCase: RemoveMarkerUseCase,
) : BaseMarkersViewModel() {

    override fun getMarkers() =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            getOperationLiveData()
                .postValue(getMarkersUseCase.execute())
        }

    override fun removeMarker(markerId: Int) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)

            getOperationLiveData()
                .postValue(removeMarkerUseCase.execute(markerId))

            getOperationLiveData()
                .postValue(getMarkersUseCase.execute())
        }

    override fun handleError(throwable: Throwable) {
        getOperationLiveData().postValue(AppState.Error(throwable))
    }
}