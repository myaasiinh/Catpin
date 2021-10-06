package com.myaasiinh.catpin.ui.main.view


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.myaasiinh.catpin.R
import com.myaasiinh.catpin.data.model.Catpin
import com.myaasiinh.catpin.ui.main.adapter.CatpinAdapter
import com.myaasiinh.catpin.ui.main.viewmodel.CatpinViewModel
import com.myaasiinh.catpin.utils.listener.Listener

class MainActivity : AppCompatActivity(R.layout.activity_main),
    Listener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var catpinviewModel: CatpinViewModel
    private lateinit var  catpinAdapter: CatpinAdapter
    private lateinit var catpinList: ArrayList<Catpin>
    private lateinit var searchView: SearchView
    private lateinit var toolbar : MaterialToolbar
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        toolbar = findViewById(R.id.topAppBar)
        toolbar.setNavigationOnClickListener {
            //handle click press toolbar app
        }

        setSupportActionBar(toolbar)
        catpinAdapter = CatpinAdapter(applicationContext, ArrayList<Catpin>(), this)
        initialiseRecyclerView()

        catpinviewModel = ViewModelProvider(this).get(CatpinViewModel::class.java)
        catpinviewModel.getCardsData(this)?.observe(this, Observer {
            catpinAdapter.setData(it as ArrayList<Catpin>)
            catpinList = it
        })

        val itemTouchHelper=ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }

    private fun initialiseRecyclerView() {
        recyclerView = findViewById(R.id.recylerviewhome)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = catpinAdapter


        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.settingmenu, menu)
        val search= menu?.findItem(R.id.searchItems)
        searchView=search?.actionView as SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null)
                    getItemsFromDB(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null)
                    getItemsFromDB(newText)
                return true
            }

        })
        return true
    }

    private fun getItemsFromDB(data:String)
    {
        var searchText: String = "%$data%"
        catpinviewModel.search(this,searchText)?.observe(this, Observer  {
            Log.d("main", "$it")
            catpinAdapter.setData(it as ArrayList<Catpin>)
        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tambahkanmemo -> {
                //handle move create new memo
                val intent = Intent(this, LainnyaActivity::class.java)
                startActivity(intent)

            }

            R.id.tentangapp -> {
                //handle move detail tentang app layout
                val intent = Intent(this, TentangAplikasiActivity::class.java)
                startActivity(intent)

            }
            R.id.keluarapp -> {
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                System.exit(1)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClickListener(position: Int) {
        val intent=Intent(this, TambahkanmemoActivity::class.java)
        intent.putExtra("catpin",catpinList[position])
        startActivity(intent)
    }
    private val simpleCallback= object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position=viewHolder.adapterPosition
            val catpin=catpinList[position]
            when(direction)
            {
                ItemTouchHelper.RIGHT->{
                    catpinviewModel.delete(this@MainActivity,catpin)
                    catpinAdapter.notifyDataSetChanged()
                }
                ItemTouchHelper.LEFT->{
                    catpinviewModel.delete(this@MainActivity,catpin)
                    catpinAdapter.notifyDataSetChanged()
                }
            }
        }

    }

}