package mx.ulai.test.util

object GeneriUtil {
    fun <T, U, R> Pair<T?, U?>.biLet(body: (T, U) -> R): R? {
        val first = first
        val second = second
        if (first != null && second != null) {
            return body(first, second)
        }
        return null
    }
}