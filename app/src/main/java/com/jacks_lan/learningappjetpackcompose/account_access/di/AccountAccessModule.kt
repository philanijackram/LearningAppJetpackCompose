package com.jacks_lan.learningappjetpackcompose.account_access.di

import com.jacks_lan.learningappjetpackcompose.account_access.data.remote.api.AccountAccessApi
import com.jacks_lan.learningappjetpackcompose.account_access.data.repository.AccountAccessRepositoryImpl
import com.jacks_lan.learningappjetpackcompose.account_access.domain.repository.AccountAccessRepository
import com.jacks_lan.learningappjetpackcompose.account_access.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AccountAccessModule {

    @Provides
    fun provideAuthApi(): AccountAccessApi {
        return Retrofit.Builder()
            .baseUrl("https://9c56-41-116-31-192.ngrok-free.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AccountAccessApi::class.java)
    }

    @Provides
    fun provideAuthRepository(api: AccountAccessApi): AccountAccessRepository {
        return AccountAccessRepositoryImpl(api)
    }

    @Provides
    fun provideLoginUseCase(repo: AccountAccessRepository): LoginUseCase {
        return LoginUseCase(repo)
    }

}