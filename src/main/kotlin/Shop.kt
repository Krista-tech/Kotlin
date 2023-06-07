import java.time.Instant

//So the in the input we have list of purchases named as cart
//and list of purchases named as products.
//The business function will have to return the eligible purchases as list.
//The purchase contains
//id as string
//price as Int
//start date as instant
//end date as instant
//allowed provider Id list as list of int
//In the business function you have to find matching Ids, check if the cart provider id is the same as in the products ( the chart provider id size has to be one in the products its can contain several ids).
//you have to check if the price corresponds
//and if the  cart dates are in the start and end date period of the products.
//if yes, please return the producr as part of that list


class Shop {

    fun buy(cart: List<Purchases>, products: List<Purchases>): List<Purchases> {
        var finalList =mutableListOf<Purchases>()
        cart.forEach { cartProduct->
            val matchingProd = products.firstOrNull(){it.id == cartProduct.id}
            if (matchingProd != null ){
            val priceValid = matchingProd.price == cartProduct.price
            val carInBetween = cartProduct.startDate< matchingProd.startDate && matchingProd.startDate < cartProduct.endDate
                if (priceValid.and(carInBetween)){
                    finalList.add(cartProduct)
                }
            }


        }

//        var finalList = mutableListOf<Purchases>()
//        for (c in cart) {
//            for (p in products) {
//                if (c.id == p.id && c.price == p.price
//                    && c.startDate == p.startDate
//                    && c.endDate == p.endDate) {
//                    finalList.add(p)
//                }
//            }
//        }

        return finalList
    }


    data class Purchases(
        val id: String,
        val price: Int,
        val startDate: Instant,
        val endDate: Instant

    )

}