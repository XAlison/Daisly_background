package com.icekredit.http;

/**
 * Simple data structure for holding a single instance of an HTTP request query parameter. Query parameters are provided
 * as part of a requests URL following the question mark symbol.
 */
public class ApiQueryParam extends ApiProperty {

    public ApiQueryParam(String name, String value) {
        super(name, value);
    }

    private ApiQueryParam(){
        // required for serialization
    }
}
