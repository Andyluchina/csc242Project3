package bn.inference;

import java.util.ArrayList;

import bn.core.Assignment;
import bn.core.BayesianNetwork;
import bn.base.Domain;
import bn.core.Distribution;
import bn.core.Inferencer;
import bn.core.RandomVariable;

public class RejectionSamplingInferencer implements Inferencer{
	
	public Assignment priorSample(BayesianNetwork bn) {
		Assignment result=new bn.base.Assignment();
		Assignment e = new bn.base.Assignment();
		ArrayList<RandomVariable> topSort = (ArrayList<RandomVariable>) bn.getVariablesSortedTopologically();
		for(int i = 0; i < topSort.size(); i++) {
			RandomVariable xi = topSort.get(i);
			ArrayList<Double> dist= new ArrayList();
			int count = 0;
			for(int j = 0; j < xi.getDomain().size(); j++) {
				//System.out.println(count++);
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
		System.out.println(e);
		
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

	@Override
	public Distribution query(RandomVariable X, bn.core.Assignment e, bn.core.BayesianNetwork network) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
