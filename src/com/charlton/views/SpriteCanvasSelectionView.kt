package com.charlton.views

import com.charlton.algorithms.FloodFillAlgorithm
import javafx.scene.canvas.Canvas
import javafx.scene.input.MouseEvent
import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

class SpriteCanvasSelectionView constructor(canvas: Canvas, file: File) :  BaseCanvasView(canvas, file) {
    init {
        canvas.onDragDetected = this
        redraw()
    }


    override fun handle(event: MouseEvent?) {
        super.handle(event)
        when(event?.eventType){
            MouseEvent.MOUSE_CLICKED, MouseEvent.MOUSE_DRAGGED -> {
                drawImage(event!!)
            }
        }
    }

    private fun drawImage(e: MouseEvent) {
        drawImage(e.x, e.y)
    }

    private fun drawImage(x: Double, y: Double) {
        canvas.graphicsContext2D.clearRect(0.0,0.0, canvas.width, canvas.height)
        val i = ImageIO.read(file)

        val rgb = i.getRGB(x.toInt(), y.toInt())
        val c = Color(rgb,true)
        if(c.alpha != 0) {
            FloodFillAlgorithm.floodNonTranslucent(i, canvas.graphicsContext2D, x.toInt(), y.toInt(),c, Color.RED)
        }
        //canvas.graphicsContext2D.drawImage(SwingFXUtils.toFXImage(i, null), 0.0, 0.0)
        println(c.alpha)

    }

}