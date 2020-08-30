package version2.model.io.disks;


import version2.def.IO_Device;

public class NCQHDD extends HDD {
	
	private static final int MAXOPSParallel = 32;

	public NCQHDD(long size) {
		super(size, MAXOPSParallel);
	}

	@Override
	public IO_Device clonePrototype() {
		return new NCQHDD(this.blockCount());
	}
	
}
