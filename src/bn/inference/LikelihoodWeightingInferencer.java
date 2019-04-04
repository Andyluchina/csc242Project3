package bn.inference;

import bn.core.Assignment;
import bn.core.BayesianNetwork;
import bn.core.RandomVariable;

public class LikelihoodWeightingInferencer {








	public class WeightedAssignment{

		double weight;
		Assignment event;

		public WeightedAssignment(Assignment a, double w) {
			weight = w;
			event = a;
		}

	}




	public WeightedAssignment weightedSample(BayesianNetwork bn, Assignment e) {
		double weight = 1;
		Assignment x = e.copy();
		for(RandomVariable v : bn.getVariablesSortedTopologically()) {
			if(e.containsKey(v)) {
				
			}
		}
		
		
		
	}
}

