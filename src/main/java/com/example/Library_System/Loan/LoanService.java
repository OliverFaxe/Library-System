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
    private final LoanValidator loanValidator;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, UserRepository userRepository, LoanValidator loanValidator) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.loanValidator = loanValidator;
    }

    public List<Loan> getUserLoans(Long userId) {
        loanValidator.validateIfUserExists(userId);
        return loanRepository.findByUser_UserId(userId);
    }

    public Loan createLoan(Long userId, Long bookId){

        loanValidator.validateCreateLoan(userId, bookId);

        User user = userRepository.findById(userId).get();
        Book book = bookRepository.findById(bookId).get();

        // 1. Skapar lånet
        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);
        loan.setBorrowedDate(new Date());

        // 2. 14 dagar från nu skall boken returneras
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 14);
        loan.setDueDate(calendar.getTime());

        // 3. Spara lånet
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public Loan returnBook(Long loanId){

        // 1. hämtar lånet och ändrar return date till nu.
        Loan loan = loanValidator.doubleCheckLoanReturnOrExtend(loanId);

        loan.setReturnedDate(new Date());

        // 2. ändrar tillgänglighet till nuvarande copies + 1
        Book book = loan.getBook();

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return loanRepository.save(loan);
    }

    public Loan extendLoan(Long loanId){

        Loan loan = loanValidator.doubleCheckLoanReturnOrExtend(loanId);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loan.getDueDate());
        calendar.add(Calendar.DAY_OF_YEAR, 14);

        System.out.println("Before: " + loan.getDueDate());
        loan.setDueDate(calendar.getTime());
        System.out.println("After: " + calendar.getTime());

        return loanRepository.save(loan);
    }
}
