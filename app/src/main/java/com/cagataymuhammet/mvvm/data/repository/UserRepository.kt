package com.cagataymuhammet.mvvm.data.repository

import com.cagataymuhammet.mvvm.data.model.UserModel
import com.cagataymuhammet.mvvm.util.Resource

import io.reactivex.Observable
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UserRepository {

    fun getUserList(): Flow<Resource<List<UserModel>>>
}