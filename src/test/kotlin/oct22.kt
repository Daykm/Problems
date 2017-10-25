import org.junit.Test
import org.junit.Assert.*
import java.time.Duration
import java.time.Instant
import java.util.*

class Oct22Tests {

    @Test
    fun `random exhaust`() {
        val rand = Random()

        val dataSet = (0 until 1000000).map {
            gen(rand)
        }

        println("finished generating")

        val start = Instant.now()

        dataSet.forEach { (originalMissingNum, second) ->
            val missing = eval(second)
            if(missing == null) {
                if(second.last() - second.first() == second.size - 1) {
                    println("array is sorted")
                } else {
                    println("Didn't find missing num ${originalMissingNum}")
                    println(second)
                    throw RuntimeException()
                }
            } else if(missing != originalMissingNum) {
                println("Didn't find missing num ${originalMissingNum}, found $missing instead ")
                println(second)
                throw RuntimeException()
            }
        }

        val end = Instant.now()
        val timespent = Duration.between(start, end).toMillis()
        println("took $timespent milliseconds to process ${dataSet.size} lists of size $DATA_SIZE")
    }

    @Test
    fun `finds the missing number`() {
        val data = listOf(43, 44, 45, 46, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 41, 42)
        assertEquals(40, findMissingNumber(data))
    }

    @Test
    fun `slices array between 41 and 42`() {
        val data = listOf(39, 41, 42)

        slice(data).let {
            assertEquals(2, it.first.size)
            assertEquals(1, it.second.size)
        }
    }

    @Test
    fun `missing number in array of 2`() {
        val data = listOf(18, 20)
        assertEquals(19, findMissingNumber(data))
    }

    @Test
    fun `missing number at end of array`() {
        val data = listOf(19, 20, 21, 22, 15, 16,17)
        assertEquals(18, findMissingNumber(data))
    }

    @Test
    fun `missing number in 3 before`() {
        val data = listOf(39, 41, 42)

        assertEquals(40, findMissingNumber(data))
    }

    @Test
    fun `missing number in 3 after`() {
        val data = listOf(38, 39, 41)

        assertEquals(40, findMissingNumber(data))
    }

    @Test
    fun `choose opinionated pivot after`() {
        val data = listOf(39, 41, 42)

        assertEquals(2, choosePivot(data))
    }

    @Test
    fun `choose opinionated pivot before`() {
        val data = listOf(38, 39, 41)

        assertEquals(1, choosePivot(data))
    }

    @Test
    fun `choose 3rd position for pivot in 4 size array`() {
        val data = listOf(1, 2, 3, 4)
        assertEquals(2, choosePivot(data))
    }

    @Test
    fun `1 divided by 2 = 0`() {
        assertEquals(0, 1/2)
    }
}