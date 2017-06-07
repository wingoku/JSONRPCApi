package com.wingoku.jsonrpc.interfaces.components;

import com.wingoku.jsonrpc.modules.CredentialsModule;
import com.wingoku.jsonrpc.modules.WarehouseModule;
import com.wingoku.jsonrpc.modules.WingokuServerModule;
import com.wingoku.jsonrpc.server.WingokuServer;
import dagger.Component;

/**
 * Created by Umer on 6/7/2017.
 */
@Component (modules = {WingokuServerModule.class, CredentialsModule.class, WarehouseModule.class})
public interface ServerComponent {
    void inject(WingokuServer server);
}
