package com.victorhvs.lotonaticos.domain.models

data class Lottery(
    val uuid: String,
    val title: String,
    val description: String,
    val color: String,
    val enabled: Boolean
) {
    constructor() : this(uuid = "", title = "", description = "", color = "", enabled = false)
}
