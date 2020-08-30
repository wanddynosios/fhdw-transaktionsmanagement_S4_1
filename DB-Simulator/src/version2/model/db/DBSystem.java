package version2.model.db;

import version2.def.DB_Transaction;
import version2.def.DB_Transaction_Callback;
import version2.def.DB_Transaction_Collector;
import version2.model.io.AbstractLocalController;
import vorlesung.version2.scheduler.DESScheduler;

public class DBSystem implements DB_Transaction_Collector {
	
	private DBBufferManagerComponent bufferManager;
	private DBRecoveryManagerComponent recoveryManager;
	private DBSchedulerComponent scheduler;
	private long operationCounter;

	public DBSystem(AbstractLocalController controller) {
		this.operationCounter = 0;
		this.bufferManager = new DBBufferManagerComponent(controller);
		this.recoveryManager = new DBRecoveryManagerComponent(this.bufferManager);
		this.scheduler = new DBSchedulerComponent(this.recoveryManager);
	}

	@Override
	public void transmitt(DB_Transaction transaction, DB_Transaction_Callback callback) {
		this.operationCounter++;
		DESScheduler.log("Operation ID: " + this.operationCounter + " - " + DESScheduler.getSimulationTime());
		this.scheduler.schedule(transaction, this.operationCounter);
	}

}
