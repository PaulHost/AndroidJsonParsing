package ph.hostev.paul.androidjsonparsing

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import ph.hostev.paul.androidjsonparsing.adapters.PostsAdapter
import ph.hostev.paul.androidjsonparsing.httpClient.modules.Post


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycler = findViewById<RecyclerView>(R.id.recycler_view)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        App.http().getPosts { posts: List<Post> ->
            Handler(mainLooper).post(object : Runnable {
                override fun run() {
                    if (posts.isNotEmpty()) {
                        recycler.adapter = PostsAdapter(posts)
                    }
                }
            })
        }
    }
}
