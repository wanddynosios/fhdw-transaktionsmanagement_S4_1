package version2.model.io;


import version2.def.IO_Callback;
import version2.def.IO_Device;
import version2.def.IO_Request;

public class DirectController extends AbstractLocalController {

	public DirectController(int redundant) {
		super(redundant);
	}

	@Override
	public void attachDevice(IO_Device... devices) {
		super.setDisks(devices);
	}
	
	@Override
	public void read(IO_Request request, IO_Callback callback) {
		this.scheduleReadRequest(request, callback);
	}

	private void scheduleReadRequest(IO_Request request, IO_Callback callback) {
		IOCallbackConnector collector = new IOCallbackConnector(callback, request);
		long ressourceEnd = request.getRessourceStart() + request.getRessourceLength();
		int currentDisk = 0;
		IO_Device[] disks = this.getDisks();

		long diskMin = 0;
		long diskMax = disks[0].blockCount() - 1;
		while(diskMin < ressourceEnd && currentDisk < disks.length) {
			if(request.getRessourceStart() >= diskMin && ressourceEnd <= diskMax) {
				IO_Request innerRequest = new IO_Request(Long.max(diskMin, request.getRessourceStart()), Long.min(diskMax, ressourceEnd));
				disks[currentDisk].read(innerRequest, collector);
				collector.addCall();
				this.ressourceRequested(currentDisk, request);
			}
			
			currentDisk++;
			diskMin = diskMax;
			diskMax += disks[currentDisk].blockCount() - 1;
		}	
	}

	@Override
	public void write(IO_Request request, IO_Callback callback) {
		this.scheduleWriteRequest(request, callback);
	}

	private void scheduleWriteRequest(IO_Request request, IO_Callback callback) {
		IOCallbackConnector collector = new IOCallbackConnector(callback, request);
		long ressourceEnd = request.getRessourceStart() + request.getRessourceLength();
		int currentDisk = 0;
		IO_Device[] disks = this.getDisks();

		long diskMin = 0;
		long diskMax = disks[0].blockCount() - 1;
		while(diskMin < ressourceEnd && currentDisk < disks.length) {
			if(request.getRessourceStart() >= diskMin && ressourceEnd <= diskMax) {
				IO_Request innerRequest = new IO_Request(Long.max(diskMin, request.getRessourceStart()), Long.min(diskMax, ressourceEnd));
				disks[currentDisk].write(innerRequest, collector);
				collector.addCall();
				this.ressourceRequested(currentDisk, request);
			}
			
			currentDisk++;
			diskMin = diskMax;
			diskMax += disks[currentDisk].blockCount() - 1;
		}	
	}
	
	protected void ressourceRequested(int currentDisk, IO_Request request) {
		
	}

}
