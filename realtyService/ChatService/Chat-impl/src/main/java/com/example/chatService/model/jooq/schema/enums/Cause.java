/*
 * This file is generated by jOOQ.
 */
package com.example.chatService.model.jooq.schema.enums;


import com.example.chatService.model.jooq.schema.Public;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum Cause implements EnumType {

    INTERESTED("INTERESTED"),

    BANNED("BANNED"),

    DELETED("DELETED"),

    OPENED("OPENED"),

    SERVICE("SERVICE");

    private final String literal;

    private Cause(String literal) {
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
        return "cause";
    }

    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Lookup a value of this EnumType by its literal
     */
    public static Cause lookupLiteral(String literal) {
        return EnumType.lookupLiteral(Cause.class, literal);
    }
}
