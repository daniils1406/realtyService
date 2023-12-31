/*
 * This file is generated by jOOQ.
 */
package com.example.advertismentService.model.jooq.schema;


import com.example.advertismentService.model.jooq.schema.tables.Agency;
import com.example.advertismentService.model.jooq.schema.tables.Builder;
import com.example.advertismentService.model.jooq.schema.tables.Deal;
import com.example.advertismentService.model.jooq.schema.tables.Favourites;
import com.example.advertismentService.model.jooq.schema.tables.Flat;
import com.example.advertismentService.model.jooq.schema.tables.House;
import com.example.advertismentService.model.jooq.schema.tables.Realty;
import com.example.advertismentService.model.jooq.schema.tables.Regions;
import com.example.advertismentService.model.jooq.schema.tables.RegionsAndAgency;
import com.example.advertismentService.model.jooq.schema.tables.ResidentialComplex;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.agency</code>.
     */
    public final Agency AGENCY_ENTITY = Agency.AGENCY_ENTITY;

    /**
     * The table <code>public.builder</code>.
     */
    public final Builder BUILDER_ENTITY = Builder.BUILDER_ENTITY;

    /**
     * The table <code>public.deal</code>.
     */
    public final Deal DEAL_ENTITY = Deal.DEAL_ENTITY;

    /**
     * The table <code>public.favourites</code>.
     */
    public final Favourites FAVOURITES_ENTITY = Favourites.FAVOURITES_ENTITY;

    /**
     * The table <code>public.flat</code>.
     */
    public final Flat FLAT_ENTITY = Flat.FLAT_ENTITY;

    /**
     * The table <code>public.house</code>.
     */
    public final House HOUSE_ENTITY = House.HOUSE_ENTITY;

    /**
     * The table <code>public.realty</code>.
     */
    public final Realty REALTY_ENTITY = Realty.REALTY_ENTITY;

    /**
     * The table <code>public.regions</code>.
     */
    public final Regions REGIONS_ENTITY = Regions.REGIONS_ENTITY;

    /**
     * The table <code>public.regions_and_agency</code>.
     */
    public final RegionsAndAgency REGIONS_AND_AGENCY_ENTITY = RegionsAndAgency.REGIONS_AND_AGENCY_ENTITY;

    /**
     * The table <code>public.residential_complex</code>.
     */
    public final ResidentialComplex RESIDENTIAL_COMPLEX_ENTITY = ResidentialComplex.RESIDENTIAL_COMPLEX_ENTITY;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Agency.AGENCY_ENTITY,
            Builder.BUILDER_ENTITY,
            Deal.DEAL_ENTITY,
            Favourites.FAVOURITES_ENTITY,
            Flat.FLAT_ENTITY,
            House.HOUSE_ENTITY,
            Realty.REALTY_ENTITY,
            Regions.REGIONS_ENTITY,
            RegionsAndAgency.REGIONS_AND_AGENCY_ENTITY,
            ResidentialComplex.RESIDENTIAL_COMPLEX_ENTITY
        );
    }
}
