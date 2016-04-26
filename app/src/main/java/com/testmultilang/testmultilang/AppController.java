package com.testmultilang.testmultilang;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by lenovo on 13-04-2016.
 */
public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();

    private RequestQueue mRequestQueue;
    //private ImageLoader mImageLoader;

    private static AppController mInstance;


    //private int recordId;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //recordId = 0;
    }

   /* public String increaseRecordId() {
        setRecordId(recordId + 1);
        return String.valueOf(recordId + 1) ;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }
*/
/*    public int increaseRecordId(){
        return getRecordId() + 1;
    }

    public int decreaseRecordId(){
        if(getRecordId() == 0)
           return getRecordId();
        else
        return getRecordId() - 1;
    }*/

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


}
