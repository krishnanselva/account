package com.abc.account.domain;

public class Account extends DomainObject {

    private String firstName;
    private String secondName;
    private String accountNumber;

    public Account(Long id, String firstName, String secondName, String accountNumber) {
        this(firstName, secondName, accountNumber);
        setId(id);
    }

    public Account(String firstName, String secondName, String accountNumber) {

        this.firstName = firstName;
        this.secondName = secondName;
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
