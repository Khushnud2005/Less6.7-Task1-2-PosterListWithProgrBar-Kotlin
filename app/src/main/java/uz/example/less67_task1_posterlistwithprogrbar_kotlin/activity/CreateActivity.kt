package uz.example.less67_task1_posterlistwithprogrbar_kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import uz.example.less67_task1_posterlistwithprogrbar_kotlin.R

class CreateActivity : AppCompatActivity() {
    lateinit var et_idUser: EditText
    lateinit var et_title: EditText
    lateinit var et_post: EditText
    lateinit var btn_create: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        initViews()
    }
    fun initViews(){
        et_idUser = findViewById<EditText>(R.id.et_userIdCreate)
        et_title = findViewById<EditText>(R.id.et_titleCreate)
        et_post = findViewById<EditText>(R.id.et_textCreate)
        btn_create = findViewById<Button>(R.id.btn_submitCreate)

        btn_create.setOnClickListener(View.OnClickListener {
            val title: String = et_title.getText().toString()
            val post: String = et_post.getText().toString().trim { it <= ' ' }
            val id_user: String = et_idUser.getText().toString().trim { it <= ' ' }
            val intent = Intent()
            intent.putExtra("title", title)
            intent.putExtra("post", post)
            intent.putExtra("id_user", id_user)
            setResult(78, intent)
            super@CreateActivity.onBackPressed()
        })
    }
}