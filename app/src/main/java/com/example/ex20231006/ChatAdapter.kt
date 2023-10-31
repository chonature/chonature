package com.example.ex20231006

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ChatAdapter(val context : Context, val layout : Int, val data : ArrayList<ChatVO>)
    :RecyclerView.Adapter<ChatAdapter.ViewHolder>(){

    class ViewHolder(view : View):RecyclerView.ViewHolder(view){
    // onCreateViewHolder가 inflate한 레이아웃의 View들의 아이디값을 통해 찾아오는 곳
        val tvName : TextView
        val tvMsg : TextView
        init {
            tvName = view.findViewById(R.id.tvName)
            tvMsg = view.findViewById(R.id.tvMsg)
        }

    }
    // 1) 한 칸에 들어갈 디자인을 눈에 보이는 View로 바꿔서 ViewHolder클래스로 보내주는 기능
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(layout, parent, false)
        return ViewHolder(view)
    }
    // holder : tvName, tvMsg
    override fun onBindViewHolder(holder: ChatAdapter.ViewHolder, position: Int) {
        // ViewHolder클래스가 찾아온 뷰들을 컨트롤 할 수 있는곳
        // 데이터 + 디자인
        holder.tvName.text = data[position].name
        holder.tvMsg.text = data[position].msg
    }

    override fun getItemCount(): Int {
        return data.size
    }


}