package com.piotrwalkusz.smartlaw.core.model.template

import com.fasterxml.jackson.annotation.JsonTypeInfo

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "templateType")
interface Template<T>