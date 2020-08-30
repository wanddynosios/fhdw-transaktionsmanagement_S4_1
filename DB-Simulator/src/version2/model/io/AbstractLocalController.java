package version2.model.io;


import version2.def.IO_Controller;
import version2.def.IO_Device;

public abstract class AbstractLocalController implements IO_Controller {

	private IO_Device[] disks;
	private int redundant;
	
	public AbstractLocalController(int redundant, IO_Device... disks) {
		this.disks = disks;
		this.redundant = redundant;
	}

	public long blockCount() {
		int result = 0;
		for (int i = redundant; i < disks.length; i++) {
			result += disks[i].blockCount();
		}
		return result;
	}
	
	protected void setDisks(IO_Device[] disks) {
		this.disks = disks;
	}
	
	public IO_Device[] getDisks() {
		return disks;
	}
	
	public int countDisks(boolean write) {
		if(write) {
			return disks.length - this.redundant;			
		}else {
			return disks.length;
		}
	}
	
	public int countDisks() {
		return disks.length;
	}
	
	
}
