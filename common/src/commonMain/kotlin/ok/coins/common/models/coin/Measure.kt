package ok.coins.common.models.coin

import ok.coins.common.utils.NONE

sealed interface Measure{
    val amount: Double
    val unit: String
}

data class Weight(override val amount: Double, override val unit: String): Measure {
    companion object {
        val NONE = Weight(amount = 0.0, unit = String.NONE)
    }
}

data class Diameter(override val amount: Double, override val unit: String): Measure {
    companion object {
        val NONE = Diameter(amount = 0.0, unit = String.NONE)
    }
}

data class Thickness(override val amount: Double, override val unit: String) : Measure {
    companion object {
        val NONE = Thickness(amount = 0.0, unit = String.NONE)
    }
}