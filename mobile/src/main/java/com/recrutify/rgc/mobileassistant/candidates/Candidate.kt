package com.recrutify.rgc.mobileassistant.candidates

import android.arch.persistence.room.Entity
import android.arch.persistence.room.TypeConverters
import com.recrutify.rgc.mobileassistant.Model.Label
import com.recrutify.rgc.mobileassistant.db.CommonTypeConverters
import java.security.CodeSource

@Entity (
        primaryKeys = [
            "id"
        ],
        indices = [
            android.arch.persistence.room.Index("name")
        ]
)
@TypeConverters(CommonTypeConverters::class)
data class Candidate (
    val id: Int,
    val avatarLink: String?,
    val accountId: Int,
    val active: Boolean,
    val cleanName: String?,
    val creationTime: String,
    val isFavorite: Boolean,
    val sourceId: Int?,
    val name: String?,
    val labels: List<Label>
)
