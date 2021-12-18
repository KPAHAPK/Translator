package com.example.dictionary.ui.main.imageloader

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import coil.ImageLoader
import coil.request.LoadRequest
import coil.transform.CircleCropTransformation
import com.example.dictionary.R

class CoilImageLoader(private val context: Context) : IImageLoader {
    override fun loadImage(imageView: ImageView, imageLink: String) {
        val request = LoadRequest.Builder(context)
            .data("http:$imageLink")
            .target(
                onStart = {},
                onSuccess = { result ->
                    imageView.setImageDrawable(result)
                },
                onError = {
                    imageView.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_load_error_vector))
                }
            )
            .transformations(
                CircleCropTransformation()
            )
            .build()

        ImageLoader(context).execute(request)
    }
}