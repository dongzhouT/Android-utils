package com.haierac.biz.cp.cloudplatformandroid.utils;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

import com.haierac.biz.cp.cloudplatformandroid.entity.HeaderBodyEntry;

/**
 * Created by Administrator on 2016/7/29.
 */
public class HeaderBodyRequest extends Request<HeaderBodyEntry> {
    private final Listener<HeaderBodyEntry> mListener;

    public HeaderBodyRequest(int method, String url, Listener<HeaderBodyEntry> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
    }

    public HeaderBodyRequest(String url, Listener<HeaderBodyEntry> listener, Response.ErrorListener errorListener) {
        this(0, url, listener, errorListener);
    }

    @Override
    protected Response<HeaderBodyEntry> parseNetworkResponse(NetworkResponse response) {
        HeaderBodyEntry entry = new HeaderBodyEntry();
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException var4) {
            parsed = new String(response.data);
        }
        entry.setBody(parsed);
        entry.setHeader(response);
        return Response.success(entry, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(HeaderBodyEntry headerBodyEntry) {
        mListener.onResponse(headerBodyEntry);

    }

    public interface Listener<T> {
        void onResponse(T var1);
    }
}

