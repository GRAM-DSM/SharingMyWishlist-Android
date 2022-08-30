package com.example.sharingmywishlist.Response

import com.google.gson.annotations.SerializedName

data class WishAllResponse (
    @SerializedName("wishResponseList") val wishResponseList: List<WishResponseList>
) {
    data class WishResponseList (
        val id: Int,
        val title: String,
        val contents: String,
        val writer: String,
        val clear: Boolean,
        val color: String
    )
}