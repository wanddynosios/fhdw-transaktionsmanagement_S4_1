package vorlesung.version2.spielwiese;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;

public class Random {

	public static void main(String[] args) {
		RandomGenerator r = new MersenneTwister(0);
		NormalDistribution nd = new NormalDistribution(r, 2, 1);
		
		for (int i = 0; i < 100; i++) {
			System.out.println(nd.sample());
		} 
		
	}

}
