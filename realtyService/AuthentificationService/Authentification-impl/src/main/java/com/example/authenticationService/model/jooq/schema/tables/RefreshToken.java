/*
 * This file is generated by jOOQ.
 */
package com.example.authenticationService.model.jooq.schema.tables;


import com.example.authenticationService.model.jooq.schema.Keys;
import com.example.authenticationService.model.jooq.schema.Public;
import com.example.authenticationService.model.jooq.schema.tables.records.RefreshTokenRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RefreshToken extends TableImpl<RefreshTokenRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.refresh_token</code>
     */
    public static final RefreshToken REFRESH_TOKEN_ENTITY = new RefreshToken();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RefreshTokenRecord> getRecordType() {
        return RefreshTokenRecord.class;
    }

    /**
     * The column <code>public.refresh_token.token</code>.
     */
    public final TableField<RefreshTokenRecord, UUID> TOKEN = createField(DSL.name("token"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.refresh_token.user_id</code>.
     */
    public final TableField<RefreshTokenRecord, UUID> USER_ID = createField(DSL.name("user_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.refresh_token.insert_date</code>.
     */
    public final TableField<RefreshTokenRecord, LocalDateTime> INSERT_DATE = createField(DSL.name("insert_date"), SQLDataType.LOCALDATETIME(6), this, "");

    private RefreshToken(Name alias, Table<RefreshTokenRecord> aliased) {
        this(alias, aliased, null);
    }

    private RefreshToken(Name alias, Table<RefreshTokenRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.refresh_token</code> table reference
     */
    public RefreshToken(String alias) {
        this(DSL.name(alias), REFRESH_TOKEN_ENTITY);
    }

    /**
     * Create an aliased <code>public.refresh_token</code> table reference
     */
    public RefreshToken(Name alias) {
        this(alias, REFRESH_TOKEN_ENTITY);
    }

    /**
     * Create a <code>public.refresh_token</code> table reference
     */
    public RefreshToken() {
        this(DSL.name("refresh_token"), null);
    }

    public <O extends Record> RefreshToken(Table<O> child, ForeignKey<O, RefreshTokenRecord> key) {
        super(child, key, REFRESH_TOKEN_ENTITY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public List<UniqueKey<RefreshTokenRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.REFRESH_TOKEN_TOKEN_KEY);
    }

    @Override
    public RefreshToken as(String alias) {
        return new RefreshToken(DSL.name(alias), this);
    }

    @Override
    public RefreshToken as(Name alias) {
        return new RefreshToken(alias, this);
    }

    @Override
    public RefreshToken as(Table<?> alias) {
        return new RefreshToken(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public RefreshToken rename(String name) {
        return new RefreshToken(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RefreshToken rename(Name name) {
        return new RefreshToken(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public RefreshToken rename(Table<?> name) {
        return new RefreshToken(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, UUID, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super UUID, ? super UUID, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super UUID, ? super UUID, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
