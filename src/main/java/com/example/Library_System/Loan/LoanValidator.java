package com.example.Library_System.Loan;

import com.example.Library_System.Book.Book;
import com.example.Library_System.Book.BookRepository;
import com.example.Library_System.Book.BookValidator;
import com.example.Library_System.User.User;
import com.example.Library_System.User.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;

@Component
public class LoanValidator {

    LoanRepository loanRepository;
    UserRepository userRepository;
    BookRepository bookRepository;

    public LoanValidator(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public void validateIfUserExists(Long userId){
        if (userId == null || userId < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public void validateCreateLoan(Long userId, Long bookId){
        if (userId == null || userId < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "userId must be valid");
        }

        if (bookId == null || bookId < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bookId must be valid");
        }

        // 1. Kollar user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 2. Kollar book
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // 3. Kollar tillgänglighet på boken
        if (book.getAvailableCopies() <= 0) {
            throw new RuntimeException("No copies available");
        }

    }

    public Loan doubleCheckLoanReturnOrExtend(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        // Inte nödvändig eftersom vi skapar detta i createLoan.
        // Behålls för "defensive programming"
        if (loan.getDueDate() == null) {
            loan.setDueDate(new Date());
        }

        return loan;
    }

}
