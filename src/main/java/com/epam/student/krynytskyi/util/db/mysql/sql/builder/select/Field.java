package com.epam.student.krynytskyi.util.db.mysql.sql.builder.select;

public class Field {
    private StringBuffer resultString = new StringBuffer();
    private As as = new As();
    private NextField nextField = new NextField();
    private NewField newField = new NewField();
    private Form form = new Form();

    public As setField(String field) {
        newField.setField(field);
        return as;
    }

    private FormInnerJoin form(){
        return form.form(resultString);
    }
    public class As {
        public NextField as(String alias) {
            resultString.append(" as " + alias);
            return nextField;
        }

        public FormInnerJoin form() {
            return Field.this.form();
        }
    }

    public class NextField {
        public NewField next() {
            resultString.append(",");
            return Field.this.newField;
        }

        public FormInnerJoin form() {
            return Field.this.form();
        }
    }

    public class NewField {
        As setField(String field) {
            resultString.append(" " + field);
            return as;
        }
    }
}