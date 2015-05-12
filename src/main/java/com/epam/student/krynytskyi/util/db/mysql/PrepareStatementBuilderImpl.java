package com.epam.student.krynytskyi.util.db.mysql;

import com.epam.student.krynytskyi.beans.PrepareStatementBuilderParamsBean;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class PrepareStatementBuilderImpl implements PrepareStatementBuilder {
    private PrepareStatementBuilderParamsBean paramsBean;

    public PrepareStatementBuilderImpl(PrepareStatementBuilderParamsBean paramsBean) {
        this.paramsBean = paramsBean;
    }

    public String getQuery(){
        return paramsBean.getSqlQuery();
    }

    public PreparedStatement build(PreparedStatement statement) throws SQLException {
        List<String> prepareStatementParams = paramsBean.getPrepareStatementParams();
        for (int i = 0; i < prepareStatementParams.size(); i++) {
            statement.setString(i+1,prepareStatementParams.get(i));
        }
        return statement;
    }
}
