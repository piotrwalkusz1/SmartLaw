package com.piotrwalkusz.smartlaw.service.model

data class Project(
        val id: String,
        val name: String,
        val documentsIds: List<String> = listOf(),
        val modulesIds: List<String> = listOf()
)