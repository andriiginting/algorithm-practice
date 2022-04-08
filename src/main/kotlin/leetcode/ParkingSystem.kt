package leetcode

class ParkingSystem(big: Int, medium: Int, small: Int) {
    val map = mutableMapOf<Int, Int>(
        1 to big,
        2 to medium,
        3 to small
    )
    fun addCar(carType: Int): Boolean {
        if (map.getOrDefault(carType, 0) > 1) {
            map[carType] = map.getOrDefault(carType, 0) - 1
            return true
        }
        return false
    }

}