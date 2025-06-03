package com.example.Library_System.Loan;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("/{userId}")
    public List<Loan> getUserLoans(@PathVariable Long userId) {
        return loanService.getUserLoans(userId);
    }

    @PostMapping
    public Loan createLoan(@RequestBody LoanRequestDTO dto) {
        return loanService.createLoan(dto.getUserId(), dto.getBookId());
    }

    @PutMapping("/{id}/return")
    public Loan returnBook(@PathVariable Long id) {
        return loanService.returnBook(id);
    }

    @PutMapping("/{id}/extend")
    public Loan extendLoan(@PathVariable Long id) {
        return loanService.extendLoan(id);
    }

}
