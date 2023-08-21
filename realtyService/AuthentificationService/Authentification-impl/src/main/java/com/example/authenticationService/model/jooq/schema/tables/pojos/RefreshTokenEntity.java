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
public class RefreshTokenEntity extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID token;
    private UUID userId;
    private LocalDateTime insertDate;

    public RefreshTokenEntity() {}

    public RefreshTokenEntity(RefreshTokenEntity value) {
        this.token = value.token;
        this.userId = value.userId;
        this.insertDate = value.insertDate;
    }

    public RefreshTokenEntity(
        UUID token,
        UUID userId,
        LocalDateTime insertDate
    ) {
        this.token = token;
        this.userId = userId;
        this.insertDate = insertDate;
    }

    /**
     * Getter for <code>public.refresh_token.token</code>.
     */
    public UUID getToken() {
        return this.token;
    }

    /**
     * Setter for <code>public.refresh_token.token</code>.
     */
    public void setToken(UUID token) {
        this.token = token;
    }

    /**
     * Getter for <code>public.refresh_token.user_id</code>.
     */
    public UUID getUserId() {
        return this.userId;
    }

    /**
     * Setter for <code>public.refresh_token.user_id</code>.
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    /**
     * Getter for <code>public.refresh_token.insert_date</code>.
     */
    public LocalDateTime getInsertDate() {
        return this.insertDate;
    }

    /**
     * Setter for <code>public.refresh_token.insert_date</code>.
     */
    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final RefreshTokenEntity other = (RefreshTokenEntity) obj;
        if (this.token == null) {
            if (other.token != null)
                return false;
        }
        else if (!this.token.equals(other.token))
            return false;
        if (this.userId == null) {
            if (other.userId != null)
                return false;
        }
        else if (!this.userId.equals(other.userId))
            return false;
        if (this.insertDate == null) {
            if (other.insertDate != null)
                return false;
        }
        else if (!this.insertDate.equals(other.insertDate))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.token == null) ? 0 : this.token.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.insertDate == null) ? 0 : this.insertDate.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("RefreshTokenEntity (");

        sb.append(token);
        sb.append(", ").append(userId);
        sb.append(", ").append(insertDate);

        sb.append(")");
        return sb.toString();
    }
}
