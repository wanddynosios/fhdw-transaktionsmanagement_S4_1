package version2.model.db;

import version2.def.DB_Operation;
import version2.def.DB_Transaction;

public class DBRecoveryManagerComponent {
	
	private DBBufferManagerComponent bufferManager;

	public DBRecoveryManagerComponent(DBBufferManagerComponent bufferManager) {
		this.bufferManager = bufferManager;
	}
	
	public void read(long transactionID, long dataID, long requestSize) {
		this.bufferManager.read(dataID, requestSize);
	}
	
	public void write(long transactionID, long dataID, long requestSize) {
		this.bufferManager.read(dataID, requestSize);
		this.bufferManager.writeLog(dataID, requestSize);
		this.bufferManager.write(dataID, requestSize);
	}

	public void commit(long transactionID, DB_Transaction transaction) {
		this.bufferManager.writeLog(transaction.get(0).getDataID(),  transaction.size() * 4);
	}
	
	public void abort(long transactionID, DB_Transaction transaction) {
		for (DB_Operation op : transaction) {
			this.bufferManager.readLog(op.getDataID(), 64);
			this.bufferManager.write(op.getDataID(), 16);
		}
	}
	
}
