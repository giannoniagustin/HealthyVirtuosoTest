package com.example.healthyvirtuosotest.core.exception.model

import com.example.healthyvirtuosotest.core.enums.ErrorCode
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ErrorApi(
    @SerializedName("statusCode")
    var statusCode: Int,
    @SerializedName("errorMessage")
    var errorMessage: String? = null,
    @SerializedName("errorDevMessage")
    var devError: String? = null,
    var codeError: ErrorCode = ErrorCode.UNKNOWN
) : Serializable