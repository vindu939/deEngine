package com.talentica.defaultNamespace.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by aravindp on 25/5/16.
 */
public class TestC {

    @Autowired
    @Qualifier("test2")
    TestA testA;

    public static String namespace = "default";
   /* @Autowired
    TestB testB;*/

    public String getVal(){
        return  namespace + " : " + testA.getName();
    }
}
