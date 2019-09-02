package com.abba.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author dengbojing
 */
@Slf4j
public class QiChaChaHelper {

    private final static String KEY = "035543ea0b8d40eea79cc4fe33343bad";
    private final static String SECRET_KEY = "6CA65AA0EFADCF646B3582E39C5D108E";
    private static String URL  = "http://api.qichacha.com/ECICreditCode/GetCreditCode";
    public static String matchByNameLike(String name) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        String requestUrl = URL.concat("?key=").concat(KEY).concat("&").concat("keyword="+name);
        Request request = new Request.Builder().url(requestUrl)
                .addHeader("Token",authentHeader()[0])
                .addHeader("Timespan",authentHeader()[1]).build();
        okhttp3.Response okResponse = httpClient.newCall(request).execute();
        checkNotNull(okResponse.body(),"未获取到企业信息！");
        return okResponse.body().string();
    }

    private static String[] authentHeader(){
        String timeSpan = String.valueOf(System.currentTimeMillis() / 1000);
        return new String[] { DigestUtils.md5Hex(KEY.concat(timeSpan).concat(SECRET_KEY)).toUpperCase(), timeSpan };
    }

    public static void main(String[] args) throws IOException {
        String result = matchByNameLike("重庆长基科技有限公司");
        System.out.println(result);
    }
}
