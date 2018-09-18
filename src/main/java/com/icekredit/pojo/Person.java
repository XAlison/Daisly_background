package com.icekredit.pojo;

import java.io.Serializable;

/**
 * Created by root on 16-12-19.
 */
public class Person implements Serializable{

    private static final long serialVersionUID = 8253364118180356839L;

    private String id;
    private String age;

    public Person(){
        super();
    }

    public Person(String id, String age){
        this.id = id;
        this.age = age;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;

    }

}
