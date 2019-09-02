package com.abba;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

@Slf4j
public class EncryptorTest {


    @Test
    public void configurationEncryptor() {
        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword("jas");
<<<<<<< HEAD
        String name = basicTextEncryptor.encrypt("root");
        String pwd = basicTextEncryptor.encrypt("20042156q");
        String url = basicTextEncryptor.encrypt("jdbc:mysql://localhost:3305/yichen_trash_bin?autoReconnect=true&useSSL=false");
=======
        String name = basicTextEncryptor.encrypt("m123456");
>>>>>>> e074b52e05aa1ebdbdfab00d97ffa40331ec28be
        log.info(name);
        log.info(pwd);
        log.info(url);
    }

    @Test
    public void testNetWorkInterfaces() throws SocketException {
        NetworkInterface nif = NetworkInterface.getByName("net1");
        Enumeration<InetAddress> nifAddresses = nif.getInetAddresses();
        while(nifAddresses.hasMoreElements()){
            System.out.println(new String(nifAddresses.nextElement().getAddress()));
        }

    }
}
