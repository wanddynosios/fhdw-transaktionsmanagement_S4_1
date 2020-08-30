package version2.model;


import version2.def.DB_Transaction_Collector;
import version2.def.IO_Device;
import version2.model.db.DBSystem;
import version2.model.io.AbstractIODevice;
import version2.model.io.AbstractLocalController;

public class DBModelFactory {

	private IO_Device[] diskArray;
	private AbstractLocalController controller;

	public void preconstructDiskArray(AbstractIODevice disk, int countDisks) {
		IO_Device[] array = new IO_Device[countDisks];
		for (int i = 0; i < array.length; i++) {
			array[i] = disk.clonePrototype();
		}
		this.diskArray = array;
	}
	
	public void attacheController(AbstractLocalController controller) {
		controller.attachDevice(this.diskArray);
		this.controller = controller;
	}
	
	public DB_Transaction_Collector construct() {
		return new DBSystem(this.controller);
	}

}
