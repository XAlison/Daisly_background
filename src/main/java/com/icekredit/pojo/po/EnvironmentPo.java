package com.icekredit.pojo.po;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.icekredit.pojo.vo.EnvironmentVo;


public class EnvironmentPo {
    /* 环境id*/
    private String id;

    /* 环境名 */
    private String environmentName;

    /* 所属项目id */
    private String projectId;

    /* 创建者 */
    private String createBy;

    /* 创建日期 */
    private String createDate;

    /* 更新者 */
    private String updateBy;

    /* 跟新日期 */
    private String updateDate;

    /* 头部信息 */
    private String apiHeaders;

    /* 变量信息 */
    private String initVariables;

    /* Url参数  */
    private String apiUrlParameter;

    /** Auth */
    private String authInfo;


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


    public String getEnvironmentName() {
        return environmentName;
    }

    public void setEnvironmentName(String environmentName) {
        this.environmentName = environmentName;
    }

    public String getApiHeaders() {
        return apiHeaders;
    }

    public void setApiHeaders(String apiHeaders) {
        this.apiHeaders = apiHeaders;
    }

    public String getInitVariables() {
        return initVariables;
    }

    public void setInitVariables(String initVariables) {
        this.initVariables = initVariables;
    }

    public String getApiUrlParameter() {
        return apiUrlParameter;
    }

    public void setApiUrlParameter(String apiUrlParameter) {
        this.apiUrlParameter = apiUrlParameter;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(String authInfo) {
        this.authInfo = authInfo;
    }
}
