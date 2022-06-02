package ok.coins.common.models.coin

import kotlinx.datetime.Instant
import ok.coins.common.models.series.SeriesId
import ok.coins.common.utils.NONE

data class Coin(
  var id: CoinId = CoinId.NONE,
  var name: String = String.NONE,
  var seriesId: SeriesId = SeriesId.NONE,
  var releaseDate: Instant = Instant.NONE,
  var cNum: String = String.NONE,
  var nominal: Nominal = Nominal.NONE,
  var material: String = String.NONE,
  var weight: Weight = Weight.NONE,
  var diameter: Diameter = Diameter.NONE,
  var thickness: Thickness = Thickness.NONE,
  var allCount: Int = Int.NONE,
  var averse: String = String.NONE,
  var reverse: String = String.NONE,
  var authors: String = String.NONE,
  var info: String = String.NONE,
  var imgUri: String = String.NONE,
)
