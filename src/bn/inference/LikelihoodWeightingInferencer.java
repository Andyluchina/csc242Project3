package bn.inference;

import java.util.ArrayList;

import bn.base.Domain;
import bn.core.Assignment;
import bn.core.BayesianNetwork;
import bn.core.RandomVariable;
import bn.core.Value;

public class LikelihoodWeightingInferencer {








	public class WeightedAssignment{

		double weight;
		Assignment event;

		public WeightedAssignment(Assignment a, double w) {
			weight = w;
			event = a;
		}
		
		@Override
		public String toString() {
			return "" + weight + " " + event;
		}

	}

	public Assignment priorSample(BayesianNetwork bn) {

		Assignment e = new bn.base.Assignment();
		ArrayList<RandomVariable> topSort = (ArrayList<RandomVariable>) bn.getVariablesSortedTopologically();
		for(int i = 0; i < topSort.size(); i++) {
			RandomVariable xi = topSort.get(i);
			ArrayList<Double> dist= new ArrayList();
			for(int j = 0; j < xi.getDomain().size(); j++) {
				Domain domain = (Domain) xi.getDomain();
				e.put(xi, domain.getElements().get(j));
				//System.out.println(e);
				dist.add(bn.getProbability(xi, e));
				e.remove(xi);
			}
			double chance = Math.random();
			int index = this.getIndexWithChance(chance, dist);
			Domain domain = (Domain) xi.getDomain();
			e.put(xi, domain.getElements().get(index));
			//System.out.println(dist);
		}
//		System.out.println(e);
		
		return e;
	}
	
	public int getIndexWithChance(double chance, ArrayList<Double> dist) {
		for(int i=0; i<dist.size(); i++) {
			chance-=dist.get(i);
			if(chance<=0) {
				return i;
			}
		}
		return 0;
	}


	public WeightedAssignment weightedSample(BayesianNetwork bn, Assignment e) {
		double weight = 1;
		Assignment x = e.copy();
		for(RandomVariable v : bn.getVariablesSortedTopologically()) {
			if(e.containsKey(v)) {
				x.put(v, e.get(v));
				weight *= bn.getProbability(v, x);
			}else {
				x.put(v,  priorSample(bn));
			}
		}
		WeightedAssignment ans = new WeightedAssignment(x, weight);
		
		System.out.println("ans");
		
		return ans;
		
		
		
	}
}

