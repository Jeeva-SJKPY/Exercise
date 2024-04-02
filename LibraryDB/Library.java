package LibraryDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
	static HashMap<String, Integer> books = new HashMap<>();
	static HashMap<String, String> users = new HashMap<>();
	static HashMap<String, String> userToBookMap = new HashMap<>();
	static Map<String, ArrayList<String>> bookToUserListMap = new HashMap<String, ArrayList<String>>();
	static HashMap<String, Integer> bookTransaction = new HashMap<>();
	static HashMap<String, Integer> userTransaction = new HashMap<>();

	public static void main(String[] args) {
		Library lib = new Library();
		lib.booksValues();
		lib.usersValues();
		
		lib.bookBorrowTransactions("12001", "Horse ride");
		lib.bookBorrowTransactions("12003", "Peacock feather");
		lib.bookBorrowTransactions("12002", "Scool days");
		lib.bookBorrowTransactions("12006", "Sun shine");
		
		lib.bookReturnTransactions("12001", "Horse ride");
		lib.bookReturnTransactions("12002", "Scool days");
		lib.bookReturnTransactions("12007", "Horse ride");
		lib.bookReturnTransactions("12006", "Sun shine");
		
		lib.bookBorrowTransactions("12007", "Horse ride");
		lib.bookBorrowTransactions("12003", "Ugly duckling");
		lib.bookBorrowTransactions("12004", "Moon Light");
		lib.bookBorrowTransactions("12006", "Sun shine");
		lib.bookBorrowTransactions("12006", "Hollowin");
		
		System.out.println(Arrays.asList(userTransaction));
		System.out.println(Arrays.asList(bookTransaction));
		
		
		lib.transactionDetails();
		
		

	}

	public void booksValues() {
		books.put("Horse ride", 2);
		books.put("Moon Light", 2);
		books.put("Peacock feather", 2);
		books.put("Scool days", 2);
		books.put("Sun shine", 2);
		books.put("Honeybee worker", 2);
		books.put("Hollowin", 2);
		books.put("Ugly duckling", 2);
	}

	public void usersValues() {
		users.put("12001", "Premium");
		users.put("12002", "Normal");
		users.put("12003", "Premium");
		users.put("12004", "Normal");
		users.put("12005", "Premium");
		users.put("12006", "Premium");
		users.put("12007", "Normal");
	}

	public boolean isBookExist(String bookName) {
		return books.containsKey(bookName);
	}

	public boolean isAvailable(String bookName) {
		if (books.get(bookName) > 0) {
			return true;
		} else {
			return false;

		}
	}

	public boolean isPremium(String userID) {
		if (users.get(userID) == "Premium") {
			return true;
		} else {
			return false;

		}
	}

	public boolean isEligible(String userID) {
		if(userToBookMap.containsKey(userID))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public void reduceBooksQuantity(String bookName) {
		if (books.get(bookName) != 0) {
			Integer quantity = books.get(bookName);
			quantity = quantity - 1;
			books.put(bookName, quantity);
		}
	}
	public void updateBookBorrowedUserList(String bookName,String userId)
	{   
		
		if (bookToUserListMap.containsKey(bookName)) {
			List<String> userList = bookToUserListMap.get(bookName);
			userList.add(userId);
			bookToUserListMap.put(bookName, (ArrayList<String>) userList);
			}
		else
		{   List<String> userList = new ArrayList<>();
			userList.add(userId);
			bookToUserListMap.put(bookName, (ArrayList<String>) userList);

		} 
	}

	public void increaseBooksQuantity(String bookName) {
		Integer quantity = books.get(bookName);
		quantity = quantity + 1;
		books.put(bookName, quantity);
	}
	public void returnBookUpdation(String bookName,String userId)
	{
		increaseBooksQuantity(bookName);
		removeUserIdFromUserToBookMap(userId);
		removeUserIdFromBookToUserMap(userId,bookName);
		
	}

	private void removeUserIdFromUserToBookMap(String userId) {
		if(userToBookMap.containsKey(userId))
		{
			userToBookMap.remove(userId);
		}
		
	}

	private void removeUserIdFromBookToUserMap(String userId,String bookName) {
		
		List<String> ul= bookToUserListMap.get(bookName);
		ul.remove(String.valueOf(userId)); 
		bookToUserListMap.put(bookName, (ArrayList<String>) ul);
		
	}

	public void userTransactionUpdation(String userId) {
		if (userTransaction.containsKey(userId)) {
			Integer transactionCount = userTransaction.get(userId);
			transactionCount = transactionCount + 1;
			userTransaction.put(userId, transactionCount);
			}
		else
		{
			userTransaction.put(userId,1);
		}

	}
	public void booksTransactionUpdation(String bookName) {
		if (bookTransaction.containsKey(bookName)) {
			Integer transactionCount = bookTransaction.get(bookName);
			transactionCount = transactionCount + 1;
			bookTransaction.put(bookName, transactionCount);
			}
		else
		{
			bookTransaction.put(bookName,1);
		}

	}
	
	public String borrowBookFromUser(String bookName,String userID)
	{   String oldUserId = null;
		if(bookToUserListMap.containsKey(bookName))
		{
			List<String> userList = bookToUserListMap.get(bookName);
			oldUserId= userList.get(0);
			userList.set(0, userID);
			
		}
		return oldUserId;
	}
	public void addValuesToUserbookMap(String bookName,String userId)
	{ if(!userToBookMap.containsKey(userId)) 
	   {
		userToBookMap.put(userId,bookName);
	   }
	
	}
		
	
	public void updateUserBookMap(String oldUserId,String userId)
	{
		if(userToBookMap.containsKey(oldUserId)) 
		   {
			String book = userToBookMap.get(oldUserId);
			userToBookMap.remove(oldUserId);
			userToBookMap.put(userId,book);
		   }
	}	
	
	public void topN(HashMap<String, Integer> map,Integer n)
	{
		
	 // Convert HashMap values to a List
    List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

    // Sort the List in descending order based on values
    Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));

    // Get the top 3 values
    List<Map.Entry<String, Integer>> top3 = list.subList(0, Math.min(n, list.size()));

    // Print the top 3 values
    for (Map.Entry<String, Integer> entry : top3) {
        System.out.println(entry.getKey() + " : " + entry.getValue());
    }
	}
	
	public void bookBorrowTransactions(String userId,String bookName)
	{
		if(isBookExist(bookName)&& isEligible(userId))
		{
		 if(isAvailable(bookName))
		 {
			 reduceBooksQuantity(bookName);
			 userTransactionUpdation(userId);
			 booksTransactionUpdation(bookName);
			 updateBookBorrowedUserList(bookName, userId);
			 addValuesToUserbookMap(bookName, userId);
		 }
		 else
		 {
			 if(isPremium(userId))
			 {
				 String oldUserId= borrowBookFromUser(bookName, userId);
				 userTransactionUpdation(userId);
				 booksTransactionUpdation(bookName);
				 updateBookBorrowedUserList(bookName, userId);
				 updateUserBookMap(oldUserId, userId);
				
			 }
			 else
			 {
				 System.out.println("Book is not in LIBRARY");
			 }
			 
		 }
		}
		else
		{
			System.out.println("The book "+ bookName +" is not Exists or the user is not eligible to get another book");
		}
		
	}
	
	public void transactionDetails()
	{
		 	
		System.out.println("top 3 Users");
		topN(userTransaction,3);
		
		System.out.println("top 3 Books " );
		topN(bookTransaction,3);
	}
	
	public void bookReturnTransactions(String userId,String bookName)
	{
		if(userToBookMap.containsKey(userId))
		{
			returnBookUpdation(bookName, userId);
			userTransactionUpdation(userId);
			booksTransactionUpdation(bookName);
			
		}
		else
		{
			System.out.println("User does not hold a book to return");
		}
	}

}
