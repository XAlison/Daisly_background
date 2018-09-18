package com.icekredit.http;

/**
 * Represents a data parameter which is used for {@code application/x-www-form-urlencoded} data. You can supply these to
 * a {@link FormUrlEncodedRequest} to build the content body.
 */
public class ApiDataParameter extends ApiProperty {

    public ApiDataParameter(String name, String value) {
        super(name, value);
    }

}
