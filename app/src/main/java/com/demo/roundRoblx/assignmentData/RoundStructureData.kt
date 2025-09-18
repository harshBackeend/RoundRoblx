package com.demo.roundRoblx.assignmentData

import com.google.firebase.database.PropertyName

data class RoundStructureData(
    @PropertyName("round_status") val round_status: String? = "0",
    @PropertyName("round_big") val round_big: ArrayList<BossModel>? = null,
    @PropertyName("round_small") val round_small: ArrayList<EmploymentModel>? = null,
    @PropertyName("round_small_2") val round_small_2: ArrayList<EmploymentModel>? = null,
    @PropertyName("round_pcs") val round_pcs: String? = null,
){
    data class CatalogModel(
        var roundViewType: Int = 1,
        var roundTitleHolder: String? = null,
        var roundSubTitleHolder: String? = null,
        var roundBigLayout: BossModel? = null
    )

    data class BossModel(
        @PropertyName("round_main_image") val round_main_image: String? = "",
        @PropertyName("round_p") val round_p: String? = "",
        @PropertyName("round_main_button") val round_main_button: String? = "",
        @PropertyName("round_main_text") val round_main_text: String? = "",
        @PropertyName("round_main_sub_text") val round_main_sub_text: String? = "",
    )

    data class EmploymentModel(
        @PropertyName("round_main_image") val round_main_image: String? = "",
    )
}

