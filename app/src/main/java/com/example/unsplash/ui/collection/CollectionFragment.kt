package com.example.unsplash.ui.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.unsplash.R
import com.example.unsplash.databinding.FragmentCollectionBinding
import com.example.unsplash.ui.collection.adapter.CollectionAdapter
import org.koin.android.ext.android.inject
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
import com.example.unsplash.ui.photo_collection.PhotoCollectionFragment
import com.example.unsplash.utils.Status

class CollectionFragment : Fragment() {

    private lateinit var binding: FragmentCollectionBinding
    private val collectionViewModel: CollectionViewModel by inject()
    private val collectionAdapter by lazy {
        CollectionAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding()
        handleEvent()
        registerObserver()
    }

    private fun binding() {
        binding.apply {
            lifecycleOwner = this@CollectionFragment
            viewModel = collectionViewModel
            (recyclerViewCollection.layoutManager as StaggeredGridLayoutManager).gapStrategy =
                GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            adapter = collectionAdapter
        }
    }

    private fun handleEvent() {
        collectionAdapter.setOnclickItem {
            findNavController().navigate(
                R.id.photoCollectionFragment,
                bundleOf(PhotoCollectionFragment.BUNDLE_COLLECTION to it)
            )
        }
    }

    private fun registerObserver() {
        collectionViewModel.resource.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    collectionAdapter.submitList(it.data?.value?.toMutableList())
                    binding.swipeRefresh.isRefreshing = false
                }
                Status.ERROR -> {
                    binding.swipeRefresh.isRefreshing = false
                }
                Status.LOADING -> {
                }
            }
        })
    }

    companion object {
        const val BUNDLE_COLLECTION = "BUNDLE_COLLECTION"

        fun newInstance() = CollectionFragment()
    }
}
