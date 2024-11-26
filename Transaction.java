import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

	//Implement Singleton Design Pattern 
	private static Transaction instance;
	
	//Private Constructor 
	private Transaction() {}
	
	//'Lazy Getter to initialize instance
	public static Transaction getTransaction() {
		if (instance == null) {
			instance = new Transaction();
		}
		return instance;
	}
	
    // Perform the borrowing of a book
    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public boolean returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            System.out.println(transactionDetails);
            saveTransaction(transactionDetails);
            return true;
        } else {
            System.out.println("This book was not borrowed by the member.");
            return false;
        }
    }

    // Get the current date and time in a readable format
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
    //Adding a saveTransaction class to store borrowed and returned books
    public void saveTransaction (String transactionDetails) {
    	try {
			BufferedWriter saving = new BufferedWriter(new FileWriter("transaction.txt", true));
			saving.write(transactionDetails+"\n");
			saving.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    //Adding a displayTransactionHistory class
    public void displayTransactionHistory() {
    	try {
			BufferedReader display = new BufferedReader(new FileReader("transaction.txt"));
			String line;
			while((line=display.readLine()) != null) {
				System.out.print(line+"\n");
			}
			display.close();
		}  
    	catch (IOException e) {
			System.out.println("No file has been created yet. No Transactions have taken place.");
			e.printStackTrace();
		}
    }
    
    
}