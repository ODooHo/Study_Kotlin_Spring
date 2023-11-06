package studio.aroundhub.kotlin.condition

import kotlin.random.Random

class WhenSample {
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val a = Random.nextInt(10)
            val b = Random.nextInt(10)

            when (a){
                1,2 -> println("a = ${a}")
                3 -> println("a = ${a}")

                in 6 .. 8 -> println("a = ${a}")
                else -> println("else! a = ${a}")
            }


            when{
                a > b -> println("a > b")
                else -> println("a <= b")
            }

        }
    }
}