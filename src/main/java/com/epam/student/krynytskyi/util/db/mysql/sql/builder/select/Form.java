package com.epam.student.krynytskyi.util.db.mysql.sql.builder.select;

public class Form {
    private FormInnerJoin formInnerJoin;
    public FormInnerJoin form(StringBuffer resultString) {
        formInnerJoin = new FormInnerJoin(resultString);
        return formInnerJoin;
    }
}
