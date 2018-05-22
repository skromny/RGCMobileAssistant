package com.recrutify.rgc.mobileassistant.Model

import android.arch.persistence.room.Entity

@Entity (
        primaryKeys = [
            "id"
        ]
)
data class Label (
    val color: String,
    val id: Int,
    val name: String,
    val userId: Int
)