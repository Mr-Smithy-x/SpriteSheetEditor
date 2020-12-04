package com.charlton.extensions

import com.charlton.helpers.SpriteCellValueFactory
import com.charlton.models.AnimationRow
import com.charlton.models.AnimationSet
import com.charlton.models.FileFormat
import com.charlton.views.SpriteCanvasSelectionView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.cell.PropertyValueFactory
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileNotFoundException

fun TableView<AnimationRow>.init(
    spriteCanvasSelectionView: SpriteCanvasSelectionView
): List<TableColumn<AnimationRow, Any>> {
    return init(spriteCanvasSelectionView.image)
}

fun TableView<AnimationRow>.init(
    image: BufferedImage
): List<TableColumn<AnimationRow, Any>> {
    val map = ArrayList<TableColumn<AnimationRow, Any>>()
    for (i in 0..16) {
        if (i == 0) {
            val element = TableColumn<AnimationRow, Any>("Pose")
            element.cellValueFactory = PropertyValueFactory("pose")
            map.add(element)
        } else {
            val element = TableColumn<AnimationRow, Any>("F:$i")
            element.cellValueFactory = SpriteCellValueFactory(i - 1, image)
            map.add(element)
        }
    }
    columns.clear()
    columns.addAll(map)
    return map
}

fun TableView<AnimationRow>.load(spriteCanvasSelectionView: SpriteCanvasSelectionView, file: File) {
    val format = Gson().fromJson(file.readText(), FileFormat::class.java)
    val imagePath = "${file.parent}/${format.image}"
    val imageFile = File(imagePath)
    if (imageFile.exists()) {
        spriteCanvasSelectionView.file = imageFile
        init(spriteCanvasSelectionView.image)
        items.addAll(format.poses)
        refresh()
    } else {
        throw FileNotFoundException("$imagePath, Couldnt find relative to location")
    }
}

fun TableView<AnimationRow>.hasPose(pose: String): Boolean {
    return items.any { it.pose.equals(pose, true) }
}

fun TableView<AnimationRow>.find(pose: String): AnimationRow? {
    return items.find {
        it.pose.equals(
            pose,
            true
        )
    }
}

fun TableView<AnimationRow>.add(pose: String) {
    items.add(AnimationRow(pose, AnimationSet()))
}

fun TableView<AnimationRow>.removeLastInserted() {
    val item = selectionModel.selectedItem
    item.set.remove(item.set.last())
    refresh()
}

fun TableView<AnimationRow>.map(filename: String): FileFormat {
    return FileFormat(filename, items)
}

fun TableView<AnimationRow>.map(image: File): FileFormat {
    return map(image.name)
}

fun TableView<AnimationRow>.save(file: File, imageFile: File) {
    file.writeText(json(imageFile))
}

fun TableView<AnimationRow>.save(file: File, imageFilename: String) {
    file.writeText(json(imageFilename))
}

fun TableView<AnimationRow>.json(imageFile: File): String {
    return GsonBuilder().setPrettyPrinting().create().toJson(map(imageFile))
}

fun TableView<AnimationRow>.json(filename: String): String {
    return GsonBuilder().setPrettyPrinting().create().toJson(map(filename))
}

