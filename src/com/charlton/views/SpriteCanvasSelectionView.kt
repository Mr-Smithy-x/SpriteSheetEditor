package com.charlton.views

import com.charlton.algorithms.FloodFillAlgorithm
import javafx.embed.swing.SwingFXUtils
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
        val map = HashMap<Double, ArrayList<Double>>()
        canvas.graphicsContext2D.clearRect(0.0,0.0, canvas.width, canvas.height)
        val i = ImageIO.read(file)

        val rgb = i.getRGB(x.toInt(), y.toInt())
        val c = Color(rgb,true)
        if(c.alpha != 0) {
            FloodFillAlgorithm.floodNonTranslucent(map,i, canvas.graphicsContext2D, x, y, Color.RED)
        }
        canvas.graphicsContext2D.drawImage(SwingFXUtils.toFXImage(i, null), 0.0, 0.0)

        val lowX = map.keys.minOrNull()
        val highX = map.keys.maxOrNull()?.plus(1)
        val lowY = map.values.flatten().minOrNull()
        val highY = map.values.flatten().maxOrNull()?.plus(1)
        if(lowX != null && highX != null && lowY != null && highY != null) {
            val width = highX - lowX
            val height = highY - lowY
            canvas.graphicsContext2D.strokeRect(lowX, lowY, width, height)
        }

    }

}