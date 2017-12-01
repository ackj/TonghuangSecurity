package com.meilun.smart;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.meilun.security.smart.common.ApiService;

import org.junit.Test;
import org.junit.runner.RunWith;

import cn.itsite.abase.network.http.HttpHelper;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getTargetContext();
//
//        assertEquals("com.aglhz.s1", appContext.getPackageName());


        HttpHelper.getService(ApiService.class).requestSubCategoryLevel(ApiService.requestSubCategoryLevel,
                "tk_ff8e293f-070d-4a8d-bd43-e836c0bd2bc3",
                0,
                "06686c28-21e9-4da5-a5d3-ede98599aa2e")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categoryBean -> {
//                    if (categoryBean.getOther().getCode() == 200) {
//                        getView().responseSubCategoryList(categoryBean.getData());
//                    } else {
//                        getView().error(categoryBean.getOther().getMessage());
//                    }
                });

    }
}
