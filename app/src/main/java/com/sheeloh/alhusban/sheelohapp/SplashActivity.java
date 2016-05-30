package com.sheeloh.alhusban.sheelohapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.EditText;
import android.widget.ImageView;
import com.sheeloh.alhusban.sheelohapp.ServerRelevants.ConnectionHandler;
import com.sheeloh.alhusban.sheelohapp.ServerRelevants.Tags;
import com.sheeloh.alhusban.sheelohapp.util.SharedUtil;
import com.sheeloh.alhusban.sheelohapp.util.SystemUiHider;

import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class SplashActivity extends SuperActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = false;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    boolean doubleBackToExitPressedOnce = false;
    private SystemUiHider mSystemUiHider;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    public boolean validatePassword(String password) {
        return password.length() > 5;
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final ImageView contentView = (ImageView) findViewById(R.id.fullscreen_content);
        final View login_lay = findViewById(R.id.login_lay);
        final View skip_link = findViewById(R.id.skip_link);
        final EditText Email_View = (EditText) findViewById(R.id.input_email);
        final EditText Pass_View = (EditText) findViewById(R.id.input_password);
        final View sign_in_button = findViewById(R.id.sign_in_button);
        final View register_button = findViewById(R.id.register_button);
        final TextInputLayout usernameWrapper = (TextInputLayout) findViewById(R.id.input_layout_email);
        final TextInputLayout passwordWrapper = (TextInputLayout) findViewById(R.id.input_layout_password);


        ((AnimationDrawable) contentView.getDrawable()).start();


        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validateEmail(Email_View.getText().toString())) {
                    usernameWrapper.setError("Not a valid email address!");
                } else if (!validatePassword(Pass_View.getText().toString())) {
                    passwordWrapper.setError("Not a valid password!");
                } else {
                    ConnectionHandler.Login(SplashActivity.this, Email_View.getText().toString(), Pass_View.getText().toString());

                }

            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, Register.class));
            }
        });
        skip_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //TODO Guest Check


                //


                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        if (!SharedUtil.getShared(SplashActivity.this, Tags.USER_EMAIL, "null").equals("null")) {
                            finish();
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        }


                        ((AnimationDrawable) contentView.getDrawable()).stop();
                        contentView.setImageDrawable(getDrawable(R.drawable.logo));
                        mSystemUiHider.show();
//                        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
////                        layoutParams.height=420;
////                        layoutParams.width=420;


                        ResizeAnimation resizeAnimation = new ResizeAnimation(contentView, 500, 500);
                        resizeAnimation.setDuration(1000);
                        contentView.startAnimation(resizeAnimation);


//                        login_lay.setVisibility(View.VISIBLE);
                        Animation fadeIn = new AlphaAnimation(0, 1);
                        fadeIn.setDuration(2000);
                        fadeIn.setRepeatCount(0);
                        fadeIn.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                login_lay.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        login_lay.setAnimation(fadeIn);


                    }
                });
            }
        };
        timer.schedule(timerTask, 3000);
        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                                mControlsHeight = controlsView.getHeight();
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                            controlsView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.
                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        // Set up the user interaction to manually show or hide the system UI.
//        contentView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (TOGGLE_ON_CLICK) {
//                    mSystemUiHider.toggle();
//                } else {
//                    mSystemUiHider.show();
//                }
//            }
//        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
//        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    class ResizeAnimation extends Animation {
        final int startWidth;
        final int startHeight;
        final int targetWidth;
        final int targetHeight;
        View view;

        public ResizeAnimation(View view, int targetWidth, int targetHeight) {
            this.view = view;
            this.targetWidth = targetWidth;
            this.targetHeight = targetHeight;
            startWidth = view.getWidth();
            startHeight = view.getHeight();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            int newWidth = (int) (startWidth + (targetWidth - startWidth) * interpolatedTime);
            int newHeight = (int) (startHeight + (targetHeight - startHeight) * interpolatedTime);
            view.getLayoutParams().width = newWidth;
            view.getLayoutParams().height = newHeight;
            view.requestLayout();
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }
}
