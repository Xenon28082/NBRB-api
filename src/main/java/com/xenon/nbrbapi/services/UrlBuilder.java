package com.xenon.nbrbapi.services;

import com.squareup.okhttp.HttpUrl;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class UrlBuilder {

    public static String build (String host, List<String> pathSegments, Map<String, String> params){
        var builder = new HttpUrl.Builder()
                .scheme("https")
                .host(host);
        pathSegments.forEach(builder::addPathSegment);
        params.keySet().forEach(key -> builder.addQueryParameter(key, params.get(key)));

        return builder.build().toString();
    }

}
