package version2.model.io;


import version2.def.IO_Device;

public abstract class AbstractIODevice implements IO_Device {
	
	private long size;

	public AbstractIODevice(long size) {
		this.size = size;
	}
	
	public abstract IO_Device clonePrototype();

	public long blockCount() {
		return size;
	}
	
	@Override
	public void changeBlockCount(int delta) {
		this.size += delta;
	}
	
	public abstract void checkQueued();

}
