package managmentSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.google.gson.Gson;

public class Service {

	private Map<Integer, Account> accounts = new HashMap<Integer, Account>();
	private Gson gson = new Gson();

	public int generateAccountNumber() {

		Random rand = new Random();
		int number;
		Account exists;

		do {
			number = rand.nextInt(10000) + 1;
			exists = accounts.get(number);
		} while (exists != null);

		return number;
	}

	public String convertToJson() {

		String json = gson.toJson(accounts);
		return json;
	}

	public Map<Integer, Account> convertToObject(String stringMap) {

		Map<Integer, Account> newMap = gson.fromJson(stringMap, HashMap.class);
		return newMap;

	}

	public Map<Integer, Account> getMap() {
		return accounts;
	}

	public boolean addAccount(Account account) {

		Account exists = accounts.get(account.getAccountNumber());
		if (exists != null) {
			return false;
		} else {
			accounts.put(account.getAccountNumber(), account);
			return true;
		}
	}

	public Account getAccount(int accountNumber) {
		Account exists = accounts.get(accountNumber);
		if (exists != null) {
			return exists;
		} else {
			return null;
		}
	}

	public int addPadding(int number) {
		String newNum = String.valueOf(number);
		for (int i = newNum.length(); i < 5; i++) {
			newNum = "0" + newNum;
		}
		return Integer.valueOf(newNum);
	}

	public int search(String firstname) {
		int count = 0;
		for(HashMap.Entry<Integer, Account> entry : accounts.entrySet()){
		   if(entry.getValue().getFirstName().equals(firstname)) {
			   count++;
		   }
		}
		return count;
	}
	
	//Your scientists were so preoccupied with whether or not they could that they didn't stop to think if they should
	public int search8(String firstname) {
		return (int) accounts.values().stream().filter(account -> account.getFirstName().equals(firstname)).count();		
	}
	
	//public 

}
