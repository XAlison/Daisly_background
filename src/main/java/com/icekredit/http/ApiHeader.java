package com.icekredit.http;

/**
 * Simple data structure for holding a single instance of an HTTP response header.
 */
public class ApiHeader extends ApiProperty {

    public ApiHeader(String name, String value) {
        super(name, value);
    }

    private ApiHeader(){
        // required for serialization
    }
}
