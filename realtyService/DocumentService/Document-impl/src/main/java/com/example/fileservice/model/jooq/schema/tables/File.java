/*
 * This file is generated by jOOQ.
 */
package com.example.fileservice.model.jooq.schema.tables;


import com.example.fileservice.model.jooq.schema.Public;
import com.example.fileservice.model.jooq.schema.enums.EntityType;
import com.example.fileservice.model.jooq.schema.enums.FileStatus;
import com.example.fileservice.model.jooq.schema.enums.FileType;
import com.example.fileservice.model.jooq.schema.tables.records.FileRecord;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function11;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row11;
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
public class File extends TableImpl<FileRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.file</code>
     */
    public static final File FILE_ENTITY = new File();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FileRecord> getRecordType() {
        return FileRecord.class;
    }

    /**
     * The column <code>public.file.id</code>.
     */
    public final TableField<FileRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.file.original_file_name</code>.
     */
    public final TableField<FileRecord, String> ORIGINAL_FILE_NAME = createField(DSL.name("original_file_name"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.file.storage_file_name</code>.
     */
    public final TableField<FileRecord, String> STORAGE_FILE_NAME = createField(DSL.name("storage_file_name"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.file.size</code>.
     */
    public final TableField<FileRecord, Integer> SIZE = createField(DSL.name("size"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.file.mime_type</code>.
     */
    public final TableField<FileRecord, String> MIME_TYPE = createField(DSL.name("mime_type"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.file.insert_date</code>.
     */
    public final TableField<FileRecord, LocalDate> INSERT_DATE = createField(DSL.name("insert_date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.file.update_date</code>.
     */
    public final TableField<FileRecord, LocalDate> UPDATE_DATE = createField(DSL.name("update_date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.file.path</code>.
     */
    public final TableField<FileRecord, String> PATH = createField(DSL.name("path"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.file.file_type</code>.
     */
    public final TableField<FileRecord, FileType> FILE_TYPE = createField(DSL.name("file_type"), SQLDataType.VARCHAR.asEnumDataType(com.example.fileservice.model.jooq.schema.enums.FileType.class), this, "");

    /**
     * The column <code>public.file.file_status</code>.
     */
    public final TableField<FileRecord, FileStatus> FILE_STATUS = createField(DSL.name("file_status"), SQLDataType.VARCHAR.asEnumDataType(com.example.fileservice.model.jooq.schema.enums.FileStatus.class), this, "");

    /**
     * The column <code>public.file.entity_type</code>.
     */
    public final TableField<FileRecord, EntityType> ENTITY_TYPE = createField(DSL.name("entity_type"), SQLDataType.VARCHAR.asEnumDataType(com.example.fileservice.model.jooq.schema.enums.EntityType.class), this, "");

    private File(Name alias, Table<FileRecord> aliased) {
        this(alias, aliased, null);
    }

    private File(Name alias, Table<FileRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.file</code> table reference
     */
    public File(String alias) {
        this(DSL.name(alias), FILE_ENTITY);
    }

    /**
     * Create an aliased <code>public.file</code> table reference
     */
    public File(Name alias) {
        this(alias, FILE_ENTITY);
    }

    /**
     * Create a <code>public.file</code> table reference
     */
    public File() {
        this(DSL.name("file"), null);
    }

    public <O extends Record> File(Table<O> child, ForeignKey<O, FileRecord> key) {
        super(child, key, FILE_ENTITY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public File as(String alias) {
        return new File(DSL.name(alias), this);
    }

    @Override
    public File as(Name alias) {
        return new File(alias, this);
    }

    @Override
    public File as(Table<?> alias) {
        return new File(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public File rename(String name) {
        return new File(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public File rename(Name name) {
        return new File(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public File rename(Table<?> name) {
        return new File(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row11 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row11<UUID, String, String, Integer, String, LocalDate, LocalDate, String, FileType, FileStatus, EntityType> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function11<? super UUID, ? super String, ? super String, ? super Integer, ? super String, ? super LocalDate, ? super LocalDate, ? super String, ? super FileType, ? super FileStatus, ? super EntityType, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function11<? super UUID, ? super String, ? super String, ? super Integer, ? super String, ? super LocalDate, ? super LocalDate, ? super String, ? super FileType, ? super FileStatus, ? super EntityType, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
