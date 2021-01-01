package com.cagataymuhammet.mvvm.ui

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.cagataymuhammet.mvvm.data.model.UserModel
import com.cagataymuhammet.mvvm.data.repository.UserRepository
import com.cagataymuhammet.mvvm.util.Resource
import com.cagataymuhammet.mvvm.util.ResourceStatus
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

class MainViewModel(private  val userRepository: UserRepository) : ViewModel() {

    init {
        //getAllUsersListWithFlow()
    }

    var usersLiveData =    MutableLiveData<List<UserModel>>()
    var error =    MutableLiveData<Throwable>()
    var loading =    MutableLiveData<Boolean>()

    fun getUsers() {

        viewModelScope.launch {

            userRepository.getUserList()
                .asLiveData(viewModelScope.coroutineContext).observeForever {

                    when(it.status) {
                        ResourceStatus.LOADING -> {
                            loading.postValue(true)
                        }

                        ResourceStatus.SUCCESS -> {
                            usersLiveData.postValue(it.data)
                            loading.postValue(false)
                        }

                        ResourceStatus.ERROR -> {
                            Log.e("ERROR", "${it.throwable}")
                            error.postValue(it.throwable)
                            loading.postValue(false)
                        }

                    }
                }

        }
    }
}