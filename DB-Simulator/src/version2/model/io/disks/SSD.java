package version2.model.io.disks;

import org.apache.commons.math3.distribution.UniformRealDistribution;
import version2.def.IO_Callback;
import version2.def.IO_Device;
import version2.def.IO_Request;
import version2.model.io.AbstractIODevice;
import vorlesung.version2.scheduler.DESScheduler;

import java.util.LinkedList;
import java.util.Queue;



public class SSD extends AbstractIODevice {

	private static final long STATIC_READ_TIME = 150;
	private static final long DYNAMIC_READ_MAX_TIME = 150;
	private static final long STATIC_WRITE_TIME = 400;
	private static final long DYNAMIC_WRITE_MAX_TIME = 300;
	private static final int MAXOPSParallel = 1000;
	
	private UniformRealDistribution randomRead;
	private UniformRealDistribution randomWrite;
	private int runningOpsCounter;
	private Queue<IOOperation> opBuffer;
	
	public SSD(long size) {
		super(size);
		this.randomRead = new UniformRealDistribution(DESScheduler.getRandom(), 0, DYNAMIC_READ_MAX_TIME);
		this.randomWrite = new UniformRealDistribution(DESScheduler.getRandom(), 0, DYNAMIC_WRITE_MAX_TIME);
		this.runningOpsCounter = 0;
		this.opBuffer = new LinkedList<IOOperation>();
	}
	
	@Override
	public IO_Device clonePrototype() {
		return new SSD(this.blockCount());
	}

	@Override
	public void read(IO_Request request, IO_Callback callback) {
		this.scheduleOP(new IOOperation(STATIC_READ_TIME, this.randomRead, callback, request, this));
		DESScheduler.log("Access Read  :" + request.getRessourceStart());
	}

	@Override
	public void write(IO_Request request, IO_Callback callback) {
		this.scheduleOP(new IOOperation(STATIC_WRITE_TIME, this.randomWrite, callback, request, this));
		DESScheduler.log("Access Write :" + request.getRessourceStart());
	}

	private void scheduleOP(IOOperation op) {
		if(this.runningOpsCounter <= MAXOPSParallel) {
			this.runningOpsCounter++;
			op.exec();
		}else{
			this.opBuffer.offer(op);
		}
	}

	@Override
	public void checkQueued() {
		this.runningOpsCounter--;
		if( ! this.opBuffer.isEmpty()) {
			this.scheduleOP(this.opBuffer.poll());
		}
	}

}
