package version2.model.io;


import org.apache.commons.math3.distribution.UniformRealDistribution;
import version2.def.IO_Callback;
import version2.def.IO_Request;
import vorlesung.version2.scheduler.DESScheduler;

public class CacheController extends DirectController {
	
	private static final double CACHE_READ_HIT_RATIO = 0.3;
	private static final double CACHE_WRITE_HIT_RATIO = 0.0;
	
	private UniformRealDistribution random;
	private double readRatioHit;
	private double writeRatioHit;
	
	public CacheController(int redundant, double readRatioHit, double writeRatioHit) {
		super(redundant);
		this.random = new UniformRealDistribution(DESScheduler.getRandom(), 0, 1);
		this.readRatioHit = readRatioHit;
		this.writeRatioHit = writeRatioHit;
	}
	
	public CacheController(int redundant) {
		this(redundant, CACHE_READ_HIT_RATIO, CACHE_WRITE_HIT_RATIO);
	}
	
	@Override
	public void read(IO_Request request, IO_Callback callback) {
		if( this.random.sample() > this.readRatioHit) {
			super.read(request, callback);			
		}else {
			callback.operationComplete(request);
		}
	}

	@Override
	public void write(IO_Request request, IO_Callback callback) {
		if( this.random.sample() > this.writeRatioHit) {
			super.write(request, callback);			
		}else {
			callback.operationComplete(request);
		}
	}
	
}
