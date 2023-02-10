package mx.ulai.test.model

import androidx.room.Entity

@Entity(tableName = "records", primaryKeys = ["page"])
data class ShoppAppResponse(
    val plpResults: PlpResults,
    var page: Int,
    var totalPage: Int,
)

data class PlpResults(
    var records: List<Records>
)

@Entity(tableName = "record", primaryKeys = ["productId"])
data class Records(
    val productId : String,
    val skuRepositoryId : String,
    val productDisplayName : String,
    val productType : String,
    val productRatingCount : String,
    val productAvgRating : Double,
    val promotionalGiftMessage : String,
    val listPrice : Double,
    val minimumListPrice : Double,
    val maximumListPrice : Double,
    val promoPrice : Double,
    val minimumPromoPrice : Double,
    val maximumPromoPrice : Double,
    val isHybrid : Boolean,
    val isMarketPlace : Boolean,
    val isImportationProduct : Boolean,
    val brand : String,
    val seller : String,
    val category : String,
    val dwPromotionInfo : DwPromotionInfo,
    val isExpressFavoriteStore : Boolean,
    val isExpressNearByStore : Boolean,
    val smImage : String,
    val lgImage : String,
    val xlImage : String,
    val groupType : String,
    val plpFlags : List<PlpFlags>,
    val variantsColor: List<VariantsColor>,
)

data class DwPromotionInfo(
    val dwToolTipInfo: String,
    val dWPromoDescription: String,
)

data class PlpFlags(
    val flagId: String,
    val flagMessage: String,
)

data class VariantsColor(
    val colorName: String,
    val colorHex: String,
    val colorImageURL: String,
    val colorMainURL: String,
    val skuId: String,
)

