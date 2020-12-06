

fun main(args: Array<String>) {
    println("We in this 0xDEADBEEF")
    val animateSet = FileFormat.AnimationSet();
    animateSet.add(FileFormat.SpriteBounds(10, 20, 30, 40))
    animateSet.add(FileFormat.SpriteBounds(10, 20, 30, 40))
    println(animateSet.size)
}
