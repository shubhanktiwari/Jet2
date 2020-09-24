package com.example.article.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.article.R
import com.example.article.model.response.ArticleItem
import com.example.article.utils.Constant
import java.text.SimpleDateFormat
import java.util.*

class ArticleViewHolder(itemView: View, private val context: Context) :
    RecyclerView.ViewHolder(itemView) {
    private val userNameTextView: TextView = itemView.findViewById(R.id.userName_TextView)
    private val userDesigTextView: TextView = itemView.findViewById(R.id.userDesign_TextView)
    private val userImageView: ImageView = itemView.findViewById(R.id.userProfile_ImageView)
    private val postCreatedAt: TextView = itemView.findViewById(R.id.postCreatedAt_TextView)
    private val articleImageView: ImageView = itemView.findViewById(R.id.articleImage_ImageView)
    private val articleContentTextView: TextView =
        itemView.findViewById(R.id.articleContent_TextView)
    private val articleTitleTextView: TextView =
        itemView.findViewById(R.id.articleTitle_TextView)
    private val articleUrlTextView: TextView = itemView.findViewById(R.id.articleUrl_TextView)
    private val likesTextView: TextView = itemView.findViewById(R.id.likes_TextView)
    private val commentsTextView: TextView = itemView.findViewById(R.id.comment_TextView)

    @SuppressLint("SimpleDateFormat")
    fun initialize(item: ArticleItem) {
        var articleTitle: String? = null
        var articleUrl: String? = null
        var articleImage: String? = null
        val likes = item.likes / 1000.toFloat()
        val comments = item.comments / 1000.toFloat()
        val articleContent: String? = item.content
        val endDate =
            stringToDate(SimpleDateFormat(Constant.DATE_FORMAT_FROM_API).format(Date()))

        for (user in item.user) {
            userNameTextView.text = String.format(
                context.resources.getString(R.string.getFullNameText),
                user.name,
                user.lastname
            )
            userDesigTextView.text = user.designation
            Glide.with(context)
                .load(user.avatar)
                .placeholder(R.drawable.progress_bar)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(userImageView)
            val startDate = stringToDate(user.createdAt)
            postCreatedAt.text = printDifference(startDate, endDate)
        }

        for (media in item.media) {
            articleTitle = media.title
            articleUrl = media.url
            articleImage = media.image
        }

        if (articleImage == null) {
            articleImageView.visibility = View.GONE
        } else {
            articleImageView.visibility = View.VISIBLE
            Glide.with(context)
                .load(articleImage)
                .placeholder(R.drawable.progress_bar)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(articleImageView)
        }

        if (articleTitle == null) {
            articleTitleTextView.visibility = View.GONE
        } else {
            articleTitleTextView.visibility = View.VISIBLE
            articleTitleTextView.text = articleTitle
        }

        if (articleUrl == null) {
            articleUrlTextView.visibility = View.GONE
        } else {
            articleUrlTextView.visibility = View.VISIBLE
            articleUrlTextView.text = articleUrl
        }

        if (articleContent == null) {
            articleContentTextView.visibility = View.GONE
        } else {
            articleContentTextView.visibility = View.VISIBLE
            articleContentTextView.text = articleContent
        }

        likesTextView.text =
            String.format(context.resources.getString(R.string.getLikesText), likes)
        commentsTextView.text =
            String.format(context.resources.getString(R.string.getCommentsText), comments)
    }

    private fun stringToDate(stringDate: String): Date? {
        val simpleDateFormat =
            SimpleDateFormat(Constant.DATE_FORMAT_FROM_API, Locale.getDefault())
        return simpleDateFormat.parse(stringDate)
    }

    private fun printDifference(startDate: Date?, endDate: Date?): String {
        var different = endDate?.time?.minus(startDate?.time!!)

        val secondsInMilli: Long = 1000
        val minutesInMilli = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24
        val elapsedDays = different?.div(daysInMilli)
        different = different?.rem(daysInMilli)
        val elapsedHours = different?.div(hoursInMilli)
        different = different?.rem(hoursInMilli)
        val elapsedMinutes = different?.div(minutesInMilli)
        different = different?.rem(minutesInMilli)
        val elapsedSeconds = different?.div(secondsInMilli)

        return elapsedDays.toString() + "d " + elapsedHours.toString() + "h " + elapsedMinutes.toString() + "m " + elapsedSeconds.toString() + "s"
    }
}