package com.epam.student.krynytskyi.util.db.mysql.sql.builder.select;


public class Table {
    private StringBuffer resultString = new StringBuffer();
    private As as = new As();
    private NextTable nextTable = new NextTable();
    private FormInnerJoin innerJoinTable = new FormInnerJoin(resultString);

    public Table(StringBuffer resultString) {
        this.resultString = resultString;
    }

    public As setTable(String name) {
        resultString.append(" " + name);
        return as;
    }

    private String where() {
        return resultString.toString();
    }

    public class As {

        public NextTable as(String alias) {
            resultString.append(" as " + alias);
            return Table.this.nextTable;
        }

        private String build() {
            return Table.this.where();
        }
    }

    public class NextTable {
        public Table next() {
            resultString.append(",");
            return Table.this;
        }

        public String where() {
            return Table.this.where();
        }

        public FormInnerJoin innerJoin(){
            return innerJoinTable;
        }

    }

    public static void main(String[] args) {
        Table table = new Table(new StringBuffer());
        System.out.println(table.setTable("tabasdsa").as("sdsd").next().setTable("second").build());
    }
}
