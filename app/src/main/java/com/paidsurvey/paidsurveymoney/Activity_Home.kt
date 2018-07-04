package com.paidsurvey.paidsurveymoney

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import kotlinx.android.synthetic.main.activity_home.*
import android.view.LayoutInflater
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.widget.TextView
import java.util.*
import android.support.v7.app.ActionBar
import android.view.Window
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.content.Intent




class Activity_Home : AppCompatActivity() {
    private val mTabsIcons = intArrayOf(R.drawable.logo_one, R.drawable.logo_two, R.drawable.logo_four,R.drawable.logo_three)
    private val mTabText = arrayOf("PrizeRebel", "Clixsense", "PalmResearch","Help","Share")

    var mUrlArray= ArrayList<UrlModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        //setActionBarHome("")

        var obj1=UrlModel("https://www.prizerebel.com/index.php?r=abdullashamsi","Prize Rebel")
        mUrlArray.add(obj1)
        var obj2=UrlModel("https://www.clixsense.com/?r=7407583&s=101","Clixsense")
        mUrlArray.add(obj2)
        var obj3=UrlModel( "http://palmresearch.com/ref/1644593","Palmresearch")
        mUrlArray.add(obj3)
        var obj4=UrlModel("http://paidsurveyuae.blogspot.com/","Help & Tips")
        mUrlArray.add(obj4)
        var obj5=UrlModel("http://paidsurveyuae.blogspot.com/","Share")
        mUrlArray.add(obj5)
        val pagerAdapter = MyPagerAdapter(supportFragmentManager,mUrlArray,Activity_Home@this)

        viewPager.setAdapter(pagerAdapter)
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {


            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                // Check if this is the page you want.
                var title=pagerAdapter.getPageTitle(viewPager.currentItem)
                if(title== "Share"){
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.paidsurvey.paidsurveymoney")
                    sendIntent.type = "text/plain"
                    startActivity(Intent.createChooser(sendIntent,"Share via"))

                }

            }
        })

        tab_layout!!.setupWithViewPager(viewPager)

        for (i in 0 until tab_layout.getTabCount()) {
            val tab = tab_layout.getTabAt(i)
            tab!!.setCustomView(pagerAdapter.getTabView(i))
        }

        tab_layout.getTabAt(0)!!.getCustomView()!!.setSelected(true)
    }

    private inner class MyPagerAdapter (fragmentManager: FragmentManager, maurlArray: ArrayList<UrlModel>, activity_Home: Activity_Home) : FragmentPagerAdapter(fragmentManager) {

        var mDialog:Dialog

        init {
            mDialog= Dialog(activity_Home)
        }
        fun getTabView(position: Int): View {
            // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
            val view = LayoutInflater.from(this@Activity_Home).inflate(R.layout.custom_tab, null)
        //    val icon = view.findViewById(R.id.icon) as ImageView
            val name = view.findViewById(R.id.name) as TextView
            name.setText(mTabText[position])
         //   icon.setImageResource(mTabsIcons[position])
            return view
        }

        override fun getItem(pos: Int): Fragment? {
            var obj = mUrlArray.get(pos)
            Log.d("sdsf", obj.name)
                return PageFragment.newInstance(obj.url, obj.name)


        }

        override fun getCount(): Int {
            return mUrlArray.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            var obj=mUrlArray.get(position)
            return obj.name
        }
    }
    fun setActionBarHome(mName: String) {
       // val actionBarLayout = layoutInflater.inflate(R.layout.toolbar, null) as ViewGroup
        var actionBar = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(false);
        actionBar!!.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        val viewOBJ = layoutInflater.inflate(R.layout.toolbar, null)
        val layoutParams = android.support.v7.app.ActionBar.LayoutParams(android.app.ActionBar.LayoutParams.MATCH_PARENT, android.app.ActionBar.LayoutParams.MATCH_PARENT)
        actionBar.setCustomView(viewOBJ, layoutParams)
        var title=viewOBJ.findViewById<TextView>(R.id.title)
        title.setText(mName)


    }


}
