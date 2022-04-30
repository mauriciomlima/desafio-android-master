package com.picpay.desafio.android.utils.mvp

import com.picpay.desafio.android.PicPayService

interface RequestContract {

    interface Presenter {
        fun requestContactsList(service: PicPayService)
    }

}