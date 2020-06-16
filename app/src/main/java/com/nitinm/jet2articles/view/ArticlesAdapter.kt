package com.nitinm.jet2articles.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nitinm.jet2articles.R
import com.nitinm.jet2articles.data.model.ArticlesData
import com.nitinm.jet2articles.data.model.MediaData
import com.nitinm.jet2articles.data.model.UserData
import com.nitinm.jet2articles.util.getNumberInDisplayFormat
import com.nitinm.jet2articles.util.timeFromPublished
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_item_layout.view.*

class ArticlesAdapter(articleList: ArrayList<ArticlesData>) :
    RecyclerView.Adapter<ArticlesAdapter.ArticlesViewHolder>() {

    var articleList: ArrayList<ArticlesData> = arrayListOf()
        set(value) {
            articleList.addAll(value)
            notifyDataSetChanged()
        }

    init {
        this.articleList = articleList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder =
        ArticlesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.article_item_layout, parent, false))


    override fun getItemCount(): Int = articleList.size


    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        holder.bind(articleList.get(position))
    }

    class ArticlesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(articlesData: ArticlesData) {
            val userData: UserData = articlesData.userList.get(0)
            val mediaData: MediaData = articlesData.mediaList.get(0)
            Picasso.get().load(userData.avatar).into(itemView.userImageView)
            Picasso.get().load(mediaData.imageurl).into(itemView.articleImageView)
            itemView.userNameTextView.text = "${userData.name} ${userData.lastname}"
            itemView.userDesignationTextView.text = userData.designation
            itemView.articleContentTextView.text = articlesData.content
            itemView.articleTitleTextView.text = mediaData.title
            itemView.articleUrlTextView.text = mediaData.url
            itemView.likesTextView.text = articlesData.likes.getNumberInDisplayFormat(
                itemView.context,
                itemView.context.getString(R.string.likes_text)
            )
            itemView.comments_textView.text = articlesData.comments.getNumberInDisplayFormat(
                itemView.context,
                itemView.context.getString(R.string.comments_text)
            )
            itemView.durationTextView.text = timeFromPublished(itemView.context, articlesData.createdAt)
        }
    }
}