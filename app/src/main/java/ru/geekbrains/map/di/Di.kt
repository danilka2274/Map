package ru.geekbrains.map.di

import androidx.room.Room
import ru.geekbrains.map.data.repository.CacheRepositoryImpl
import ru.geekbrains.map.data.repository.datasource.CacheDataSource
import ru.geekbrains.map.data.repository.datasource.CacheDataSourceImpl
import ru.geekbrains.map.data.storage.Storage
import ru.geekbrains.map.domain.repository.CacheRepository
import ru.geekbrains.map.domain.usecases.*
import ru.geekbrains.map.ui.edit.EditViewModel
import ru.geekbrains.map.ui.map.MapViewModel
import ru.geekbrains.map.ui.markers.MarkersViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object Di {

    private const val PERSISTED = "Persisted"
    private const val IN_MEMORY = "InMemory"
    private const val DB_NAME = "MapDataBase"

    fun viewModelModule() = module {
        viewModel() {
            MapViewModel(
                addMarkerUseCase = get(),
                getMarkersUseCase = get()
            )
        }

        viewModel() {
            MarkersViewModel(
                getMarkersUseCase = get(),
                removeMarkerUseCase = get()
            )
        }

        viewModel() {
            EditViewModel(
                updateMarkerUseCase = get(),
                getMarkerByIdUseCase = get()
            )
        }
    }

    fun useCasesModule() = module {
        factory<AddMarkerUseCase> {
            AddMarkerUseCase(repository = get())
        }

        factory<GetMarkersUseCase> {
            GetMarkersUseCase(repository = get())
        }

        factory<GetMarkerByIdUseCase> {
            GetMarkerByIdUseCase(repository = get())
        }

        factory<RemoveMarkerUseCase> {
            RemoveMarkerUseCase(repository = get())
        }

        factory<UpdateMarkerUseCase> {
            UpdateMarkerUseCase(repository = get())
        }
    }

    fun repositoryModule() = module {
        single<CacheRepository>() {
            CacheRepositoryImpl(dataSource = get())
        }

        single<CacheDataSource> {
            CacheDataSourceImpl(storage = get(qualifier = named(PERSISTED)))
        }
    }

    fun storageModule() = module {
        single<Storage>(qualifier = named(PERSISTED)) {
            Room.databaseBuilder(androidContext(), Storage::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

        single<Storage>(qualifier = named(IN_MEMORY)) {
            Room.inMemoryDatabaseBuilder(androidContext(), Storage::class.java)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}