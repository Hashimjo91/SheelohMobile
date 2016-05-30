package com.sheeloh.alhusban.sheelohapp.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;
import com.sheeloh.alhusban.sheelohapp.R;

/**
 * Created by Hashim Al-husban
 * on 5/26/2016.
 */
public class CustomDialog extends ProgressDialog {
    public CustomDialog(Context context) {
        super(context);
        loadStateFromAttrs();
    }

    /** See also the fffffu . */
    private void loadStateFromAttrs() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        getWindow().requestFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setIndeterminate(false);
        setCancelable(false);
        show();
        setContentView(R.layout.progress_dialog);
    }

}
