/*
使用方法：
get:
 public UserRelationshipResponse getAllUserRelationship() throws HttpException {
        String url = getURL("friendship/all");
        String result = httpManager.get(url);
        UserRelationshipResponse response = null;
        if (!TextUtils.isEmpty(result)) {
            response = jsonToBean(result, UserRelationshipResponse.class);
        }
        return response;
    }

post:
public FriendInvitationResponse sendFriendInvitation(String userid, String addFriendMessage) throws HttpException {
        String url = getURL("friendship/invite");
        String json = JsonMananger.beanToJson(new FriendInvitationRequest(userid, addFriendMessage));
        StringEntity entity = null;
        try {
            entity = new StringEntity(json, ENCODING);
            entity.setContentType(CONTENT_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result = httpManager.post(mContext, url, entity, CONTENT_TYPE);
        FriendInvitationResponse response = null;
        if (!TextUtils.isEmpty(result)) {
            response = jsonToBean(result, FriendInvitationResponse.class);
        }
        return response;
    }

put:
public RestPasswordResponse restPassword(String phone, String password, String code, String sessionId) throws HttpException {
        String uri = DOMAIN + "doc/docs/password";
        String json = JsonMananger.beanToJson(new RestPasswordRequest(phone, password, code, sessionId));
        StringEntity entity = null;
        try {
            entity = new StringEntity(json, ENCODING);
            entity.setContentType(CONTENT_TYPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String result = httpManager.put(mContext, uri, entity, CONTENT_TYPE);
        RestPasswordResponse response = null;
        if (!TextUtils.isEmpty(result)) {
            NLog.e("RestPasswordResponse", result);
            response = jsonToBean(result, RestPasswordResponse.class);
        }
        return response;
    }

delete:
 public DismissGroupResponse dissmissGroup(String groupId) throws HttpException {
        String url = getURL("doc/groups/" + groupId);
        String result = httpManager.delete(mContext, url);
        DismissGroupResponse response = null;
        if (!TextUtils.isEmpty(result)) {
            response = jsonToBean(result, DismissGroupResponse.class);
        }
        if (checkAuth(result, totalRecursionCount)) {
            totalRecursionCount++;
            return dissmissGroup(groupId);
        }
        return response;
    }

 */
package com.newframework.server.action;

import android.content.Context;
import android.text.TextUtils;

import com.newframework.App;
import com.newframework.server.network.http.HttpException;
import com.newframework.server.network.http.SyncHttpClient;
import com.newframework.server.utils.json.JsonMananger;

import java.util.List;

/**
 * Created by AMing on 16/1/14.
 * Company RongCloud
 *
 *
 */
public class BaseAction {
    //调试地址
    public static String DOMAIN = "http://172.16.1.131:8080/mockjsdata/1/";

    public final String CONTENT_TYPE = "application/json";
    public final String ENCODING = "utf-8";
    protected Context mContext;
    protected SyncHttpClient httpManager;

    /**
     * 构造方法
     * @param context 上下文
     */
    public BaseAction(Context context) {
        this.mContext = context;
        this.httpManager = SyncHttpClient.getInstance(context);
    }

    /**
     * JSON转JAVA对象方法
     *
     * @param json json
     * @param cls  class
     * @throws HttpException
     */
    public <T> T jsonToBean(String json, Class<T> cls) throws HttpException {
        return JsonMananger.jsonToBean(json, cls);
    }

    /**
     * JSON转JAVA数组方法
     *
     * @param json json
     * @param cls  class
     * @throws HttpException
     */
    public <T> List<T> jsonToList(String json, Class<T> cls) throws HttpException {
        return JsonMananger.jsonToList(json, cls);
    }

    /**
     * JAVA对象转JSON方法
     *
     * @param obj object
     * @throws HttpException
     */
    public String BeanTojson(Object obj) throws HttpException {
        return JsonMananger.beanToJson(obj);
    }


    /**
     * 获取完整URL方法
     *
     * @param url url
     */
    protected String getURL(String url) {

        return getURL(url, new String[]{});
    }

    /**
     * 获取完整URL方法
     *
     * @param url    url
     * @param params params
     */
    protected String getURL(String url, String... params) {
        StringBuilder urlBuilder = new StringBuilder(DOMAIN).append(url);
        if (params != null) {
            for (String param : params) {
                if (!urlBuilder.toString().endsWith("/")) {
                    urlBuilder.append("/");
                }
                urlBuilder.append(param);
            }
        }
        return urlBuilder.append(getVerifyToken()).toString();
    }

    private String getVerifyToken() {

        String verifyToken = App.getInstance().getSharedPreferences("config", App.MODE_PRIVATE).getString("verify_token", "");
        if (TextUtils.isEmpty(verifyToken)) {
            return "";
        } else {
            return "?token=" + verifyToken;
        }
    }
}
