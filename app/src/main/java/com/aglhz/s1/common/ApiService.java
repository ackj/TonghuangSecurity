package com.aglhz.s1.common;

import com.aglhz.s1.bean.BaseBean;
import com.aglhz.s1.bean.GatewaysBean;
import com.aglhz.s1.bean.HostListBean;
import com.aglhz.s1.bean.SecurityBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Author: LiuJia on 2017/7/4 0004 09:21.
 * Email: liujia95me@126.com
 */

public interface ApiService {

    String BASE_URL = "http://119.23.129.133:8096/gas";

    // 确认主机结束安装
    String requestConfirmInstallFinished = "/client/confirmInstallFinished";

    // 确认主机开始安装
    String requestConfirmInstallStart = "/client/confirmInstallStart";

    // 删除设备(非传感器类)
    String requestDeldevice = "/client/deldevice ";

    // 删除设备日志消息记录；主机管理员可删除
    String requestDeldevicelog = "/client/deldevicelog";

    // 删除主机
    String requestDelgateway = "/client/delgateway";

    // 删除探测器(传感器)
    String requestDelsensor = "/client/delsensor";

    // 控制设备开关
    String requestDevicectrl = "/client/devicectrl";

    //  信息反馈
    String requestFeedback = "/client/feedback";

    // 会员主机布防状态设置
    String requestGatewayDSS = "/client/gatewayDSS";

    // 会员主机切换
    String requestGatewaySW = "/client/gatewaySW";

    //修改控制类设备(非传感器类)的属性
    String requestModdevice = "/client/moddevice";

    //修改场景
    String requestModscene = "/client/modscene";

    // 修改探测器(传感器)的属性
    String requestModsensor = "/client/modsensor";

    // 消息处理登记
    String requestMsgprocl = "/client/msgprocl";

    //消息已读登记
    String requestMsgread = "/client/msgread";

    //添加控制类设备(非传感器类)
    String requestNewdevice = "/client/newdevice";

    //会员扫描添加主机
    String requestNewgateway = "/client/newgateway";

    //会员扫描添加主机跳过安装
    String requestNewgateway2 = "/client/newgateway2";

    // 添加联动
    String requestNewlinkage = "/client/newlinkage";

    // 添加房间
    String requestNewroom = "/client/newroom";

    // 添加场景
    String requestNewscene = "/client/newscene";

    //添加探测器(传感器)
    String requestNewsensor = "/client/newsensor";

    //----------------------------- 主机 ---------------------------------
    String requestgatewayList = BASE_URL + "/client/info/gatewayList";

    @FormUrlEncoded
    @POST
    Observable<GatewaysBean> requestgatewayList(@Url String url
            , @Field("token") String token
            , @Field("page") int page
            , @Field("pageSize") int pageSize
    );


    //----------------------------- 安防 ----------------------------------

    //主页显示内容
    String requestSecurity = BASE_URL + "";

    @POST
    Observable<SecurityBean> requestSecurity(@Url String url);

    //获取主机列表
    String requestHostList = BASE_URL + "";

    @POST
    Observable<HostListBean> requestHostList(@Url String url);

    //状态切换
    String requestChangedHostStatus = BASE_URL + "";

    @POST
    Observable<BaseBean> requestChangedHostStatus(@Url String url);

    //探测器属性显示
    String requestDetectorProperty = BASE_URL + "";

    @POST
    Observable<BaseBean> requestDetectorProperty(@Url String url);

    //设置探测器名称
    String requestUpdateDetectorName = BASE_URL + "";

    @POST
    Observable<BaseBean> requestUpdateDetectorName(@Url String url);

    //设置探测器图片
    String requestUpdateDetectorPicture = BASE_URL + "";

    @POST
    Observable<BaseBean> requestUpdateDetectorPicture(@Url String url);

    //设置探测器防线
    String requestUpdateDetectorLine = BASE_URL + "";

    @POST
    Observable<BaseBean> requestUpdateDetectorLine(@Url String url);

    //设置探测器延迟
    String requestUpdateDetectorIsDelay = BASE_URL + "";

    @POST
    Observable<BaseBean> requestUpdateDetectorIsDelay(@Url String url);

