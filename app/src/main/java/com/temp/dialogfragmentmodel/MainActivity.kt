package com.temp.dialogfragmentmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.temp.dialogfragment.DialogCallBack
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show_with_custon_view.setOnClickListener {
            var dialogWithCustomView = DialogWithCustomViewByCreateDialogImpl();
            dialogWithCustomView.setDialogCallBack(object :DialogCallBack(){
                override fun onResume() {
                   /*获取网络数据 可以直接在 这里获取*/
                }

                override fun getWidthRote(): Double {
                    return 0.8
                }

                override fun getHeightRote(): Double {
                    return 0.8
                }

                override fun initView(view: View?) {
                  var btn = view?.findViewById<Button>(R.id.btn_custom_view);
                    btn?.setOnClickListener {
                        Toast.makeText(this@MainActivity,"自定义Dialog 添加事件成功",Toast.LENGTH_SHORT).show()
                    }
                }
            })
            dialogWithCustomView.show(supportFragmentManager,MainActivity::class.java.simpleName)
        }
    }
}
