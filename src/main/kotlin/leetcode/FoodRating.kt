package leetcode
import java.util.*

class FoodRatings(private val foods: Array<String>, private val cuisines: Array<String>, private val ratings: IntArray) {

    private val foodRatingMap = mutableMapOf<String, Int>()
    private val foodCuisineMap = mutableMapOf<String, MutableList<String>>()

    init {
        initFoodRatings()
    }

    fun changeRating(food: String, newRating: Int) {
        foodRatingMap[food] = newRating
    }

    fun highestRated(cuisine: String): String {
        val foodList = foodCuisineMap[cuisine]

        return foodList?.maxBy { it }.toString()
    }


    private fun initFoodRatings() {
        for(i in foods.indices) {
            foodRatingMap[foods[i]] = ratings[i]
        }

        for(i in cuisines.indices) {
            foodCuisineMap[cuisines[i]] = foodCuisineMap.getOrDefault(foods[i], mutableListOf()).apply { add(foods[i]) }
        }
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * var obj = FoodRatings(foods, cuisines, ratings)
 * obj.changeRating(food,newRating)
 * var param_2 = obj.highestRated(cuisine)
 */