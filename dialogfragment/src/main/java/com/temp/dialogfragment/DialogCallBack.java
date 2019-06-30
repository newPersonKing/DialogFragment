package com.temp.dialogfragment;

import android.view.View;

public abstract class DialogCallBack {

    /*提供给自定义view返回 添加事件*/
    public abstract void initView(View view);

    public abstract void onResume();

    /*设置 width 百分比*/
    public double getWidthRote(){
        return 0.0;
    }

    /*设置高度百分比*/
    public double getHeightRote(){
        return 0.0;
    }
}
