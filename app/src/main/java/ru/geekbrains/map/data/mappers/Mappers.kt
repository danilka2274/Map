package ru.geekbrains.map.data.mappers

import ru.geekbrains.map.data.storage.entity.Marker
import ru.geekbrains.map.domain.models.MarkerDomain


fun markerToDataLayer(target: MarkerDomain): Marker =
    Marker(
        markerId = target.markerId,
        latitude = target.latitude,
        longitude = target.longitude,
        visible = target.visible,
        title = target.title,
        description = target.description
    )