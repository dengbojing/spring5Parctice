package com.abba.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author dengbojing
 */
public class GaodeMapHelper {

    private final static String KEY = "da352b0b66e3369da16348b96b5ea817";
    private static String ADDRESS_2_LOCATION_URL = "https://restapi.amap.com/v3/geocode/geo";
    private static String LOCATION_2_ADDRESS_ULR = "https://restapi.amap.com/v3/geocode/regeo";

    public static String address2Location(String address,String cityCode) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        String requestUrl  = ADDRESS_2_LOCATION_URL.concat("?").concat("key="+KEY).concat("&").concat("address="+address).concat("&").concat("city="+cityCode);
        Request request = new Request.Builder().url(requestUrl).build();
        okhttp3.Response okResponse = httpClient.newCall(request).execute();
        checkNotNull(okResponse.body(),"未获取到任何地址信息！");
        return okResponse.body().string();
    }

    public static void main(String[] args) throws IOException {
        String result = address2Location("重庆市铜梁工业园区龙安路9号","500100");
        System.out.println(result);
    }
}
