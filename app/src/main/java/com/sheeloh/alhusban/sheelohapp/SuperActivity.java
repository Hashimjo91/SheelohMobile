package com.sheeloh.alhusban.sheelohapp;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.najjar.android.lib.AppPreferences;
import com.najjar.android.lib.service_connection.ServiceRequestQueue;

import java.lang.reflect.Field;
import java.util.Locale;

import butterknife.ButterKnife;

/**
 * date: 5/20/2016
 *
 * @author Hashim Al-Husban
 * @version 1
 * @since 1
 */
public class SuperActivity extends AppCompatActivity {

    public static final String TAG = SuperActivity.class.getSimpleName();

    private boolean isLocalEnglish = true;
    private boolean hideSettingsOption;
    private boolean hideSignOutOption;


    private MaterialDialog signOutDialog;
    private SwipeRefreshLayout swipeLayout;
    private View rootView;

    private MaterialDialog loadingDialog;
    private MaterialDialog exitDialog;

    private CircularProgressView loadingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLocalEnglish = AppPreferences.getStringPreference(com.najjar.android.lib.R.string.key_local).equals(getString(com.najjar.android.lib.R.string.key_local_en));
    }

    protected final void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(com.najjar.android.lib.R.id.tool_bar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            }
        }


        toolbar.setLogo(R.mipmap.logo2);

        ((ImageView)toolbar.findViewById(R.id.slogan)).setBackgroundResource(R.mipmap.logo1);
        setTitle("");
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(com.najjar.android.lib.R.id.swipe_layout);
        if (swipeRefreshLayout != null) {
            swipeLayout = swipeRefreshLayout;
            swipeLayout.setColorSchemeResources(com.najjar.android.lib.R.color.accent);
        }

        loadingView = (CircularProgressView) findViewById(com.najjar.android.lib.R.id.loading_view);

        rootView = findViewById(com.najjar.android.lib.R.id.root_view);


        /**
         * to show actionbar overflow button on all devices, ignore hardware menu button
         */
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception ex) {
            // Ignore
        }

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean isLocalEnglish = AppPreferences.getStringPreference(com.najjar.android.lib.R.string.key_local).equals(getString(com.najjar.android.lib.R.string.key_local_en));
        if (isLocalEnglish != this.isLocalEnglish) {
            Log.i(TAG, "change local");
            finish();
            startActivity(getIntent());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == com.najjar.android.lib.R.id.item_settings) {
            onItemSettingClick();
            return true;
        } else if (item.getItemId() == com.najjar.android.lib.R.id.item_sign_out) {
            showSignOutDialog();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSignOutDialog() {
        if (signOutDialog == null) {
            signOutDialog = new MaterialDialog.Builder(this)
                    .positiveText(com.najjar.android.lib.R.string.button_signout)
                    .negativeText(android.R.string.cancel)
                    .content(com.najjar.android.lib.R.string.message_confirm_sign_out)
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            AppPreferences.putStringPreference(com.najjar.android.lib.R.string.key_username, "");
                            AppPreferences.putStringPreference(com.najjar.android.lib.R.string.key_password, "");
                            onItemSignOutClick();
                        }
                    })
                    .build();
        }
        if (!signOutDialog.isShowing()) {
            signOutDialog.show();
        }
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent e) {
        switch (keycode) {
            case KeyEvent.KEYCODE_MENU:
                return false;
        }

        return super.onKeyDown(keycode, e);
    }

    public final boolean isLocalEnglish() {
        return isLocalEnglish;
    }

    public boolean isHideSettingsOption() {
        return hideSettingsOption;
    }

    public void setHideSettingsOption(boolean hideSettingsOption) {
        this.hideSettingsOption = hideSettingsOption;
    }

    public boolean isHideSignOutOption() {
        return hideSignOutOption;
    }

    public void setHideSignOutOption(boolean hideSignOutOption) {
        this.hideSignOutOption = hideSignOutOption;
    }

    public SwipeRefreshLayout getSwipeLayout() {
        return swipeLayout;
    }

    public View getRootView() {
        return rootView;
    }

    private DialogInterface.OnDismissListener onDismissListener = new DialogInterface.OnDismissListener() {
        @Override
        public void onDismiss(DialogInterface dialog) {
            ServiceRequestQueue.getInstance().cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    return true;
                }
            });
        }
    };

    /**
     * show loading dialog
     *
     * @since lib 1.5.36
     */
    public void showLoadingDialog() {
        showLoadingDialog(getString(com.najjar.android.lib.R.string.message_loading), false);
    }

    /**
     * show process dialog with message
     *
     * @param message message will show in dialog
     * @since lib 1.5.36
     */
    public void showLoadingDialog(String message) {
        showLoadingDialog(message, false);
    }

    /**
     * show process dialog with Loading message
     *
     * @param flag if true: when the dialog dismiss will clear all request on ServiceRequestQueue
     * @since lib 1.5.36
     */
    public void showLoadingDialog(boolean flag) {
        showLoadingDialog(getString(com.najjar.android.lib.R.string.message_loading), flag);
    }

    public void showLoadingDialog(String message, boolean flag) {
        if (loadingDialog == null) {
            loadingDialog = new com.afollestad.materialdialogs.MaterialDialog.Builder(this)
                    .progress(true, 0).build();

            /*loadingDialog.setCancelable(false);
            loadingDialog.setCanceledOnTouchOutside(false);*/
        }
        if (!loadingDialog.isShowing()) {
            loadingDialog.setContent(message);
            loadingDialog.setOnDismissListener(flag ? onDismissListener : null);
            loadingDialog.show();
        }
    }

    public void dismissLoadingDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    public void showExitDialog() {
        if (exitDialog == null) {
            exitDialog = new MaterialDialog.Builder(this)
                    .content(com.najjar.android.lib.R.string.message_exit)
                    .positiveText(com.najjar.android.lib.R.string.button_exit)
                    .negativeText(com.najjar.android.lib.R.string.button_cancel)
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            dialog.dismiss();
                            finish();
                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {
                            dialog.dismiss();
                        }
                    })
                    .build();
        }

        if (!exitDialog.isShowing()) {
            exitDialog.show();
        }
    }

    public void showLoadingView() {
        if (loadingView != null) {
            loadingView.startAnimation();
        }
    }

    private void checkLocal() {
        Locale current = Locale.getDefault();
        String localValue = AppPreferences.getStringPreference(com.najjar.android.lib.R.string.key_local);
        if (!current.toString().equals(localValue)) {
            Locale locale = new Locale(localValue);
            Locale.setDefault(locale);

            Configuration config = new Configuration();
            config.locale = locale;
            Resources resources = getResources();
            resources.updateConfiguration(config, resources.getDisplayMetrics());
            recreate();
        }
        isLocalEnglish = localValue.equals(getString(com.najjar.android.lib.R.string.key_local_en));
    }

    /**
     * check if the device tablet or not
     *
     * @return true if the device tablet, otherwise false
     * @since 1.5.37
     */
    private boolean isTablet() {
        return (getApplicationContext().getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    protected SuperActivity getThis() {
        return this;
    }

    protected void onItemSettingClick() {
    }

    protected void onItemSignOutClick() {
    }


}
