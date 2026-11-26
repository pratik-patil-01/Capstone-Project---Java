import java.util.*;

public class Library {
    private final Map<String, LibraryItem> items;
    private final Map<String, Member> members;
    private final Map<String, Loan> loans;
    private final Map<String, Queue<String>> reservations;
    private final int today;

    public Library() {
        items = new HashMap<>();
        members = new HashMap<>();
        loans = new HashMap<>();
        reservations = new HashMap<>();
        today = 0;
    }

    public void addItem(LibraryItem i) { items.put(i.getId(), i);}
    public void addMember(Member m) { members.put(m.getId(), m);}
    public List<LibraryItem> inventory() {
        List<LibraryItem> res = new ArrayList<>(items.values());
        res.sort(Comparator.comparing(LibraryItem::getId));
        return res; 
    }
    public String getStatus(LibraryItem i) {
        if(!(i instanceof Borrowable)) return "NOT_BORROWABLE";
        Loan loan = loans.get(i.getId());
        if(loan != null) return "ON_LOAN|" + loan.getMember().getId();
        return "AVAILABLE";
    }

}
