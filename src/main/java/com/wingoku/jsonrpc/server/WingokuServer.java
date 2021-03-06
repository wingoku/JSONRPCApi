package com.wingoku.jsonrpc.server;

/**
 * Created by Umer on 6/6/2017.
 */

import com.google.gson.Gson;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Error;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.Dispatcher;
import com.wingoku.jsonrpc.handlers.CredentialRequestHandler;
import com.wingoku.jsonrpc.handlers.WarehouseRequestHandler;
import com.wingoku.jsonrpc.interfaces.WingokuServerCallback;
import com.wingoku.jsonrpc.models.JSONRPCRequest;
import com.wingoku.jsonrpc.utils.Constants;
import fi.iki.elonen.NanoHTTPD;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Umer 6/7/2017
 */
public class WingokuServer extends NanoHTTPD {

    @Inject
    Gson mGson;

    @Inject
    Dispatcher mRPCDispatcher;

    @Inject
    WarehouseRequestHandler mWarehouseRequestHandler;

    @Inject
    CredentialRequestHandler mCredentialRequestHandler;

    private WingokuServerCallback mListener;

    /**
     * Constructor for {@link #WingokuServer(int)}
     * @param port port upon which the server must listen
     */
    public WingokuServer(int port) {
        super(port);
        DaggerServerComponent.builder().build().inject(this);
        registerJSONRPCRequestDispatchers();
    }

    /**
     * Starts running the server
     */
    public void startServer(){
        try {
            start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
            mListener.onServerStatus("Server is running");
        } catch (IOException e) {
            mListener.onServerStatus("Server couldn't start");
            e.printStackTrace();
        }
    }

    /**
     * Set listener for callbacks to listen for server state
     * @param l {@link #setServerStatusListener(WingokuServerCallback)} implementation instance
     */
    public void setServerStatusListener(WingokuServerCallback l) {
        mListener = l;
    }

    /**
     *  This method will be called by NanoHTTPD upon receiving requests from client
     * @param session comprehensive class containing information about the request
     * @return response to the client
     */
    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        Method method = session.getMethod();
        Map<String, String> headers = session.getHeaders();
        Map<String, String> files = new HashMap<>();

        if (Method.POST.equals(method)) {
            try {
                session.parseBody(files);
            } catch (Exception e) {
                JSONRPC2Error error = new JSONRPC2Error(JSONRPC2Error.INTERNAL_ERROR.getCode(), JSONRPC2Error.INTERNAL_ERROR.getMessage(), e.getMessage());
                return newFixedLengthResponse(new JSONRPC2Response(error, "").toJSONString());
            }

            String postData = "";
            if (session.getHeaders().get("content-type").equals("application/json")) {
                postData = files.get("postData");

                if (Constants.DEBUG_MODE) {
                    System.out.println("post Data is: " + postData);
                    System.out.println(Constants.ANSI_RED + String.format("URI: %s, Method: %s, Header: %s, files: %s", uri, method, headers, files)+ Constants.ANSI_RESET);
                }

                JSONRPCRequest rpcRequestModel = mapJsonRequestToModel(postData);

                JSONRPC2Request request = new JSONRPC2Request(rpcRequestModel.getMethod(), rpcRequestModel.getParams(), rpcRequestModel.getId());
                JSONRPC2Response response = mRPCDispatcher.process(request, null);

                System.out.println(Constants.ANSI_GREEN + "request " + request+ Constants.ANSI_RESET);
                System.out.println(Constants.ANSI_BLUE +"response " + response+ Constants.ANSI_RESET);

                return newFixedLengthResponse(response.toJSONString());
            }
            JSONRPC2Error error = new JSONRPC2Error(JSONRPC2Error.INVALID_PARAMS.getCode(), JSONRPC2Error.INVALID_PARAMS.getMessage(), "Content Type Must Be application/json");
            return newFixedLengthResponse(new JSONRPC2Response(error, "").toJSONString());
        }

        JSONRPC2Error error = new JSONRPC2Error(JSONRPC2Error.INVALID_REQUEST.getCode(), JSONRPC2Error.INVALID_REQUEST.getMessage(), " Only POST requests are supported by server at the moment");
        return newFixedLengthResponse(new JSONRPC2Response(error, "").toJSONString());
    }

    /**
     * Maps the JSON to POJO model class using #Gson
     * @param requestBody JSON string
     * @return {@Link #JSONRPCRequest} model class instance
     */
    private JSONRPCRequest mapJsonRequestToModel(String requestBody) {
        return mGson.fromJson(requestBody, JSONRPCRequest.class);
    }

    /**
     * Registers dispatches for handling JSON RPC requests
     */
    private void registerJSONRPCRequestDispatchers() {
        mRPCDispatcher.register(mCredentialRequestHandler);
        mRPCDispatcher.register(mWarehouseRequestHandler);
    }
}