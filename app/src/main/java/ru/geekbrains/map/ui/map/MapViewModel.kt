package ru.geekbrains.map.ui.map

import ru.geekbrains.map.domain.AppState
import ru.geekbrains.map.domain.models.MarkerDomain
import ru.geekbrains.map.domain.usecases.AddMarkerUseCase
import ru.geekbrains.map.domain.usecases.GetMarkersUseCase
import ru.geekbrains.map.ui.map.base.BaseMapViewModel
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.launch



class MapViewModel(
    private val addMarkerUseCase: AddMarkerUseCase,
    private val getMarkersUseCase: GetMarkersUseCase,
) : BaseMapViewModel() {

    override fun handleError(throwable: Throwable) {
        getOperationLiveData().postValue(AppState.Error(throwable))
    }

    override fun saveMarker(position: Point) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            addMarker(position)
        }

    private suspend fun addMarker(position: Point) {
        getOperationLiveData()
            .postValue(
                addMarkerUseCase.execute(
                    MarkerDomain(
                        latitude = position.latitude,
                        longitude = position.longitude
                    )
                )
            )
    }

    override fun getMarkers() =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            getOperationLiveData()
                .postValue(getMarkersUseCase.execute())
        }
}