package version2.model.db;

import org.apache.commons.math3.distribution.UniformRealDistribution;
import version2.def.DB_Operation;
import version2.def.DB_Scheduler;
import version2.def.DB_Transaction;

import java.util.HashMap;

public class DBSchedulerComponent implements DB_Scheduler {

	private static final int MAX_DATAID_SIZE = 10000;
	private static final double ABORT_RATIO = 0.00;
	
	private DBRecoveryManagerComponent recoveryManager;
	private HashMap<Long, DB_Transaction> transactionMap;
	private UniformRealDistribution requestSize;
	private UniformRealDistribution abortDistribution;
	
	
	public DBSchedulerComponent(DBRecoveryManagerComponent recoveryManager) {
		this.recoveryManager = recoveryManager;
		this.transactionMap = new HashMap<Long, DB_Transaction>();
		this.requestSize = new UniformRealDistribution(1, MAX_DATAID_SIZE);
		this.abortDistribution = new UniformRealDistribution(0, 1);
	}


	public void schedule(DB_Transaction transaction, long transactionID) {
		transactionMap.put(transactionID, transaction);
		for (DB_Operation op : transaction) {
			op.schedule(this, transactionID);
		}
		if(abortDistribution.sample() < ABORT_RATIO) {
			this.operationAbort(transactionID);
		}else{
			this.operationCommit(transactionID);
		}
	}

	@Override
	public void operationRead(long transactionID, DB_Operation operation) {
		recoveryManager.read(transactionID, operation.getDataID(), (long) this.requestSize.sample());
	}
	
	@Override
	public void operationWrite(long transactionID, DB_Operation operation) {
		recoveryManager.write(transactionID, operation.getDataID(), (long) this.requestSize.sample());
	}

	@Override
	public void operationAbort(long transactionID) {
		recoveryManager.abort(transactionID, this.transactionMap.get(transactionID));
		this.transactionMap.remove(transactionID);
	}

	@Override
	public void operationCommit(long transactionID) {
		recoveryManager.commit(transactionID, this.transactionMap.get(transactionID));
		this.transactionMap.remove(transactionID);
	}



}
