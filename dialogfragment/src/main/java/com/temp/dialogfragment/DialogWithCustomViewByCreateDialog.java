package com.temp.dialogfragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public abstract class DialogWithCustomViewByCreateDialog extends DialogFragment {

    private DialogCallBack dialogCallBack;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder =  new AlertDialog.Builder(getContext());

        View view = getActivity().getLayoutInflater().inflate(getLayoutId(),null);
        if(dialogCallBack!=null){
            dialogCallBack.initView(view);
        }
        builder.setCancelable(true);
        builder.setView(view);
        return builder.create();
    }

    /*通过这里设置 可以强行提前设置dialog 宽高 通过createdialog 设置的view 最终显示的宽高 不是这里设置的 所以这里的设置 好像没啥用*/
    /*这里设置的是整个dialog 的宽高 不是自己定义的view的宽高 */
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        /*dialog的宽高必须 设置 因为在布局里面设置的没有效果*/
        if (dialog != null) {
            /*只有宽高都设置百分比这里才可用 默认都是0*/
            if(dialogCallBack.getWidthRote()<=0){
                return;
            }
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            /*这里设置的宽度 就是将来显示的宽度 并且 会作为xml最外层布局的宽度*/
            /*这里设置的高度 也是将来显示的高度 但并不会作为最外层布局的高度 所以设置为WRAP_CONTENT 保持跟内容一样的高度*/
            dialog.getWindow().setLayout((int) (dm.widthPixels * dialogCallBack.getWidthRote()),ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(dialogCallBack!=null){
            dialogCallBack.onResume();
        }
    }

    public void setDialogCallBack(DialogCallBack dialogCallBack){
        this.dialogCallBack = dialogCallBack;
    }

    public abstract int getLayoutId();
}
