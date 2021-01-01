package com.cagataymuhammet.mvvm.data.repository

import com.cagataymuhammet.mvvm.data.locale.LocalDataProvider
import com.cagataymuhammet.mvvm.data.model.UserModel
import com.cagataymuhammet.mvvm.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class UserLocaleDataRepository :UserRepository {

    override fun getUserList(): Flow<Resource<List<UserModel>>> = flow {

        try {
            emit(Resource.Loading())
            emit(Resource.Success(LocalDataProvider.getUserListLocale()))
        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }

}