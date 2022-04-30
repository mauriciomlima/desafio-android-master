package com.picpay.desafio.android


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User) {

        if (user.name!!.isNotEmpty()) {
            itemView.name.text = user.name
        }

        if (user.username!!.isNotEmpty()) {
            itemView.username.text = user.username
        }

        if (user.img != null && user.img.isNotEmpty()){
            itemView.progressBar.visibility = View.VISIBLE
            Picasso.get()
                .load(user.img)
                .error(R.drawable.ic_round_account_circle)
                .into(itemView.picture, object : Callback {
                    override fun onSuccess() {
                        itemView.progressBar.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        itemView.progressBar.visibility = View.GONE
                    }
                })
        } else {
            itemView.picture.setImageDrawable(itemView.context.getDrawable(R.drawable.ic_round_account_circle))
            itemView.progressBar.visibility = View.GONE
        }

    }
}