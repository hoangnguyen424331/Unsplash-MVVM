package com.example.unsplash.data.source

import com.example.unsplash.data.model.Collection
import com.example.unsplash.data.model.PhotoCollection
import com.example.unsplash.data.model.PhotoDetail
import com.example.unsplash.data.model.Topic
import com.example.unsplash.data.source.local.sqlite.entity.ImageLocal
import com.example.unsplash.data.model.SearchCollection
import com.example.unsplash.data.model.SearchPhoto

class PhotoDataSource {

    interface Remote {

        suspend fun getCollections(page: Int): MutableList<Collection>

        suspend fun getPhotosCollection(id: String, page: Int): MutableList<PhotoCollection>

        suspend fun getTopics(page: Int): MutableList<Topic>

        suspend fun getRandomPhotos(): MutableList<PhotoCollection>

        suspend fun getPhotoDetail(id: String?): PhotoDetail

        suspend fun getSearchCollection(keyword: String, page: Int): SearchCollection

        suspend fun getSearchPhoto(keyword: String, page: Int): SearchPhoto

        suspend fun getPhotosTopic(id: String, page: Int): MutableList<PhotoCollection>
    }

    interface Local {

        suspend fun getImages(): List<ImageLocal>

        suspend fun insertImage(imageLocal: ImageLocal)

        suspend fun deleteImage(imageLocal: ImageLocal)

        suspend fun getImage(imageId: String?): ImageLocal
    }
}
