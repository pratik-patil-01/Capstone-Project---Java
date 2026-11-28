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

    public int getToday() { return today;} //as a helper func for main
    public void addItem(LibraryItem i) { items.put(i.getId(), i); }
    public void addMember(Member m) { members.put(m.getId(), m); }

    public int advance(int d) {
        today += d;
        return today;
    }

    public List<LibraryItem> search(String txt) {
        String t = txt.toLowerCase();
        List<LibraryItem> res = new ArrayList<>();
        for( LibraryItem i : items.values()) {
            if(i.getTitle().toLowerCase().contains(t)) res.add(i);
        }
        res.sort(Comparator.comparing(LibraryItem::getTitle));
        return res;
    }

    public List<Loan> loansOf(String mi) throws MemberNotFoundException {
        findMember(mi);
        List<Loan> res = new ArrayList<>();
        for(Loan l : loans.values()) {
            if(l.getMember().getId().equals(mi)) res.add(l);
        }
        res.sort(Comparator.comparingInt(Loan::getDueDay).thenComparing(x -> x.getItem().getId()));
        return res;
    }

    public List<Loan> overdue() {
        List<Loan> res = new ArrayList<>();
        for(Loan l : loans.values()) {
            if(l.isOverdue(today)) res.add(l);
        }
        res.sort(Comparator.comparingInt((Loan x) -> x.daysLate(today)).reversed().thenComparing(x -> x.getItem().getId()));
        return res;
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

    public int borrow(String mi, String ii) throws LibraryException {
        Member mem = findMember(mi);
        LibraryItem i = findItem(ii);

        if (!(i instanceof Borrowable)) {
            throw new ItemNotAvailableException(ii + " cannot be borrowed");
        }
        if (loans.containsKey(ii)) {
            throw new ItemNotAvailableException(ii + " is on loan");
        }
        if (countLoansOf(mi) >= mem.getMaxItems()) {
            throw new BorrowLimitExceededException(mi, mem.getMaxItems());
        }

        Loan loan = issueTo(mem, i);
        return loan.getDueDay();
    }

    public String returnItem(String ii) throws LibraryException {
        findItem(ii);
        Loan loan = loans.remove(ii);
        if (loan == null) {
            throw new ItemNotOnLoanException(ii);
        }
        return "OK fine " + loan.fine(today);
    }

    private LibraryItem findItem(String ii) throws ItemNotFoundException {
        LibraryItem i = items.get(ii);
        if (i == null) throw new ItemNotFoundException(ii);
        return i;
    }

    private Member findMember(String mi) throws MemberNotFoundException {
        Member m = members.get(mi);
        if (m == null) throw new MemberNotFoundException(mi);
        return m;
    }

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