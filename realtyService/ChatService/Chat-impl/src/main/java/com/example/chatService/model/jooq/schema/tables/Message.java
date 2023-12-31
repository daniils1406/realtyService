/*
 * This file is generated by jOOQ.
 */
package com.example.chatService.model.jooq.schema.tables;


import com.example.chatService.model.jooq.schema.Keys;
import com.example.chatService.model.jooq.schema.Public;
import com.example.chatService.model.jooq.schema.enums.Status;
import com.example.chatService.model.jooq.schema.tables.records.MessageRecord;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function7;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row7;
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
public class Message extends TableImpl<MessageRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.message</code>
     */
    public static final Message MESSAGE_ENTITY = new Message();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MessageRecord> getRecordType() {
        return MessageRecord.class;
    }

    /**
     * The column <code>public.message.id</code>.
     */
    public final TableField<MessageRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("uuid_generate_v4()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.message.sender_id</code>.
     */
    public final TableField<MessageRecord, UUID> SENDER_ID = createField(DSL.name("sender_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.message.recipient_id</code>.
     */
    public final TableField<MessageRecord, String> RECIPIENT_ID = createField(DSL.name("recipient_id"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.message.sender_name</code>.
     */
    public final TableField<MessageRecord, String> SENDER_NAME = createField(DSL.name("sender_name"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.message.content</code>.
     */
    public final TableField<MessageRecord, String> CONTENT = createField(DSL.name("content"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.message.create_date</code>.
     */
    public final TableField<MessageRecord, LocalDate> CREATE_DATE = createField(DSL.name("create_date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.message.status</code>.
     */
    public final TableField<MessageRecord, Status> STATUS = createField(DSL.name("status"), SQLDataType.VARCHAR.asEnumDataType(com.example.chatService.model.jooq.schema.enums.Status.class), this, "");

    private Message(Name alias, Table<MessageRecord> aliased) {
        this(alias, aliased, null);
    }

    private Message(Name alias, Table<MessageRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.message</code> table reference
     */
    public Message(String alias) {
        this(DSL.name(alias), MESSAGE_ENTITY);
    }

    /**
     * Create an aliased <code>public.message</code> table reference
     */
    public Message(Name alias) {
        this(alias, MESSAGE_ENTITY);
    }

    /**
     * Create a <code>public.message</code> table reference
     */
    public Message() {
        this(DSL.name("message"), null);
    }

    public <O extends Record> Message(Table<O> child, ForeignKey<O, MessageRecord> key) {
        super(child, key, MESSAGE_ENTITY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<MessageRecord> getPrimaryKey() {
        return Keys.MESSAGE_PKEY;
    }

    @Override
    public Message as(String alias) {
        return new Message(DSL.name(alias), this);
    }

    @Override
    public Message as(Name alias) {
        return new Message(alias, this);
    }

    @Override
    public Message as(Table<?> alias) {
        return new Message(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Message rename(String name) {
        return new Message(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Message rename(Name name) {
        return new Message(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Message rename(Table<?> name) {
        return new Message(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<UUID, UUID, String, String, String, LocalDate, Status> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super UUID, ? super UUID, ? super String, ? super String, ? super String, ? super LocalDate, ? super Status, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super UUID, ? super UUID, ? super String, ? super String, ? super String, ? super LocalDate, ? super Status, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
