/*
 * This file is generated by jOOQ.
 */
package com.example.advertismentService.model.jooq.schema.tables;


import com.example.advertismentService.model.jooq.schema.Keys;
import com.example.advertismentService.model.jooq.schema.Public;
import com.example.advertismentService.model.jooq.schema.enums.Adverttype;
import com.example.advertismentService.model.jooq.schema.enums.FlatOrHouse;
import com.example.advertismentService.model.jooq.schema.enums.Ownertype;
import com.example.advertismentService.model.jooq.schema.enums.Realtystatus;
import com.example.advertismentService.model.jooq.schema.enums.Tariff;
import com.example.advertismentService.model.jooq.schema.tables.records.RealtyRecord;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function18;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row18;
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
public class Realty extends TableImpl<RealtyRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.realty</code>
     */
    public static final Realty REALTY_ENTITY = new Realty();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RealtyRecord> getRecordType() {
        return RealtyRecord.class;
    }

    /**
     * The column <code>public.realty.id</code>.
     */
    public final TableField<RealtyRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("uuid_generate_v4()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.realty.owner_id</code>.
     */
    public final TableField<RealtyRecord, UUID> OWNER_ID = createField(DSL.name("owner_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.realty.square</code>.
     */
    public final TableField<RealtyRecord, Double> SQUARE = createField(DSL.name("square"), SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>public.realty.region</code>.
     */
    public final TableField<RealtyRecord, String> REGION = createField(DSL.name("region"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.realty.district</code>.
     */
    public final TableField<RealtyRecord, String> DISTRICT = createField(DSL.name("district"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.realty.address</code>.
     */
    public final TableField<RealtyRecord, String> ADDRESS = createField(DSL.name("address"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.realty.description</code>.
     */
    public final TableField<RealtyRecord, String> DESCRIPTION = createField(DSL.name("description"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.realty.advert_type</code>.
     */
    public final TableField<RealtyRecord, Adverttype> ADVERT_TYPE = createField(DSL.name("advert_type"), SQLDataType.VARCHAR.nullable(false).asEnumDataType(com.example.advertismentService.model.jooq.schema.enums.Adverttype.class), this, "");

    /**
     * The column <code>public.realty.cost</code>.
     */
    public final TableField<RealtyRecord, Integer> COST = createField(DSL.name("cost"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.realty.tariff_type</code>.
     */
    public final TableField<RealtyRecord, Tariff> TARIFF_TYPE = createField(DSL.name("tariff_type"), SQLDataType.VARCHAR.asEnumDataType(com.example.advertismentService.model.jooq.schema.enums.Tariff.class), this, "");

    /**
     * The column <code>public.realty.flat_or_house</code>.
     */
    public final TableField<RealtyRecord, FlatOrHouse> FLAT_OR_HOUSE = createField(DSL.name("flat_or_house"), SQLDataType.VARCHAR.asEnumDataType(com.example.advertismentService.model.jooq.schema.enums.FlatOrHouse.class), this, "");

    /**
     * The column <code>public.realty.insert_date</code>.
     */
    public final TableField<RealtyRecord, LocalDate> INSERT_DATE = createField(DSL.name("insert_date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.realty.update_date</code>.
     */
    public final TableField<RealtyRecord, LocalDate> UPDATE_DATE = createField(DSL.name("update_date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.realty.release_date</code>.
     */
    public final TableField<RealtyRecord, LocalDate> RELEASE_DATE = createField(DSL.name("release_date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.realty.status</code>.
     */
    public final TableField<RealtyRecord, Realtystatus> STATUS = createField(DSL.name("status"), SQLDataType.VARCHAR.asEnumDataType(com.example.advertismentService.model.jooq.schema.enums.Realtystatus.class), this, "");

    /**
     * The column <code>public.realty.complex_id</code>.
     */
    public final TableField<RealtyRecord, UUID> COMPLEX_ID = createField(DSL.name("complex_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.realty.owner_type</code>.
     */
    public final TableField<RealtyRecord, Ownertype> OWNER_TYPE = createField(DSL.name("owner_type"), SQLDataType.VARCHAR.asEnumDataType(com.example.advertismentService.model.jooq.schema.enums.Ownertype.class), this, "");

    /**
     * The column <code>public.realty.verify</code>.
     */
    public final TableField<RealtyRecord, Boolean> VERIFY = createField(DSL.name("verify"), SQLDataType.BOOLEAN.defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");

    private Realty(Name alias, Table<RealtyRecord> aliased) {
        this(alias, aliased, null);
    }

    private Realty(Name alias, Table<RealtyRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.realty</code> table reference
     */
    public Realty(String alias) {
        this(DSL.name(alias), REALTY_ENTITY);
    }

    /**
     * Create an aliased <code>public.realty</code> table reference
     */
    public Realty(Name alias) {
        this(alias, REALTY_ENTITY);
    }

    /**
     * Create a <code>public.realty</code> table reference
     */
    public Realty() {
        this(DSL.name("realty"), null);
    }

    public <O extends Record> Realty(Table<O> child, ForeignKey<O, RealtyRecord> key) {
        super(child, key, REALTY_ENTITY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<RealtyRecord> getPrimaryKey() {
        return Keys.PK_REALTY;
    }

    @Override
    public List<ForeignKey<RealtyRecord, ?>> getReferences() {
        return Arrays.asList(Keys.REALTY__FK_TO_RESIDENTIAL_COMPLEX);
    }

    private transient ResidentialComplex _residentialComplex;

    /**
     * Get the implicit join path to the <code>public.residential_complex</code>
     * table.
     */
    public ResidentialComplex residentialComplex() {
        if (_residentialComplex == null)
            _residentialComplex = new ResidentialComplex(this, Keys.REALTY__FK_TO_RESIDENTIAL_COMPLEX);

        return _residentialComplex;
    }

    @Override
    public Realty as(String alias) {
        return new Realty(DSL.name(alias), this);
    }

    @Override
    public Realty as(Name alias) {
        return new Realty(alias, this);
    }

    @Override
    public Realty as(Table<?> alias) {
        return new Realty(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Realty rename(String name) {
        return new Realty(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Realty rename(Name name) {
        return new Realty(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Realty rename(Table<?> name) {
        return new Realty(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row18 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row18<UUID, UUID, Double, String, String, String, String, Adverttype, Integer, Tariff, FlatOrHouse, LocalDate, LocalDate, LocalDate, Realtystatus, UUID, Ownertype, Boolean> fieldsRow() {
        return (Row18) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function18<? super UUID, ? super UUID, ? super Double, ? super String, ? super String, ? super String, ? super String, ? super Adverttype, ? super Integer, ? super Tariff, ? super FlatOrHouse, ? super LocalDate, ? super LocalDate, ? super LocalDate, ? super Realtystatus, ? super UUID, ? super Ownertype, ? super Boolean, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function18<? super UUID, ? super UUID, ? super Double, ? super String, ? super String, ? super String, ? super String, ? super Adverttype, ? super Integer, ? super Tariff, ? super FlatOrHouse, ? super LocalDate, ? super LocalDate, ? super LocalDate, ? super Realtystatus, ? super UUID, ? super Ownertype, ? super Boolean, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
