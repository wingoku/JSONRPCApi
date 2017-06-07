package com.wingoku.jsonrpc.interfaces.implementations;

import com.wingoku.jsonrpc.interfaces.Credentials;

import java.util.HashMap;


/**
 * Created by Umer on 6/7/2017.
 */

public class CredentialsImpl implements Credentials {

    @Override
    public HashMap<String, String> login(String userName, String password) {
        HashMap<String, String> response = new HashMap<>();
        response.put("token", "weersd454545sdf581@@@r%2");
        response.put("success", "true");
        return response;
    }

    @Override
    public String logout() {
        return "logout success";
    }
}