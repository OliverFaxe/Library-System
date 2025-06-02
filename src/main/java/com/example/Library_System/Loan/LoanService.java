package com.example.Library_System.Loan;

import com.example.Library_System.Book.Book;
import com.example.Library_System.Book.BookRepository;
import com.example.Library_System.User.User;
import com.example.Library_System.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<Loan> getUserLoans(Long userId) {
        return loanRepository.findByUser_UserId(userId);
    }

    public Loan createLoan(Long userId, Long bookId){

        // 1. Kollar user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Kollar book
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // 3. Kollar tillgänglighet på boken
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("Book is not available");
        }

        // 4. Skapar lånet
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setBorrowedDate(new Date());

        // 5. 14 dagar från nu skall boken returneras
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 14);
        loan.setDueDate(calendar.getTime());

        // 6. Spara lånet
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public Loan returnBook(Long loanId){
        // 1. hämtar lånet och ändrar return date till nu.
        Loan loan = loanRepository.getReferenceById(loanId);

        loan.setReturnedDate(new Date());

        // 2. ändrar tillgänglighet till nuvarande copies + 1
        Book book = loan.getBook();

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public Loan extendLoan(Long loanId){
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        if (loan.getDueDate() == null) {
            loan.setDueDate(new Date());
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loan.getDueDate());
        calendar.add(Calendar.DAY_OF_YEAR, 14);

        System.out.println("Before: " + loan.getDueDate());
        loan.setDueDate(calendar.getTime());
        System.out.println("After: " + calendar.getTime());

        return loanRepository.save(loan);
    }
}
