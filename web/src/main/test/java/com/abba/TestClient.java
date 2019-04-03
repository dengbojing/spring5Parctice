package com.abba;

import org.junit.Test;

public class TestClient {

    @Test
    public void test(){
        final int i = 9;
        setVariable(i);
        System.out.println(i);
    }
    public void setVariable(int i){
        i =10;
    }

}
