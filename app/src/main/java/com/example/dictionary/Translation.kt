package com.example.dictionary

import com.google.gson.annotations.SerializedName

class Translation(
    @field:SerializedName("text") val text: String?
) {
}