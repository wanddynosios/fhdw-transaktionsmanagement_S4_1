package version2.model.io;

import version2.def.IO_Device;
import version2.def.IO_Request;

import java.util.Arrays;


public class IntelligentController extends CachePersistantController {

	private static final int DO_BALANCE_COUNT = 1000;
	private static final int BALANCE_OP_LOAD = 250;
	private static final int BALANCE_LIMIT = BALANCE_OP_LOAD * 10;
	
	private int balanceCounter;
	private int[] accessCounter;
	
	public IntelligentController(int redundant) {
		super(redundant);
		this.balanceCounter = 0;
	}
	
	@Override
	public void attachDevice(IO_Device... devices) {
		super.attachDevice(devices);
		this.accessCounter = new int[devices.length];
		Arrays.fill(this.accessCounter, 0);
	}
	
	@Override
	protected void ressourceRequested(int currentDisk, IO_Request request) {
		if(balanceCounter >= DO_BALANCE_COUNT) {
			int minValue = Integer.MAX_VALUE;
			int maxValue = Integer.MIN_VALUE;
			int minPos = 0;
			int maxPos = 0;
			for (int i = 0; i < accessCounter.length; i++) {
				int current = accessCounter[i];
				if(current < minValue ) {
					minValue = current;
					minPos = i;
				}
				if(current > maxValue ) {
					minValue = current;
					minPos = i;
				}
			}
			this.balance(minPos, maxPos, currentDisk, request);
			
			this.balanceCounter = 0;	
			Arrays.fill(this.accessCounter, 0);
		}
		
		this.balanceCounter++;
		this.accessCounter[currentDisk]++;
	}

	private void balance(int minDiskID, int maxDiskID, int currentDisk, IO_Request request) {
		IO_Device[] disks = this.getDisks();
		if(disks[maxDiskID].blockCount() > BALANCE_LIMIT) {
			IOCallbackConnector collector = new IOCallbackConnector();
			for (int i = 0; i < BALANCE_OP_LOAD; i++) {
				IO_Request innerRequest = new IO_Request(i, 1);
				disks[maxDiskID].read(innerRequest, collector);	
				disks[minDiskID].write(innerRequest, collector);
			}
			disks[maxDiskID].changeBlockCount(-BALANCE_OP_LOAD);
			disks[minDiskID].changeBlockCount(BALANCE_OP_LOAD);
		}
		
	}
}