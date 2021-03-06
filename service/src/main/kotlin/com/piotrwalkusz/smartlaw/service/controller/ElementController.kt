package com.piotrwalkusz.smartlaw.service.controller

import com.piotrwalkusz.smartlaw.service.controller.dto.SearchTypesDto
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/elements")
class ElementController {

    fun searchTypes(@RequestBody searchTypes: SearchTypesDto) {

    }
}