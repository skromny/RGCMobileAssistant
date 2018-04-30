package com.recrutify.rgc.mobileassistant.overlay

/**
 * Created by arturnowak on 15.01.2018.
 */
data class Project (
        val id: Int,
        val name: String,
        val companyId: Int,
        val companyName: String,
        val quantity: Int,
        val description: String,
        val location: String,
        val referenceId: String
)
