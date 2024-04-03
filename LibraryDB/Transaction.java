package LibraryDB;

public class Transaction {
	private String operation;
	private String userId;
	private String book;
	private String status;
	private String borrowedFrom;
	
	
	public String toString()
	{
		if(operation == "borrow" && status == "success") {
			return "book: "+ this.book +" is borrowed by user: "+ this.userId +" from "+ this.borrowedFrom+ " is "+this.status +"\n";
		}else if(operation == "return" && status == "success") {
			return "book: "+ this.book +" returned by user: "+ this.userId +" is "+this.status+"\n";
		}else {
			return "Operation: "+this.operation+ " performed by user: "+this.userId + " on book: "+ this.book+" is "+this.status+"\n";
		}
	}
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBorrowedFrom() {
		return borrowedFrom;
	}
	public void setBorrowedFrom(String borrowedFrom) {
		this.borrowedFrom = borrowedFrom;
	}

}
