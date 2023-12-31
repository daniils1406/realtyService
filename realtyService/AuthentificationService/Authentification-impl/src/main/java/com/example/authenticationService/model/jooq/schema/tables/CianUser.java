/*
 * This file is generated by jOOQ.
 */
package com.example.authenticationService.model.jooq.schema.tables;


import com.example.authenticationService.model.jooq.schema.Keys;
import com.example.authenticationService.model.jooq.schema.Public;
import com.example.authenticationService.model.jooq.schema.enums.Role;
import com.example.authenticationService.model.jooq.schema.enums.Status;
import com.example.authenticationService.model.jooq.schema.enums.Usertype;
import com.example.authenticationService.model.jooq.schema.tables.records.CianUserRecord;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function14;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row14;
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
public class CianUser extends TableImpl<CianUserRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.cian_user</code>
     */
    public static final CianUser CIAN_USER_ENTITY = new CianUser();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CianUserRecord> getRecordType() {
        return CianUserRecord.class;
    }

    /**
     * The column <code>public.cian_user.id</code>.
     */
    public final TableField<CianUserRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("uuid_generate_v4()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.cian_user.create_date</code>.
     */
    public final TableField<CianUserRecord, LocalDate> CREATE_DATE = createField(DSL.name("create_date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.cian_user.update_date</code>.
     */
    public final TableField<CianUserRecord, LocalDate> UPDATE_DATE = createField(DSL.name("update_date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.cian_user.first_name</code>.
     */
    public final TableField<CianUserRecord, String> FIRST_NAME = createField(DSL.name("first_name"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.cian_user.Last_name</code>.
     */
    public final TableField<CianUserRecord, String> LAST_NAME = createField(DSL.name("Last_name"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.cian_user.patronymic</code>.
     */
    public final TableField<CianUserRecord, String> PATRONYMIC = createField(DSL.name("patronymic"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.cian_user.logo</code>.
     */
    public final TableField<CianUserRecord, String> LOGO = createField(DSL.name("logo"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.cian_user.phone</code>.
     */
    public final TableField<CianUserRecord, String> PHONE = createField(DSL.name("phone"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.cian_user.login</code>.
     */
    public final TableField<CianUserRecord, String> LOGIN = createField(DSL.name("login"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.cian_user.password</code>.
     */
    public final TableField<CianUserRecord, String> PASSWORD = createField(DSL.name("password"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.cian_user.role</code>.
     */
    public final TableField<CianUserRecord, Role> ROLE = createField(DSL.name("role"), SQLDataType.VARCHAR.asEnumDataType(com.example.authenticationService.model.jooq.schema.enums.Role.class), this, "");

    /**
     * The column <code>public.cian_user.status</code>.
     */
    public final TableField<CianUserRecord, Status> STATUS = createField(DSL.name("status"), SQLDataType.VARCHAR.asEnumDataType(com.example.authenticationService.model.jooq.schema.enums.Status.class), this, "");

    /**
     * The column <code>public.cian_user.user_type</code>.
     */
    public final TableField<CianUserRecord, Usertype> USER_TYPE = createField(DSL.name("user_type"), SQLDataType.VARCHAR.asEnumDataType(com.example.authenticationService.model.jooq.schema.enums.Usertype.class), this, "");

    /**
     * The column <code>public.cian_user.redis_id</code>.
     */
    public final TableField<CianUserRecord, String> REDIS_ID = createField(DSL.name("redis_id"), SQLDataType.VARCHAR, this, "");

    private CianUser(Name alias, Table<CianUserRecord> aliased) {
        this(alias, aliased, null);
    }

    private CianUser(Name alias, Table<CianUserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.cian_user</code> table reference
     */
    public CianUser(String alias) {
        this(DSL.name(alias), CIAN_USER_ENTITY);
    }

    /**
     * Create an aliased <code>public.cian_user</code> table reference
     */
    public CianUser(Name alias) {
        this(alias, CIAN_USER_ENTITY);
    }

    /**
     * Create a <code>public.cian_user</code> table reference
     */
    public CianUser() {
        this(DSL.name("cian_user"), null);
    }

    public <O extends Record> CianUser(Table<O> child, ForeignKey<O, CianUserRecord> key) {
        super(child, key, CIAN_USER_ENTITY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<CianUserRecord> getPrimaryKey() {
        return Keys.PK_USER;
    }

    @Override
    public CianUser as(String alias) {
        return new CianUser(DSL.name(alias), this);
    }

    @Override
    public CianUser as(Name alias) {
        return new CianUser(alias, this);
    }

    @Override
    public CianUser as(Table<?> alias) {
        return new CianUser(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public CianUser rename(String name) {
        return new CianUser(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CianUser rename(Name name) {
        return new CianUser(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public CianUser rename(Table<?> name) {
        return new CianUser(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row14 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row14<UUID, LocalDate, LocalDate, String, String, String, String, String, String, String, Role, Status, Usertype, String> fieldsRow() {
        return (Row14) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function14<? super UUID, ? super LocalDate, ? super LocalDate, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Role, ? super Status, ? super Usertype, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function14<? super UUID, ? super LocalDate, ? super LocalDate, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super Role, ? super Status, ? super Usertype, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
