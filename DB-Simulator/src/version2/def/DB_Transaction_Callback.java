package version2.def;

public interface DB_Transaction_Callback {

	/**
	 *  DB_Request transmitted to system 
	 */
	
	public void operationComplete(DB_Transaction transaction, DB_Result result);
	
}
