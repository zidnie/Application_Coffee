package com.appcoffe.app.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val id: String = "",
    val nama: String = "",
    val logo_url: String = "",
    val kategori: String = "",
    val deskripsi: String = ""
) : Parcelable
