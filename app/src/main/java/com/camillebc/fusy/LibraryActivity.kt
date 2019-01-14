package com.camillebc.fusy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.camillebc.fusy.data.Fiction
import com.camillebc.fusy.data.FictionRepository
import com.camillebc.fusy.data.FictionViewModel
import com.camillebc.fusy.di.Injector
import com.camillebc.fusy.fragments.FictionListFragment
import com.camillebc.fusy.utilities.addFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LibraryActivity : AppCompatActivity(), FictionListFragment.OnListFragmentInteractionListener {
    @Inject lateinit var fictionRepository: FictionRepository
    private lateinit var fictionViewModel: FictionViewModel

    init {
        Injector.fictionComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        fictionViewModel = ViewModelProviders.of(this).get(FictionViewModel::class.java)
        val favoriteFragment = FictionListFragment()
        addFragment(favoriteFragment, R.id.library_fragment_layout)

        GlobalScope.launch(Dispatchers.IO) {
            val favouriteFictionList = fictionRepository.getAll() as MutableList<Fiction>?
            withContext(Dispatchers.Default) {
                fictionViewModel.fictionList.postValue(favouriteFictionList)
            }
        }
    }

    override fun onListFragmentInteraction(item: Fiction?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
