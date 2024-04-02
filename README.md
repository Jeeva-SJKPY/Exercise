# Exercise
Exercise on library Data set.
# Design Document
Library Database:
Requirement:
1.	Normal transaction for all customers.
2.	If book doesn’t exist throw exception for normal users.
3.	premium user gets the books from other users if book does not exist in library.
4.	Each user can have hold only one book (either normal or premium).
5.	Transaction history based on each transaction (top 3 user as well as top 3 books).
Implementation Idea:
  Using different HashMap for different requirement.
1.	User HashMap (key: user id, value: premium or normal)
2.	Books HashMap (key: book id, value: quantity)
3.	Borrow HashMap (key: book id, value: list of user id)
4.	Transaction Tree Map with comparator:
   User transaction (key: user id, value: no of transaction)
   Book transaction (key: book id, value: no of transaction)
 Using different methods for different operation:
1.	Is_book_exist method will say whether the particular book is there in library or not.
2.	Is_book_available method will tell whether the book is there are not by checking the value in HashMap.
3.	Is_premium method will tell whether the user is premium or not by checking the value in HashMap.
4.	Transaction method holds all transaction.
5.	update_borrow_books will update the borrow tabl e (HashMap) in all transaction.
6.	update_user will update the user table (HashMap) in all transaction.
7.	update_books will update the books table (HashMap) in all transaction.
8.	Is_eligible will checks for the user is already holding the book or not.
By using the Tree map we can sort the transaction using comparator, that helps to get the top transaction details(books and users).

Use Cases:
1.	Borrowing Books:
      
If isBookExists(bookName)
 {
    If isBookAvailable(bookName)
    {
      updateBooks(bookName);
      updateBorrowBooks(bookName);
      updateTransaction(bookName,userID);
     }
   Else
    {  
      If isPremium(userID)
        {  borrowFromUser((bookName,userID)
            {      updateBooks(bookName);
                   updateBorrowBooks(bookName);
                    updateTransaction(bookName,userID);
             }
        Else
          { throw(“book not available”);
            }
}
Else
{
Throw(“book is not existing”);
}

2.	Transaction:
Transaction(userID,bookName)
{
  sortTheTransaction();
  getTopTransactionDetails()
}



3.	Return books:
Return(userId,BookName)
{
updateBooks();
update transaction();
updateBookToUserMap();
updateUserToBookMap();
}








          



     

    
