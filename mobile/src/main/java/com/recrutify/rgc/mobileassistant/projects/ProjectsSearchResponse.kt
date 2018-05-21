package com.recrutify.rgc.mobileassistant.projects

data class ProjectsSearchResponse (
    val items: List<Project>,
    val currentPage: Int,
    val pagesCount: Int,
    val itemsPerPage: Int,
    val totalCount: Int
)