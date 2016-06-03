package com.talentica.defaultNamespace.test;

/**
 * Created by aravindp on 25/5/16.
 */
public class TestB {

    private String name;
    public TestB(TestA a){
        this.name = "Static" + a.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
