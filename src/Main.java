import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import bn.base.Assignment;
import bn.base.BooleanDomain;
import bn.base.BooleanValue;
import bn.base.NamedVariable;
import bn.base.StringValue;
import bn.core.BayesianNetwork;
import bn.core.Distribution;
import bn.core.Inferencer;
import bn.core.RandomVariable;
import bn.parser.XMLBIFParser;
//import bn.inference.EnumerationInferencer;
import bn.inference.*;

public class Main {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException{
		// TODO Auto-generated method
		BooleanValue TRUE = BooleanValue.TRUE;
		BooleanValue FALSE = BooleanValue.FALSE;
		String prefix = "src/bn/examples/";
		String filename = prefix+args[0];
		XMLBIFParser parser = new XMLBIFParser();
		BayesianNetwork network = parser.readNetworkFromFile(filename);
		 //= new NamedVariable(args[1], new BooleanDomain());
		Assignment a=new bn.base.Assignment();
		for(int i =2; i<args.length; i+=2) {
			RandomVariable evidence = network.getVariableByName(args[i]);
			if(args[i+1].equals("true")) {
				a.put(evidence, new StringValue("true"));
			}else {
				a.put(evidence, new StringValue("false"));
			}
			//System.out.println(args[i+1].equals("true"));
		}

		RandomVariable QueryVariable=network.getVariableByName(args[1]);
		//new NamedVariable(args[1], new BooleanDomain())
		//network.getVariableByName(args[1])
//		System.out.println("file name is "+ filename);
//		System.out.println("QueryVariable is ");
//		System.out.println(QueryVariable);
//		System.out.println("Assignment: ");
//		System.out.println(a);
//		System.out.println(network);
//		
		Inferencer exact = new EnumerationInferencer();
		Distribution dist = exact.query(QueryVariable, a, network);
		System.out.println(dist);
		
		RejectionSamplingInferencer rej = new RejectionSamplingInferencer();
		System.out.println(rej.rejectionSampling(QueryVariable, a, network, 10000000));
	}

}
