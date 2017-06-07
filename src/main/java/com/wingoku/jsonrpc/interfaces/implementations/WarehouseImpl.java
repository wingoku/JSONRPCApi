package com.wingoku.jsonrpc.interfaces.implementations;


import com.wingoku.jsonrpc.interfaces.Warehouse;

import java.util.ArrayList;

/**
 * Created by Umer on 6/7/2017.
 */

public class WarehouseImpl implements Warehouse {

    @Override
    public ArrayList<String> getPickList() {
        ArrayList<String> pickList = new ArrayList<>();
        pickList.add("Banana");
        pickList.add("PineApple");
        pickList.add("Mango");
        pickList.add("Guava");
        return pickList;
    }
}
