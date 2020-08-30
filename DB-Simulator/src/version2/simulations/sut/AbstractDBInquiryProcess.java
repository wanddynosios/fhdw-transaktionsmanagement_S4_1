package version2.simulations.sut;


import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.ParetoDistribution;
import version2.def.DB_Transaction;
import version2.def.DB_Transaction_Collector;
import vorlesung.version2.modelling.ModelProcess;
import vorlesung.version2.modelling.ProcessStep;
import vorlesung.version2.modelling.ProcessStepDelay;
import vorlesung.version2.scheduler.DESScheduler;

public class AbstractDBInquiryProcess {

	private static final double READ_RATIO = 0.8;

	private DB_Transaction_Collector sut;
	private int callCount;
	private AbstractRealDistribution waitDistribution;
	private AbstractRealDistribution complexityDistribution;
	private ParetoDistribution dataIDDistribtion;
	private long maxDataID;

	public AbstractDBInquiryProcess(DB_Transaction_Collector db, int calls, AbstractRealDistribution waitDistribution, AbstractRealDistribution complexityDistribution, double AccessScale, long maxDataID) {
		this.sut = db;
		this.callCount = calls;
		this.waitDistribution = waitDistribution;
		this.complexityDistribution = complexityDistribution;
		this.dataIDDistribtion= new ParetoDistribution(DESScheduler.getRandom(), 1, AccessScale);
		this.maxDataID = maxDataID;
		ModelProcess.scheduleProcess(this);
	}

	@ProcessStepDelay(0)
	public long startDelay() {
		return (long) waitDistribution.sample();
	}

	@ProcessStep(0)
	public void scheduleNext() {
		if(this.callCount-- > 0) {
			this.generateInquiry();
			DESScheduler.log("Request: " + DESScheduler.getSimulationTime());
			ModelProcess.scheduleProcess(this);		//TODO do with callback
		}
	}

	private void generateInquiry() {
		int c = this.getComplexity();
		DB_Transaction transaction = new DB_Transaction();
		
		double callSplitter = 0.0;
		
		for (int i = 0; i < c; i++) {
			callSplitter += READ_RATIO;
			if(callSplitter >= 1.0) {
				callSplitter -= 1;
				this.addReadInquiry(transaction);
			}else {
				this.addWriteInquiry(transaction);
			}
		}
		
	}

	private void addWriteInquiry(DB_Transaction transaction) {
		this.addReadInquiry(transaction);
		transaction.add(new TestDBWriteOperation(this.getDataID()));
	}

	private void addReadInquiry(DB_Transaction transaction) {
		transaction.add(new TestDBReadOperation(this.getDataID()));
	}

	private int getComplexity() {
		return (int) Math.max(1, complexityDistribution.sample());
	}

	private long getDataID() {
		long sample;
		do {
			sample = (long) dataIDDistribtion.sample();
		}while(sample > this.maxDataID);
		return sample;
	}

}
