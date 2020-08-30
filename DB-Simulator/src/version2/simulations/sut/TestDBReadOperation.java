package version2.simulations.sut;


import version2.def.DB_Read;
import version2.def.DB_Scheduler;

public class TestDBReadOperation extends TestDBOperation implements DB_Read {

	public TestDBReadOperation(long dataID) {
		super(dataID);
	}

	@Override
	public void schedule(DB_Scheduler dbScheduler, long transactionID) {
		// TODO Do better ;)
		dbScheduler.operationRead(transactionID, this);
		
	}

}
