package com.abba.controller;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.PushBuilder;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * for HTTP/2 Push test
 * @author dengbojing
 */
@Controller
public class PushController {

    @RequestMapping("/push")
    public String push( PushBuilder pushBuilder){
        if(null != pushBuilder){
            pushBuilder.path("static/js/test.js").push();
        }
        return "message";
    }

    @RequestMapping(value="/push/{fileId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> userHeader(@PathVariable("fileId") String fileId) throws IOException {
        String url = "";  
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okhttp3.Response okResponse = okHttpClient.newCall(request).execute();
        //InputStream in = okResponse.body().byteStream();
        InputStream in = new FileInputStream("F:\\data\\target\\logs\\"+fileId+".mp4");
        ResponseEntity.BodyBuilder bodyBuilder = ResponseEntity.ok();
        return bodyBuilder.body(IOUtils.toByteArray(in));
    }

}
