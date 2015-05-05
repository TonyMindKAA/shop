package com.epam.student.krynytskyi.db.transaction;

import java.sql.Connection;

public interface TransactionOperation {

	Object execute(Connection conn) throws Exception;

}
