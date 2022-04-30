package com.picpay.desafio.android.utils.test

import com.picpay.desafio.android.User
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UserTestPresenter : UserTestContractor.TestPresenter {

    override fun mockUsers(): List<User>{

        val users = ArrayList<User>()
        val names = listOf("James", "Arnold", "John", "Daniel", "Jason", "Michael")
        val imgs: MutableList<String?> = mutableListOf(
            "https://randomuser.me/api/portraits/men/1.jpg",
            "https://randomuser.me/api/portraits/men/2.jpg",
            "https://randomuser.me/api/portraits/men/3.jpg",
            null,
            "https://randomuser.me/api/portraits/men/5.jpg",
            "https://randomuser.me/api/portraits/men/6.jpg"
            )

        for (i in 100 downTo 0) {
            val p = (0..5).random()
            val r = (0..99999999).random()
            users.add(User(imgs[p], names[p], i, "@"+names[p]+r))
        }
        return users
    }

}