package com.example.Library_System;

import com.example.Library_System.Book.Book;
import com.example.Library_System.Book.BookRepository;
import com.example.Library_System.Book.BookService;
import com.example.Library_System.Loan.Loan;
import com.example.Library_System.Loan.LoanRepository;
import com.example.Library_System.Loan.LoanService;
import com.example.Library_System.Loan.LoanValidator;
import com.example.Library_System.User.User;
import com.example.Library_System.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoanServiceTest {


    private LoanService loanService;

    @Mock
    private LoanRepository loanRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private BookRepository bookRepositoryMock;


    private LoanValidator loanValidatorMock;

    @BeforeEach
    void setUp() {
        loanValidatorMock = new LoanValidator(loanRepositoryMock, userRepositoryMock, bookRepositoryMock);
        loanService = new LoanService(loanRepositoryMock, bookRepositoryMock, userRepositoryMock, loanValidatorMock);
    }

    @Test
    void testDueDateOnLoan() {
        // Arrange
        Long userId = 1L;
        Long bookId = 1L;

        User user = new User();
        user.setUserId(userId);

        Book book = new Book();
        book.setBookId(bookId);
        book.setAvailableCopies(1);

        // Mocka att userRepository hittar user och bookRepository hittar bok
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(user));
        when(bookRepositoryMock.findById(bookId)).thenReturn(Optional.of(book));

        // Mocka sparandet av lån
        when(loanRepositoryMock.save(any(Loan.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Loan loan = loanService.createLoan(userId, bookId);

        // Assert
        assertNotNull(loan.getDueDate(), "Due date should be set");
    }

    @Test
    void testAvailableCopiesNotAvailable() {
        // Arrange
        Book book = new Book();
        book.setBookId(1L);
        book.setAvailableCopies(0);

        when(bookRepositoryMock.findById(1L)).thenReturn(Optional.of(book));


        User user = new User();
        user.setUserId(1L);
        when(userRepositoryMock.findById(1L)).thenReturn(Optional.of(user));


        Loan loan = new Loan();
        loan.setUser(user);
        loan.setBook(book);

        // Act

        Exception exception = assertThrows(RuntimeException.class, () -> {
            loanService.createLoan(1L, 1L);
        });

        // Assert
        assertEquals("No copies available", exception.getMessage());
    }


}
