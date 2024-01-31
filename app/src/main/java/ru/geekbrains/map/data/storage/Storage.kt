package ru.geekbrains.map.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.geekbrains.map.data.storage.entity.Marker


@Database(
    exportSchema = true,
    entities = [Marker::class],
    version = 2
)
abstract class Storage : RoomDatabase() {
    abstract fun storageDao(): StorageDao
}