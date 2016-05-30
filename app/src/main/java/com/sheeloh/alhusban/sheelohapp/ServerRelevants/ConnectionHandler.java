package com.sheeloh.alhusban.sheelohapp.ServerRelevants;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.sheeloh.alhusban.sheelohapp.MainActivity;
import com.sheeloh.alhusban.sheelohapp.util.SharedUtil;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Hashim Al-husban
 * on 5/26/2016.
 */
public final class ConnectionHandler {
    static String resp = "error";
    static boolean validLogin = false;

    public static boolean Login(final Context c, String userName, String password) {
        String entity = String.format(Tags.USER_ENTITY, userName, password);
        try {

            mAsyncTask mAsyncTask = new mAsyncTask(c, Tags.LOGIN_LINK, entity) {
                @Override
                public String CustomDoInBackGround() {
                    try {
                        response = httpClient.execute(httpPost);
                        try {
                            resp = EntityUtils.toString(response.getEntity());
                        } catch (IOException e) {
                            validLogin = false;

                        }
                    } catch (IOException e) {
                        validLogin = false;

                    }
                    return null;
                }

                @Override
                public void CustomOnPostExecute() {

                    try {
                        User u = new User(resp);
                        SharedUtil.setShared(c, Tags.USER_EMAIL, u.getEmail());
                        SharedUtil.setShared(c, Tags.USER_APITOKEN, u.getApiToken());
                        Log.e("error", resp);

                        c.startActivity(new Intent(c, MainActivity.class));
                    } catch (Exception e) {
                        validLogin = false;
                    }

                    if (!validLogin) {
                        Toast.makeText(c, "Invalid UserName or Password", Toast.LENGTH_LONG).show();
                        return;
                    }

                }
            };
            mAsyncTask.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return validLogin;
    }


}
