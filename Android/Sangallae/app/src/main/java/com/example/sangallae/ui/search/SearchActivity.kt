package com.example.sangallae.ui.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sangallae.R
import com.example.sangallae.retrofit.models.Course
import com.example.sangallae.retrofit.*
import com.example.sangallae.retrofit.models.CourseItem
import com.example.sangallae.ui.detail.CourseDetailActivity
import com.example.sangallae.ui.detail.CourseDetailFragment
import com.example.sangallae.utils.RESPONSE_STATUS
import com.jeongdaeri.unsplash_app_tutorial.retrofit.RetrofitManager
import com.example.sangallae.utils.Constants.TAG
import com.example.sangallae.utils.Usage


class SearchActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private var courseList = ArrayList<CourseItem>()
    private lateinit var courseRecyeclerViewAdapter: CourseRecyclerViewAdapter
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private var page = 0;
    private var itemFinished = false
    private var query = ""

    private lateinit var mySearchViewEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setSupportActionBar(findViewById(R.id.search_toolbar))
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        if (Intent.ACTION_SEARCH == intent.action) {
//            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
//                Log.d("query", query)
//            }
//        }
     //   courseList = intent.getBundleExtra("array_bundle")?.getSerializable("corse_array_list") as ArrayList<Course>

        this.courseCollectionRecyclerViewSetting(this.courseList)

        courseRecyeclerViewAdapter.setOnItemClickListener(object : CourseRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data: Int, pos : Int) {
                Intent(this@SearchActivity, CourseDetailActivity::class.java).apply {
                    putExtra("id", data)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { startActivity(this) }
//                supportFragmentManager.beginTransaction().replace(
//                    R.id.search,
//                   CourseDetailFragment().apply {
//                        arguments = Bundle().apply {
//                            putInt("id", data)
//                        }
//                    }
//                ).commit()
            }
        })

    }

    private fun courseCollectionRecyclerViewSetting(courseList: ArrayList<CourseItem>) {
        recyclerView = findViewById(R.id.search_course_recycler_view)

        this.courseRecyeclerViewAdapter = CourseRecyclerViewAdapter()

        this.courseRecyeclerViewAdapter.submitList(courseList)

        recyclerView.layoutManager =
            GridLayoutManager(
                this,
                1,
                GridLayoutManager.VERTICAL,
                false
            )
        recyclerView.adapter = this.courseRecyeclerViewAdapter
        courseRecyeclerViewAdapter.notifyItemRangeChanged((page - 1) * 20, 20)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount-1

                // 스크롤이 끝에 도달했는지 확인
                if (!recyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount && !itemFinished) {
                    courseRecyeclerViewAdapter.loadItem()
                    searchCourseApiCall(query, ++page)
                    courseRecyeclerViewAdapter.deleteLoading()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.app_bar_search).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            searchView = this
            isFocusable = true
            isIconified = false
            requestFocusFromTouch()
            this.queryHint = getString(R.string.search_hint)

            setOnQueryTextListener(this@SearchActivity)


        }

        return true
    }


    override fun onQueryTextSubmit(query: String?): Boolean {

        Log.d("query", "SearchActivity - onQueryTextSubmit() called / query: $query")


        if (!query.isNullOrEmpty()) {
            this.query = query
            this.searchCourseApiCall(query, page)
            searchView.clearFocus()
        }
        this.findViewById<androidx.appcompat.widget.Toolbar>(R.id.search_toolbar)
            .collapseActionView()

        return true
    }

    private fun searchCourseApiCall(query: String, page: Int) {
        val retrofit = RetrofitManager(Usage.ACCESS)
        retrofit.searchCourses(keyword = query, order = "", page, completion = { status, list ->
            when(status){
                RESPONSE_STATUS.OKAY -> {
                    Log.d(TAG, "SearchActivity - searchCourseApiCall() called 응답 성공 / list.size : ${list?.size}")

                    if (list != null){
                        this.courseList.clear()
                        this.courseList = list
                        this.courseRecyeclerViewAdapter.submitList(this.courseList)
                        this.courseRecyeclerViewAdapter.notifyDataSetChanged()
                    }
                }
                RESPONSE_STATUS.NO_CONTENT -> {
                    Toast.makeText(this, "$query 에 대한 검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "검색을 할 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val userInputText = newText.let {
            it
        } ?: ""
        return true
    }

}


