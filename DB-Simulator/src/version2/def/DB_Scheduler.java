package version2.def;

public interface DB_Scheduler {

	public abstract void operationWrite(long transactionID, DB_Operation operation);
	public abstract void operationRead(long transactionID, DB_Operation operation);
	public abstract void operationAbort(long transactionID);
	public abstract void operationCommit(long transactionID);
}
