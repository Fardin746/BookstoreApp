package com.example.bookstoreapp.repository.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_audit")
public class AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id")
    private Integer id;
    @Column(name = "audit_type",nullable = false)
    private String auditType;
    @Column(name = "book_id",nullable = false)
    private Integer bookId;
    @Column(name = "create_date",nullable = false)
    private LocalDateTime auditDate;

    public AuditEntity() {
    }



    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }


    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setAuditDate(LocalDateTime auditDate) {
        this.auditDate = auditDate;
    }
}
