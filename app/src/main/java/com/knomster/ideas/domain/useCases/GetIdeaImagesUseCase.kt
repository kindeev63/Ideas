package com.knomster.ideas.domain.useCases

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import java.io.File
import kotlin.collections.set

class GetIdeaImagesUseCase(val path: String) {
    fun get(): Map<Int, ImageBitmap> {
        val images = mutableMapOf<Int, ImageBitmap>()
        val dir = File(path)
        dir.listFiles()?.map { file ->
            file.name.split(".")[0].toIntOrNull()?.let { key ->
                images[key] = BitmapFactory.decodeFile(file.path).asImageBitmap()
            }
        }
        return images
    }
}