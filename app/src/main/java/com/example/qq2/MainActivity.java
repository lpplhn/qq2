package com.example.qq2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ChatModel> chatModelList = new ArrayList<ChatModel>();
    RecyclerView recyclerView;
    ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecycler();
        this.setTitle("😂");

        Button sendBtn = (Button) findViewById(R.id.button);
        EditText editText = (EditText) findViewById(R.id.input_text);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                if (!msg.isEmpty()) {
                    sendMessage(msg);
                    reply(msg);
                    editText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Cant be empty！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initRecycler() {
        chatModelList.clear();
        recyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        //给recyclerView创建布局方式
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //创建适配器
        chatAdapter = new ChatAdapter(chatModelList);
        recyclerView.setAdapter(chatAdapter);
    }

    /**
     * 发送信息
     *
     * @param message
     */
    void sendMessage(String message) {
        ChatModel chatModel = new ChatModel(R.drawable.cat2, "金城武", message, ChatModel.SEND);
        chatModelList.add(chatModel);
        chatAdapter.notifyItemInserted(chatModelList.size() - 1);
        recyclerView.scrollToPosition(chatModelList.size() - 1);
    }

    /**
     * 接收信息
     *
     * @param message
     */
    void receiveMessage(String message) {
        ChatModel chatModel = new ChatModel(R.drawable.dujiao, "邱淑贞", message, ChatModel.RECEIVE);
        chatModelList.add(chatModel);
        chatAdapter.notifyItemInserted(chatModelList.size() - 1);
        recyclerView.scrollToPosition(chatModelList.size() - 1);
    }

    /**
     * 回复
     * @param msg
     */
    void reply(String msg){
        String rMsg="";
        switch (msg){
            case "hello":
                rMsg="hello! How are you？";
                break;
            case  "How old are you?":
                rMsg="22";
                break;
        }
        if(!rMsg.isEmpty()){
            receiveMessage(rMsg);
        }
    }

}