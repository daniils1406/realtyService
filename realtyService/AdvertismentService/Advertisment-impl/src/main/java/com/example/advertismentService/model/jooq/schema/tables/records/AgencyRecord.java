/*
 * This file is generated by jOOQ.
 */
package com.example.advertismentService.model.jooq.schema.tables.records;


import com.example.advertismentService.model.jooq.schema.enums.Agentlevel;
import com.example.advertismentService.model.jooq.schema.enums.Status;
import com.example.advertismentService.model.jooq.schema.tables.Agency;
import com.example.advertismentService.model.jooq.schema.tables.pojos.AgencyEntity;

import java.time.LocalDate;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AgencyRecord extends UpdatableRecordImpl<AgencyRecord> implements Record8<UUID, String, String, String, String, Agentlevel, Status, LocalDate> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.agency.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.agency.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.agency.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.agency.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.agency.description</code>.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.agency.description</code>.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.agency.phone_number</code>.
     */
    public void setPhoneNumber(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.agency.phone_number</code>.
     */
    public String getPhoneNumber() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.agency.link_for_website</code>.
     */
    public void setLinkForWebsite(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.agency.link_for_website</code>.
     */
    public String getLinkForWebsite() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.agency.level</code>.
     */
    public void setLevel(Agentlevel value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.agency.level</code>.
     */
    public Agentlevel getLevel() {
        return (Agentlevel) get(5);
    }

    /**
     * Setter for <code>public.agency.status</code>.
     */
    public void setStatus(Status value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.agency.status</code>.
     */
    public Status getStatus() {
        return (Status) get(6);
    }

    /**
     * Setter for <code>public.agency.insert_date</code>.
     */
    public void setInsertDate(LocalDate value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.agency.insert_date</code>.
     */
    public LocalDate getInsertDate() {
        return (LocalDate) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<UUID, String, String, String, String, Agentlevel, Status, LocalDate> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<UUID, String, String, String, String, Agentlevel, Status, LocalDate> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Agency.AGENCY_ENTITY.ID;
    }

    @Override
    public Field<String> field2() {
        return Agency.AGENCY_ENTITY.NAME;
    }

    @Override
    public Field<String> field3() {
        return Agency.AGENCY_ENTITY.DESCRIPTION;
    }

    @Override
    public Field<String> field4() {
        return Agency.AGENCY_ENTITY.PHONE_NUMBER;
    }

    @Override
    public Field<String> field5() {
        return Agency.AGENCY_ENTITY.LINK_FOR_WEBSITE;
    }

    @Override
    public Field<Agentlevel> field6() {
        return Agency.AGENCY_ENTITY.LEVEL;
    }

    @Override
    public Field<Status> field7() {
        return Agency.AGENCY_ENTITY.STATUS;
    }

    @Override
    public Field<LocalDate> field8() {
        return Agency.AGENCY_ENTITY.INSERT_DATE;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getDescription();
    }

    @Override
    public String component4() {
        return getPhoneNumber();
    }

    @Override
    public String component5() {
        return getLinkForWebsite();
    }

    @Override
    public Agentlevel component6() {
        return getLevel();
    }

    @Override
    public Status component7() {
        return getStatus();
    }

    @Override
    public LocalDate component8() {
        return getInsertDate();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getDescription();
    }

    @Override
    public String value4() {
        return getPhoneNumber();
    }

    @Override
    public String value5() {
        return getLinkForWebsite();
    }

    @Override
    public Agentlevel value6() {
        return getLevel();
    }

    @Override
    public Status value7() {
        return getStatus();
    }

    @Override
    public LocalDate value8() {
        return getInsertDate();
    }

    @Override
    public AgencyRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public AgencyRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public AgencyRecord value3(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public AgencyRecord value4(String value) {
        setPhoneNumber(value);
        return this;
    }

    @Override
    public AgencyRecord value5(String value) {
        setLinkForWebsite(value);
        return this;
    }

    @Override
    public AgencyRecord value6(Agentlevel value) {
        setLevel(value);
        return this;
    }

    @Override
    public AgencyRecord value7(Status value) {
        setStatus(value);
        return this;
    }

    @Override
    public AgencyRecord value8(LocalDate value) {
        setInsertDate(value);
        return this;
    }

    @Override
    public AgencyRecord values(UUID value1, String value2, String value3, String value4, String value5, Agentlevel value6, Status value7, LocalDate value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AgencyRecord
     */
    public AgencyRecord() {
        super(Agency.AGENCY_ENTITY);
    }

    /**
     * Create a detached, initialised AgencyRecord
     */
    public AgencyRecord(UUID id, String name, String description, String phoneNumber, String linkForWebsite, Agentlevel level, Status status, LocalDate insertDate) {
        super(Agency.AGENCY_ENTITY);

        setId(id);
        setName(name);
        setDescription(description);
        setPhoneNumber(phoneNumber);
        setLinkForWebsite(linkForWebsite);
        setLevel(level);
        setStatus(status);
        setInsertDate(insertDate);
    }

    /**
     * Create a detached, initialised AgencyRecord
     */
    public AgencyRecord(AgencyEntity value) {
        super(Agency.AGENCY_ENTITY);

        if (value != null) {
            setId(value.getId());
            setName(value.getName());
            setDescription(value.getDescription());
            setPhoneNumber(value.getPhoneNumber());
            setLinkForWebsite(value.getLinkForWebsite());
            setLevel(value.getLevel());
            setStatus(value.getStatus());
            setInsertDate(value.getInsertDate());
        }
    }
}
