package com.j23.server.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.awt.*;
import java.io.File;
import java.io.Serializable;
import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDate;

@Entity
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String firstName;
    private String lastName;
    private String gender;
    private String email;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private java.sql.Date dateJoined;

    private Long phoneNumber;
    private String rank;
    private String nationality;
    private String address;
    private String imageUrl;
    private String memberCode;
    private Long bankAccount;

    public Member(Long id, String firstName, String lastName, String email, String gender, Date dateJoined, Long phoneNumber, String rank, String nationality, String address, String imageUrl, String memberCode, Long bankAccount) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.dateJoined = dateJoined;
        this.phoneNumber = phoneNumber;
        this.rank = rank;
        this.nationality = nationality;
        this.address = address;
        this.imageUrl = imageUrl;
        this.memberCode = memberCode;
        this.bankAccount = bankAccount;
    }

    public Member() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public java.sql.Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(java.sql.Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public Long getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(Long bankAccount) {
        this.bankAccount = bankAccount;
    }


    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + firstName + '\'' +
                ", gender='" + gender + '\'' +
                ", dateJoined=" + dateJoined +
                ", phoneNumber=" + phoneNumber +
                ", rank='" + rank + '\'' +
                ", nationality='" + nationality + '\'' +
                ", address='" + address + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", memberCode='" + memberCode + '\'' +
                ", bankAccount=" + bankAccount +
                '}';
    }
}
