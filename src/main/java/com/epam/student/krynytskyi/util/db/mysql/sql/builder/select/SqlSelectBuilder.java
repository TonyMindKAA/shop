package com.epam.student.krynytskyi.util.db.mysql.sql.builder.select;

/**
 * Created by Mr.Green on 5/10/2015.
 */
public class SqlSelectBuilder {
    private Field field = new Field();
    private Table table = new Table(new StringBuffer());

    public Field select()
    {
        return field;
    }

}
