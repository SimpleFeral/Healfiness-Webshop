package com.healfiness.backend.core.domain.entities;

import java.time.Instant;

/**
 * Projection class for {@link com.healfiness.backend.outbound.db.jpaentities.AbstractObjectMetaDataEntity}.
 * <br>This class is used to transfer data between layers and to the client.
 */
public abstract class AbstractObjectMetaData {

    private String createUser;

    private Instant createDate;

    private String lastModifiedUser;

    private Instant lastModifiedDate;

    private Long version;

    protected AbstractObjectMetaData() {
    }

    protected AbstractObjectMetaData(
            String createUser,
            Instant createDate,
            String lastModifiedUser,
            Instant lastModifiedDate,
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

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
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
        return "AbstractObjectMetaData{" +
                "createUser='" + createUser + '\'' +
                ", createDate=" + createDate +
                ", lastModifiedUser='" + lastModifiedUser + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", version=" + version +
                '}';
    }

}
