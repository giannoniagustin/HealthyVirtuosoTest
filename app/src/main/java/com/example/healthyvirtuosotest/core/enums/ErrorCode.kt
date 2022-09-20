package com.example.healthyvirtuosotest.core.enums

import com.example.healthyvirtuosotest.R


enum class ErrorCode(var code: Int = -1, var message: Int = R.string.app_name) {

    UNKNOWN(1000000001, message = R.string.error_unknown),

    SUCCESS(200, message = R.string.api_success),

    INVALID_SERVICE(501),

    AUTH_FAILED(401);

    companion object {
        fun getErrorByName(name: String): ErrorCode {
            return values().find { it.name == name }!!
        }

        fun getErrorByCode(code: Int): ErrorCode {
            return values().find { it.code == code }!!
        }
    }
}