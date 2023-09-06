package com.loansystem.loansystem.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "status")
    private List<Loan> loans;

    public Status() {
    }

//    public List<Loan> getLoans() {
//        return loans;
//    }
//
//    public void setLoans(List<Loan> loans) {
//        this.loans = loans;
//    }

    public Status(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", loans=" + loans +
                '}';
    }
}
