package com.example.dictionary.ui.main

import android.content.Context
import android.widget.ImageView
import com.example.dictionary.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class PicassoImageLoader(private val context: Context): IImageLoader {
    override fun loadImage(imageView: ImageView, imageLink: String) {
        Picasso.get().load("http:$imageLink")
            .placeholder(R.drawable.ic_no_photo_vector)
            .fit()
            .centerCrop()
            .into(imageView, object : Callback{
                override fun onSuccess() {
                    if (context is DescriptionActivity){
                        context.stopRefreshAnimationIfNeeded()
                    }
                }

                override fun onError(e: Exception?) {
                    if (context is DescriptionActivity){
                        context.stopRefreshAnimationIfNeeded()
                    }
                    imageView.setImageResource(R.drawable.ic_load_error_vector)
                }
            })
    }
}