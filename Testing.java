import static org.junit.Assert.*;

import java.lang.reflect.Constructor;

import org.junit.Test;

public class Testing {

	@Test
		public void testBorrowReturn() throws Exception{
			Book testbook1 = new Book(123, "Test1");
			Member testmember1 = new Member(1111, "George");
			//Test that borrowing a book causes it to be unavailable
			Transaction.getTransaction().borrowBook(testbook1, testmember1);
			assertFalse(testbook1.isAvailable());
			
			//Test that you can no longer borrow an unavailable book
			assertFalse(Transaction.getTransaction().borrowBook(testbook1, testmember1));
			
			//Test that you can return the book
			assertTrue(Transaction.getTransaction().returnBook(testbook1, testmember1));
			
			//Test returning fails when its not borrowed 
			assertFalse(Transaction.getTransaction().returnBook(testbook1, testmember1));
	}

	@Test 
	public void testSingletonTransaction() throws Exception{
		 Constructor<Transaction>  constructor  =  Transaction.class.getDeclaredConstructor();
		 int observed = constructor.getModifiers();
		 int known = 2;
		 assertEquals(known, observed);
	}

}
