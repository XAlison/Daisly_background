package com.icekredit.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.internal.path.json.JSONAssertion;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.config.HttpClientConfig.httpClientConfig;
import static com.jayway.restassured.config.SSLConfig.sslConfig;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import com.jayway.restassured.specification.RequestSpecification;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.params.CoreConnectionPNames;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.comparator.JSONComparator;
import uk.co.datumedge.hamcrest.json.SameJSONAs;

public class ExampleForDoubanAPI {

    @Before
    public void before() {
        RestAssured.baseURI = "https://api.douban.com/v2/book";
//        RestAssured.baseURI = "https://www.baidu.com";
//        RestAssured.port = 80;
    }

    @Test
    //URL为https://api.douban.com/v2/book/1220562
    //判断Json中的返回信息title
    public void testGetBook() {
        get("/1220562").then().body("title", equalTo("满月之夜白鲸现"));
    }

    @Test
    //URL为http://api.douban.com/v2/book/search?q=java8
    //判断Json中的返回信息关键字为“java8”的书本的数目
    public void testSearchBook() {
        given().param("q", "java8").when().get("/search").then().body("count", equalTo(2));
    }
    @Test
    public void validates_schema_in_classpath() {

    }
    @Test
    //解析JSON
    public void testParseJson() {
        RestAssured.config = RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation());
        RequestSpecification requestSpecification = given();
        requestSpecification.config(RestAssured.config);
        Response res = requestSpecification.when().get("/1220562");
        ValidatableResponse resp = res.then();
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();
//        resp.body("$", SameJSONAs.sameJSONAs(new J{}"));
        //判断返回Json数据的title
        System.out.println(res.body().jsonPath().get("$").toString());
        String t = res.body().jsonPath().get("$").toString();
        JSON.toJSONString(res.body().jsonPath().get("$"));
        assertThat("{}", SameJSONAs.sameJSONAs(JSON.toJSONString(res.body().jsonPath().get("$"))));
        resp.body("title", equalTo("满月之夜白鲸现"));
        //判断二级属性rating.max的值
        resp.body("rating.max", equalTo(10));
        //调用数组的方法判断数组的大小
        resp.body("tags.size()", is(8));
        //判断数组第一个对象的值
        resp.body("tags[0].name", equalTo("片山恭一"));
        //判断数组中是否有该元素
        resp.body("author", hasItems("[日] 片山恭一"));
    }

    @Test
    public void testString(){
        RestAssured.config = RestAssured.config().sslConfig(sslConfig().relaxedHTTPSValidation());
        RestAssured.config = RestAssured.config().httpClient(httpClientConfig()
                .setParam(ClientPNames.CONN_MANAGER_TIMEOUT, Long.valueOf(1000))  // HttpConnectionManager connection return time
                .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 1000) // Remote host connection time
                .setParam(CoreConnectionPNames.SO_TIMEOUT, 1000)  // Remote host response time
        );
        RequestSpecification requestSpecification = given();
        requestSpecification.config(RestAssured.config);
        Response resp = requestSpecification.when().get("/1220562");
        String tt = resp.getContentType();
        System.out.println(tt);
    }

    @After
    public void after() {
    }

}