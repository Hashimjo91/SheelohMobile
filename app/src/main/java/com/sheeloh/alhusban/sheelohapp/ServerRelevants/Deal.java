package com.sheeloh.alhusban.sheelohapp.ServerRelevants;

import org.json.JSONException;
import org.json.JSONObject;

import static com.sheeloh.alhusban.sheelohapp.ServerRelevants.Tags.*;


/**
 * Created by Hashim Al-husban
 * on 5/25/2016.
 */
public class Deal {


    private int D_id;
    private String D_desc;
    private String D_desc_s;
    private String D_extra;
    private String D_data;
    private int C_id;

    public Deal() {
    }

    public Deal(String data) {
        this();
        ValidateData(data);
    }

    public void ValidateData(String data) {
        try {
            JSONObject obj = new JSONObject(data);
            if (obj.has(D_ID)) setD_id(obj.getInt(D_ID));
            if (obj.has(D_DESC)) setD_desc(obj.getString(D_DESC));
            if (obj.has(D_DESC_S)) setD_desc_s(obj.getString(D_DESC_S));
            if (obj.has(D_EXTRA)) setD_extra(obj.getString(D_EXTRA));
            if (obj.has(D_DATA)) setD_data(obj.getString(D_DATA));
            if (obj.has(C_ID)) setC_id(obj.getInt(C_ID));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getD_id() {
        return D_id;
    }

    public void setD_id(int d_id) {
        D_id = d_id;
    }

    public String getD_desc() {
        return D_desc;
    }

    public void setD_desc(String d_desc) {
        D_desc = d_desc;
    }

    public String getD_desc_s() {
        return D_desc_s;
    }

    public void setD_desc_s(String d_desc_s) {
        D_desc_s = d_desc_s;
    }

    public String getD_extra() {
        return D_extra;
    }

    public void setD_extra(String d_extra) {
        D_extra = d_extra;
    }

    public String getD_data() {
        return D_data;
    }

    public void setD_data(String d_data) {
        D_data = d_data;
    }

    public int getC_id() {
        return C_id;
    }

    public void setC_id(int c_id) {
        C_id = c_id;
    }

    @Override
    public String toString() {
        return "{" +
                "'D_id'='" + D_id + '\'' +
                ", 'D_desc'='" + D_desc + '\'' +
                ", 'D_desc_s'='" + D_desc_s + '\'' +
                ", 'D_extra'='" + D_extra + '\'' +
                ", 'D_data'='" + D_data + '\'' +
                ", 'C_id'='" + C_id + '\'' +
                '}';
    }
}
