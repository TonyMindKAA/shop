package com.epam.student.krynytskyi.util.db.mysql.sql.builder.select;

public class FormInnerJoin {
    private StringBuffer resultString;
    private InnerJoin innerJoin = new InnerJoin();
    private TableInnerJoin tableInnerJoin = new TableInnerJoin();
    private Node node = new Node();
    private As as = new As();
    private On on = new On();
    private LastNode LastNode = new LastNode();
    private Where where = new Where();
    private FirstAs firstAs = new FirstAs();

    public FormInnerJoin(StringBuffer resultString) {
        this.resultString = resultString;
    }

    public InnerJoin table(String tableName) {

        return innerJoin;
    }

    class InnerJoin {
        public TableInnerJoin innerJoin() {

            return tableInnerJoin;
        }

        public FirstAs as(String alias) {
            return firstAs;
        }
    }

    class FirstAs {
        public TableInnerJoin innerJoin() {

            return FormInnerJoin.this.tableInnerJoin;
        }
    }


    class TableInnerJoin {
        public Node tableName(String tableName) {

            return node;
        }
    }

    class Node {
        public As as(String alias) {

            return as;
        }

        public On on() {

            return on;
        }


    }

    class As {
        public On on() {
            return FormInnerJoin.this.on;
        }
    }

    class On {
        public LastNode predicate(String predicateStrinig) {
            return LastNode;
        }
    }

    class LastNode {
        public Where where() {
            return where();
        }

        public TableInnerJoin innerJoin() {
            return FormInnerJoin.this.tableInnerJoin;
        }
    }
}