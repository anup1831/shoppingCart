package com.anup.pricingbasketsecond.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.anup.pricingbasketsecond.R;

/**
 * Created by Anup on 6/4/2018.
 */

public class FullScreenLoading extends Dialog {
    private Context context;
    private TextView tvLoaderText;

    public FullScreenLoading(Context context) {
        super(context);
        this.context = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.full_screen_loading, null);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        tvLoaderText =(TextView) view.findViewById(R.id.tvLoaderText);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(view);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    public FullScreenLoading(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected FullScreenLoading(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
