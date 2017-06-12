package com.wingoku.jsonrpc.handlers;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Error;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.MessageContext;
import com.thetransactioncompany.jsonrpc2.server.RequestHandler;
import com.wingoku.jsonrpc.interfaces.Credentials;
import com.wingoku.jsonrpc.interfaces.implementations.CredentialsImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Umer on 6/7/2017.
 */

public class CredentialRequestHandler implements RequestHandler {

    private CredentialsImpl mCredentialsImpl;
    private String[] mMethodNamesArray;

    /**
     * JSON RPC handler for {@link Credentials}
     * @param implementation implementation instance of {@link Credentials}
     */
    public CredentialRequestHandler(CredentialsImpl implementation) {
        mCredentialsImpl = implementation;
        ArrayList<String> methodsList = new ArrayList<>();

        for(Credentials.CredentialMethods method: Credentials.CredentialMethods.values()) {
            methodsList.add(method.name());
        }
        mMethodNamesArray = methodsList.toArray(new String[0]);
    }

    @Override
    public String[] handledRequests() {
        return mMethodNamesArray;
    }

    @Override
    public JSONRPC2Response process(JSONRPC2Request request, MessageContext requestCtx) {
        // check if method name in the request exists in the Warehouse interface
        System.out.println("process!!  1");
        if (request.getMethod().equals(Credentials.CredentialMethods.login.name())) {
            Map<String, Object> requestParamsMap = request.getNamedParams();
            HashMap<String, String> response = mCredentialsImpl.login((String)requestParamsMap.get("userName"), (String)requestParamsMap.get("password"));
            return new JSONRPC2Response(response, request.getID());
        }
        else if (request.getMethod().equals(Credentials.CredentialMethods.logout.name())) {
            String response = mCredentialsImpl.logout();
            return new JSONRPC2Response(response, request.getID());
        }

        // Method name not supported
        return new JSONRPC2Response(JSONRPC2Error.METHOD_NOT_FOUND, request.getID());
    }
}
