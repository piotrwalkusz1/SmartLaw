package com.piotrwalkusz.smartlaw.compiler.validator

data class ValidationResult(
        val error: String?
) {

    companion object {

        fun ok(): ValidationResult {
            return ValidationResult(null)
        }

        fun error(error: String): ValidationResult {
            return ValidationResult(error)
        }
    }
}