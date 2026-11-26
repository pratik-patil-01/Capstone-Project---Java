public class LibraryException extends Exception {
    public LibraryException(String msg) { super(msg); }
}

class ItemNotFoundException extends LibraryException {
    public ItemNotFoundException(String ii) { super("item " + ii + " not found"); }
}

class MemberNotFoundException extends LibraryException {
    public MemberNotFoundException(String mi) { super("member " + mi + " not found"); }
}

class ItemNotAvailableException extends LibraryException {
    public ItemNotAvailableException(String msg) { super(msg); }
}

class BorrowLimitExceededException extends LibraryException {
    public BorrowLimitExceededException(String mi, int max) {
        super(mi + " has reached the limit of " + max);
    }
}

class ItemNotOnLoanException extends LibraryException {
    public ItemNotOnLoanException(String ii) { super(ii + " is not on loan"); }
}

class ReservationNotAllowedException extends LibraryException {
    public ReservationNotAllowedException(String msg) { super(msg); }
}