package training

fun main() {
    val data = listOf(
        BCSConfig("transport", "GO_CAR,GO_RIDE,GO_BLUE_BIRD,GO_SILVER_BIRD,GO_BIRD_COMBO,GO_HAIL_BIKE,GO_CAR_EXTRA,SG_GO_CAR,GO_TAXI,GO_RIDE_HYGIENE,GO_CAR_HYGIENE,GO_RIDE_EV,GO_CAR_PREMIUM"),
        BCSConfig("food", "GO_FOOD,GO_FOOD_PICK_UP"),
        BCSConfig("send", "GO_SEND,GO_KILAT,GOKILAT_SHOP,GO_KILAT_NEW,SAMEDAY_KILAT,SAMEDAY_SEND,LOGISTICS_INTERCITY"),
        BCSConfig("shop", "GO_SHOP")
    )

    val serviceName = data.first { it.service.contains("GO_CAR_EXTRA") }
        .value
    val litmusConfigApproach = data.firstOrNull { it.value == "food" }
        ?.service.orEmpty()

    //format object payload
    val bcs = mutableMapOf(
        "transport" to Transport("Transport", listOf("GO_RIDE", "GO_CAR")),
        "food" to Food("Food", listOf("GO_FOOD", "GO_FOOD_PICKUP"))
    )

    val inputLitmus = "food"
    val inputOrders = "GO_FOOD"

    val response = bcs[inputLitmus]
    var responseOrders = ""

    responseOrders = getDynamicTag(bcs, inputOrders)

    println("with array approach $serviceName services $litmusConfigApproach")
    println("with object map approach: litmus $response")
    println("with object map approach: recent orders $responseOrders")
}

private fun getDynamicTag(
    bcs: MutableMap<String, ProductGroup>,
    inputOrders: String
): String {
    for ((key, value) in bcs) {
        if (value.serviceName.contains(inputOrders)) {
            return key
        }
    }
    return ""
}

interface ProductGroup {
    val title: String
    val serviceName: List<String>
}

data class BcsConfiguration(
    val transport: Transport,
    val food: Food
)

data class Transport(
    override val title: String,
    override val serviceName: List<String>
) : ProductGroup

data class Food(
    override val title: String,
    override val serviceName: List<String>
) : ProductGroup

data class BCSConfig(
    val value: String,
    val service: String
)