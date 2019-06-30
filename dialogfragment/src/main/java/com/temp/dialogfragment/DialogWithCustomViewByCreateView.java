package com.temp.dialogfragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/*通过onCreateView 生成view*/
public abstract class DialogWithCustomViewByCreateView extends DialogFragment {

    private DialogCallBack dialogCallBack;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        if(dialogCallBack!=null){
            dialogCallBack.initView(view);
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        /*dialog的宽高必须 设置 因为在布局里面设置的没有效果*/
        if (dialog != null) {
            /*只有宽高都设置百分比 这个才可用*/
            if(dialogCallBack.getWidthRote()<=0||dialogCallBack.getHeightRote()<=0){
                return;
            }
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            /*通过这里可以直接 设定dialog的宽高 这里设置的 就是xml里面最外布局的宽高*/
            dialog.getWindow().setLayout((int) (dm.widthPixels * dialogCallBack.getWidthRote()), (int) (dm.heightPixels * dialogCallBack.getHeightRote()));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(dialogCallBack!=null){
            dialogCallBack.onResume();
        }
    }

    public void setDialongCallBack(DialogCallBack dialongCallBack){
        this.dialogCallBack = dialongCallBack;
    }

    public abstract int getLayoutId();

}
