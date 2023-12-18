package studio.aroundhub.kotlin.inheritance

import java.time.LocalDate

class MyCar(name: String, price: Double, brand: String, private val purchaseDate: LocalDate)
	: Car(name, price, brand){
	override fun myPurchaseData() {
		println("you made a purchase on $purchaseDate")
	}
}
