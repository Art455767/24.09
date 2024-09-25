package ru.netology.nmedia.dto

import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {

            author.text = post.author
            published.text = post.published
            content.text = post.content

            like.text = formatCount(post.likes)
            like.setOnClickListener { onInteractionListener.onLike(post) }

            share.text = formatCount(post.shares)
            share.setOnClickListener { onInteractionListener.onShare(post) }

            menu.text = ""
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.menu_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }

                            R.id.edit -> {
                                onInteractionListener.onEdit(post)

                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }
        }
    }
}