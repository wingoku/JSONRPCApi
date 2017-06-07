package com.wingoku.jsonrpc;

import com.wingoku.jsonrpc.server.WingokuServer;

/**
 * Created by Umer on 6/6/2017.
 */
public class Main {

    public static void main(String[] args) {
        // create and start server
        WingokuServer server = new WingokuServer(5005);
        server.setServerStatusListener(System.out::println);
        server.startServer();
    }
}
