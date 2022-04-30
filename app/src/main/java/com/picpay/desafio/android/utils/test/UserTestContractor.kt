package com.picpay.desafio.android.utils.test

import com.picpay.desafio.android.User

interface UserTestContractor {

    interface TestPresenter {
        fun mockUsers(): List<User>
    }

}