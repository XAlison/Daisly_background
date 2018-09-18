package com.icekredit.pojo.vo;

import com.icekredit.pojo.Header;
import com.icekredit.pojo.UrlParameter;
import com.icekredit.pojo.Variable;

import java.util.List;

public class EnvironmentVo {

    /* 环境id*/
    private String id;

    /* 所属项目id */
    private String projectId;

    /* 环境名 */
    private String environmentName;

    /* 创建者 */
    private String createBy;

    /* 创建日期 */
    private String createDate;

    /* 更新者 */
    private String updateBy;

    /* 跟新日期 */
    private String updateDate;

    /* 顺序号 */
    private int sequence;

    /* 头部信息 */
    List<Header> apiHeaders;

    /* 变量信息 */
    List<Variable> initVariables;

    /* Url参数  */
    List<UrlParameter> apiUrlParameter;

    /** auth */
    AuthInfo authInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getSequence() {
        return sequence;
    }

    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public List<Header> getApiHeaders() {
        return apiHeaders;
    }

    public void setApiHeaders(List<Header> apiHeaders) {
        this.apiHeaders = apiHeaders;
    }

    public List<Variable> getInitVariables() {
        return initVariables;
    }

    public void setInitVariables(List<Variable> initVariables) {
        this.initVariables = initVariables;
    }

    public List<UrlParameter> getApiUrlParameter() {
        return apiUrlParameter;
    }

    public void setApiUrlParameter(List<UrlParameter> apiUrlParameter) {
        this.apiUrlParameter = apiUrlParameter;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public AuthInfo getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(AuthInfo authInfo) {
        this.authInfo = authInfo;
    }
}
