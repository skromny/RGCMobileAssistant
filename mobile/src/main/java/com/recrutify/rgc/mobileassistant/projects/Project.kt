package com.recrutify.rgc.mobileassistant.projects

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.recrutify.rgc.mobileassistant.Model.Label
import com.recrutify.rgc.mobileassistant.db.CommonTypeConverters

@Entity(
        indices = [
            Index("name")
        ],
        primaryKeys = [
            "id"
        ]
)
@TypeConverters(CommonTypeConverters::class)
data class Project (
    val accountId: Int,
    val clientRate: Int?,
    val companyId: Int?,
    val companyName: String?,
    val contractEndDate: String?,
    val contractStartDate: String?,
    val contractTypeId: Int?,
    val contractorRate: Int?,
    val createdBy: Int?,
    val creationTime: String?,
    val currency: String?,
    val dateFrom: String?,
    val dateTo: String?,
    val description: String?,
    val fee: Int?,
    val feeTypeId: Int?,
    val folderId: Int?,
    val id: Int,
    val incomeHigh: Int?,
    val incomeLow: Int?,
    val isFavourite: Boolean,
    val isPublic: Boolean?,
    val labels: List<Label>,
    val lastUpdate: String?,
    val location: String?,
    val name: String,
    @field:SerializedName("package")
    val _package: String?,
    val quantity: Int?,
    val referenceId: String?,
    val responsibleContactId: Int?,
    val responsibleContactName: String?,
    val responsibleUserId: Int?,
    val responsibleUserName: String?,
    val score: Int?,
    val statusId: Int?,
    val tagsVol: Int?,
    val templateId: Int?,
    val typeId: Int?
)