package com.wingoku.jsonrpc.interfaces;

import java.util.ArrayList;

/**
 * Created by Umer on 6/7/2017.
 */

public interface Warehouse {
    public enum WarehouseMethods {
        getPickList
    }

    public ArrayList<String> getPickList();
}
