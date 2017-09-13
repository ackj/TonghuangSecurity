package com.aglhz.s1.net.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglhz.s1.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.itsite.abase.log.ALog;
import cn.itsite.abase.mvp.view.base.BaseFragment;

import static com.tsvclient.ipc.WifiIpc.TSV_C_SendXmlCommand;

/**
 * Created by leguang on 2017/5/24 0029.
 * Email：langmanleguang@qq.com
 */
public class SetNetFragment extends BaseFragment {
    private static final String TAG = SetNetFragment.class.getSimpleName();
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll_set_net_set_net_fragment)
    LinearLayout llSettingNet;
    @BindView(R.id.ll_update_password_set_net_fragment)
    LinearLayout llUpdatePassword;
    Unbinder unbinder;

    public static SetNetFragment newInstance() {
        return new SetNetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_net, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolbar();
    }

    private void initToolbar() {
        initStateBar(toolbar);
        toolbarTitle.setText("网络设置");
//        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_white_24dp);
//        toolbar.setNavigationOnClickListener(v -> _mActivity.onBackPressedSupport());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_set_net_set_net_fragment, R.id.ll_update_password_set_net_fragment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_set_net_set_net_fragment:
                start(SetWifiFragment.newInstance());
//                IPC_DispatchText("10.10.10.250", 215, 215, "");
                break;
            case R.id.ll_update_password_set_net_fragment:
//                IPC_DispatchText("10.10.10.250", 216, 216, "");
                break;
        }
    }

    private int IPC_DispatchText(final String paramString1, final int paramInt1, final int paramInt2, final String paramString2) {
        new Thread(new Runnable() {
            public void run() {
                String result = TSV_C_SendXmlCommand(paramString1, 12368, paramInt1, paramInt2, paramString2);
                ALog.e("result-->" + result);
            }
        }).start();
        return 0;
    }

    @Override
    public boolean onBackPressedSupport() {
        return true;
    }
}
