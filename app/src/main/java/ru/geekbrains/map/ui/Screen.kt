package ru.geekbrains.map.ui

interface Screen {
    fun loading(isLoading: Boolean)
    fun showError(throwable: Throwable)
}