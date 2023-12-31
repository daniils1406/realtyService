/*
 * This file is generated by jOOQ.
 */
package com.example.advertismentService.model.jooq.schema.tables;


import com.example.advertismentService.model.jooq.schema.Keys;
import com.example.advertismentService.model.jooq.schema.Public;
import com.example.advertismentService.model.jooq.schema.enums.Dealstatus;
import com.example.advertismentService.model.jooq.schema.tables.records.DealRecord;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function9;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row9;
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
public class Deal extends TableImpl<DealRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.deal</code>
     */
    public static final Deal DEAL_ENTITY = new Deal();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DealRecord> getRecordType() {
        return DealRecord.class;
    }

    /**
     * The column <code>public.deal.id</code>.
     */
    public final TableField<DealRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("uuid_generate_v4()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.deal.client_id</code>.
     */
    public final TableField<DealRecord, UUID> CLIENT_ID = createField(DSL.name("client_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.deal.broker_id</code>.
     */
    public final TableField<DealRecord, UUID> BROKER_ID = createField(DSL.name("broker_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.deal.period_of_deal</code>.
     */
    public final TableField<DealRecord, LocalDate> PERIOD_OF_DEAL = createField(DSL.name("period_of_deal"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.deal.transaction_amount</code>.
     */
    public final TableField<DealRecord, Integer> TRANSACTION_AMOUNT = createField(DSL.name("transaction_amount"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.deal.status</code>.
     */
    public final TableField<DealRecord, Dealstatus> STATUS = createField(DSL.name("status"), SQLDataType.VARCHAR.asEnumDataType(com.example.advertismentService.model.jooq.schema.enums.Dealstatus.class), this, "");

    /**
     * The column <code>public.deal.insert_date</code>.
     */
    public final TableField<DealRecord, LocalDate> INSERT_DATE = createField(DSL.name("insert_date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.deal.transaction_date</code>.
     */
    public final TableField<DealRecord, LocalDate> TRANSACTION_DATE = createField(DSL.name("transaction_date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.deal.realty_id</code>.
     */
    public final TableField<DealRecord, UUID> REALTY_ID = createField(DSL.name("realty_id"), SQLDataType.UUID, this, "");

    private Deal(Name alias, Table<DealRecord> aliased) {
        this(alias, aliased, null);
    }

    private Deal(Name alias, Table<DealRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.deal</code> table reference
     */
    public Deal(String alias) {
        this(DSL.name(alias), DEAL_ENTITY);
    }

    /**
     * Create an aliased <code>public.deal</code> table reference
     */
    public Deal(Name alias) {
        this(alias, DEAL_ENTITY);
    }

    /**
     * Create a <code>public.deal</code> table reference
     */
    public Deal() {
        this(DSL.name("deal"), null);
    }

    public <O extends Record> Deal(Table<O> child, ForeignKey<O, DealRecord> key) {
        super(child, key, DEAL_ENTITY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<DealRecord> getPrimaryKey() {
        return Keys.PK_DEAL;
    }

    @Override
    public List<ForeignKey<DealRecord, ?>> getReferences() {
        return Arrays.asList(Keys.DEAL__FK_TOREALTY);
    }

    private transient Realty _realty;

    /**
     * Get the implicit join path to the <code>public.realty</code> table.
     */
    public Realty realty() {
        if (_realty == null)
            _realty = new Realty(this, Keys.DEAL__FK_TOREALTY);

        return _realty;
    }

    @Override
    public Deal as(String alias) {
        return new Deal(DSL.name(alias), this);
    }

    @Override
    public Deal as(Name alias) {
        return new Deal(alias, this);
    }

    @Override
    public Deal as(Table<?> alias) {
        return new Deal(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Deal rename(String name) {
        return new Deal(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Deal rename(Name name) {
        return new Deal(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Deal rename(Table<?> name) {
        return new Deal(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<UUID, UUID, UUID, LocalDate, Integer, Dealstatus, LocalDate, LocalDate, UUID> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function9<? super UUID, ? super UUID, ? super UUID, ? super LocalDate, ? super Integer, ? super Dealstatus, ? super LocalDate, ? super LocalDate, ? super UUID, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function9<? super UUID, ? super UUID, ? super UUID, ? super LocalDate, ? super Integer, ? super Dealstatus, ? super LocalDate, ? super LocalDate, ? super UUID, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
