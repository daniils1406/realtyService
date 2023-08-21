/*
 * This file is generated by jOOQ.
 */
package com.example.advertismentService.model.jooq.schema.tables.records;


import com.example.advertismentService.model.jooq.schema.tables.RegionsAndAgency;
import com.example.advertismentService.model.jooq.schema.tables.pojos.RegionsAndAgencyEntity;

import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RegionsAndAgencyRecord extends TableRecordImpl<RegionsAndAgencyRecord> implements Record2<UUID, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.regions_and_agency.agency_id</code>.
     */
    public void setAgencyId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.regions_and_agency.agency_id</code>.
     */
    public UUID getAgencyId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.regions_and_agency.region_code</code>.
     */
    public void setRegionCode(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.regions_and_agency.region_code</code>.
     */
    public Integer getRegionCode() {
        return (Integer) get(1);
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<UUID, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<UUID, Integer> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return RegionsAndAgency.REGIONS_AND_AGENCY_ENTITY.AGENCY_ID;
    }

    @Override
    public Field<Integer> field2() {
        return RegionsAndAgency.REGIONS_AND_AGENCY_ENTITY.REGION_CODE;
    }

    @Override
    public UUID component1() {
        return getAgencyId();
    }

    @Override
    public Integer component2() {
        return getRegionCode();
    }

    @Override
    public UUID value1() {
        return getAgencyId();
    }

    @Override
    public Integer value2() {
        return getRegionCode();
    }

    @Override
    public RegionsAndAgencyRecord value1(UUID value) {
        setAgencyId(value);
        return this;
    }

    @Override
    public RegionsAndAgencyRecord value2(Integer value) {
        setRegionCode(value);
        return this;
    }

    @Override
    public RegionsAndAgencyRecord values(UUID value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RegionsAndAgencyRecord
     */
    public RegionsAndAgencyRecord() {
        super(RegionsAndAgency.REGIONS_AND_AGENCY_ENTITY);
    }

    /**
     * Create a detached, initialised RegionsAndAgencyRecord
     */
    public RegionsAndAgencyRecord(UUID agencyId, Integer regionCode) {
        super(RegionsAndAgency.REGIONS_AND_AGENCY_ENTITY);

        setAgencyId(agencyId);
        setRegionCode(regionCode);
    }

    /**
     * Create a detached, initialised RegionsAndAgencyRecord
     */
    public RegionsAndAgencyRecord(RegionsAndAgencyEntity value) {
        super(RegionsAndAgency.REGIONS_AND_AGENCY_ENTITY);

        if (value != null) {
            setAgencyId(value.getAgencyId());
            setRegionCode(value.getRegionCode());
        }
    }
}