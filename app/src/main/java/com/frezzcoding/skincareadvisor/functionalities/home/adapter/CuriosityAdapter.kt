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

class CuriosityAdapter(context : Context,
                       itemList : ArrayList<Curiosity>,
                       isInfinite : Boolean,
                       private var onClickListener : OnItemClickListener) : LoopingPagerAdapter<Curiosity>(context, itemList, isInfinite)
{
    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        if(listPosition <= itemList!!.size) {
            if (itemList!![listPosition].ad == "true") {
                var v = convertView.findViewById<ImageView>(R.id.iv_ad)
                v.visibility = View.VISIBLE
                v.setOnClickListener {
                    onClickListener.onItemClick(itemList!![listPosition])
                }
            }
        }
        val description = convertView.findViewById<TextView>(R.id.description)
        val image = convertView.findViewById<ImageView>(R.id.image)
        convertView.findViewById<View>(R.id.image)
            .setBackgroundColor(context.resources.getColor(android.R.color.background_light))
        description.text = itemList?.get(listPosition)?.description
        Picasso.get().load(itemList?.get(listPosition)?.image_url).into(image)
    }

    override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
        return LayoutInflater.from(context).inflate(R.layout.item_pager, container, false)
    }


    interface OnItemClickListener{
        fun onItemClick(curiosity: Curiosity)
    }


}