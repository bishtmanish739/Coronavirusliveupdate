package in.technicalkeeda.coronavirusliveupdate;


import android.content.Context;

import org.json.JSONObject;

import java.util.Map;

public class UserController {

    private final Context mContext;
    private UserModel mUserModel;


    public UserController(final Context pContext) {
        mContext = pContext;
        mUserModel = new UserModel(pContext);
    }



    /**
     * Request for post with header
     */


    public void postWithUrlEncodedRequest(Map<String,String> pHeader, Map<String,String> pParm, String url , ResponseListener pResponseListener) {
        if (pResponseListener != null) {
            pResponseListener.onRequestStart();
        }
        mUserModel.postWithUrlEncodedRequest(pHeader,pParm,url,pResponseListener);
    }


    /**
     * Request for GET with header
     */


    public void getRequest(Map<String,String> pHeader,Map<String,String> pParms, String url , ResponseListener pResponseListener) {
        if (pResponseListener != null) {
            pResponseListener.onRequestStart();
        }
        mUserModel.getRequest(pHeader,pParms,url,pResponseListener);
    }


    /**
     * Request for Post with json object
     */

    public void postWithJsonRequest(String url , String token, JSONObject jsonObject, ResponseListener pResponseListener) {
        if (pResponseListener != null) {
            pResponseListener.onRequestStart();
        }
        mUserModel.postWithJsonRequest(url,token,jsonObject,pResponseListener);
    }


    /**
     * Request for PUT with header
     */


    public void putRequest(Map<String,String> pHeader, String url , ResponseListener pResponseListener) {
        if (pResponseListener != null) {
            pResponseListener.onRequestStart();
        }
        mUserModel.putRequest(pHeader,url,pResponseListener);
    }


    /**
     * Request for Put with json object
     */

    public void putWithJsonRequest(String url , String token, JSONObject jsonObject, ResponseListener pResponseListener) {
        if (pResponseListener != null) {
            pResponseListener.onRequestStart();
        }
        mUserModel.putWithJsonRequest(url,token,jsonObject,pResponseListener);
    }



    /**
     * Request for Post with json Array
     */

    public void postWithArrayString(String url , String token, String jsonArray, ResponseListener pResponseListener) {
        if (pResponseListener != null) {
            pResponseListener.onRequestStart();
        }
        mUserModel.postStringRequest(url,token,jsonArray,pResponseListener);
    }




}

