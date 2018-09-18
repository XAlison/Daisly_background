package com.icekredit.http;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;

import static org.assertj.core.api.Java6Assertions.allOf;
import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by root on 16-12-29.
 */
public class BastionTest {
    @Test
    public void testGet(){


//        Bastion.request("test get", GeneralRequest.get("http://www.icekredit.com"))
//                .withAssertions((statusCode, response, model) -> {
//                    try {
//                        assertThat(statusCode).isNotNull();
//                    }catch (AssertionError r1){
//                        System.out.println("11111111111111");
//                    }
//                    try {
//                        System.out.println(response.getHeaders());
//                        assertThat(response).isEqualTo("200");
//                    }catch (AssertionError r2){
//                        System.out.println("222222222222222");
//                    }
//
//                })
//                .call();
    }
}