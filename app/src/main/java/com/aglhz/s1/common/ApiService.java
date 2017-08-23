package com.aglhz.s1.common;

import com.aglhz.s1.entity.bean.BaseBean;
import com.aglhz.s1.entity.bean.CheckTokenBean;
import com.aglhz.s1.entity.bean.DeviceListBean;
import com.aglhz.s1.entity.bean.DevicesBean;
import com.aglhz.s1.entity.bean.GatewaysBean;
import com.aglhz.s1.entity.bean.RoomTypesBean;
import com.aglhz.s1.entity.bean.RoomsBean;
import com.aglhz.s1.entity.bean.SecurityBean;
import com.aglhz.s1.entity.bean.SubDeviceDetBean;
import com.aglhz.s1.entity.bean.UserBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Author: LiuJia on 2017/7/4 0004 09:21.
 * Email: liujia95me@126.com
 */

public interface ApiService {

    String BASE_URL = "http://119.23.129.133:8096/gas";

    String BASE_USER = Constants.BASE_USER;           //用户

    //*************以上基础路径*******************

    //-----------------以下为推送相关---------------------
    //友盟用户登记接口
    String requestRegisterUMeng = BASE_USER + "/client/logUMengParams.do";

    @POST
    Observable<BaseBean> requestRegisterUMeng(@Url String url,
                                              @Query("token") String token,
                                              @Query("deviceToken") String deviceToken,
                                              @Query("alias") String alias,
                                              @Query("aliasType") String aliasType);
    //-----------------以上为推送相关---------------------


    //----------以下为Launch模块--------------
    //登录
    String requestLogin = BASE_USER + "/client/login.do";

    @POST
    Observable<UserBean> requestLogin(@Url String url
            , @Query("sc") String sc
            , @Query("user") String user
            , @Query("pwd") String pwd);

    //注册
    String requestRegister = BASE_USER + "/client/register.do";

    @POST
    Observable<BaseBean> requestRegister(@Url String url,
                                         @Query("sc") String sc,
                                         @Query("account") String account,
                                         @Query("code") String code,
                                         @Query("Password1") String password1,
                                         @Query("Password2") String password2);

    //获取验证码
    String requestVerifyCode = BASE_USER + "/client/validCode.do";

    @POST
    Observable<BaseBean> requestVerifyCode(@Url String url,
                                           @Query("sc") String sc,
                                           @Query("phone") String phone,
                                           @Query("type") String type);

    //重置密码
    String requestResetPassword = BASE_USER + "/client/renewMemberPwd.do";

    @POST
    Observable<BaseBean> requestResetPassword(@Url String url,
                                              @Query("sc") String sc,
                                              @Query("account") String account,
                                              @Query("code") String code,
                                              @Query("pwd1") String password1,
                                              @Query("pwd2") String password2);

    //登录验证
    String requestCheckToken = BASE_USER + "/client/checkIfTokenInvalid.do";

    @POST
    Observable<CheckTokenBean> requestCheckToken(@Url String url,
                                                 @Query("token") String token);

    //登出
    String requestLogout = BASE_USER + "/client/logout.do";

    @POST
    Observable<BaseBean> requestLogout(@Url String url,
            @Query("token") String token);

    //----------以上为Launch模块--------------


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

    // 控制设备开关
    String requestDevicectrl = "/client/devicectrl";

    // 会员主机布防状态设置
    String requestGatewayDSS = "/client/gatewayDSS";

    // 会员主机切换
    String requestGatewaySW = "/client/gatewaySW";

    //修改控制类设备(非传感器类)的属性
    String requestModdevice = "/client/moddevice";

    //修改场景
    String requestModscene = "/client/modscene";

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


    // 添加场景
    String requestNewscene = "/client/newscene";

    //添加探测器(传感器)
    String requestNewsensor = "/client/newsensor";

    //----------------------------- 以下为主机操作相关 ---------------------------------
    //获取主机列表
    String requestGateways = BASE_URL + "/client/info/gatewayList";

    @FormUrlEncoded
    @POST
    Observable<GatewaysBean> requestGateways(@Url String url
            , @Field("token") String token
            , @Field("page") int page
            , @Field("pageSize") int pageSize
    );

    //-----------------------------探测器 相关 ---------------------------------
    //获取探测器列表
    String requestSensorTypeList = BASE_URL + "/client/info/sensorTypeList";

    @FormUrlEncoded
    @POST
    Observable<DevicesBean> requestSensorTypeList(@Url String url
            , @Field("token") String token
            , @Field("page") int page
            , @Field("pageSize") int pageSize
    );

    //添加探测器
    String reqeuestNewsensor = BASE_URL + "/client/newsensor";

//    @FormUrlEncoded
//    @POST
//    Observable<BaseBean> reqeuestNewsensor(@Url String url
//            , @Field("token") String token
//            , @Field("sensorType") String sensorType
//            , @Field("name") String name
//            , @Field("defenseLevel") String defenseLevel
//            , @Field("roomFid") String roomFid
//    );

