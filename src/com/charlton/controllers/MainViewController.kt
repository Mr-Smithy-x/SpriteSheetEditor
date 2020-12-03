package com.charlton.controllers

import com.charlton.views.SpriteCanvasSelectionView
import javafx.embed.swing.SwingFXUtils
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.canvas.Canvas
import javafx.scene.control.MenuItem
import javafx.stage.FileChooser
import javax.imageio.ImageIO

class MainViewController : EventHandler<ActionEvent> {


    @FXML
    lateinit var newMenuItem: MenuItem

    @FXML
    lateinit var openMenuItem: MenuItem

    @FXML
    lateinit var saveMenuItem: MenuItem

    @FXML
    lateinit var closeMenuItem: MenuItem

    @FXML
    lateinit var spriteCanvas: Canvas

    lateinit var spriteCanvasSelectionView: SpriteCanvasSelectionView

    private var fileChooser: FileChooser = FileChooser().also {
        it.extensionFilters.add(FileChooser.ExtensionFilter("Image", "*.png", "*.gif"))
    }

    @FXML
    fun initialize() {
        newMenuItem.onAction = this
    }

    override fun handle(event: ActionEvent?) {
        when (event?.source) {
            newMenuItem -> {
                val file = fileChooser.showOpenDialog(null)
                if (file.exists()) {
                    if (!this::spriteCanvasSelectionView.isInitialized) {
                        spriteCanvasSelectionView = SpriteCanvasSelectionView(spriteCanvas, file)
                    } else {
                        spriteCanvasSelectionView.file = file
                    }
                }
            }
        }
    }
}