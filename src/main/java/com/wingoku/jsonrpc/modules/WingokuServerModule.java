package com.wingoku.jsonrpc.modules;

import com.google.gson.Gson;
import com.thetransactioncompany.jsonrpc2.server.Dispatcher;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Umer on 6/7/2017.
 */
@Module
public class WingokuServerModule {

    @Provides
    public Dispatcher providesServerDispatcher() {
        return new Dispatcher();
    }

    @Provides
    public Gson providesGson() {
        return new Gson();
    }
}
