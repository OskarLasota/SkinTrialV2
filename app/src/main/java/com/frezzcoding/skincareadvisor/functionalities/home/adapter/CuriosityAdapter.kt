package com.frezzcoding.skincareadvisor.functionalities.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.frezzcoding.skincareadvisor.R
import com.frezzcoding.skincareadvisor.data.Curiosity
import com.squareup.picasso.Picasso

class CuriosityAdapter(var context : Context,
                       itemList : ArrayList<Curiosity>,
                       isInfinite : Boolean,
                       private var onClickListener : OnItemClickListener) : LoopingPagerAdapter<Curiosity>(itemList, isInfinite)
{
    override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
        return LayoutInflater.from(context).inflate(R.layout.item_pager, container, false)
    }

    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        itemList?.let { list ->
            val adImage = convertView.findViewById<ImageView>(R.id.iv_ad)
            val description = convertView.findViewById<TextView>(R.id.description)
            val image = convertView.findViewById<ImageView>(R.id.image)

            if (list[listPosition].ad == "true") {
                adImage.visibility = View.VISIBLE
                adImage.setOnClickListener {
                    onClickListener.onItemClick(list[listPosition])
                }
            }else{
                adImage.visibility = View.GONE
            }

            description.text = list[listPosition].description
            Picasso.get().load(list[listPosition].image_url).into(image)
        }
    }


    interface OnItemClickListener{
        fun onItemClick(curiosity: Curiosity)
    }


}