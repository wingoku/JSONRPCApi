package com.wingoku.jsonrpc.modules;

import com.wingoku.jsonrpc.handlers.CredentialRequestHandler;
import com.wingoku.jsonrpc.interfaces.implementations.CredentialsImpl;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Umer on 6/7/2017.
 */

@Module
public class CredentialsModule {

    @Provides
    public CredentialRequestHandler providesCredentialsRequestHandler(CredentialsImpl implementation) {
        return new CredentialRequestHandler(implementation);
    }

    @Provides
    public CredentialsImpl providesCredentialsImplementation() {
        return new CredentialsImpl();
    }
}
