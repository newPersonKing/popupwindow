package com.gy.mypopupwindow.adpter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gy.mypopupwindow.R

class RecyclerAdapter(context:Context) :RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){

    var mStringList= arrayListOf<String>()
    var context:Context
    var onItemClickListerner:OnItemClickListener?=null
    init {
        this.context=context
    }

    fun setOnitemClickListerner(itemListerner:OnItemClickListener){
        onItemClickListerner=itemListerner
    }

    fun addAll(datas:ArrayList<String>){
        mStringList.clear()
        mStringList.addAll(datas)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int =mStringList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.mTextView.text=mStringList[position]

        holder.mTextView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View) {
                if (onItemClickListerner!=null){
                    onItemClickListerner?.onItemClick(v,position)
                }
            }
        })

        holder.mImageView.setOnClickListener {
            if (onItemClickListerner!=null){
                onItemClickListerner?.onItemClick(it,position)
            }
        }

        holder.mLeftTextView.setOnClickListener {
            if (onItemClickListerner!=null){
                onItemClickListerner?.onItemClick(it,position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var  view=LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)

        return MyViewHolder(view)
    }

    public interface OnItemClickListener{
        fun onItemClick(view:View,position: Int)
    }


    class MyViewHolder :RecyclerView.ViewHolder{
        lateinit var mTextView:TextView
        lateinit var mImageView:ImageView
        lateinit var mLeftTextView:TextView

        constructor(itemView:View):super(itemView){
            with(itemView){
                mLeftTextView=findViewById(R.id.left)
                mTextView=findViewById(R.id.item)
                mImageView=findViewById(R.id.more)
            }
        }
    }
}