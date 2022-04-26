package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column(length = 32, nullable = false)
    private String userId;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(length = 64, nullable = false)
    private String passwordCheck;

    @Column(length = 64, nullable = false)
    private String email;

    private String auth;

    @CreationTimestamp
    private Date regDate;

    @UpdateTimestamp
    private Date updDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<MemberAuth> authList = new ArrayList();

    @JsonManagedReference
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Reservation> reservation = new ArrayList<>();

    public Member (String userId, String password, String passwordCheck, String email) {
        this.userId = userId;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.email = email;
    }

    public Member (String userId, String password, String passwordCheck, String email, MemberAuth auth) {
        this.userId = userId;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.email = email;

        if (auth != null) {
            changeAuth(auth);
        }
    }

    public void changeAuth (MemberAuth auth) {}

    public void addAuth (MemberAuth auth) {
        if (authList == null) {
            authList = new ArrayList();
        }

        authList.add(auth);
    }

    public void clearAuthList () {

        authList.clear();
    }
}



