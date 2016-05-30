package com.sheeloh.alhusban.sheelohapp.ServerRelevants;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;
import com.sheeloh.alhusban.sheelohapp.R;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

/**
 * Created by Hashim Al-husban
 * on 5/26/2016.
 */
public abstract class mAsyncTask extends AsyncTask<String, String, String> {

    String link;
    String entity;
    HttpClient httpClient;
    HttpPost httpPost;
    HttpResponse response;
    ProgressDialog progressDialog;
    Context context;

    public mAsyncTask(Context context, String link, String entity) {
        this.link = link;
        this.entity = entity;
        this.context = context;
        httpClient = new DefaultHttpClient();
        httpPost = new HttpPost(link);
        httpPost.addHeader("Content-Type", "text/plain");
        httpPost.addHeader("Accept", "text/plain");
        httpPost.setEntity(new StringEntity(entity, "UTF-8"));

        this.execute();
    }

    @Override
    protected void onPreExecute() {

        progressDialog = ProgressDialog.show(context, null, null);
//        progressDialog = new ProgressDialog(context);
        progressDialog.setContentView(R.layout.custom_view);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setIndeterminateDrawable(context.getDrawable(R.drawable.loading_anim));
//        progressDialog.setMessage("");
//        progressDialog.setTitle("");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        progressDialog.show();
        Window window = progressDialog.getWindow();
        window.setLayout(380, 380);
//        super.onPreExecute();


    }

    @Override
    protected final String doInBackground(String... params) {
        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CustomDoInBackGround();
    }

    public abstract String CustomDoInBackGround();

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected final void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        CustomOnPostExecute();

    }

    public abstract void CustomOnPostExecute();
}
