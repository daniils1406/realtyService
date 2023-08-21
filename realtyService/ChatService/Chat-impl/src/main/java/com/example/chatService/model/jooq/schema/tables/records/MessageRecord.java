/*
 * This file is generated by jOOQ.
 */
package com.example.chatService.model.jooq.schema.tables.records;


import com.example.chatService.model.jooq.schema.enums.Status;
import com.example.chatService.model.jooq.schema.tables.Message;
import com.example.chatService.model.jooq.schema.tables.pojos.MessageEntity;

import java.time.LocalDate;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MessageRecord extends UpdatableRecordImpl<MessageRecord> implements Record7<UUID, UUID, String, String, String, LocalDate, Status> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.message.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.message.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.message.sender_id</code>.
     */
    public void setSenderId(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.message.sender_id</code>.
     */
    public UUID getSenderId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>public.message.recipient_id</code>.
     */
    public void setRecipientId(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.message.recipient_id</code>.
     */
    public String getRecipientId() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.message.sender_name</code>.
     */
    public void setSenderName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.message.sender_name</code>.
     */
    public String getSenderName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.message.content</code>.
     */
    public void setContent(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.message.content</code>.
     */
    public String getContent() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.message.create_date</code>.
     */
    public void setCreateDate(LocalDate value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.message.create_date</code>.
     */
    public LocalDate getCreateDate() {
        return (LocalDate) get(5);
    }

    /**
     * Setter for <code>public.message.status</code>.
     */
    public void setStatus(Status value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.message.status</code>.
     */
    public Status getStatus() {
        return (Status) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<UUID, UUID, String, String, String, LocalDate, Status> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<UUID, UUID, String, String, String, LocalDate, Status> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Message.MESSAGE_ENTITY.ID;
    }

    @Override
    public Field<UUID> field2() {
        return Message.MESSAGE_ENTITY.SENDER_ID;
    }

    @Override
    public Field<String> field3() {
        return Message.MESSAGE_ENTITY.RECIPIENT_ID;
    }

    @Override
    public Field<String> field4() {
        return Message.MESSAGE_ENTITY.SENDER_NAME;
    }

    @Override
    public Field<String> field5() {
        return Message.MESSAGE_ENTITY.CONTENT;
    }

    @Override
    public Field<LocalDate> field6() {
        return Message.MESSAGE_ENTITY.CREATE_DATE;
    }

    @Override
    public Field<Status> field7() {
        return Message.MESSAGE_ENTITY.STATUS;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public UUID component2() {
        return getSenderId();
    }

    @Override
    public String component3() {
        return getRecipientId();
    }

    @Override
    public String component4() {
        return getSenderName();
    }

    @Override
    public String component5() {
        return getContent();
    }

    @Override
    public LocalDate component6() {
        return getCreateDate();
    }

    @Override
    public Status component7() {
        return getStatus();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public UUID value2() {
        return getSenderId();
    }

    @Override
    public String value3() {
        return getRecipientId();
    }

    @Override
    public String value4() {
        return getSenderName();
    }

    @Override
    public String value5() {
        return getContent();
    }

    @Override
    public LocalDate value6() {
        return getCreateDate();
    }

    @Override
    public Status value7() {
        return getStatus();
    }

    @Override
    public MessageRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public MessageRecord value2(UUID value) {
        setSenderId(value);
        return this;
    }

    @Override
    public MessageRecord value3(String value) {
        setRecipientId(value);
        return this;
    }

    @Override
    public MessageRecord value4(String value) {
        setSenderName(value);
        return this;
    }

    @Override
    public MessageRecord value5(String value) {
        setContent(value);
        return this;
    }

    @Override
    public MessageRecord value6(LocalDate value) {
        setCreateDate(value);
        return this;
    }

    @Override
    public MessageRecord value7(Status value) {
        setStatus(value);
        return this;
    }

    @Override
    public MessageRecord values(UUID value1, UUID value2, String value3, String value4, String value5, LocalDate value6, Status value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MessageRecord
     */
    public MessageRecord() {
        super(Message.MESSAGE_ENTITY);
    }

    /**
     * Create a detached, initialised MessageRecord
     */
    public MessageRecord(UUID id, UUID senderId, String recipientId, String senderName, String content, LocalDate createDate, Status status) {
        super(Message.MESSAGE_ENTITY);

        setId(id);
        setSenderId(senderId);
        setRecipientId(recipientId);
        setSenderName(senderName);
        setContent(content);
        setCreateDate(createDate);
        setStatus(status);
    }

    /**
     * Create a detached, initialised MessageRecord
     */
    public MessageRecord(MessageEntity value) {
        super(Message.MESSAGE_ENTITY);

        if (value != null) {
            setId(value.getId());
            setSenderId(value.getSenderId());
            setRecipientId(value.getRecipientId());
            setSenderName(value.getSenderName());
            setContent(value.getContent());
            setCreateDate(value.getCreateDate());
            setStatus(value.getStatus());
        }
    }
}