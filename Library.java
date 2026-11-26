import java.util.*;

public class Library {
    private final Map<String, LibraryItem> items;
    private final Map<String, Member> members;
    private final Map<String, Loan> loans;
    private final Map<String, Queue<String>> reservations;
    private int today;

    public Library() {
        items = new HashMap<>();
        members = new HashMap<>();
        loans = new HashMap<>();
        reservations = new HashMap<>();
        today = 0;
    }

    public void addItem(LibraryItem i) { items.put(i.getId(), i); }
    public void addMember(Member m) { members.put(m.getId(), m); }

    public int advance(int d) {
        today += d;
        return today;
    }

    public List<LibraryItem> inventory() {
        List<LibraryItem> res = new ArrayList<>(items.values());
        res.sort(Comparator.comparing(LibraryItem::getId));
        return res;
    }

    public String getStatus(LibraryItem i) {
        if (!(i instanceof Borrowable)) return "NOT_BORROWABLE";
        Loan loan = loans.get(i.getId());
        if (loan != null) return "ON_LOAN|" + loan.getMember().getId();
        return "AVAILABLE";
    }

    public int borrow(String mi, String ii) {
        Member mem = findMember(mi);
        if (mem == null) {
            System.out.println("ERROR member " + mi + " not found");
            return -1;
        }
        LibraryItem i = findItem(ii);
        if (i == null) {
            System.out.println("ERROR item " + ii + " not found");
            return -1;
        }
        if (!(i instanceof Borrowable)) {
            System.out.println("ERROR " + ii + " cannot be borrowed");
            return -1;
        }
        if (loans.containsKey(ii)) {
            System.out.println("ERROR " + ii + " is on loan");
            return -1;
        }
        if (countLoansOf(mi) >= mem.getMaxItems()) {
            System.out.println("ERROR " + mi + " has reached the limit of " + mem.getMaxItems());
            return -1;
        }

        Loan loan = issueTo(mem, i);
        return loan.getDueDay();
    }

    public String returnItem(String ii) {
        LibraryItem item = findItem(ii);
        if (item == null) {
            return "ERROR item " + ii + " not found";
        }
        Loan loan = loans.remove(ii);
        if (loan == null) {
            return "ERROR " + ii + " is not on loan";
        }
        return "OK fine " + loan.fine(today);
    }

    private LibraryItem findItem(String ii) { return items.get(ii); }
    private Member findMember(String mi) { return members.get(mi); }

    private int countLoansOf(String mi) {
        int count = 0;
        for (Loan loan : loans.values()) {
            if (loan.getMember().getId().equals(mi)) count++;
        }
        return count;
    }

    private Loan issueTo(Member mem, LibraryItem item) {
        int dueDay = today + mem.getLoanPeriod();
        Loan loan = new Loan(mem, item, dueDay);
        loans.put(item.getId(), loan);
        return loan;
    }
}