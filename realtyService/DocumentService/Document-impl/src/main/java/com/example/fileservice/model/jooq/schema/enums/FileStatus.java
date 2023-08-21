/*
 * This file is generated by jOOQ.
 */
package com.example.fileservice.model.jooq.schema.enums;


import com.example.fileservice.model.jooq.schema.Public;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum FileStatus implements EnumType {

    DELETE("DELETE"),

    VERIFIED("VERIFIED"),

    INSERT("INSERT");

    private final String literal;

    private FileStatus(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public String getName() {
        return "file_status";
    }

    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Lookup a value of this EnumType by its literal
     */
    public static FileStatus lookupLiteral(String literal) {
        return EnumType.lookupLiteral(FileStatus.class, literal);
    }
}
