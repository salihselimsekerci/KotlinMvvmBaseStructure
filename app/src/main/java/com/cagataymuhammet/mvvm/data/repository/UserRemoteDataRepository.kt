package com.cagataymuhammet.mvvm.data.repository

import com.cagataymuhammet.mvvm.data.model.UserModel
import com.cagataymuhammet.mvvm.data.remote.Service
import com.cagataymuhammet.mvvm.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRemoteDataRepository : UserRepository {

    override fun getUserList(): Flow<Resource<List<UserModel>>> = flow {

        try {
            emit(Resource.Loading())

            val response = Service.build().getUsers()

            if (response.isSuccessful) {

                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }

}