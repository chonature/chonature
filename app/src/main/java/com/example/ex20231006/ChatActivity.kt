package com.example.ex20231006

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class ChatActivity : AppCompatActivity() {

    // 실제 요청
    lateinit var request : StringRequest
    // 요청을 서버로 보내는 기능
    var queue : RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        // 1) RecyclerView의 위치 정해주기 (activity_chat.xml)
        // 2) 한 칸에 들어갈 디자인 만들기 (chat_item.xml)
        // 3) 항목에 데이터 - 이름, 메시지 내용(ChatVO)
        // chatList : ArrayList<ChatVO>
        val chatList : ArrayList<ChatVO> = ArrayList()
        // 4) RecyclerView.Adapter<VH>
        // - onCreateViewHolder : 한칸에 들어갈 디자인을 inflate해서 VH로 보내주는
        // - ViewHolder class : findViewById
        // - onBindViewHolder : 데이터 + 디자인
        // - getItemCounter : 항목의 갯수
        // 5) RecyclerView에 adapter적용
        // + layoutmanager

        // Node에 설정한 키
        //  이름 : name
        //  메시지 내용 : msg
        val etName : EditText = findViewById(R.id.etName)
        val etChat : EditText = findViewById(R.id.etChat)
        val btnSend : Button = findViewById(R.id.btnSend)
        val rv : RecyclerView = findViewById(R.id.rvChat)

        if (queue == null) queue = Volley.newRequestQueue(this@ChatActivity)

        val adapter = ChatAdapter(applicationContext, R.layout.chat_list, chatList)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this@ChatActivity)

        btnSend.setOnClickListener {

            val name = etName.text.toString()
            val msg = etChat.text.toString()

            chatList.add(ChatVO(name, msg))
            adapter.notifyDataSetChanged() // 어댑터 새로고침
            request = object : StringRequest(
                // 1) 전송방식
                Method.POST,
                // 2) url
                "http://192.168.0.2:5500/chat",
                // 3) 응답 성공
                {response->
                    // response : 결과값
                    // 응답 성공했을 때 실행시킬 코드
                },
                // 4) 응답 실패
                {error->
                    // error : Exception의 내용
                    // 응답 실패 했을 때 실행시킬 코드
                }
            ){
                // ctrl + o
                override fun getParams(): MutableMap<String, String>? {

                    val params = HashMap<String, String>()
                    params.put("name", name)
                    params.put("msg",msg)

                    return params
                }
            } // 실제 요청이 다 만들어진 곳
            request.setShouldCache(false)
            queue?.add(request)

        }

    }
}