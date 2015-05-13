package com.epam.student.krynytskyi.beans;

import java.util.List;

public class PrepareStatementBuilderParamsBean {
    private String sqlQuery;
    private List<String> prepareStatementParams;

    public String getSqlQuery() {
        return sqlQuery;
    }

    public PrepareStatementBuilderParamsBean setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
        return this;
    }

    public List<String> getPrepareStatementParams() {
        return prepareStatementParams;
    }

    public PrepareStatementBuilderParamsBean setPrepareStatementParams(List<String> prepareStatementParams) {
        this.prepareStatementParams = prepareStatementParams;
        return this;
    }
}
