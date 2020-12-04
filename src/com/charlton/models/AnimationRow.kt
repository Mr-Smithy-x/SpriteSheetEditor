package com.charlton.models

import java.io.Serializable

data class AnimationRow(var pose: String, var set: AnimationSet) : Serializable {
    fun add(bounds: SpriteBounds){
        set.add(bounds)
    }
}