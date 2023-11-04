package studio.aroundhub.kotlin.variable

class VariableSample {

    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            val name : String = "ODooHo"
            val org = "Around Hub Studio"
            var age = 12

            age = 15

            println("my name is $name")
            println("my org is $org")
            println("my age is $age")
        }
    }
}