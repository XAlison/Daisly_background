package com.icekredit.apitest;

import com.icekredit.http.ApiProperty;
 /**
 * Simple data structure for holding a single instance of an HTTP request query parameter. Query parameters are provided
 * as part of a requests URL following the question mark symbol.
 */
public class ApiAssertion extends ApiProperty {

    public ApiAssertion(String name, String value) {
        super(name, value);
    }

    private ApiAssertion(){
        // required for serialization
    }
}
