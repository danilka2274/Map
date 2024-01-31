package ru.geekbrains.map.ui.markers.base

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.geekbrains.map.R
import ru.geekbrains.map.databinding.FragmentMarkersBinding
import ru.geekbrains.map.ui.markers.MarkersViewModel


abstract class BaseMarkersFragment : Fragment(R.layout.fragment_markers) {
    protected val viewModel: MarkersViewModel by viewModel()
    protected val viewBinding: FragmentMarkersBinding by viewBinding()
}