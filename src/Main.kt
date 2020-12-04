import com.charlton.models.AnimationSet
import com.charlton.models.SpriteBounds


fun main(args: Array<String>) {
    println("We in this 0xDEADBEEF")
    val animateSet = AnimationSet();
    animateSet.add(SpriteBounds(10, 20, 30, 40))
    animateSet.add(SpriteBounds(10, 20, 30, 40))
    println(animateSet.size)
}
