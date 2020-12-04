package com.charlton.models
import java.io.Serializable
import java.util.*

data class FileFormat(
    var image: String,
    var poses: LinkedList<AnimationRow>
): Serializable