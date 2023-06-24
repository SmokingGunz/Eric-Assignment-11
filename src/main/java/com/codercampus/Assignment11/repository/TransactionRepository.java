package com.codercampus.Assignment11.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.codercampus.Assignment11.domain.Transaction;

@Repository
public class TransactionRepository {
	private List<Transaction> transactions = new ArrayList<>(100);

	public TransactionRepository() {
		super();
		populateData();
	}

	public List<Transaction> findAll() {

		// sort all transactions by date in ascending order using streams
		return transactions.stream().sorted(Comparator.comparing(Transaction::getDate)).collect(Collectors.toList());
	
	}

	@SuppressWarnings("unchecked")
	private void populateData() {
		try (FileInputStream fileInputStream = new FileInputStream("transactions.txt");
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);) {
			this.transactions = (List<Transaction>) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Transaction findById(Long id) {

		// find transaction by Long id using streams
		return transactions.stream().filter(transaction -> transaction.getId().equals(id)).findFirst().orElse(null);
	}
}
