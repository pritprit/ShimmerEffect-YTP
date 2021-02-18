package com.eunidev.shimmereffectytp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eunidev.shimmereffectytp.databinding.ActivityMainBinding
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.swipeRefreshLayout.setOnRefreshListener {
            refresh()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        val list = ArrayList<ContentModel>()
        list.add(ContentModel(Random.nextInt(), "Title1", "Decription1"))
        list.add(ContentModel(Random.nextInt(), "Title2", "Decription2"))
        list.add(ContentModel(Random.nextInt(), "Title3", "Decription3"))
        list.add(ContentModel(Random.nextInt(), "Title4", "Decription4"))
        list.add(ContentModel(Random.nextInt(), "Title5", "Decription5"))
        list.add(ContentModel(Random.nextInt(), "Title6", "Decription6"))
        list.add(ContentModel(Random.nextInt(), "Title7", "Decription7"))
        list.add(ContentModel(Random.nextInt(), "Title8", "Decription8"))
        list.add(ContentModel(Random.nextInt(), "Title9", "Decription9"))
        list.add(ContentModel(Random.nextInt(), "Title10", "Decription10"))

        adapter = RVAdapter(this, list)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return false
            }
        })

        Handler(Looper.getMainLooper()).postDelayed({
            adapter.setShimmer(false)
            adapter.notifyDataSetChanged()
        }, 4000)

    }

    private fun refresh() {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(0,0)
        finish()
        overridePendingTransition(0,0)
    }
}