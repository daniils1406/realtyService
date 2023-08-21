/*
 * This file is generated by jOOQ.
 */
package com.example.advertismentService.model.jooq.schema.tables.records;


import com.example.advertismentService.model.jooq.schema.enums.Dealstatus;
import com.example.advertismentService.model.jooq.schema.tables.Deal;
import com.example.advertismentService.model.jooq.schema.tables.pojos.DealEntity;

import java.time.LocalDate;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DealRecord extends UpdatableRecordImpl<DealRecord> implements Record9<UUID, UUID, UUID, LocalDate, Integer, Dealstatus, LocalDate, LocalDate, UUID> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.deal.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.deal.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.deal.client_id</code>.
     */
    public void setClientId(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.deal.client_id</code>.
     */
    public UUID getClientId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.deal.broker_id</code>.
     */
    public void setBrokerId(UUID value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.deal.broker_id</code>.
     */
    public UUID getBrokerId() {
        return (UUID) get(2);
    }

    /**
     * Setter for <code>public.deal.period_of_deal</code>.
     */
    public void setPeriodOfDeal(LocalDate value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.deal.period_of_deal</code>.
     */
    public LocalDate getPeriodOfDeal() {
        return (LocalDate) get(3);
    }

    /**
     * Setter for <code>public.deal.transaction_amount</code>.
     */
    public void setTransactionAmount(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.deal.transaction_amount</code>.
     */
    public Integer getTransactionAmount() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>public.deal.status</code>.
     */
    public void setStatus(Dealstatus value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.deal.status</code>.
     */
    public Dealstatus getStatus() {
        return (Dealstatus) get(5);
    }

    /**
     * Setter for <code>public.deal.insert_date</code>.
     */
    public void setInsertDate(LocalDate value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.deal.insert_date</code>.
     */
    public LocalDate getInsertDate() {
        return (LocalDate) get(6);
    }

    /**
     * Setter for <code>public.deal.transaction_date</code>.
     */
    public void setTransactionDate(LocalDate value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.deal.transaction_date</code>.
     */
    public LocalDate getTransactionDate() {
        return (LocalDate) get(7);
    }

    /**
     * Setter for <code>public.deal.realty_id</code>.
     */
    public void setRealtyId(UUID value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.deal.realty_id</code>.
     */
    public UUID getRealtyId() {
        return (UUID) get(8);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record9 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row9<UUID, UUID, UUID, LocalDate, Integer, Dealstatus, LocalDate, LocalDate, UUID> fieldsRow() {
        return (Row9) super.fieldsRow();
    }

    @Override
    public Row9<UUID, UUID, UUID, LocalDate, Integer, Dealstatus, LocalDate, LocalDate, UUID> valuesRow() {
        return (Row9) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Deal.DEAL_ENTITY.ID;
    }

    @Override
    public Field<UUID> field2() {
        return Deal.DEAL_ENTITY.CLIENT_ID;
    }

    @Override
    public Field<UUID> field3() {
        return Deal.DEAL_ENTITY.BROKER_ID;
    }

    @Override
    public Field<LocalDate> field4() {
        return Deal.DEAL_ENTITY.PERIOD_OF_DEAL;
    }

    @Override
    public Field<Integer> field5() {
        return Deal.DEAL_ENTITY.TRANSACTION_AMOUNT;
    }

    @Override
    public Field<Dealstatus> field6() {
        return Deal.DEAL_ENTITY.STATUS;
    }

    @Override
    public Field<LocalDate> field7() {
        return Deal.DEAL_ENTITY.INSERT_DATE;
    }

    @Override
    public Field<LocalDate> field8() {
        return Deal.DEAL_ENTITY.TRANSACTION_DATE;
    }

    @Override
    public Field<UUID> field9() {
        return Deal.DEAL_ENTITY.REALTY_ID;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public UUID component2() {
        return getClientId();
    }

    @Override
    public UUID component3() {
        return getBrokerId();
    }

    @Override
    public LocalDate component4() {
        return getPeriodOfDeal();
    }

    @Override
    public Integer component5() {
        return getTransactionAmount();
    }

    @Override
    public Dealstatus component6() {
        return getStatus();
    }

    @Override
    public LocalDate component7() {
        return getInsertDate();
    }

    @Override
    public LocalDate component8() {
        return getTransactionDate();
    }

    @Override
    public UUID component9() {
        return getRealtyId();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public UUID value2() {
        return getClientId();
    }

    @Override
    public UUID value3() {
        return getBrokerId();
    }

    @Override
    public LocalDate value4() {
        return getPeriodOfDeal();
    }

    @Override
    public Integer value5() {
        return getTransactionAmount();
    }

    @Override
    public Dealstatus value6() {
        return getStatus();
    }

    @Override
    public LocalDate value7() {
        return getInsertDate();
    }

    @Override
    public LocalDate value8() {
        return getTransactionDate();
    }

    @Override
    public UUID value9() {
        return getRealtyId();
    }

    @Override
    public DealRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public DealRecord value2(UUID value) {
        setClientId(value);
        return this;
    }

    @Override
    public DealRecord value3(UUID value) {
        setBrokerId(value);
        return this;
    }

    @Override
    public DealRecord value4(LocalDate value) {
        setPeriodOfDeal(value);
        return this;
    }

    @Override
    public DealRecord value5(Integer value) {
        setTransactionAmount(value);
        return this;
    }

    @Override
    public DealRecord value6(Dealstatus value) {
        setStatus(value);
        return this;
    }

    @Override
    public DealRecord value7(LocalDate value) {
        setInsertDate(value);
        return this;
    }

    @Override
    public DealRecord value8(LocalDate value) {
        setTransactionDate(value);
        return this;
    }

    @Override
    public DealRecord value9(UUID value) {
        setRealtyId(value);
        return this;
    }

    @Override
    public DealRecord values(UUID value1, UUID value2, UUID value3, LocalDate value4, Integer value5, Dealstatus value6, LocalDate value7, LocalDate value8, UUID value9) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached DealRecord
     */
    public DealRecord() {
        super(Deal.DEAL_ENTITY);
    }

    /**
     * Create a detached, initialised DealRecord
     */
    public DealRecord(UUID id, UUID clientId, UUID brokerId, LocalDate periodOfDeal, Integer transactionAmount, Dealstatus status, LocalDate insertDate, LocalDate transactionDate, UUID realtyId) {
        super(Deal.DEAL_ENTITY);

        setId(id);
        setClientId(clientId);
        setBrokerId(brokerId);
        setPeriodOfDeal(periodOfDeal);
        setTransactionAmount(transactionAmount);
        setStatus(status);
        setInsertDate(insertDate);
        setTransactionDate(transactionDate);
        setRealtyId(realtyId);
    }

    /**
     * Create a detached, initialised DealRecord
     */
    public DealRecord(DealEntity value) {
        super(Deal.DEAL_ENTITY);

        if (value != null) {
            setId(value.getId());
            setClientId(value.getClientId());
            setBrokerId(value.getBrokerId());
            setPeriodOfDeal(value.getPeriodOfDeal());
            setTransactionAmount(value.getTransactionAmount());
            setStatus(value.getStatus());
            setInsertDate(value.getInsertDate());
            setTransactionDate(value.getTransactionDate());
            setRealtyId(value.getRealtyId());
        }
    }
}
