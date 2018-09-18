package com.icekredit.apitest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.icekredit.pojo.bo.RequestBo;
import com.icekredit.utils.IsANumMatcher;
import com.icekredit.pojo.Assertion;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import uk.co.datumedge.hamcrest.json.SameJSONAs;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


public class AssertionExecutor {

    private RequestBo requestBo;
    private Response res;
    private ValidatableResponse validatableResponse;
    public AssertionExecutor(RequestBo requestBo, Response res){
        this.requestBo = requestBo;
        this.res = res;
        validatableResponse = res.then();
    }

    //Integer.parseInt
    public  Matcher assertionType(Assertion assertion){
        switch(assertion.getComparison()){
            case "equals":
                return Matchers.equalTo(assertion.getTargetValue());
            case "equals(json)":
                return SameJSONAs.sameJSONAs(assertion.getTargetValue()).
                        allowingExtraUnexpectedFields().allowingAnyArrayOrdering();
            case "does not equal":
                return not(assertion.getTargetValue());
            case "is empty":
                return isEmptyString();
            case "is not empty":
                return Matchers.is(not(isEmptyString()));
            case "contains":
                return Matchers.containsString(assertion.getTargetValue());
            case "does not contains":
                return Matchers.not(containsString(assertion.getTargetValue()));
            case "is a number":
                return IsANumMatcher.generateMatcher();
            case "equals(number)":
                return Matchers.anyOf(Matchers.equalTo(Double.parseDouble(assertion.getTargetValue())), Matchers.equalTo(Integer.parseInt(assertion.getTargetValue())));
            case "less than or equal":
                return Matchers.lessThanOrEqualTo(Double.parseDouble(assertion.getTargetValue()));
            case "greater than":
                return Matchers.greaterThan(Double.parseDouble(assertion.getTargetValue()));
            case "greater than or equal":
                return Matchers.greaterThanOrEqualTo(Double.parseDouble(assertion.getTargetValue()));
            case "has item":
                return hasItem(assertion.getTargetValue());
            case "json schema":
                // Given
                JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();
                return matchesJsonSchema(assertion.getTargetValue()).using(jsonSchemaFactory);
            default:
                return nullValue();
        }
    }

    public void sourceMatch(Assertion assertion){
        switch(assertion.getSource()){
            case "Headers":
                break;
            case "JSONBody":
                processJsonBody(assertion);
                break;
            case "Response Size(bytes)":
                break;
            case "Status code":
                processStatusCode(assertion);
                break;
            case "Response Time(ms)":
                processResponseTime(assertion);
                break;
            case "XML Body":
                break;
            default:
                break;
        }
    }


    private void processJsonBody(Assertion assertion){
        try {
            if (assertion.getComparison().equals("json schema")){
                validatableResponse.body(assertionType(assertion));
            }else if (assertion.getComparison().equals("equals(json)")){
                String body = JSON.toJSONString(res.body().jsonPath().get(assertion.getProperty()), SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue);
                assertThat(body, assertionType(assertion));
            }
            else {
                validatableResponse.body(assertion.getProperty(), assertionType(assertion));
            }
            assertion.setIsSuccess('1');
        }catch (AssertionError e){
            assertion.setIsSuccess('0');
            assertion.setFailedReason(e.toString());
        }
    }

    private void processStatusCode(Assertion assertion){
        Double d;
        try {
            d = Double.parseDouble(assertion.getTargetValue());
            if (res.getStatusCode() == d){
                assertion.setIsSuccess('1');
            }else {
                assertion.setIsSuccess('0');
                assertion.setFailedReason("status code is not equal " + assertion.getTargetValue());
            }
        }catch (AssertionError e){
            assertion.setIsSuccess('0');
            assertion.setFailedReason(e.toString());
        }
    }

    private void processResponseTime(Assertion assertion){
        try {
            validatableResponse.time(assertionType(assertion), TimeUnit.MICROSECONDS);
            assertion.setIsSuccess('1');
        }catch (AssertionError e){
            assertion.setIsSuccess('0');
            assertion.setFailedReason(e.toString());
        }
    }

    public void executeHttpAssert(){
        requestBo.getAssertionParams().stream()
                .forEach(x->{
                    sourceMatch(x);
                });
    }

    public static void main(String args[]){
        Assertion assertion = new Assertion("xx","Status code","","equals","200");
        try {
           // assertThat(20, assertionType(assertion));
        }catch (AssertionError e){

            System.out.println(e.toString());
        }
    }
}
