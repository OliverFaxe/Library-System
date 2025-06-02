package com.example.Library_System.Loan;

import java.util.Date;

public class LoanResponseDTO {
    private Long loanId;
    private Long userId;
    private Long bookId;
    private Date loanDate;
    private Date dueDate;
    private Date returnedDate;

    public LoanResponseDTO(Long loanId, Date returnedDate, Long userId, Long bookId, Date loanDate, Date dueDate) {
        this.loanId = loanId;
        this.returnedDate = returnedDate;
        this.userId = userId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
