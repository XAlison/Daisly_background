package com.icekredit.pojo.bo;

import com.icekredit.pojo.*;
import com.icekredit.pojo.vo.EnvironmentVo;
import com.icekredit.pojo.vo.RequestVo;
import com.icekredit.utils.Groovy;
import com.icekredit.utils.MyString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RequestBo {
    private RequestVo requestVo;

    private String apiName;

    private String url;

    private Map  variableMap;

    private String methodType;

    private String bodyType;

    private Collection<Header> headers;

    private Collection<UrlParameter> queryParams;

    private Collection<FormData> formParams;

    private Collection<RequestVariable> variables;

    private Collection<Assertion> assertionParams;

    private EnvironmentVo environment;

    private Raw rawBody;

    private Logger logger = LoggerFactory.getLogger(RequestBo.class);

    private final static String functionName = "process";

    public RequestBo(RequestVo requestVo, EnvironmentVo environment){
        this.environment = environment;
        this.requestVo = requestVo;
        this.variables = requestVo.getApiVariables();
        if (environment == null){
            this.environment = new EnvironmentVo();
        }
        this.variableMap = variableaToMap(this.environment.getInitVariables());
        this.url = preProcess(requestVo.getApi());
        this.apiName = preProcess(requestVo.getRequestName());
        this.methodType = requestVo.getRequestType();
        processHeaders();
        processQueryParams();
        processFormParam();
        processRawParam();
        processAssertions();
        processBodyType();
    }

    private Map variableaToMap(List<Variable> variables){
        Map m = new HashMap();
        if (null == variables){
            return m;
        }
        variables.stream().forEach(x->{
            m.put(x.getName(), x.getValue());
        });
        return m;
    }


    private String preProcess(String source){
        // 按指定模式在字符串查找
        String sourceCopy = source;
        //\{[^}]*\}
        //String regex = "<[^>]*>";
        String regex = "\\{\\{[^}]*}}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceCopy);
        while (matcher.find()) {
            String nameMatch = matcher.group().replaceAll("\\{\\{", "");
            String name = nameMatch.replaceAll("\\}\\}", "");
            String value = "";
            if (variableMap.containsKey(name)){
                value = (String) variableMap.get(name);
            }
            if (null == value){
                System.out.println(sourceCopy);
            }
            sourceCopy = sourceCopy.replaceAll("\\{\\{" + name + "}}", value);
        }
        return sourceCopy;
    }

    public void processBody(){


    }

    public void processHeaders(){
        headers = new ArrayList<>();
        List<com.icekredit.pojo.Header> headerList = this.requestVo.getApiHeaders();
        if (null != environment.getApiHeaders()){
            headerList.addAll(environment.getApiHeaders());
        }
        headerList.stream()
                .filter(x->{
                    logger.info(x.toString());
                    return !x.getKey().equals("") ||
                            !x.getValue().equals("");
                })
                .forEach(x->{
                    headers.add(new Header(preProcess(x.getKey()), preProcess(x.getValue())));
                });
    }

    public void processQueryParams(){
        queryParams = new ArrayList<>();
        List<UrlParameter> urlParameterList = this.requestVo.getApiUrlParameter();
        if (null != environment.getApiUrlParameter()){
            urlParameterList.addAll(environment.getApiUrlParameter());
        }
        urlParameterList.stream()
                .filter(x->{
                    logger.info(x.toString());
                    return !x.getKey().equals("") ||
                            !x.getValue().equals("");
                })
                .forEach(x->{
                    queryParams.add(new UrlParameter(preProcess(x.getKey()), preProcess(x.getValue())));
                });
    }

    public void processFormParam(){
        formParams = new ArrayList<>();
        List<FormData> formDataList = this.requestVo.getApiBodies().getFormData();
        formDataList.stream()
                .filter(
                        x->{
                            logger.info(x.toString());
                            return !x.getKey().equals("") ||
                                    !x.getValue().equals("");
                        }
                ).forEach(
                x->{
                    logger.info(x.toString());
//                    String afterData = preProcess(x.toString());
//                    JSONObject data = JSON.parseObject(afterData);
                    formParams.add(new FormData(preProcess(x.getKey()), preProcess(x.getValue())));
                }
        );
    }

    public void processRawParam(){
        Groovy groovy = new Groovy();
        rawBody = this.requestVo.getApiBodies().getRaw();
        rawBody.setValue(preProcess(rawBody.getValue()));
        if (!MyString.isEmpty(requestVo.getPreRequestScript())) {
            String oriBody = rawBody.getValue();
            String body = (String) groovy.runGroovyScript(requestVo.getPreRequestScript(), functionName, new String[]{oriBody});
            rawBody.setValue(body);
        }
    }

    public Collection<Assertion> getAssertionParams() {
        return assertionParams;
    }

    public void processAssertions(){
        assertionParams = new ArrayList<>();
        List<Assertion> assertions = this.requestVo.getApiAssertions();
        assertions.stream()
                .filter(
                        x->{
                            logger.info(x.toString());
                            return !x.getName().equals("");
                        }
                ).forEach(
                x->{
                    logger.info(x.toString());
                    assertionParams.add(new Assertion(
                            preProcess(x.getName()),
                            preProcess(x.getSource()),
                            preProcess(x.getProperty()),
                            preProcess(x.getComparison()),
                            preProcess(x.getTargetValue())));
                }
        );
    }

    public void processBodyType(){
        bodyType = this.requestVo.getApiBodies().getFlag();
    }


    public RequestVo getData() {
        return requestVo;
    }

    public String getApiName() {
        return apiName;
    }

    public String getUrl() {
        return url;
    }

    public String getMethodType() {
        return methodType;
    }

    public Collection<Header> getHeaders() {
        return headers;
    }

    public Collection<UrlParameter> getQueryParams() {
        return queryParams;
    }

    public Collection<FormData> getFormParams() {
        return formParams;
    }

    public Raw getRawBody() {
        return rawBody;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public RequestVo getRequestVo() {
        return requestVo;
    }

    public void setRequestVo(RequestVo requestVo) {
        this.requestVo = requestVo;
    }

    public void setEnvironment(EnvironmentVo environment) {
        this.environment = environment;
    }

    public Collection<RequestVariable> getVariables() {
        return variables;
    }
}
