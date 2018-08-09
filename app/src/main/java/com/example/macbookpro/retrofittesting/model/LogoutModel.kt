package com.example.macbookpro.retrofittesting.model

data class LogoutRequestModel(val REQ_DATA: LogoutRequestBody)

data class LogoutRequestBody(val api_key: String, val token: String, val driver_code: String)

data class LogoutResponse(val RES_DATA:ResponseData)

data class ResponseData(val api_key: String, val error_code: String, val error_msg: String, val data: ResponseMessage)

data class ResponseMessage(val msg: String)