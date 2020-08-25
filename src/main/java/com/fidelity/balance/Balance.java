/**
 * The requirement is to build a feature to take in a balance and convert it into denominations (bills/coins). 
 * The intent is to convert the balance into the least number of bills/coins. 
 * The input balance is expressed as the lowest unit of currency (e.g. pennies for USD).
 * Examples (USD):
 * Given the balance 87, the function will return 3 Quarter, 1 Dime, 2 Penny coins.
 * Given the balance 287, the function will return 2 Dollar, 3 Quarter, 1 Dime, 2 Penny coins.
 *
 * Created by Suresh Bhaskaran on 8/24/2020.
 */
package com.fidelity.balance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Balance {

	public static void main(String[] args) {

		if(args.length < 2) {
			System.out.println("Usage Balance balance currencyName");
			System.exit(-1);
		}
		
		final List<Denomination> us   = Arrays.asList(new Denomination("Dollar", 100), new Denomination("Quarter", 25), new Denomination("Dime", 10), new Denomination("Nickel", 5), new Denomination("Penny", 1));
		final List<Denomination> euro = Arrays.asList(new Denomination("Euro", 100), new Denomination("50c", 50), new Denomination("20c", 20), new Denomination("10c", 10), new Denomination("5c", 5), new Denomination("1c", 1));
		
		final Map<String, List<Denomination>> currencyToDenominationMap = new HashMap<>(2);
		currencyToDenominationMap.put("US", us); 
		currencyToDenominationMap.put("EURO", euro); 
		int balance = Integer.parseInt(args[0]);
		String currencyName = args[1];
		
		System.out.println(getDenominations(new Currency(currencyName, balance), currencyToDenominationMap)); 
	}
	
	/**
	 * @param balanceCurrency
	 * @param currencyToDenominationMap
	 * @return
	 */
	public static String getDenominations(final Currency balanceCurrency, final Map<String, List<Denomination>> currencyToDenominationMap) {
		
		final List<Denomination> denominationList = currencyToDenominationMap.get(balanceCurrency.getName());
		
		final StringBuilder sb = new StringBuilder();
		int balance = balanceCurrency.getBalance();
		int i=0;
		while (balance > 0 && i < denominationList.size()) {
			final String name = denominationList.get(i).getName();
			final int denomVal = denominationList.get(i).getValue();
			final int result = balance / denomVal;
			if(result != 0) {
				final String comma = (i==0) ? "" : ", ";
				sb.append(comma).append(result).append(" ").append(name);
			}
			balance = balance % denomVal;
			i++;
		}
			
		if(balance != 0) {
			sb.append(", ").append(balance).append(" ").append(denominationList.get(i).getName());
		}
		
		return sb.toString();
	}

}
