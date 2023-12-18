package studio.aroundhub.kotlin.condition

import kotlin.random.Random

class IfSample {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			val a = Random.nextInt(10)
			val b = Random.nextInt(10)

			if (a > b) {
				println("$a 는 $b 보다 크다")
			} else if (a == b) {
				println("$a 와 $b 는 같다")
			} else {
				println("$b 는 $a 보다 크다")
			}

			val max = if (a < b) {
				println("max is $b")
				b
			} else {
				println("max is $a")

				a
			}
		}
	}
}
