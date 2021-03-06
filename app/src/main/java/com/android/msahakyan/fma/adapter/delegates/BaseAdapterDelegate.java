package com.android.msahakyan.fma.adapter.delegates;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import com.android.msahakyan.fma.application.FmaApplication;
import com.android.msahakyan.fma.network.FmaApiService;
import com.android.msahakyan.fma.util.AppUtils;
import com.android.msahakyan.fma.util.Item;
import com.android.volley.toolbox.ImageLoader;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by msahakyan on 31/07/16.
 */

public abstract class BaseAdapterDelegate implements AdapterDelegate<List<Item>> {

    private static final int DEFAULT_SPAN_SIZE = 2;

    static final int TYPE_GENRE = 0;
    static final int TYPE_CURATOR = 1;
    static final int TYPE_ALBUM = 2;
    static final int TYPE_ARTIST = 3;
    static final int TYPE_TRACK = 4;
    static final int TYPE_TRACK_WITH_ICON = 5;
    static final int TYPE_SEARCH_RESULT = 6;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TYPE_GENRE,
        TYPE_CURATOR,
        TYPE_ALBUM,
        TYPE_ARTIST,
        TYPE_TRACK,
        TYPE_TRACK_WITH_ICON,
        TYPE_SEARCH_RESULT})
    @interface ElementViewType {
    }

    @Inject
    protected FmaApiService fmaApiService;
    @Inject
    protected ImageLoader imageLoader;

    private int mViewType;
    private Context context;

    BaseAdapterDelegate(Context context, @ElementViewType int viewType) {
        this.context = context;
        mViewType = viewType;
        FmaApplication.get(this.context).getApplicationComponent().inject(this);
    }

    @Override
    public boolean isForViewType(@NonNull List<Item> items, int position) {
        return !AppUtils.isEmpty(items);
    }

    @Override
    @ElementViewType
    public int getItemViewType() {
        return mViewType;
    }

    @Override
    public int getSpanSize() {
        return DEFAULT_SPAN_SIZE;
    }

    protected Context getContext() {
        return context;
    }
}
