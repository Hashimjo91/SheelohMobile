package com.sheeloh.alhusban.sheelohapp.ServerRelevants;

/**
 * Created by Hashim Al-husban
 * on 25/04/16.
 */
public class Tags {

    /*Server Links*/
    public static final String LOGIN_LINK="http://restful-env.us-west-2.elasticbeanstalk.com/Users/Login";//POST
    public static final String REGISTER_LINK="http://restful-env.us-west-2.elasticbeanstalk.com/Users/Register";//POST
    public static final String GET_USER_INFO="http://restful-env.us-west-2.elasticbeanstalk.com/Users/GetUserInfo";//POST
    public static final String UPDATE_USER_INFO="http://restful-env.us-west-2.elasticbeanstalk.com/Users/UpdateUserInfo";//POST
    public static final String GET_DEALS="http://restful-env.us-west-2.elasticbeanstalk.com/Deals/Get_Deals";//GET
    public static final String GET_DEALS_BY_COMPANY="http://restful-env.us-west-2.elasticbeanstalk.com/Deals/Get_Deals_By_Company";//GET

    /*Server Links*/



    /*User Data TAGS*/
    public static final String USER_FIRSTNAME = "FirstName";
    public static final String USER_LASTNAME = "LastName";
    public static final String USER_EMAIL = "Email";
    public static final String USER_PASSWORD = "Password";
    public static final String USER_PHONE = "Phone";
    public static final String USER_GENDER = "Gender";
    public static final String USER_COUNTRY = "Country";
    public static final String USER_AGE = "Age";
    public static final String USER_ISACTIVE = "IsActive";
    public static final String USER_COUNTRYNAME = "Country_Name";
    public static final String USER_COUNTRYNAMES = "Country_Name_S";
    public static final String USER_GENDERNAME = "Gender_Name";
    public static final String USER_GENDERNAMES = "Gender_Name_S";
    public static final String USER_LANGUAGE = "Language";
    public static final String USER_TABLETAG = "Users";
    public static final String USER_APITOKEN = "api_token";
    /*User Data TAGS*/

    /*Deal Data TAGS*/
    public static final String D_ID = "D_id";
    public static final String D_DESC = "D_desc";
    public static final String D_DESC_S = "D_desc_s";
    public static final String D_EXTRA = "D_extra";
    public static final String D_DATA = "D_data";
    public static final String C_ID = "C_id";
    /*Deal Data TAGS*/

    public final static String USERINSERT = "INSERT INTO " + USER_TABLETAG + " (" + USER_EMAIL + "," + USER_PASSWORD + "," + USER_FIRSTNAME + "," + USER_LASTNAME + "," + USER_PHONE + "," + USER_ISACTIVE + "," + USER_GENDER + "," + USER_COUNTRY + "," + USER_AGE + ") VALUES ";
    public final static String USERINSERTWITHLANG = "INSERT INTO " + USER_TABLETAG + " (" + USER_EMAIL + "," + USER_PASSWORD + "," + USER_FIRSTNAME + "," + USER_LASTNAME + "," + USER_PHONE + "," + USER_ISACTIVE + "," + USER_GENDER + "," + USER_COUNTRY + "," + USER_LANGUAGE + "," + USER_AGE + ") VALUES ";
    public final static String USERINFOSELECT = "SELECT a.*,b.*,c.* FROM Users AS a,Country_Lookup AS b,Gender_Lookup AS c WHERE Email='_-_' and a.Country=b.COUNTRY_NO and a.Gender=c.G_id GROUP BY a.Email ";
    public final static String USERINFOUPDATE = "UPDATE " + USER_TABLETAG + " SET " + USER_FIRSTNAME + "='%s'," + USER_LASTNAME + "='%s'," + USER_PHONE + "='%s'," + USER_GENDER + "='%s'," + USER_COUNTRY + "='%s'," + USER_LANGUAGE + "='%s'," + USER_AGE + "='%s'  WHERE " + USER_EMAIL + "='%s'";
    public final static String USERUPDATE = "UPDATE " + USER_TABLETAG + " SET " + USER_PASSWORD + "='%s' WHERE " + USER_EMAIL + "='%s'";
    public final static String USERSELECT = "SELECT * FROM " + USER_TABLETAG + " WHERE " + USER_EMAIL + "='%s'";


    public static final String USER_ENTITY = "{'Email':'%s','Password':'%s'}";
}
