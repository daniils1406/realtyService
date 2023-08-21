/*
 * This file is generated by jOOQ.
 */
package com.example.advertismentService.model.jooq.schema.enums;


import com.example.advertismentService.model.jooq.schema.Public;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum Buildertype implements EnumType {

    REGISTERED("REGISTERED"),

    VERIFIED("VERIFIED"),

    DELETE("DELETE");

    private final String literal;

    private Buildertype(String literal) {
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
        return "buildertype";
    }

    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Lookup a value of this EnumType by its literal
     */
    public static Buildertype lookupLiteral(String literal) {
        return EnumType.lookupLiteral(Buildertype.class, literal);
    }
}
