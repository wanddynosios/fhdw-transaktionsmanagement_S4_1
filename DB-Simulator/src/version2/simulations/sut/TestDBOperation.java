package version2.simulations.sut;


import version2.def.DB_Operation;

public abstract class TestDBOperation implements DB_Operation {

	private long dataID;

	public TestDBOperation(long dataID) {
		this.dataID = dataID;
	}

	@Override
	public long getDataID() {
		return this.dataID;
	}
	
}
