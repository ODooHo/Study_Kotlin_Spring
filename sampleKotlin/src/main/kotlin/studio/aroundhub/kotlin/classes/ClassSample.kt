package studio.aroundhub.kotlin.classes

fun main() {
	val aMan = Person()
	aMan.sayHello()
	aMan.introduce()

	aMan.age = 55
	println("sorry, mt real age is ${aMan.age}")

	val jedi = PersonWithConstructor("jedi", 150)
	val flature = PersonWithConstructor("flature")
	var harry = PersonWithConstructor(name = "harry", age = 25, etc = "aaa")

	flature.sayHello()
	flature.introduce()
	jedi.sayHello()
	jedi.introduce()
	harry.sayHello()
	harry.introduce()
}
