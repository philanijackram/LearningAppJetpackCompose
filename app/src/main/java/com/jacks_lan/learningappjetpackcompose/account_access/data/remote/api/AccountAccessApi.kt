package com.jacks_lan.learningappjetpackcompose.account_access.data.remote.api

import com.jacks_lan.learningappjetpackcompose.account_access.data.remote.dto.LoginRequestDto
import com.jacks_lan.learningappjetpackcompose.account_access.data.remote.dto.LoginResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountAccessApi {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequestDto): Response<LoginResponseDto>
}