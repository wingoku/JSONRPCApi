package com.wingoku.jsonrpc.handlers;


import com.thetransactioncompany.jsonrpc2.JSONRPC2Error;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.MessageContext;
import com.thetransactioncompany.jsonrpc2.server.RequestHandler;
import com.wingoku.jsonrpc.interfaces.Warehouse;
import com.wingoku.jsonrpc.interfaces.implementations.WarehouseImpl;

import java.util.ArrayList;


/**
 * Created by Umer on 6/7/2017.
 */
public class WarehouseRequestHandler implements RequestHandler {

    private WarehouseImpl mWarehouseImpl;
    private String[] mMethodNamesArray;

    /**
     * JSON RPC handler for {@link Warehouse}
     * @param implementation implementation instance of {@link Warehouse}
     */
    public WarehouseRequestHandler(WarehouseImpl implementation) {
        mWarehouseImpl = implementation;
        ArrayList<String> methodsList = new ArrayList<>();

        for(Warehouse.WarehouseMethods method : Warehouse.WarehouseMethods.values()) {
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
        if (request.getMethod().equals(Warehouse.WarehouseMethods.getPickList.name())) {
            ArrayList<String> response = mWarehouseImpl.getPickList();
            return new JSONRPC2Response(response, request.getID());
        }

        // Method name not supported
        return new JSONRPC2Response(JSONRPC2Error.METHOD_NOT_FOUND, request.getID());
    }
}
