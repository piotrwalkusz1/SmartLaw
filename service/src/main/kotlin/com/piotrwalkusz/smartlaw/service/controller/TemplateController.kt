package com.piotrwalkusz.smartlaw.service.controller

//@RestController("/templates")
//class TemplateController(private val templateService: TemplateService, private val ruleService: RuleService) {
//
//    @PostMapping("/process")
//    fun processTemplate(processTemplateDto: ProcessTemplateDto): Any? {
//        val rule = ruleService.getRuleById(processTemplateDto.ruleId)
//        // TODO: pass linksByElementsIds
//        val templateProcessorContext = RuleInvocationTemplateProcessorContext(rule, processTemplateDto.arguments, mapOf())
//
//        return templateService.processTemplate(rule.content, templateProcessorContext)
//    }
//}