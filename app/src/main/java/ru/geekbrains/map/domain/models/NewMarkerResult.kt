package ru.geekbrains.map.domain.models

import com.yandex.mapkit.geometry.Point

data class NewMarkerResult(
    val result: Boolean,
    val position: Point,
) : DataResult