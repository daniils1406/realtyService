/*
 * This file is generated by jOOQ.
 */
package com.example.authenticationService.model.jooq.schema.tables;


import com.example.authenticationService.model.jooq.schema.Keys;
import com.example.authenticationService.model.jooq.schema.Public;
import com.example.authenticationService.model.jooq.schema.tables.records.VerificationTokenRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function4;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VerificationToken extends TableImpl<VerificationTokenRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.verification_token</code>
     */
    public static final VerificationToken VERIFICATION_TOKEN_ENTITY = new VerificationToken();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<VerificationTokenRecord> getRecordType() {
        return VerificationTokenRecord.class;
    }

    /**
     * The column <code>public.verification_token.token</code>.
     */
    public final TableField<VerificationTokenRecord, String> TOKEN = createField(DSL.name("token"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.verification_token.userId</code>.
     */
    public final TableField<VerificationTokenRecord, UUID> USERID = createField(DSL.name("userId"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.verification_token.insert_date</code>.
     */
    public final TableField<VerificationTokenRecord, LocalDateTime> INSERT_DATE = createField(DSL.name("insert_date"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>public.verification_token.id</code>.
     */
    public final TableField<VerificationTokenRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID, this, "");

    private VerificationToken(Name alias, Table<VerificationTokenRecord> aliased) {
        this(alias, aliased, null);
    }

    private VerificationToken(Name alias, Table<VerificationTokenRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.verification_token</code> table reference
     */
    public VerificationToken(String alias) {
        this(DSL.name(alias), VERIFICATION_TOKEN_ENTITY);
    }

    /**
     * Create an aliased <code>public.verification_token</code> table reference
     */
    public VerificationToken(Name alias) {
        this(alias, VERIFICATION_TOKEN_ENTITY);
    }

    /**
     * Create a <code>public.verification_token</code> table reference
     */
    public VerificationToken() {
        this(DSL.name("verification_token"), null);
    }

    public <O extends Record> VerificationToken(Table<O> child, ForeignKey<O, VerificationTokenRecord> key) {
        super(child, key, VERIFICATION_TOKEN_ENTITY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public List<ForeignKey<VerificationTokenRecord, ?>> getReferences() {
        return Arrays.asList(Keys.VERIFICATION_TOKEN__FR_TO_USER);
    }

    private transient CianUser _cianUser;

    /**
     * Get the implicit join path to the <code>public.cian_user</code> table.
     */
    public CianUser cianUser() {
        if (_cianUser == null)
            _cianUser = new CianUser(this, Keys.VERIFICATION_TOKEN__FR_TO_USER);

        return _cianUser;
    }

    @Override
    public VerificationToken as(String alias) {
        return new VerificationToken(DSL.name(alias), this);
    }

    @Override
    public VerificationToken as(Name alias) {
        return new VerificationToken(alias, this);
    }

    @Override
    public VerificationToken as(Table<?> alias) {
        return new VerificationToken(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public VerificationToken rename(String name) {
        return new VerificationToken(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VerificationToken rename(Name name) {
        return new VerificationToken(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public VerificationToken rename(Table<?> name) {
        return new VerificationToken(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<String, UUID, LocalDateTime, UUID> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super String, ? super UUID, ? super LocalDateTime, ? super UUID, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super String, ? super UUID, ? super LocalDateTime, ? super UUID, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
