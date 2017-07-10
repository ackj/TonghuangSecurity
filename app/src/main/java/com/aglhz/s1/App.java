package com.aglhz.s1;

import cn.itsite.abase.BaseApplication;
import me.yokeyword.fragmentation.Fragmentation;

/**
 * Author： Administrator on 2017/5/2 0002.
 * Email： liujia95me@126.com
 */
public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Fragmentation.builder().stackViewMode(Fragmentation.BUBBLE).install();
    }


}
