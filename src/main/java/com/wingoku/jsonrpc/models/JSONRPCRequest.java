package com.wingoku.jsonrpc.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

/**
 * Created by Umer on 6/6/2017.
 */

public class JSONRPCRequest {

    @SerializedName("jsonrpc")
    @Expose
    private String mJsonRPC;
    @SerializedName("method")
    @Expose
    private String mMethod;
    @SerializedName("id")
    @Expose
    private String mID;
    @SerializedName("params")
    @Expose
    private HashMap<String, Object> mParams;

    public String getJsonrpc() {
        return mJsonRPC;
    }

    public void setJsonrpc(String jsonrpc) {
        this.mJsonRPC = jsonrpc;
    }

    public String getMethod() {
        return mMethod;
    }

    public void setMethod(String method) {
        this.mMethod = method;
    }

    public String getId() {
        return mID;
    }

    public void setId(String id) {
        this.mID = id;
    }

    public HashMap<String, Object> getParams() {
        return mParams;
    }

    public void setParams(HashMap<String, Object> params) {
        this.mParams = params;
    }
}
