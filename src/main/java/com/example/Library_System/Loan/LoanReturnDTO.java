package com.example.Library_System.Loan;

public class LoanReturnDTO {
    private Long loanId;

    public LoanReturnDTO(Long loanId) {
        this.loanId = loanId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }
}
