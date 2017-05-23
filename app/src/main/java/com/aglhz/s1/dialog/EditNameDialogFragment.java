package com.aglhz.s1.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.aglhz.s1.R;

/**
 * Author: LiuJia on 2017/5/6 0006 14:35.
 * Email: liujia95me@126.com
 * 自定义dialog，是所有自定义dialog的基类
 */

public class EditNameDialogFragment extends DialogFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_dialog_selector, container);
        return view;
    }

}
