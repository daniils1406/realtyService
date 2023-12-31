/*
 * This file is generated by jOOQ.
 */
package com.example.advertismentService.model.jooq.schema.tables.records;


import com.example.advertismentService.model.jooq.schema.tables.Favourites;
import com.example.advertismentService.model.jooq.schema.tables.pojos.FavouritesEntity;

import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FavouritesRecord extends TableRecordImpl<FavouritesRecord> implements Record2<UUID, UUID> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.favourites.client_id</code>.
     */
    public void setClientId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.favourites.client_id</code>.
     */
    public UUID getClientId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.favourites.realty_id</code>.
     */
    public void setRealtyId(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.favourites.realty_id</code>.
     */
    public UUID getRealtyId() {
        return (UUID) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<UUID, UUID> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<UUID, UUID> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Favourites.FAVOURITES_ENTITY.CLIENT_ID;
    }

    @Override
    public Field<UUID> field2() {
        return Favourites.FAVOURITES_ENTITY.REALTY_ID;
    }

    @Override
    public UUID component1() {
        return getClientId();
    }

    @Override
    public UUID component2() {
        return getRealtyId();
    }

    @Override
    public UUID value1() {
        return getClientId();
    }

    @Override
    public UUID value2() {
        return getRealtyId();
    }

    @Override
    public FavouritesRecord value1(UUID value) {
        setClientId(value);
        return this;
    }

    @Override
    public FavouritesRecord value2(UUID value) {
        setRealtyId(value);
        return this;
    }

    @Override
    public FavouritesRecord values(UUID value1, UUID value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FavouritesRecord
     */
    public FavouritesRecord() {
        super(Favourites.FAVOURITES_ENTITY);
    }

    /**
     * Create a detached, initialised FavouritesRecord
     */
    public FavouritesRecord(UUID clientId, UUID realtyId) {
        super(Favourites.FAVOURITES_ENTITY);

        setClientId(clientId);
        setRealtyId(realtyId);
    }

    /**
     * Create a detached, initialised FavouritesRecord
     */
    public FavouritesRecord(FavouritesEntity value) {
        super(Favourites.FAVOURITES_ENTITY);

        if (value != null) {
            setClientId(value.getClientId());
            setRealtyId(value.getRealtyId());
        }
    }
}
