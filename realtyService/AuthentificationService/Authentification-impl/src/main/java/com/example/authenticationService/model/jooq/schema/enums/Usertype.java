/*
 * This file is generated by jOOQ.
 */
package com.example.authenticationService.model.jooq.schema.enums;


import com.example.authenticationService.model.jooq.schema.Public;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum Usertype implements EnumType {

    ORGANISATION("ORGANISATION"),

    AGENT("AGENT"),

    CLIENT("CLIENT");

    private final String literal;

    private Usertype(String literal) {
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
        return "usertype";
    }

    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Lookup a value of this EnumType by its literal
     */
    public static Usertype lookupLiteral(String literal) {
        return EnumType.lookupLiteral(Usertype.class, literal);
    }
}