package com.example.ex20231006

import android.content.ContextParams
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.android.volley.Request.Method
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    
    // Request : 실제 요청/응답 처리
    lateinit var request : StringRequest
    // RequestQueue : 요청을 서버로 보내주고 응답을 받아오는 기능
    var queue : RequestQueue? = null
    
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val etNick : EditText = findViewById(R.id.etNick)
        val btnReq : Button = findViewById(R.id.btnReq)
        
        if (queue == null) queue = Volley.newRequestQueue(this@MainActivity)

        btnReq.setOnClickListener {
            val nick = etNick.text.toString() // 서버로 보낼 데이터
            val url = "http://192.168.0.2:5500/chat"
            request = object : StringRequest(
                // 1) 전송방식
                Method.POST,
                url,
                {
                    response->
                },
                {
                    error->
                }
            ){
                // 데이터를 보내는 기능을 가진 함수
                override fun getParams() : MutableMap<String,String>{
                    val params = HashMap<String, String>()
                    params.put("nick",nick)
                    return params
                }
            } // Post방식으로 요청만드는게 끝
            queue?.add(request)

        }
        
        
        
        
        
    }
}