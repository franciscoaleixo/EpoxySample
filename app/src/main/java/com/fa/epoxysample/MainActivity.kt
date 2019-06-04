package com.fa.epoxysample

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fa.epoxysample.bundles.BundlesController
import com.fa.epoxysample.others.BundleModel
import com.fa.epoxysample.others.model.AppBundle
import com.fa.epoxysample.others.model.HomeBundle
import kotlinx.android.synthetic.main.activity_main.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

  private var bundlesController: BundlesController = BundlesController()

  var isAlternateContent = false
  var hasMore = true


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    recyclerView.setController(bundlesController)
    loadData(false, false)

    // Pull to refresh
    refresh_layout.setOnRefreshListener {
      bundlesController = BundlesController()
      loadData(false, !isAlternateContent)
      refresh_layout.isRefreshing = false
      isAlternateContent = !isAlternateContent
      if(!isAlternateContent) hasMore = true
    }

    // Disgusting load more
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
        if(layoutManager.itemCount - layoutManager.findLastVisibleItemPosition() <= 2){
          if(hasMore) {
            loadData(true, false)
            hasMore = false
          }
        }
      }
    })


  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    bundlesController.onSaveInstanceState(outState)
  }

  override fun onRestoreInstanceState(savedInstanceState: Bundle) {
    super.onRestoreInstanceState(savedInstanceState)
    bundlesController.onRestoreInstanceState(savedInstanceState)
  }


  fun loadData(loadMore: Boolean, alternativeDataset: Boolean){
    val content = if(loadMore) R.raw.morecontent else if(alternativeDataset) R.raw.refreshcontent else R.raw.samplecontent

    GlobalScope.launch(Dispatchers.Main) {
      val result = async {
        if(loadMore) delay(2500) // Simple artificial delay
        getSampleData(content)
      }

      bundlesController.setData(result.await(), !loadMore)
      if(!loadMore){
        recyclerView.refreshController(bundlesController)
      }
    }
  }

  fun getSampleData(contentId: Int): List<HomeBundle>{
    val homeBundles = ArrayList<HomeBundle>()
    val reader = InputStreamReader(getResources().openRawResource(contentId), "UTF-8")
    val listType = object : TypeToken<ArrayList<BundleModel>>() {}.type
    val bundles = Gson().fromJson<ArrayList<BundleModel>>(reader, listType)

    for(bundle in bundles){
      homeBundles.add(AppBundle(bundle))
    }

    return homeBundles
  }

}
