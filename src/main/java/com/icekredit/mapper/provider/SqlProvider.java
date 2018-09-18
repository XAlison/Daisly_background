package com.icekredit.mapper.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class SqlProvider {
    public String selectTestCaes(@Param("uid") String uid, @Param("projectId") String projectId) {
        SQL sql = new SQL() {{
            SELECT("sys_test_case.create_date, sys_test_case.project_id, sys_test_case.environment_id, sys_test_case.test_case_name, sys_test_case.remarks, sys_user.account AS create_by, sys_environments.environment_name");
            FROM("orders");
            WHERE("loan_time is not NULL");
            WHERE("sys_user.id = #{uid}");
            WHERE("sys_test_case.project_id = #{projectId}");
        }};

        return sql.toString();
    }
}
