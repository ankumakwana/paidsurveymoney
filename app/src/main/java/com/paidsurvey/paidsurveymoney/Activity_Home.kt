package com.paidsurvey.paidsurveymoney

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import kotlinx.android.synthetic.main.activity_home.*
import android.view.LayoutInflater
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import android.widget.ImageView
import android.view.ViewGroup
import android.widget.TextView
import java.util.*


class Activity_Home : AppCompatActivity() {
    private val mTabLayout: TabLayout? = null
    private val mTabsIcons = intArrayOf(R.drawable.search, R.drawable.search, R.drawable.search,R.drawable.search)
    val maurlArray = arrayOf("https://www.clixsense.com/?r=7407583&s=101",
            "https://www.prizerebel.com/index.php?r=abdullashamsi",
            "http://palmresearch.com/ref/1644593","http://paidsurveyuae.blogspot.com/")
    var mUrlArray= ArrayList<UrlModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setActionBarHome("Clixsense")

        var obj1=UrlModel("https://www.clixsense.com/?r=7407583&s=101","Clixsense")
        mUrlArray.add(obj1)
        var obj2=UrlModel("https://www.prizerebel.com/index.php?r=abdullashamsi","Prize Rebel")
        mUrlArray.add(obj2)
        var obj3=UrlModel( "http://palmresearch.com/ref/1644593","Palmresearch")
        mUrlArray.add(obj3)
        var obj4=UrlModel("http://paidsurveyuae.blogspot.com/","Help & Tips")
        mUrlArray.add(obj4)
        val pagerAdapter = MyPagerAdapter(supportFragmentManager,mUrlArray)
        viewPager.setAdapter(pagerAdapter);
        tab_layout!!.setupWithViewPager(viewPager)

        for (i in 0 until tab_layout.getTabCount()) {
            val tab = tab_layout.getTabAt(i)
            tab!!.setCustomView(pagerAdapter.getTabView(i))
        }

        tab_layout.getTabAt(0)!!.getCustomView()!!.setSelected(true)
    }

    private inner class MyPagerAdapter (fragmentManager: FragmentManager, maurlArray: ArrayList<UrlModel>) : FragmentPagerAdapter(fragmentManager) {

        val PAGE_COUNT = 3

        private val mTabsTitle = arrayOf("Recents", "Favorites", "Nearby")

        fun getTabView(position: Int): View {
            // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
            val view = LayoutInflater.from(this@Activity_Home).inflate(R.layout.custom_tab, null)
            val icon = view.findViewById(R.id.icon) as ImageView
            icon.setImageResource(mTabsIcons[position])
            return view
        }

        override fun getItem(pos: Int): Fragment? {
            var obj=mUrlArray.get(pos)
            return PageFragment.newInstance(obj.url,obj.name)
//            when (pos) {
//                0 -> return PageFragment.newInstance(1)
//                1 -> return PageFragment.newInstance(2)
//                2 -> return PageFragment.newInstance(3)
//            }
            return null
        }

        override fun getCount(): Int {
            return maurlArray.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return maurlArray[position]
        }
    }
    fun setActionBarHome(mName: String) {
        val actionBarLayout = layoutInflater.inflate(R.layout.toolbar, null) as ViewGroup
        var actionBar = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(actionBarLayout);
        var title=actionBarLayout.findViewById<TextView>(R.id.title)
        title.setText(mName)


    }


}
