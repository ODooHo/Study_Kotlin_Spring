package studio.aroundhub.kotlin.classes

class Person {
    var name : String  = "Jedi"
    var age: Int = 15
    var country : String = "SouthKorea"
    var height: Int = 250
    var weight : Int = 30


    fun sayHello(){
        println("Hi")
    }

    fun introduce(){
        println("I'm $name and my age is $age")
    }
}