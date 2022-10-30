package com.oss.gallery.feature_posts.data.storage

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import com.oss.gallery.di.CoroutinesModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

// TODO save the picture to the photo gallery
class PhotoStorage(
    private val context: Context,
    @CoroutinesModule.IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getPhotos(amount: Int = 10): List<Uri> = withContext(ioDispatcher) {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DATE_ADDED
        )

        val query = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null
        ) ?: return@withContext emptyList()

        val images = mutableListOf<Uri>()
        query.use {
            var readAmount = 0
            val idColumn = query.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (query.moveToNext() && readAmount++ < amount) {
                val id = query.getLong(idColumn)
                images.add(
                    ContentUris.withAppendedId(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        id
                    )
                )
            }
        }

        return@withContext images
    }
}