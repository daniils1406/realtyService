/*
 * This file is generated by jOOQ.
 */
package com.example.chatService.model.jooq.schema.tables;


import com.example.chatService.model.jooq.schema.Public;
import com.example.chatService.model.jooq.schema.tables.records.ChatAndUserRecord;

import java.util.UUID;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
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
public class ChatAndUser extends TableImpl<ChatAndUserRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.chat_and_user</code>
     */
    public static final ChatAndUser CHAT_AND_USER_ENTITY = new ChatAndUser();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ChatAndUserRecord> getRecordType() {
        return ChatAndUserRecord.class;
    }

    /**
     * The column <code>public.chat_and_user.user_id</code>.
     */
    public final TableField<ChatAndUserRecord, UUID> USER_ID = createField(DSL.name("user_id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.chat_and_user.chat_id</code>.
     */
    public final TableField<ChatAndUserRecord, String> CHAT_ID = createField(DSL.name("chat_id"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.chat_and_user.isAdmin</code>.
     */
    public final TableField<ChatAndUserRecord, Boolean> ISADMIN = createField(DSL.name("isAdmin"), SQLDataType.BOOLEAN.defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");

    private ChatAndUser(Name alias, Table<ChatAndUserRecord> aliased) {
        this(alias, aliased, null);
    }

    private ChatAndUser(Name alias, Table<ChatAndUserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.chat_and_user</code> table reference
     */
    public ChatAndUser(String alias) {
        this(DSL.name(alias), CHAT_AND_USER_ENTITY);
    }

    /**
     * Create an aliased <code>public.chat_and_user</code> table reference
     */
    public ChatAndUser(Name alias) {
        this(alias, CHAT_AND_USER_ENTITY);
    }

    /**
     * Create a <code>public.chat_and_user</code> table reference
     */
    public ChatAndUser() {
        this(DSL.name("chat_and_user"), null);
    }

    public <O extends Record> ChatAndUser(Table<O> child, ForeignKey<O, ChatAndUserRecord> key) {
        super(child, key, CHAT_AND_USER_ENTITY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public ChatAndUser as(String alias) {
        return new ChatAndUser(DSL.name(alias), this);
    }

    @Override
    public ChatAndUser as(Name alias) {
        return new ChatAndUser(alias, this);
    }

    @Override
    public ChatAndUser as(Table<?> alias) {
        return new ChatAndUser(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public ChatAndUser rename(String name) {
        return new ChatAndUser(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ChatAndUser rename(Name name) {
        return new ChatAndUser(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public ChatAndUser rename(Table<?> name) {
        return new ChatAndUser(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, String, Boolean> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super UUID, ? super String, ? super Boolean, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super UUID, ? super String, ? super Boolean, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
