package com.example.unsplash.module

import com.example.unsplash.ui.collection.CollectionViewModel
import com.example.unsplash.ui.detail.ImageDetailViewModel
import com.example.unsplash.ui.favorite.FavoriteViewModel
import com.example.unsplash.ui.home.HomeViewModel
import com.example.unsplash.ui.photo_collection.PhotoCollectionsViewModel
import com.example.unsplash.ui.search.search_collection.SearchCollectionViewModel
import com.example.unsplash.ui.search.search_photo.SearchPhotoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CollectionViewModel(get()) }
    viewModel { PhotoCollectionsViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { ImageDetailViewModel(get()) }
    viewModel { SearchPhotoViewModel(get()) }
    viewModel { SearchCollectionViewModel(get()) }
}
