package com.epam.student.krynytskyi.util.db.mysql;

import com.epam.student.krynytskyi.beans.PrepareStatementBuilderParamsBean;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class PrepareStatementBuilderImpl implements PrepareStatementBuilder {
    private static final Logger log = Logger.getLogger(PrepareStatementBuilderImpl.class);
    private PrepareStatementBuilderParamsBean paramsBean;

    public PrepareStatementBuilderImpl(PrepareStatementBuilderParamsBean paramsBean) {
        this.paramsBean = paramsBean;
    }

    public String getQuery(){
        return paramsBean.getSqlQuery();
    }

    public PreparedStatement build(PreparedStatement query) throws SQLException {
        List<String> parameters = paramsBean.getPrepareStatementParams();
        populateQueryParameters(query, parameters);
        return query;
    }

    private void populateQueryParameters(PreparedStatement query, List<String> parameters) throws SQLException {
        for (int i = 0; i < parameters.size(); i++) {
            query.setString(i+1,parameters.get(i));
        }
    }
}
