package com.icekredit.apitest;

import com.icekredit.pojo.bo.RequestBo;
import com.icekredit.utils.KeyDefine;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.JsonConfig.jsonConfig;
import static com.jayway.restassured.config.SSLConfig.sslConfig;
import static com.jayway.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;


public class RequestExecutor {

    private Logger logger = LoggerFactory.getLogger(RequestExecutor.class);

    private RequestBo requestBo;

    private RequestSpecification requestSpecification;


    public RequestExecutor(RequestBo requestBo){
        this.requestBo = requestBo;
        requestSpecification = given();
        trustAllHosts();
        applyHeaders();
        applyQueryParameters();
         if (requestBo.getMethodType().equalsIgnoreCase("post")){
            if (requestBo.getBodyType().equals(KeyDefine.FORMDATA)){
                applyFormParam();
            }else if (requestBo.getBodyType().equals(KeyDefine.RAW)){
                applyRawParam();
            }
        }
    }

    //trust all hosts regardless if the SSL certificate is invalid
    private void trustAllHosts(){
        RestAssured.config = RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation());
        RestAssured.config =  RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL));
        requestSpecification.config(RestAssured.config);

    }

    private void applyHeaders(){
        requestBo.getHeaders().stream()
                .forEach(x->{
                    if (x.getKey().equalsIgnoreCase("cookie")){
                        String[] cookies = x.getValue().split(";");
                        for (String s:cookies){
                            String[] c = x.getValue().split("=");
                            requestSpecification.cookie(c[0], c[1]);
                        }
                    }else {
                        requestSpecification.header(new Header(x.getKey(), x.getValue()));
                    }
                });
    }

    private void applyQueryParameters(){
        requestBo.getQueryParams().stream()
                .forEach(x->{
                    requestSpecification.params(x.getKey(), x.getValue());
                });
    }

    private void applyFormParam(){
        requestBo.getFormParams().stream()
                .forEach(
                        x->{
                            logger.info(x.getKey() + "----" + x.getValue());
                            requestSpecification = requestSpecification.formParam(x.getKey(), x.getValue());
                        }
                );
    }

    private void applyRawParam(){
        requestSpecification = requestSpecification.body(requestBo.getRawBody().getValue());
    }

    public Response executeHttpRequest(){
        Response response = null;
        switch (requestBo.getMethodType().toUpperCase()) {
            case "GET":
                response = requestSpecification.when().get(requestBo.getUrl());
                break;
            case "POST":
                response = requestSpecification.when().post(requestBo.getUrl());
                break;
            case "PATCH":
                response = requestSpecification.when().patch(requestBo.getUrl());
                break;
            case "DELETE":
                response = requestSpecification.when().delete(requestBo.getUrl());
                break;
            case "PUT":
                response = requestSpecification.when().put(requestBo.getUrl());
                break;
            case "OPTIONS":
                response = requestSpecification.when().options(requestBo.getUrl());
                break;
            case "HEAD":
                response = requestSpecification.when().head(requestBo.getUrl());
                break;
            default:
                throw new UnsupportedOperationException(String.format("We cannot perform a request of type %s.", requestBo.getUrl()));
        }
        return response;
    }
}
