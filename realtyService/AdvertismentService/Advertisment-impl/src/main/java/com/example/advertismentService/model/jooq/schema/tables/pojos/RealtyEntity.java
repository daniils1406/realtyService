/*
 * This file is generated by jOOQ.
 */
package com.example.advertismentService.model.jooq.schema.tables.pojos;


import com.example.advertismentService.entity.Entity;
import com.example.advertismentService.model.jooq.schema.enums.Adverttype;
import com.example.advertismentService.model.jooq.schema.enums.FlatOrHouse;
import com.example.advertismentService.model.jooq.schema.enums.Ownertype;
import com.example.advertismentService.model.jooq.schema.enums.Realtystatus;
import com.example.advertismentService.model.jooq.schema.enums.Tariff;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RealtyEntity extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private UUID ownerId;
    private Double square;
    private String region;
    private String district;
    private String address;
    private String description;
    private Adverttype advertType;
    private Integer cost;
    private Tariff tariffType;
    private FlatOrHouse flatOrHouse;
    private LocalDate insertDate;
    private LocalDate updateDate;
    private LocalDate releaseDate;
    private Realtystatus status;
    private UUID complexId;
    private Ownertype ownerType;
    private Boolean verify;

    public RealtyEntity() {}

    public RealtyEntity(RealtyEntity value) {
        this.id = value.id;
        this.ownerId = value.ownerId;
        this.square = value.square;
        this.region = value.region;
        this.district = value.district;
        this.address = value.address;
        this.description = value.description;
        this.advertType = value.advertType;
        this.cost = value.cost;
        this.tariffType = value.tariffType;
        this.flatOrHouse = value.flatOrHouse;
        this.insertDate = value.insertDate;
        this.updateDate = value.updateDate;
        this.releaseDate = value.releaseDate;
        this.status = value.status;
        this.complexId = value.complexId;
        this.ownerType = value.ownerType;
        this.verify = value.verify;
    }

    public RealtyEntity(
        UUID id,
        UUID ownerId,
        Double square,
        String region,
        String district,
        String address,
        String description,
        Adverttype advertType,
        Integer cost,
        Tariff tariffType,
        FlatOrHouse flatOrHouse,
        LocalDate insertDate,
        LocalDate updateDate,
        LocalDate releaseDate,
        Realtystatus status,
        UUID complexId,
        Ownertype ownerType,
        Boolean verify
    ) {
        this.id = id;
        this.ownerId = ownerId;
        this.square = square;
        this.region = region;
        this.district = district;
        this.address = address;
        this.description = description;
        this.advertType = advertType;
        this.cost = cost;
        this.tariffType = tariffType;
        this.flatOrHouse = flatOrHouse;
        this.insertDate = insertDate;
        this.updateDate = updateDate;
        this.releaseDate = releaseDate;
        this.status = status;
        this.complexId = complexId;
        this.ownerType = ownerType;
        this.verify = verify;
    }

    /**
     * Getter for <code>public.realty.id</code>.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.realty.id</code>.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Getter for <code>public.realty.owner_id</code>.
     */
    public UUID getOwnerId() {
        return this.ownerId;
    }

    /**
     * Setter for <code>public.realty.owner_id</code>.
     */
    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Getter for <code>public.realty.square</code>.
     */
    public Double getSquare() {
        return this.square;
    }

    /**
     * Setter for <code>public.realty.square</code>.
     */
    public void setSquare(Double square) {
        this.square = square;
    }

    /**
     * Getter for <code>public.realty.region</code>.
     */
    public String getRegion() {
        return this.region;
    }

    /**
     * Setter for <code>public.realty.region</code>.
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Getter for <code>public.realty.district</code>.
     */
    public String getDistrict() {
        return this.district;
    }

    /**
     * Setter for <code>public.realty.district</code>.
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * Getter for <code>public.realty.address</code>.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Setter for <code>public.realty.address</code>.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for <code>public.realty.description</code>.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>public.realty.description</code>.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for <code>public.realty.advert_type</code>.
     */
    public Adverttype getAdvertType() {
        return this.advertType;
    }

    /**
     * Setter for <code>public.realty.advert_type</code>.
     */
    public void setAdvertType(Adverttype advertType) {
        this.advertType = advertType;
    }

    /**
     * Getter for <code>public.realty.cost</code>.
     */
    public Integer getCost() {
        return this.cost;
    }

    /**
     * Setter for <code>public.realty.cost</code>.
     */
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    /**
     * Getter for <code>public.realty.tariff_type</code>.
     */
    public Tariff getTariffType() {
        return this.tariffType;
    }

    /**
     * Setter for <code>public.realty.tariff_type</code>.
     */
    public void setTariffType(Tariff tariffType) {
        this.tariffType = tariffType;
    }

    /**
     * Getter for <code>public.realty.flat_or_house</code>.
     */
    public FlatOrHouse getFlatOrHouse() {
        return this.flatOrHouse;
    }

    /**
     * Setter for <code>public.realty.flat_or_house</code>.
     */
    public void setFlatOrHouse(FlatOrHouse flatOrHouse) {
        this.flatOrHouse = flatOrHouse;
    }

    /**
     * Getter for <code>public.realty.insert_date</code>.
     */
    public LocalDate getInsertDate() {
        return this.insertDate;
    }

    /**
     * Setter for <code>public.realty.insert_date</code>.
     */
    public void setInsertDate(LocalDate insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * Getter for <code>public.realty.update_date</code>.
     */
    public LocalDate getUpdateDate() {
        return this.updateDate;
    }

    /**
     * Setter for <code>public.realty.update_date</code>.
     */
    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * Getter for <code>public.realty.release_date</code>.
     */
    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    /**
     * Setter for <code>public.realty.release_date</code>.
     */
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * Getter for <code>public.realty.status</code>.
     */
    public Realtystatus getStatus() {
        return this.status;
    }

    /**
     * Setter for <code>public.realty.status</code>.
     */
    public void setStatus(Realtystatus status) {
        this.status = status;
    }

    /**
     * Getter for <code>public.realty.complex_id</code>.
     */
    public UUID getComplexId() {
        return this.complexId;
    }

    /**
     * Setter for <code>public.realty.complex_id</code>.
     */
    public void setComplexId(UUID complexId) {
        this.complexId = complexId;
    }

    /**
     * Getter for <code>public.realty.owner_type</code>.
     */
    public Ownertype getOwnerType() {
        return this.ownerType;
    }

    /**
     * Setter for <code>public.realty.owner_type</code>.
     */
    public void setOwnerType(Ownertype ownerType) {
        this.ownerType = ownerType;
    }

    /**
     * Getter for <code>public.realty.verify</code>.
     */
    public Boolean getVerify() {
        return this.verify;
    }

    /**
     * Setter for <code>public.realty.verify</code>.
     */
    public void setVerify(Boolean verify) {
        this.verify = verify;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final RealtyEntity other = (RealtyEntity) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.ownerId == null) {
            if (other.ownerId != null)
                return false;
        }
        else if (!this.ownerId.equals(other.ownerId))
            return false;
        if (this.square == null) {
            if (other.square != null)
                return false;
        }
        else if (!this.square.equals(other.square))
            return false;
        if (this.region == null) {
            if (other.region != null)
                return false;
        }
        else if (!this.region.equals(other.region))
            return false;
        if (this.district == null) {
            if (other.district != null)
                return false;
        }
        else if (!this.district.equals(other.district))
            return false;
        if (this.address == null) {
            if (other.address != null)
                return false;
        }
        else if (!this.address.equals(other.address))
            return false;
        if (this.description == null) {
            if (other.description != null)
                return false;
        }
        else if (!this.description.equals(other.description))
            return false;
        if (this.advertType == null) {
            if (other.advertType != null)
                return false;
        }
        else if (!this.advertType.equals(other.advertType))
            return false;
        if (this.cost == null) {
            if (other.cost != null)
                return false;
        }
        else if (!this.cost.equals(other.cost))
            return false;
        if (this.tariffType == null) {
            if (other.tariffType != null)
                return false;
        }
        else if (!this.tariffType.equals(other.tariffType))
            return false;
        if (this.flatOrHouse == null) {
            if (other.flatOrHouse != null)
                return false;
        }
        else if (!this.flatOrHouse.equals(other.flatOrHouse))
            return false;
        if (this.insertDate == null) {
            if (other.insertDate != null)
                return false;
        }
        else if (!this.insertDate.equals(other.insertDate))
            return false;
        if (this.updateDate == null) {
            if (other.updateDate != null)
                return false;
        }
        else if (!this.updateDate.equals(other.updateDate))
            return false;
        if (this.releaseDate == null) {
            if (other.releaseDate != null)
                return false;
        }
        else if (!this.releaseDate.equals(other.releaseDate))
            return false;
        if (this.status == null) {
            if (other.status != null)
                return false;
        }
        else if (!this.status.equals(other.status))
            return false;
        if (this.complexId == null) {
            if (other.complexId != null)
                return false;
        }
        else if (!this.complexId.equals(other.complexId))
            return false;
        if (this.ownerType == null) {
            if (other.ownerType != null)
                return false;
        }
        else if (!this.ownerType.equals(other.ownerType))
            return false;
        if (this.verify == null) {
            if (other.verify != null)
                return false;
        }
        else if (!this.verify.equals(other.verify))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.square == null) ? 0 : this.square.hashCode());
        result = prime * result + ((this.region == null) ? 0 : this.region.hashCode());
        result = prime * result + ((this.district == null) ? 0 : this.district.hashCode());
        result = prime * result + ((this.address == null) ? 0 : this.address.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.advertType == null) ? 0 : this.advertType.hashCode());
        result = prime * result + ((this.cost == null) ? 0 : this.cost.hashCode());
        result = prime * result + ((this.tariffType == null) ? 0 : this.tariffType.hashCode());
        result = prime * result + ((this.flatOrHouse == null) ? 0 : this.flatOrHouse.hashCode());
        result = prime * result + ((this.insertDate == null) ? 0 : this.insertDate.hashCode());
        result = prime * result + ((this.updateDate == null) ? 0 : this.updateDate.hashCode());
        result = prime * result + ((this.releaseDate == null) ? 0 : this.releaseDate.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.complexId == null) ? 0 : this.complexId.hashCode());
        result = prime * result + ((this.ownerType == null) ? 0 : this.ownerType.hashCode());
        result = prime * result + ((this.verify == null) ? 0 : this.verify.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("RealtyEntity (");

        sb.append(id);
        sb.append(", ").append(ownerId);
        sb.append(", ").append(square);
        sb.append(", ").append(region);
        sb.append(", ").append(district);
        sb.append(", ").append(address);
        sb.append(", ").append(description);
        sb.append(", ").append(advertType);
        sb.append(", ").append(cost);
        sb.append(", ").append(tariffType);
        sb.append(", ").append(flatOrHouse);
        sb.append(", ").append(insertDate);
        sb.append(", ").append(updateDate);
        sb.append(", ").append(releaseDate);
        sb.append(", ").append(status);
        sb.append(", ").append(complexId);
        sb.append(", ").append(ownerType);
        sb.append(", ").append(verify);

        sb.append(")");
        return sb.toString();
    }
}
