package com.zj.play.view.project.list

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.zj.play.model.Article
import com.zj.play.view.project.ProjectRepository

/**
 * 版权：渤海新能 版权所有
 * @author zhujiang
 * 版本：1.5
 * 创建日期：2020/5/18
 * 描述：PlayAndroid
 *
 */
class ProjectListViewModel(context: Context) : ViewModel() {

    val articleList = ArrayList<Article>()

    private val pageLiveData = MutableLiveData<QueryArticle>()

    val articleLiveData = Transformations.switchMap(pageLiveData) { query ->
        ProjectRepository(context).getProject(query.page, query.cid)
    }

    fun getArticleList(page: Int, cid: Int) {
        pageLiveData.value = QueryArticle(page, cid)
    }

}

data class QueryArticle(var page: Int, var cid: Int)