    @POST
    Observable<BaseBean> reqeuestNewsensor(@Url String url
            , @Body MultipartBody file
    );

    // 修改探测器
    String requestModsensor = BASE_URL + "/client/modsensor";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestModsensor(@Url String url
            , @Field("token") String token
            , @Field("index") int index
            , @Field("name") String name
            , @Field("defenseLevel") String defenseLevel
            , @Field("alarmDelay") int alarmDelay
    );

    // 删除探测器
    String requestDelsensor = BASE_URL + "/client/delsensor";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestDelsensor(@Url String url
            , @Field("token") String token
            , @Field("index") int index
    );

    //获取探测器/设备详情
    String requestSubDeviceDet = BASE_URL + "/client/info/subDeviceDet";

    @FormUrlEncoded
    @POST
    Observable<SubDeviceDetBean> requestSubDeviceDet(@Url String url
            , @Field("token") String token
            , @Field("category") String category
            , @Field("index") int index);

    //----------------------------- 设备相关 ---------------------------------
    //添加设备
    String requestNewDevice = BASE_URL+"/client/newdevice";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestNewDevice(@Url String url
            , @Field("token") String token
            , @Field("deviceType") String deviceType
            , @Field("name") String name
            , @Field("roomFid") String roomFid);

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestNewDevice(@Url String url
            , @Field("token") String token);

    //修改设备
    String requestModDevice = BASE_URL+"/client/moddevice";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestModDevice(@Url String url
            , @Field("token") String token
            , @Field("index") int index
            , @Field("name") String name
            , @Field("roomFid") String roomFid);


    //删除设备
    String requestDelDevice = BASE_URL +"/client/deldevice";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestDelDevice(@Url String url
            , @Field("token") String token
            , @Field("index") int index);

    String requestSubDeviceList = BASE_URL+"/client/info/subDeviceList";

    //设备列表
    @FormUrlEncoded
    @POST
    Observable<DeviceListBean> requestSubDeviceList(@Url String url
            , @Field("token") String token
            , @Field("page") int page
            , @Field("pageSize") int pageSize
            , @Field("roomId") String roomId
            , @Field("category") String category);

    //----------------------------- 房间相关 ---------------------------------
    String requestRoomList = BASE_URL + "/client/info/roomList";

    @FormUrlEncoded
    @POST
    Observable<RoomsBean> requestRoomList(@Url String url
            , @Field("token") String token
            , @Field("page") int page
            , @Field("pageSize") int pageSize
    );

    String requestNewroom = BASE_URL + "/client/newroom";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestNewroom(@Url String url
            , @Field("token") String token
            , @Field("name") String roomName
            , @Field("roomTypeFid") String roomTypeFid
            , @Field("residenceFid") String residenceFid
    );

    String requestRoomTypeList = BASE_URL + "/client/info/roomTypeList";

    @POST
    Observable<RoomTypesBean> requestRoomTypeList(@Url String url);

    //----------------------------- 关于 ---------------------------------
    String requestFeedback = BASE_URL + "/client/feedback";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestFeedback(@Url String url
            , @Field("token") String token
            , @Field("des") String des
            , @Field("contact") String contact
    );


    //*************以下基础路径*******************


    //*************以上基础路径*******************

    //-----------------以下为推送相关---------------------
    //切换主机
    String requestSwichGateway = BASE_URL + "/client/gatewaySW";

    @POST
    Observable<BaseBean> requestSwichGateway(@Url String url,
                                             @Query("token") String token,
                                             @Query("gateway") String gateway);

    //切换主机
    String requestSwichState = BASE_URL + "/client/gatewayDSS";

    @POST
    Observable<BaseBean> requestSwichState(@Url String url,
                                           @Query("token") String token,
                                           @Query("dstatus") String dstatus);

    //主页显示内容
    String requestSecurity = BASE_URL + "/client/info/homePage ";

    @POST
    Observable<SecurityBean> requestSecurity(@Url String url,
                                             @Query("token") String token);


    //添加主机
    String requestAddHost = BASE_URL + "/client/newgateway2";

    @FormUrlEncoded
    @POST
    Observable<BaseBean> requestAddHost(@Url String url,
                                        @Field("token") String token,
                                        @Field("no") String no,
                                        @Field("name") String name);


    //----------------------------- 以上为主机操作相关 ---------------------------------


    //----------------------------- 以下为探测器相关 ---------------------------------
    //删除该探测器
    String requestDeleteDetector = BASE_URL + "/client/delsensor";

    @POST
    Observable<BaseBean> requestDeleteDetector(@Url String url,
                                               @Query("token") String token,
                                               @Query("index") String index);


    //----------------------------- 以上为探测器相关 ---------------------------------


    //----------------------------- 安防 ----------------------------------


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
