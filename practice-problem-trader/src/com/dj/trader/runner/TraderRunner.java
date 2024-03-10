package com.dj.trader.runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.dj.trader.bean.Trader;
import com.dj.trader.bean.Transaction;

public class TraderRunner {

	public static void main(String[] args) {

		List<Transaction> transactions = generateTransactions(1000);

		// 1.Find all transactions in the year 2011 and sort them by value (small to
		// high).
		List<Transaction> transactionsIn2011SortedByValue = transactions.stream().filter(t -> t.getYear() == 2011)
				.sorted(Comparator.comparingInt(Transaction::getValue)).collect(Collectors.toList());
		System.out.println(transactionsIn2011SortedByValue);

		// 2. What are all the unique cities where the traders work?
		List<String> uniqueCities = transactions.stream().map(Transaction::getTrader).map(Trader::getCity).distinct()
				.collect(Collectors.toList());
		System.out.println(uniqueCities);

		// 3. Find all traders from Cambridge and sort them by name.
		List<Trader> tradersFromCambridgeSortedByName = transactions.stream().map(Transaction::getTrader)
				.filter(t -> t != null && "Cambridge".equals(t.getCity())).filter(t -> t.getName() != null)
				.sorted(Comparator.comparing(Trader::getName)).collect(Collectors.toList());
		System.out.println(tradersFromCambridgeSortedByName);

		// 4. Return a string of all traders’ names sorted alphabetically.
		String sortedTraders = transactions.stream().map(Transaction::getTrader)
				.filter(t -> t != null && t.getName() != null).map(Trader::getName).distinct().sorted()
				.collect(Collectors.joining(","));
		System.out.println(sortedTraders);

		// 5. Are any traders based in Milan?
		boolean tradersBasedInMilan = transactions.stream().map(Transaction::getTrader)
				.anyMatch(t -> "Milan".equals(t.getCity()));
		System.out.println(tradersBasedInMilan);

		// 6. Print all transactions’ values from the traders living in Cambridge.
		List<Integer> transactionVlauesFromTraderLivingInCambridge = transactions.stream()
				.filter(t -> t.getTrader() != null && "Cambridge".equals(t.getTrader().getCity()))
				.map(Transaction::getValue).collect(Collectors.toList());
		System.out.println(transactionVlauesFromTraderLivingInCambridge);

		// 7. What’s the highest value of all the transactions?
		Transaction highestValuedTransation = transactions.stream().max(Comparator.comparingInt(Transaction::getValue))
				.get();
		System.out.println(highestValuedTransation.getValue());

		// 8. Find the transaction with the smallest value.
		Transaction smallestValuedTransation = transactions.stream().min(Comparator.comparingInt(Transaction::getValue))
				.get();
		System.out.println(smallestValuedTransation.getValue());
	}

	public static List<Transaction> gunerateTransaction() {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

		return transactions;
	}

	public static List<Transaction> generateTransactions(int count) {
		List<Transaction> transactions = new ArrayList<>();

		String[] names = { "Raoul", "Mario", "Alan", "Brian", "Darshan", "Romit" };
		String[] cities = { "Cambridge", "Milan", "New York", "London", "Paris", "Tokyo", "Berlin" };

		Random random = new Random();

		for (int i = 0; i < count; i++) {
			String name = random.nextBoolean() ? names[random.nextInt(names.length)] : null;
			String city = random.nextBoolean() ? cities[random.nextInt(cities.length)] : null;
			Trader trader = new Trader(name, city);

			int year = 2000 + random.nextInt(25);
			Integer value = 100 + random.nextInt(5000);

			Transaction transaction = new Transaction(trader, year, value);
			transactions.add(transaction);
		}

		return transactions;
	}

}
