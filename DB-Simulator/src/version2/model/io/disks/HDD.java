package version2.model.io.disks;

import org.apache.commons.math3.distribution.UniformRealDistribution;
import version2.def.IO_Callback;
import version2.def.IO_Device;
import version2.def.IO_Request;
import version2.model.io.AbstractIODevice;
import vorlesung.version2.scheduler.DESScheduler;

import java.util.LinkedList;
import java.util.Queue;

public class HDD extends AbstractIODevice {
	
	private static final long STATIC_READ_TIME = 5000;
	private static final long DYNAMIC_READ_MAX_TIME = 25;
	private static final long STATIC_WRITE_TIME = 5000;
	private static final long DYNAMIC_WRITE_MAX_TIME = 25;
	
	private UniformRealDistribution randomRead;
	private UniformRealDistribution randomWrite;
	private int runningOpsCounter;
	private Queue<IOOperation> opBuffer;
	private int maxOpsParallel;

	public HDD(long size, int maxOpsParallel) {
		super(size);
		this.randomRead = new UniformRealDistribution(DESScheduler.getRandom(), 0, DYNAMIC_READ_MAX_TIME);
		this.randomWrite = new UniformRealDistribution(DESScheduler.getRandom(), 0, DYNAMIC_WRITE_MAX_TIME);
		this.runningOpsCounter = 0;
		this.opBuffer = new LinkedList<IOOperation>();
		this.maxOpsParallel = maxOpsParallel;
	}
	
	public HDD(long size) {
		this(size, 1);
	}
	
	@Override
	public IO_Device clonePrototype() {
		return new HDD(this.blockCount());
	}
	
	@Override
	public void read(IO_Request request, IO_Callback callback) {
		this.scheduleOP(new IOOperation(STATIC_READ_TIME, this.randomRead, callback, request, this));
	}

	@Override
	public void write(IO_Request request, IO_Callback callback) {
		this.scheduleOP(new IOOperation(STATIC_WRITE_TIME, this.randomWrite, callback, request, this));
	}


	private void scheduleOP(IOOperation op) {
		if(this.runningOpsCounter <= this.maxOpsParallel) {
			this.runningOpsCounter++;
			op.exec();
		}else {
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