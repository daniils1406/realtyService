/*
 * This file is generated by jOOQ.
 */
package com.example.advertismentService.model.jooq.schema.tables.pojos;


import com.example.advertismentService.model.jooq.schema.enums.Realtytype;

import java.io.Serializable;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FlatEntity extends RealtyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private Integer numberOfRooms;
    private Integer level;
    private Double kitchenSquare;
    private Double livingSquare;
    private Realtytype flatType;

    public FlatEntity() {}

    public FlatEntity(FlatEntity value) {
        this.id = value.id;
        this.numberOfRooms = value.numberOfRooms;
        this.level = value.level;
        this.kitchenSquare = value.kitchenSquare;
        this.livingSquare = value.livingSquare;
        this.flatType = value.flatType;
    }

    public FlatEntity(
        UUID id,
        Integer numberOfRooms,
        Integer level,
        Double kitchenSquare,
        Double livingSquare,
        Realtytype flatType
    ) {
        this.id = id;
        this.numberOfRooms = numberOfRooms;
        this.level = level;
        this.kitchenSquare = kitchenSquare;
        this.livingSquare = livingSquare;
        this.flatType = flatType;
    }

    /**
     * Getter for <code>public.flat.id</code>.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.flat.id</code>.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Getter for <code>public.flat.number_of_rooms</code>.
     */
    public Integer getNumberOfRooms() {
        return this.numberOfRooms;
    }

    /**
     * Setter for <code>public.flat.number_of_rooms</code>.
     */
    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Getter for <code>public.flat.level</code>.
     */
    public Integer getLevel() {
        return this.level;
    }

    /**
     * Setter for <code>public.flat.level</code>.
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * Getter for <code>public.flat.kitchen_square</code>.
     */
    public Double getKitchenSquare() {
        return this.kitchenSquare;
    }

    /**
     * Setter for <code>public.flat.kitchen_square</code>.
     */
    public void setKitchenSquare(Double kitchenSquare) {
        this.kitchenSquare = kitchenSquare;
    }

    /**
     * Getter for <code>public.flat.living_square</code>.
     */
    public Double getLivingSquare() {
        return this.livingSquare;
    }

    /**
     * Setter for <code>public.flat.living_square</code>.
     */
    public void setLivingSquare(Double livingSquare) {
        this.livingSquare = livingSquare;
    }

    /**
     * Getter for <code>public.flat.flat_type</code>.
     */
    public Realtytype getFlatType() {
        return this.flatType;
    }

    /**
     * Setter for <code>public.flat.flat_type</code>.
     */
    public void setFlatType(Realtytype flatType) {
        this.flatType = flatType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final FlatEntity other = (FlatEntity) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.numberOfRooms == null) {
            if (other.numberOfRooms != null)
                return false;
        }
        else if (!this.numberOfRooms.equals(other.numberOfRooms))
            return false;
        if (this.level == null) {
            if (other.level != null)
                return false;
        }
        else if (!this.level.equals(other.level))
            return false;
        if (this.kitchenSquare == null) {
            if (other.kitchenSquare != null)
                return false;
        }
        else if (!this.kitchenSquare.equals(other.kitchenSquare))
            return false;
        if (this.livingSquare == null) {
            if (other.livingSquare != null)
                return false;
        }
        else if (!this.livingSquare.equals(other.livingSquare))
            return false;
        if (this.flatType == null) {
            if (other.flatType != null)
                return false;
        }
        else if (!this.flatType.equals(other.flatType))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.numberOfRooms == null) ? 0 : this.numberOfRooms.hashCode());
        result = prime * result + ((this.level == null) ? 0 : this.level.hashCode());
        result = prime * result + ((this.kitchenSquare == null) ? 0 : this.kitchenSquare.hashCode());
        result = prime * result + ((this.livingSquare == null) ? 0 : this.livingSquare.hashCode());
        result = prime * result + ((this.flatType == null) ? 0 : this.flatType.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("FlatEntity (");

        sb.append(id);
        sb.append(", ").append(numberOfRooms);
        sb.append(", ").append(level);
        sb.append(", ").append(kitchenSquare);
        sb.append(", ").append(livingSquare);
        sb.append(", ").append(flatType);

        sb.append(")");
        return sb.toString();
    }
}
