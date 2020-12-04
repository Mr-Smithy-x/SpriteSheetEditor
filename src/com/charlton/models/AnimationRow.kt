package com.charlton.models

data class AnimationRow(var pose: String, var set: AnimationSet) {
    fun add(bounds: SpriteBounds){
        set.add(bounds)
    }
}