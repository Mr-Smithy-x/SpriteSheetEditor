package com.charlton.views

import javafx.embed.swing.SwingFXUtils
import javafx.event.EventHandler
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import java.io.File

import javafx.scene.image.WritableImage

import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import java.awt.image.BufferedImage
import javax.imageio.ImageIO


open class BaseCanvasView(canvas: Canvas, file: File) : EventHandler<MouseEvent?> {

    protected var pressing = BooleanArray(1024)
    protected var mode = MODE_DEFAULT
    protected var canvas: Canvas = canvas
    var file: File = file
        set(value){
            field = value
            redraw()
        }

    protected fun redraw() {
        val image = ImageIO.read(file)
        canvas.width = image.width.toDouble()
        canvas.height = image.height.toDouble()
        draw(image)
    }

    protected var last_y = 0.0
    protected var last_x = 0.0

    fun draw(toFXImage: WritableImage?) {
        canvas.graphicsContext2D.drawImage(toFXImage, last_x, last_y)
    }

    fun draw(
        image: BufferedImage,
        x: Double = 0.0,
        y: Double = 0.0,
        w: Double = image.width.toDouble(),
        h: Double = image.height.toDouble()
    ) {
        canvas.graphicsContext2D.drawImage(SwingFXUtils.toFXImage(image, null), x, y, w, h)
    }

    fun clear() {
        canvas.graphicsContext2D.clearRect(0.0, 0.0, canvas.width, canvas.height)
    }

    override fun handle(event: MouseEvent?) {}


    fun keyPressed(e: KeyEvent) {
        System.out.printf("IS SPACE PRESSED", e.code.ordinal == SPACE)
        pressing[e.code.ordinal] = true
    }

    fun keyReleased(e: KeyEvent) {
        pressing[e.code.ordinal] = false
    }

    companion object {
        const val MODE_DEFAULT = 0
        const val MODE_SELECTED = 1
        const val MODE_ENTERED = 2
        const val MODE_EXITED = 3
        const val MODE_RELEASED = 4
        val UP = KeyCode.UP.ordinal
        val DN = KeyCode.DOWN.ordinal
        val LT = KeyCode.LEFT.ordinal
        val RT = KeyCode.RIGHT.ordinal
        val _A = KeyCode.A.ordinal
        val _B = KeyCode.B.ordinal
        val _C = KeyCode.C.ordinal
        val _D = KeyCode.D.ordinal
        val _E = KeyCode.E.ordinal
        val _F = KeyCode.F.ordinal
        val _G = KeyCode.G.ordinal
        val _H = KeyCode.H.ordinal
        val _I = KeyCode.I.ordinal
        val _J = KeyCode.J.ordinal
        val _K = KeyCode.K.ordinal
        val _L = KeyCode.L.ordinal
        val _M = KeyCode.M.ordinal
        val _N = KeyCode.N.ordinal
        val _O = KeyCode.O.ordinal
        val _P = KeyCode.P.ordinal
        val _Q = KeyCode.Q.ordinal
        val _R = KeyCode.R.ordinal
        val _S = KeyCode.S.ordinal
        val _T = KeyCode.T.ordinal
        val _U = KeyCode.U.ordinal
        val _V = KeyCode.V.ordinal
        val _W = KeyCode.W.ordinal
        val _X = KeyCode.X.ordinal
        val _Y = KeyCode.Y.ordinal
        val _Z = KeyCode.Z.ordinal
        val _1 = KeyCode.DIGIT1.ordinal
        val _2 = KeyCode.DIGIT2.ordinal
        val _3 = KeyCode.DIGIT3.ordinal
        val _4 = KeyCode.DIGIT4.ordinal
        val _5 = KeyCode.DIGIT5.ordinal
        val _6 = KeyCode.DIGIT6.ordinal
        val _7 = KeyCode.DIGIT7.ordinal
        val _8 = KeyCode.DIGIT8.ordinal
        val _9 = KeyCode.DIGIT9.ordinal
        val CTRL = KeyCode.CONTROL.ordinal
        val SHFT = KeyCode.SHIFT.ordinal
        val ALT = KeyCode.ALT.ordinal
        val SPACE = KeyCode.SPACE.ordinal
        val COMMA = KeyCode.COMMA.ordinal
        val PERIOD = KeyCode.PERIOD.ordinal
        val SLASH = KeyCode.SLASH.ordinal
        val SEMICOLON = KeyCode.SEMICOLON.ordinal
        val COLON = KeyCode.COLON.ordinal
        val QUOTE = KeyCode.QUOTE.ordinal
    }

    init {
        this.canvas.onMouseClicked = this
        this.canvas.onMouseDragEntered = this
        this.canvas.onMouseDragReleased = this
        this.canvas.onMouseDragged = this
        this.canvas.onMouseDragEntered = this
        this.canvas.onMouseDragExited = this
        this.canvas.onMouseEntered = this
        this.canvas.onMouseExited = this
        this.canvas.setOnKeyPressed { e: KeyEvent -> keyPressed(e) }
        this.canvas.setOnKeyReleased { e: KeyEvent -> keyReleased(e) }
        this.canvas.isFocusTraversable = true
    }
}