
import android.view.View
import android.widget.Toast
import com.picpay.desafio.android.*
import com.picpay.desafio.android.utils.mvp.RequestContract.Presenter
import com.picpay.desafio.android.utils.test.UserTestPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestPresenter(private val view: MainActivity) :

    Presenter {
    override fun requestContactsList(service: PicPayService) {
        service.getUsers()
            .enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    val message = view.applicationContext.getString(R.string.error)
                    view.progressBar.visibility = View.GONE
                    view.recyclerView.visibility = View.GONE
                    Toast.makeText(view.applicationContext, message, Toast.LENGTH_SHORT).show()
                    view.srlList.isRefreshing = false
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                    view.progressBar.visibility = View.GONE

                    if (BuildConfig.DEBUG /*false*/) {
                        view.maadapter.users = UserTestPresenter().mockUsers()
                    } else {
                        view.maadapter.users = response.body()!!
                    }

                    view.srlList.isRefreshing = false
                }



            }
        )
    }
}