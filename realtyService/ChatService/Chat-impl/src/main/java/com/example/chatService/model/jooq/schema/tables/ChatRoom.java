/*
 * This file is generated by jOOQ.
 */
package com.example.chatService.model.jooq.schema.tables;


import com.example.chatService.model.jooq.schema.Keys;
import com.example.chatService.model.jooq.schema.Public;
import com.example.chatService.model.jooq.schema.enums.ChatStatus;
import com.example.chatService.model.jooq.schema.tables.records.ChatRoomRecord;

import java.time.LocalDate;
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
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ChatRoom extends TableImpl<ChatRoomRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.chat_room</code>
     */
    public static final ChatRoom CHAT_ROOM_ENTITY = new ChatRoom();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ChatRoomRecord> getRecordType() {
        return ChatRoomRecord.class;
    }

    /**
     * The column <code>public.chat_room.id</code>.
     */
    public final TableField<ChatRoomRecord, String> ID = createField(DSL.name("id"), SQLDataType.VARCHAR.nullable(false).defaultValue(DSL.field("uuid_generate_v4()", SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>public.chat_room.create_date</code>.
     */
    public final TableField<ChatRoomRecord, LocalDate> CREATE_DATE = createField(DSL.name("create_date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.chat_room.name</code>.
     */
    public final TableField<ChatRoomRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.chat_room.status</code>.
     */
    public final TableField<ChatRoomRecord, ChatStatus> STATUS = createField(DSL.name("status"), SQLDataType.VARCHAR.asEnumDataType(com.example.chatService.model.jooq.schema.enums.ChatStatus.class), this, "");

    private ChatRoom(Name alias, Table<ChatRoomRecord> aliased) {
        this(alias, aliased, null);
    }

    private ChatRoom(Name alias, Table<ChatRoomRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.chat_room</code> table reference
     */
    public ChatRoom(String alias) {
        this(DSL.name(alias), CHAT_ROOM_ENTITY);
    }

    /**
     * Create an aliased <code>public.chat_room</code> table reference
     */
    public ChatRoom(Name alias) {
        this(alias, CHAT_ROOM_ENTITY);
    }

    /**
     * Create a <code>public.chat_room</code> table reference
     */
    public ChatRoom() {
        this(DSL.name("chat_room"), null);
    }

    public <O extends Record> ChatRoom(Table<O> child, ForeignKey<O, ChatRoomRecord> key) {
        super(child, key, CHAT_ROOM_ENTITY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<ChatRoomRecord> getPrimaryKey() {
        return Keys.PK_CHAT;
    }

    @Override
    public ChatRoom as(String alias) {
        return new ChatRoom(DSL.name(alias), this);
    }

    @Override
    public ChatRoom as(Name alias) {
        return new ChatRoom(alias, this);
    }

    @Override
    public ChatRoom as(Table<?> alias) {
        return new ChatRoom(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public ChatRoom rename(String name) {
        return new ChatRoom(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ChatRoom rename(Name name) {
        return new ChatRoom(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public ChatRoom rename(Table<?> name) {
        return new ChatRoom(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<String, LocalDate, String, ChatStatus> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super String, ? super LocalDate, ? super String, ? super ChatStatus, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super String, ? super LocalDate, ? super String, ? super ChatStatus, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
