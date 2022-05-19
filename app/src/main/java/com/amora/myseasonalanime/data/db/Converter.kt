package com.amora.myseasonalanime.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.amora.myseasonalanime.data.source.remote.response.images.Images
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@ProvidedTypeConverter
class Converter {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val adapter by lazy { moshi.adapter(Images::class.java).lenient() }

    @TypeConverter
    fun toImages(value: String): Images? = adapter.fromJson(value)

    @TypeConverter
    fun fromImages(value: Images): String? = adapter.toJson(value)
}