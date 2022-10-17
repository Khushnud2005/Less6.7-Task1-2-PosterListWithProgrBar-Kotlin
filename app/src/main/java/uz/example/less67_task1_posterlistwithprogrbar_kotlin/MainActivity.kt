package uz.example.less67_task1_posterlistwithprogrbar_kotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import uz.example.less67_task1_posterlistwithprogrbar_kotlin.activity.CreateActivity
import uz.example.less67_task1_posterlistwithprogrbar_kotlin.adapter.PosterAdapter
import uz.example.less67_task1_posterlistwithprogrbar_kotlin.model.Poster
import uz.example.less67_task1_posterlistwithprogrbar_kotlin.network.VolleyHandler
import uz.example.less67_task1_posterlistwithprogrbar_kotlin.network.VolleyHttp

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var floating: FloatingActionButton
    var posters = ArrayList<Poster>()
    lateinit var pb_loading: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }
    fun initViews(){
        pb_loading = findViewById(R.id.pb_loading)
        floating = findViewById(R.id.floating)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        apiPosterList()

        floating.setOnClickListener { openCreateActivity() }

        val extras = intent.extras
        if (extras != null) {
            Log.d("###", "extras not NULL - ")
            val edit_title = extras.getString("title")
            val edit_post = extras.getString("post")
            val edid_userId = extras.getString("id_user")
            val id = extras.getString("id")!!
            val poster = Poster(id.toInt(),edid_userId!!.toInt(), edit_title!!, edit_post!!)
            Toast.makeText(this@MainActivity,"Post Prepared to Update",Toast.LENGTH_LONG).show()
            apiPosterEdit(poster)
        }
    }
    var launchSomeActivity = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == 78) {
            val data = result.data
            if (data != null) {
                val new_title = data.getStringExtra("title")
                val new_post = data.getStringExtra("post")
                val new_userId = data.getStringExtra("id_user")
                val poster = Poster(new_userId!!.toInt(), new_title!!, new_post!!)
                Toast.makeText(this@MainActivity, "Title modified", Toast.LENGTH_LONG).show()
                apiPosterCreate(poster)
            }
            // your operation....
        } else {
            Toast.makeText(this@MainActivity, "Operation canceled", Toast.LENGTH_LONG).show()
        }
    }

    fun refreshAdapter(posters: ArrayList<Poster>) {
        val adapter = PosterAdapter(this, posters)
        recyclerView.setAdapter(adapter)
    }
    fun openCreateActivity() {
        val intent = Intent(this@MainActivity, CreateActivity::class.java)
        launchSomeActivity.launch(intent)
    }
    fun dialogPoster(poster: Poster?) {
        AlertDialog.Builder(this)
            .setTitle("Delete Poster")
            .setMessage("Are you sure you want to delete this poster?")
            .setPositiveButton(
                android.R.string.yes
            ) { dialog, which -> apiPosterDelete(poster!!) }
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
    private fun apiPosterList() {
        pb_loading.visibility = View.VISIBLE
        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), object : VolleyHandler {
            override fun onSuccess(response: String?) {
                pb_loading.visibility = View.GONE
                val postArray = Gson().fromJson(
                    response,
                    Array<Poster>::class.java
                )
                posters.clear()
                for (poster in postArray) {
                    posters.add(poster)
                }
                refreshAdapter(posters)
                Log.d("@@@onResponse ", "" + posters.size)
            }

            override fun onError(error: String?) {
                Log.d("@@@onErrorResponse ", error!!)
            }
        })
    }
    private fun apiPosterDelete(poster: Poster) {
        pb_loading.visibility = View.VISIBLE
        VolleyHttp.del(VolleyHttp.API_DELETE_POST+poster.id, object : VolleyHandler {
            override fun onSuccess(response: String?) {
                Toast.makeText(this@MainActivity,poster.title + " Deleted",Toast.LENGTH_LONG).show()
                Log.d("@@@onDelete",response!!)
                apiPosterList()
            }

            override fun onError(error: String?) {
                Log.d("@@@onErrorResponse ", error!!)
            }
        })
    }
    private fun apiPosterCreate(poster: Poster) {
        pb_loading.visibility = View.VISIBLE
        VolleyHttp.post(
            VolleyHttp.API_CREATE_POST,
            VolleyHttp.paramsCreate(poster),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    Toast.makeText(this@MainActivity,poster.title + " Created",Toast.LENGTH_LONG).show()
                    Log.d("@@@onResponse ", response!!)
                    apiPosterList()
                }

                override fun onError(error: String?) {
                    Log.d("@@@onErrorResponse ", error!!)
                }
            })
    }

    private fun apiPosterEdit(poster: Poster) {
        pb_loading.visibility = View.VISIBLE
        VolleyHttp.put(
            VolleyHttp.API_UPDATE_POST + poster.id,
            VolleyHttp.paramsUpdate(poster),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    Toast.makeText(this@MainActivity,poster.title + " Updated",Toast.LENGTH_LONG).show()
                    apiPosterList()
                }

                override fun onError(error: String?) {
                    Log.d("@@@onErrorResponse ", error!!)
                }
            })
    }
}