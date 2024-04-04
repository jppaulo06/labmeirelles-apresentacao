import io.kotest.core.spec.style.StringSpec
import io.kotest.property.forAll

import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import kotlin.math.pow

fun fastExponentiation(base: Int, exponent: Int): Long {
    if (exponent < 0) throw IllegalArgumentException("Exponent must be non-negative")
    var result: Long = 1
    var b = base.toLong()
    var e = exponent.toLong()
    while (e > 0) {
        if (e % 2 == 1L) {
            result *= b
        }
        b *= b
        e /= 2
    }
    return result
}

class FastExponentiationPropertyTest : StringSpec({
    "Exponenciação rápida deve calcular corretamente" {
        forAll(Arb.int(min = 0, max=100), Arb.int(min = 0, max=2)) { base, exponent ->
            val actual = fastExponentiation(base, exponent)
            val expected = base.toDouble().pow(exponent).toLong()
            actual == expected
        }
    }
})

class SumTest: StringSpec({
    "Junção de strings de ter o tamanho igual à soma dos tamanhos" {
        forAll<String, String> { a, b ->
            (a + b).length == a.length + b.length + 1
        }
    }
})
