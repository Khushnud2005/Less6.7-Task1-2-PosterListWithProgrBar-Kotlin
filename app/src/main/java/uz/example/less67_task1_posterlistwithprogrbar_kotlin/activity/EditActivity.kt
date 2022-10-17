package uz.example.less67_task1_posterlistwithprogrbar_kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import uz.example.less67_task1_posterlistwithprogrbar_kotlin.MainActivity
import uz.example.less67_task1_posterlistwithprogrbar_kotlin.R

class EditActivity : AppCompatActivity() {
    lateinit var et_idUser: EditText
    lateinit var et_title: EditText
    lateinit var et_post: EditText
    lateinit var btn_edit: Button
    lateinit var id: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        initViews()
    }
    fun initViews(){
        val extras = intent.extras
        et_idUser = findViewById<EditText>(R.id.et_userIdEdit)
        et_title = findViewById<EditText>(R.id.et_titleEdit)
        et_post = findViewById<EditText>(R.id.et_textEdit)
        btn_edit = findViewById<Button>(R.id.btn_submitEdit)
        if (extras != null) {
            Log.d("###", "extras not NULL - ")
            et_idUser.setText(extras.getString("user_id"))
            et_title.setText(extras.getString("title"))
            et_post.setText(extras.getString("body"))
            id = extras.getString("id")!!
        }
        btn_edit.setOnClickListener {
            val title = et_title.text.toString()
            val post = et_post.text.toString().trim { it <= ' ' }
            val id_user = et_idUser.text.toString().trim { it <= ' ' }
            val intent = Intent(this@EditActivity, MainActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("post", post)
            intent.putExtra("id_user", id_user)
            intent.putExtra("id", id)
            //setResult(88, intent)
            startActivity(intent)
        }
    }
}