package com.healfiness.backend.outbound.db.jpaentities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractObjectMetaDataEntity {

    @CreatedBy
    @Column(length = 15, nullable = false, updatable = false)
    private String createUser;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createDate;

    @LastModifiedBy
    @Column(length = 15)
    private String lastModifiedUser;

    @LastModifiedDate
    private Instant lastModifiedDate;

    @Version
    @Column(nullable = false)
    @ColumnDefault("0")
    private Long version;

    protected AbstractObjectMetaDataEntity() {
    }

    protected AbstractObjectMetaDataEntity(
            String createUser,
            java.time.Instant createDate,
            String lastModifiedUser,
            java.time.Instant lastModifiedDate,
            Long version
    ) {
        this.createUser = createUser;
        this.createDate = createDate;
        this.lastModifiedUser = lastModifiedUser;
        this.lastModifiedDate = lastModifiedDate;
        this.version = version;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public java.time.Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.time.Instant createDate) {
        this.createDate = createDate;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    public java.time.Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(java.time.Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "AbstractObjectMetaDataEntity{" +
                "createUser='" + createUser + '\'' +
                ", createDate=" + createDate +
                ", lastModifiedUser='" + lastModifiedUser + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", version=" + version +
                '}';
    }

}
