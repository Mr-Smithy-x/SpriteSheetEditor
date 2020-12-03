package com.charlton.algorithms

import javafx.embed.swing.SwingFXUtils
import javafx.scene.canvas.GraphicsContext
import java.awt.Color
import java.awt.Graphics
import java.awt.image.BufferedImage
import kotlin.math.abs


object FloodFillAlgorithm {

    fun flood(
        i: BufferedImage,
        g: GraphicsContext,
        x: Int,
        y: Int,
        c: Color,
        c1: Color
    ) {
        if (x >= 1 && y >= 1 && x < i.width && y < i.height) {
            // find the color at point x, y
            val c2 = Color(i.getRGB(x, y))

            // if there is no boundary (the color is almost
            // same as the color of the point where
            // floodfill is to be applied
            if (abs(c2.green - c.green) < 30 && abs(c2.red - c.red) < 30 && abs(c2.blue - c.blue) < 30) {
                // change the color of the pixel of image
                i.setRGB(x, y, c1.rgb)
                g.drawImage(SwingFXUtils.toFXImage(i, null), 0.0, 0.0)

                // floodfill in all possible directions
                flood(i, g, x, y + 1, c, c1)
                flood(i, g, x + 1, y, c, c1)
                flood(i, g, x - 1, y, c, c1)
                flood(i, g, x, y - 1, c, c1)
            }
        }
    }

    fun floodNonTranslucent(
        i: BufferedImage,
        g: GraphicsContext,
        x: Int,
        y: Int,
        c: Color,
        c1: Color
    ) {
        if (x >= 1 && y >= 1 && x < i.width && y < i.height) {
            // find the color at point x, y
            val c2 = Color(i.getRGB(x, y), true)

            // if there is no boundary (the color is almost
            // same as the color of the point where
            // floodfill is to be applied
            if (c2.alpha != 0) {
                // change the color of the pixel of image
                i.setRGB(x, y, c1.rgb)
                g.drawImage(SwingFXUtils.toFXImage(i, null), 0.0, 0.0)

                // floodfill in all possible directions
                floodNonTranslucent(i, g, x, y + 1, c, c1)
                floodNonTranslucent(i, g, x + 1, y, c, c1)
                floodNonTranslucent(i, g, x - 1, y, c, c1)
                floodNonTranslucent(i, g, x, y - 1, c, c1)
            }
        }
    }
}