package version2.simulations.sut;


import version2.def.DB_Scheduler;
import version2.def.DB_Write;

public class TestDBWriteOperation extends TestDBOperation implements DB_Write {

	public TestDBWriteOperation(long dataID) {
		super(dataID);
	}

	@Override
	public void schedule(DB_Scheduler dbScheduler, long transactionID) {
		// TODO Do better ;)
		dbScheduler.operationWrite(transactionID, this);
	}
	
}
