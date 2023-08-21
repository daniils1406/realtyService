/*
 * This file is generated by jOOQ.
 */
package com.example.authenticationService.model.jooq.schema.tables.pojos;


import com.example.authenticationService.entity.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VerificationTokenEntity extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;
    private UUID userid;
    private LocalDateTime insertDate;
    private UUID id;

    public VerificationTokenEntity() {}

    public VerificationTokenEntity(VerificationTokenEntity value) {
        this.token = value.token;
        this.userid = value.userid;
        this.insertDate = value.insertDate;
        this.id = value.id;
    }

    public VerificationTokenEntity(
        String token,
        UUID userid,
        LocalDateTime insertDate,
        UUID id
    ) {
        this.token = token;
        this.userid = userid;
        this.insertDate = insertDate;
        this.id = id;
    }

    /**
     * Getter for <code>public.verification_token.token</code>.
     */
    public String getToken() {
        return this.token;
    }

    /**
     * Setter for <code>public.verification_token.token</code>.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Getter for <code>public.verification_token.userId</code>.
     */
    public UUID getUserid() {
        return this.userid;
    }

    /**
     * Setter for <code>public.verification_token.userId</code>.
     */
    public void setUserid(UUID userid) {
        this.userid = userid;
    }

    /**
     * Getter for <code>public.verification_token.insert_date</code>.
     */
    public LocalDateTime getInsertDate() {
        return this.insertDate;
    }

    /**
     * Setter for <code>public.verification_token.insert_date</code>.
     */
    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * Getter for <code>public.verification_token.id</code>.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.verification_token.id</code>.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final VerificationTokenEntity other = (VerificationTokenEntity) obj;
        if (this.token == null) {
            if (other.token != null)
                return false;
        }
        else if (!this.token.equals(other.token))
            return false;
        if (this.userid == null) {
            if (other.userid != null)
                return false;
        }
        else if (!this.userid.equals(other.userid))
            return false;
        if (this.insertDate == null) {
            if (other.insertDate != null)
                return false;
        }
        else if (!this.insertDate.equals(other.insertDate))
            return false;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.token == null) ? 0 : this.token.hashCode());
        result = prime * result + ((this.userid == null) ? 0 : this.userid.hashCode());
        result = prime * result + ((this.insertDate == null) ? 0 : this.insertDate.hashCode());
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VerificationTokenEntity (");

        sb.append(token);
        sb.append(", ").append(userid);
        sb.append(", ").append(insertDate);
        sb.append(", ").append(id);

        sb.append(")");
        return sb.toString();
    }
}