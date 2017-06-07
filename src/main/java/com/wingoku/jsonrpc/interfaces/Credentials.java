package com.wingoku.jsonrpc.interfaces;

import java.util.HashMap;

/**
 * Created by Umer on 6/7/2017.
 */

public interface Credentials {
    enum CredentialMethods {
        login,
        logout
    }

    HashMap<String, String> login(String userName, String password);
    String logout();
}
