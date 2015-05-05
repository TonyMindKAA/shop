package com.epam.student.krynytskyi.db.transaction;


public interface TransactionManager {
	Object doInTransaction(TransactionOperation operation) throws Exception;
}
