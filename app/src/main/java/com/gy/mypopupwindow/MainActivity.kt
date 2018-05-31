package com.gy.mypopupwindow

import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gy.mypopupwindow.adpter.RecyclerAdapter
import com.mrgao.luckly_popupwindow.LucklyPopopWindow
import com.mrgao.luckly_popupwindow.beans.DataBeans
import com.mrgao.luckly_popupwindow.utils.LinnerItemDivider
import com.mrgao.luckly_popupwindow.utils.ScreenUtils
import kotlinx.android.synthetic.main.activity_self.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter:RecyclerAdapter
    lateinit var mLucklyPopopWindow:LucklyPopopWindow
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_self)

        dividerRecycler.layoutManager=LinearLayoutManager(this)

        dividerRecycler.itemAnimator=DefaultItemAnimator()

        dividerRecycler.addItemDecoration(LinnerItemDivider(LinnerItemDivider.VERTICAL_LIST,Color.RED,1))

        adapter= RecyclerAdapter(this)

        dividerRecycler.adapter=adapter

        initData()
    }

    fun initData(){
        var Strings= arrayListOf<String>()

        for (i in 1..30){
            Strings.add("数据"+i)
        }

        adapter.addAll(Strings)

        mLucklyPopopWindow= LucklyPopopWindow(this)

        mLucklyPopopWindow.setViewMargin(false,20,0,0,0)

        mLucklyPopopWindow.setViewPadding(0,20,10,10)

        adapter.setOnitemClickListerner(object :RecyclerAdapter.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                //必须设置宽度
                mLucklyPopopWindow.width=ScreenUtils.dp2px(applicationContext,160f)
                //添加分割线(可选)
                mLucklyPopopWindow.addItemDecoration(LucklyPopopWindow.VERTICAL,Color.GRAY,1)
                //设置image不显示(可选)
                //mLucklyPopopWindow.setImageDisable(true);
                //设置image的大小(可选)
                mLucklyPopopWindow.setImageSize(20,20)

                mLucklyPopopWindow.setOnItemClickListener {
                    mLucklyPopopWindow.dismiss()
                }

                mLucklyPopopWindow.showAtLocation(window.decorView,view)

            }
        })
        mLucklyPopopWindow.setData(getData())
    }
 fun  getData():ArrayList<DataBeans>{
         var list= ArrayList<DataBeans>();
        var search= DataBeans()
        search.setData("查询");
        search.setBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.update));
        search.setTextColor(Color.RED);
        list.add(search);


        var add= DataBeans()
        add.setData("增加");
        add.setBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.add));
        add.setTextColor(Color.RED);
        list.add(add);

        var modify= DataBeans();
        modify.setData("修改");
        modify.setBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.modify));
        modify.setTextColor(Color.GRAY);
        list.add(modify);

        var delete= DataBeans();
        delete.setData("删除");
        delete.setBitmap(BitmapFactory.decodeResource(getResources(),R.mipmap.delete));
        delete.setTextColor(Color.YELLOW);
        list.add(delete);

        return list;
    }

}
