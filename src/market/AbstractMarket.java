package market;

import constants.Constants;
import constants.Functions;

import java.util.ArrayList;
import java.util.List;

import goods.AbstractGood;
import world.State;

public class AbstractMarket {

	protected List<AbstractGood> stockPile;
	private double[] marketNeeds = new double[Constants.AMOUNT_OF_GOODS];
	
	public AbstractMarket() {
		stockPile = new ArrayList<>();
	}
	

	public String getStockpileString() {
		
		String marketNeedsString = "";
		
		for(double d : marketNeeds) {
			marketNeedsString +="\n"+d;
		}
		
		return "size is "+ Functions.formatNum(stockPile.size())+" and string is: "+stockPile.toString() + 
				"\nneeds are"+ marketNeedsString;
	}
	
	public void tick() {
		for(AbstractGood good : stockPile) {
			good.tick();
		}
	}
	
	
	

	public double add(AbstractGood newGood, double amount) {
		int i = 0;
		for(AbstractGood g : stockPile) {
			i++;
			//System.out.println("lol "+i);
			if (newGood.compare(g)) {
				//System.out.println("yes");
				g.addAmount(newGood.getAmount());
				//System.out.println(g.getAmount());
				double money = g.getValue(amount);
				//System.out.println(g.getName()+": "+money);
				return money;
			}
		}
		System.out.println("no");
		stockPile.add(newGood);
		double money = newGood.getValue(amount);
		//System.out.println(money);
		return money;
		
	}
	
	public void updateGoods(){
		
		
		for(AbstractGood g : stockPile) {
			
			g.calculateAviliability();
			
		}
		
		
	}

	/**
	 * minimum price for a good on a market
	 * @param goodConst
	 * @param amount
	 * @return
	 */
	public double getGoodMinPrice(int goodConst, double amount) {
		List<AbstractGood> goods = getAllOfGood(goodConst);
		double newValue;
		double minPrice = -1; //prices can't be negative
		//finds average price for good on market
		for (AbstractGood g : goods) {
			if ((newValue = g.getValue(amount)) < minPrice || minPrice == -1) {
				minPrice = newValue; 
			}
		}
		
		//TODO: what if item not in market yet?
		
		if(minPrice != -1) {
		return minPrice;
		}
		else {
			return 9999; //to make artesans make one of each to start the economic calc. 
		}

	}
	/**
	 * maximum price for a good on a market
	 * @param goodConst
	 * @param amount
	 * @return
	 */
	public double getGoodMaxPrice(int goodConst, double amount) {
		List<AbstractGood> goods = getAllOfGood(goodConst);
		double maxPrice = -1; //prices can't be negative
		//finds average price for good on market
		for (AbstractGood g : goods) {
			if ((g.getValue(amount)) > maxPrice) {
				maxPrice = g.getValue(amount); 
			}
		}

		if(maxPrice != -1) {
			return maxPrice;
		}
		else {
			return 9999; //to make artesans make one of each to start the economic calc.
		}
	}


	public List<AbstractGood> getGood(int goodConst, double d) {
		
		List<AbstractGood> goods = new ArrayList<>();
		
		double stillNeeded = d;
		
		for(AbstractGood good : stockPile) {
			if(good.isGoodToBuy(goodConst)) {
				
				stillNeeded -= good.getAmount();
				
				goods.add(good);
				
				if(stillNeeded <= 0) {
					return goods;
				}
			}
		}
		
		marketNeed(goodConst, stillNeeded);
		return goods;
	}
	
	public List<AbstractGood> getAllOfGood(int goodConst){

		List<AbstractGood> goods = new ArrayList<>();
		for(AbstractGood good : stockPile) {
			if(good.isGoodToBuy(goodConst)) {
				goods.add(good);
			}
		}
		
		
		return goods;
	}


	private void marketNeed(int goodConst, double stillNeeded) {
		marketNeeds[goodConst] += stillNeeded;
		//System.out.println(goodConst +" "+ marketNeeds[goodConst]);
	}
	
	public void resetMarketNeedForTheTurn() {
		int i = 0;
		for (double thisNeed : marketNeeds) {
			marketNeeds[i] = 0;
			i++;
		}
	}


	public double[] getNeeds() {
		return marketNeeds;
	}
	
}
