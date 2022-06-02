package ok.coins.common.models.coin

enum class Nominal(val currency: Currency, val amount: Double) {
    NONE(Currency.NONE, 0.0),

    // rub
    TEN_RUB(Currency.RUB, 10.0),
}
