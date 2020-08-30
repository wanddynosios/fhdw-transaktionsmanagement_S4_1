package version2.def;

public interface DB_Operation {

	public long getDataID();
	public abstract void schedule(DB_Scheduler dbScheduler, long transactionID);
	
}
