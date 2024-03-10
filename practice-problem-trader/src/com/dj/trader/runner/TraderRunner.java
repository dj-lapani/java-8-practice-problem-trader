package com.dj.trader.runner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.dj.trader.bean.Trader;
import com.dj.trader.bean.Transaction;

public class TraderRunner {

	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

		// 1.Find all transactions in the year 2011 and sort them by value (small to
		// high).
		List<Transaction> transactionsIn2011SortedByValue = transactions.stream().filter(t -> t.getYear() == 2011)
				.sorted(Comparator.comparingInt(Transaction::getValue)).collect(Collectors.toList());
		System.out.println(transactionsIn2011SortedByValue);

		// 2. What are all the unique cities where the traders work?
		List<String> uniqueCities = transactions.stream().filter(distinctByKey(t -> t.getTrader().getCity()))
				.map(t -> t.getTrader().getCity()).collect(Collectors.toList());
		System.out.println(uniqueCities);

		// 3. Find all traders from Cambridge and sort them by name.
		List<Transaction> tradersFromCambridgeSortedByName = transactions.stream()
				.filter(t -> t.getTrader().getCity().equals("Cambridge"))
				.sorted((t1, t2) -> t1.getTrader().getName().compareTo(t2.getTrader().getName()))
				.collect(Collectors.toList());
		System.out.println(tradersFromCambridgeSortedByName);

		// 4. Return a string of all traders’ names sorted alphabetically.

		// 5. Are any traders based in Milan?

		// 6. Print all transactions’ values from the traders living in Cambridge.

		// 7. What’s the highest value of all the transactions?

		// 8. Find the transaction with the smallest value.
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}

}
