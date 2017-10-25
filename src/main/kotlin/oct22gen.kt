import java.util.*

const val DATA_SIZE = 13

fun gen(rand : Random) : Pair<Int, List<Int>> {
    val start = Math.abs(rand.nextInt()) % DATA_SIZE
    val length = 10 + (Math.abs(rand.nextInt()) % (DATA_SIZE - 10))

    var data =  (0 until length).map { index ->
        start + index
    }.toIntArray()

    val newData = data.toMutableList()

    val removed = newData.removeAt(1 + (Math.abs(rand.nextInt()) % (data.size - 1)))

    var rotatedData = newData.toIntArray()

    val rotations = Math.abs(rand.nextInt()) % DATA_SIZE

    (0 until rotations).forEach {
        rotatedData = rotate(rotatedData)
    }

    if(newData.last() - newData.first() == newData.size - 1) {
        return gen(rand)
    }

    return removed to newData
}

fun rotate(data : IntArray): IntArray {
    val newData = IntArray(data.size)
    newData[0] = data.last()

    (1 until data.size).forEach {
        newData[it] = data[it - 1]
    }
    return newData
}
