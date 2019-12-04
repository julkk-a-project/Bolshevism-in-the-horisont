package world;

import constants.Constants;

public class PopNeeds {

	private double wheat = 0.001;
	private double iron = 0.001;
	private double steel = 0.0;
	
	
	public PopNeeds(int job) {
		// TODO: make diference on job
		int strata = Constants.jobToClass(job);
		
		//strata needs:
		
		
		switch(Constants.jobToClass(job)) {
		case Constants.UPPER_STRATA:
			//rich people needs
			wheat += 0.049;
			steel += 1;
			iron += 0.049;
			break;
		case Constants.MIDDLE_STRATA:
			//middle ppl needs
			wheat += 0.049;
			break;
		case Constants.LOWER_STRATA:
			//low ppl needs
			break;
		}
	}
	
	
	/**
	 * returns a double array, with the indexes representing the different goods
	 * 
	 * adjusts need with population number
	 * 
	 * @return
	 */
	public double[] getNeeds(int population, int job){
		
		double[] needs = new double[Constants.AMOUNT_OF_GOODS];

		
		needs[Constants.WHEAT] = wheat * population;
		needs[Constants.IRON] = iron * population;
		needs[Constants.STEEL] = steel * population;
		
		return needs;	
	}

}
