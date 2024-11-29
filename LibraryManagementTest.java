import static org.junit.Assert.*;

import java.lang.reflect.Constructor;

import org.junit.Test;

public class LibraryManagementTest {

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
	
	@Test
	public void testBookId() throws Exception{
		
		// First two cases will result in book creation, much sure correct values being passed
		Book testbook1 = new Book(100, "Test100");
		assertEquals(100, testbook1.getId());
		
		Book testbook2 = new Book(999, "Test999");
		assertEquals(999, testbook2.getId());
		
		// Next cases will all throw an error, need to make sure its the correct error. 
		Exception exception = assertThrows(Exception.class, () -> {
			Book testcase3 = new Book (1000, "Test1000");
        });
		
		assertEquals("Invalid Book Id", exception.getMessage());

		Exception exception2 = assertThrows(Exception.class, () -> {
			Book testcase4 = new Book (-54, "Test-54");
        });
		
		assertEquals("Invalid Book Id", exception2.getMessage());
		
		Exception exception3 = assertThrows(Exception.class, () -> {
			Book testcase5 = new Book (987654321, "Test987654321");
        });
		
		assertEquals("Invalid Book Id", exception3.getMessage());
		
		
	}

}
