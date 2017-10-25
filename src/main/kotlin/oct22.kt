fun findMissingNumber(slice: List<Int>): Int? = when {
    slice.isEmpty() -> null
    slice.first() - slice.last() == 2 ->
        slice.last() + 1
    else -> eval(slice)
}

fun eval(slice: List<Int>): Int? = when {
    slice.first() > slice.last() -> slice(slice).let { (first, second) ->
        eval(first) ?: eval(second)
    }
    slice.last() - slice.first() == slice.size - 1 -> null
    slice.size == 2 -> slice.first() + 1
    else -> slice(slice).let { (first, second) ->
        eval(first) ?: eval(second)
    }
}

fun slice(data: List<Int>) = choosePivot(data).let { pivot ->
    data.slice(0 until pivot) to data.slice(pivot..data.lastIndex)
}

fun choosePivot(data: List<Int>): Int = (data.size / 2).let { pivot ->
    if (data[pivot] - data[pivot - 1] == 2) {
        pivot + 1
    } else {
        pivot
    }
}