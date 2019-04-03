package bn.inference;

import java.util.ArrayList;

import bn.base.Assignment;
import bn.base.BayesianNetwork;
import bn.base.Domain;
import bn.core.RandomVariable;

public class RejectionSamplingInferencer{
	
	public Assignment priorSample(BayesianNetwork bn) {
		Assignment result=new Assignment();
		Assignment e = new Assignment();
		ArrayList<RandomVariable> topSort = (ArrayList<RandomVariable>) bn.getVariablesSortedTopologically();
		for(int i = 0; i < topSort.size(); i++) {
			RandomVariable xi = topSort.get(i);
			
			for(int j = 0; j < xi.getDomain().size(); j++) {
				Domain domain = (Domain) xi.getDomain();
				e.put(xi, domain.getElements().get(j));
				bn.getProbability(xi, e);
			}
		}
		
		return null;
	}
	
}
