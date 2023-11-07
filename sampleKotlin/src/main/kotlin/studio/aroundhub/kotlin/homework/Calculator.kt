package studio.aroundhub.kotlin.homework

class Calculator {
	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			val a: Boolean = true
			while (a) {
				println("계산할 두 수를 입력해주세요")
				val param1 = readLine()?.toInt() // param1에 정수로 변환한 값을 할당
				if (param1 != null) {
					if (param1 == 0){
						break
					}
				}
				val param2 = readLine()?.toInt() // param2에 정수로 변환한 값을 할당

				if (param1 != null && param2 != null) {
					println("계산식을 입력해주세요 (+, -, *, /)")
					val cal = readLine()

					when (cal) {
						"+" -> println("결과: " + add(param1, param2))
						"-" -> println("결과: " + minus(param1, param2))
						"*" -> println("결과: " + multiply(param1, param2))
						"/" -> println("결과: " + divide(param1, param2))
						else -> println("올바른 연산자를 입력하세요.")
					}
				} else {
					println("올바른 숫자를 입력하세요.")
				}
			}
		}
	}
}

fun add(a: Int, b: Int): Int {
	return a + b
}

fun minus(a: Int, b: Int): Int {
	return a - b
}

fun multiply(a: Int, b: Int): Int {
	return a * b
}

fun divide(a: Int, b: Int): Int {
	return if (b != 0) a / b else 0
}
