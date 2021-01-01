package com.cagataymuhammet.mvvm.data.locale

import com.cagataymuhammet.mvvm.data.model.UserModel

object  LocalDataProvider{

    fun getUserListLocale():List<UserModel>
    {
        val userList = listOf<UserModel>(
            UserModel(1,"title1","Body1"),
            UserModel(1,"title2","Bod2"))

        return  userList
    }
}