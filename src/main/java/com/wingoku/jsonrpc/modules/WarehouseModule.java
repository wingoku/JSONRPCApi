package com.wingoku.jsonrpc.modules;

import com.wingoku.jsonrpc.handlers.WarehouseRequestHandler;
import com.wingoku.jsonrpc.interfaces.implementations.WarehouseImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Umer on 6/7/2017.
 */
@Module
public class WarehouseModule {

    @Provides
    public WarehouseRequestHandler providesWarehouseRequestHandler(WarehouseImpl implementation) {
        return new WarehouseRequestHandler(implementation);
    }

    @Provides
    public WarehouseImpl providesWarehouseImplementation() {
        return new WarehouseImpl();
    }
}