    //删除该探测器
    String requestDeleteDetector = BASE_URL + "";

    @POST
    Observable<BaseBean> requestDeleteDetector(@Url String url);

    //获取探测器列表
    String requestDetectorList = BASE_URL + "";

    @POST
    Observable<BaseBean> requestDetectorList(@Url String url);

    //添加探测器
    String requestAddDetector = BASE_URL + "";

    @POST
    Observable<BaseBean> requestAddDetector(@Url String url);

    //----------------------------- 房间 ----------------------------------
    //获取房间信息
    String requestRoomInfo = BASE_URL + "";

    @POST
    Observable<BaseBean> requestRoomInfo(@Url String url);

    //获取插座列表
    String requestSocketList = BASE_URL + "";

    @POST
    Observable<BaseBean> requestSocketList(@Url String url);

    //更改插座名称和图片
    String requestEditSocketInfo = BASE_URL + "";

    @POST
    Observable<BaseBean> requestEditSocketInfo(@Url String url);

    //删除该插座
    String requestDeleteSocket = BASE_URL + "";

    @POST
    Observable<BaseBean> requestDeleteSocket(@Url String url);

    //添加插座
    String requestAddSocket = BASE_URL + "";

    @POST
    Observable<BaseBean> requestAddSocket(@Url String url);

    //获取灯光列表
    String requestLampList = BASE_URL + "";

    @POST
    Observable<BaseBean> requestLampList(@Url String url);

    //更改灯光的名称和图片
    String requestChangeLamp = BASE_URL + "";

    @POST
    Observable<BaseBean> requestChangeLamp(@Url String url);

    //删除该灯光
    String requestDeleteLamp = BASE_URL + "";

    @POST
    Observable<BaseBean> requestDeleteLamp(@Url String url);

    //添加灯光
    String requestAddLamp = BASE_URL + "";

    @POST
    Observable<BaseBean> requestAddLamp(@Url String url);

    //获取警号列表
    String requestAlarmList = BASE_URL + "";

    @POST
    Observable<BaseBean> requestAlarmList(@Url String url);

    //更改警号名称和图片
    String requestEditAlarm = BASE_URL + "";

    @POST
    Observable<BaseBean> requestEditAlarm(@Url String url);

    //删除该警号
    String requestDeleteAlarm = BASE_URL + "";

    @POST
    Observable<BaseBean> requestDeleteAlarm(@Url String url);

    //添加警号
    String requestAddAlarm = BASE_URL + "";

    @POST
    Observable<BaseBean> requestAddAlarm(@Url String url);

    //----------------------------- 历史 ----------------------------------
    //获取历史列表
    String requestHistory = BASE_URL + "";

    @POST
    Observable<BaseBean> requestHistory(@Url String url);

    //----------------------------- 历史 ----------------------------------
    //场景列表
    String requestSceneList = BASE_URL + "";

    @POST
    Observable<BaseBean> requestSceneList(@Url String url);

    //启动场景
    String requestStartScene = BASE_URL + "";

    @POST
    Observable<BaseBean> requestStartScene(@Url String url);

    //删除场景
    String requestDeleteScene = BASE_URL + "";

    @POST
    Observable<BaseBean> requestDeleteScene(@Url String url);

    //场景编辑
    String requestEditScene = BASE_URL + "";

    @POST
    Observable<BaseBean> requestEditScene(@Url String url);

    //保存场景
    String requestSaveScene = BASE_URL + "";

    @POST
    Observable<BaseBean> requestSaveScene(@Url String url);

    //联动列表
    String requestLinkageList = BASE_URL + "";

    @POST
    Observable<BaseBean> requestLinkageList(@Url String url);

    //联动开关
    String requestLinkageSwitch = BASE_URL + "";

    @POST
    Observable<BaseBean> requestLinkageSwitch(@Url String url);

    //删除联动
    String requestDeleteLinkage = BASE_URL + "";

    @POST
    Observable<BaseBean> requestDeleteLinkage(@Url String url);

    //----------------------------- 更多 ----------------------------------
    //房屋列表
    String requestHouseList = BASE_URL + "";

    @POST
    Observable<BaseBean> requestHouseList(@Url String url);

}
