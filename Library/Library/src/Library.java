import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Member> members = new ArrayList<Member>();
    private List<Book> books = new ArrayList<Book>();

    // Add a new member to the library
    public boolean addMember(Member member) {
    	Member var = findMemberById(member.getId());
        if(var == null) {
        	members.add(member);
        	return true; //Member not found, so add successful
        }
        else {
        	return false;
        }
    }
    
    // Add a new book to the library
    public boolean addBook(Book book) {
    	Book var = findBookById(book.getId());
    	if (var == null ) {
    		books.add(book);
    		return true;  //Book not found, so add successful
    	}
    	else {
    		return false;
    	}
    }

    // Find a member by ID
    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    // Find a book by ID
    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    // Get the list of members
    public List<Member> getMembers() {
        return members;
    }
    
    // Get the list of books
    public List<Book> getBooks() {
        return books;
    }
}
