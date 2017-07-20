package com.haierac.biz.cp.cloudplatformandroid.utils;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.haierac.biz.cp.cloudplatformandroid.entity.HeaderBodyEntry;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/2/15.
 */

public class HeaderJsonRequest extends JsonRequest<HeaderBodyEntry> {
    public HeaderJsonRequest(int method, String url, String requestBody, Response.Listener<HeaderBodyEntry> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);
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
}
