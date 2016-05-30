package com.sheeloh.alhusban.sheelohapp.util;

/**
 * Created by Hashim Al-husban
 * on 5/25/2016.
 */
public class DataBaseHandler {
    private static DataBaseHandler ourInstance = new DataBaseHandler();

    public static DataBaseHandler getInstance() {
        return ourInstance;
    }

    private DataBaseHandler() {
    }
}
