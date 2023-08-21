/*
 * This file is generated by jOOQ.
 */
package com.example.advertismentService.model.jooq.schema.tables.pojos;


import com.example.advertismentService.entity.Entity;
import com.example.advertismentService.model.jooq.schema.enums.Status;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ResidentialComplexEntity extends Entity implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;
    private String city;
    private String district;
    private Integer numberOfBuildings;
    private Integer numberOfReadyBuildings;
    private UUID builderId;
    private String name;
    private String description;
    private String linkOnWebsite;
    private String phoneNumber;
    private LocalDate deliveryYear;
    private Status status;
    private Integer region;

    public ResidentialComplexEntity() {}

    public ResidentialComplexEntity(ResidentialComplexEntity value) {
        this.id = value.id;
        this.city = value.city;
        this.district = value.district;
        this.numberOfBuildings = value.numberOfBuildings;
        this.numberOfReadyBuildings = value.numberOfReadyBuildings;
        this.builderId = value.builderId;
        this.name = value.name;
        this.description = value.description;
        this.linkOnWebsite = value.linkOnWebsite;
        this.phoneNumber = value.phoneNumber;
        this.deliveryYear = value.deliveryYear;
        this.status = value.status;
        this.region = value.region;
    }

    public ResidentialComplexEntity(
        UUID id,
        String city,
        String district,
        Integer numberOfBuildings,
        Integer numberOfReadyBuildings,
        UUID builderId,
        String name,
        String description,
        String linkOnWebsite,
        String phoneNumber,
        LocalDate deliveryYear,
        Status status,
        Integer region
    ) {
        this.id = id;
        this.city = city;
        this.district = district;
        this.numberOfBuildings = numberOfBuildings;
        this.numberOfReadyBuildings = numberOfReadyBuildings;
        this.builderId = builderId;
        this.name = name;
        this.description = description;
        this.linkOnWebsite = linkOnWebsite;
        this.phoneNumber = phoneNumber;
        this.deliveryYear = deliveryYear;
        this.status = status;
        this.region = region;
    }

    /**
     * Getter for <code>public.residential_complex.id</code>.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.residential_complex.id</code>.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Getter for <code>public.residential_complex.city</code>.
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Setter for <code>public.residential_complex.city</code>.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter for <code>public.residential_complex.district</code>.
     */
    public String getDistrict() {
        return this.district;
    }

    /**
     * Setter for <code>public.residential_complex.district</code>.
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * Getter for <code>public.residential_complex.number_of_buildings</code>.
     */
    public Integer getNumberOfBuildings() {
        return this.numberOfBuildings;
    }

    /**
     * Setter for <code>public.residential_complex.number_of_buildings</code>.
     */
    public void setNumberOfBuildings(Integer numberOfBuildings) {
        this.numberOfBuildings = numberOfBuildings;
    }

    /**
     * Getter for
     * <code>public.residential_complex.number_of_ready_buildings</code>.
     */
    public Integer getNumberOfReadyBuildings() {
        return this.numberOfReadyBuildings;
    }

    /**
     * Setter for
     * <code>public.residential_complex.number_of_ready_buildings</code>.
     */
    public void setNumberOfReadyBuildings(Integer numberOfReadyBuildings) {
        this.numberOfReadyBuildings = numberOfReadyBuildings;
    }

    /**
     * Getter for <code>public.residential_complex.builder_id</code>.
     */
    public UUID getBuilderId() {
        return this.builderId;
    }

    /**
     * Setter for <code>public.residential_complex.builder_id</code>.
     */
    public void setBuilderId(UUID builderId) {
        this.builderId = builderId;
    }

    /**
     * Getter for <code>public.residential_complex.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>public.residential_complex.name</code>.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for <code>public.residential_complex.description</code>.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>public.residential_complex.description</code>.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for <code>public.residential_complex.link_on_website</code>.
     */
    public String getLinkOnWebsite() {
        return this.linkOnWebsite;
    }

    /**
     * Setter for <code>public.residential_complex.link_on_website</code>.
     */
    public void setLinkOnWebsite(String linkOnWebsite) {
        this.linkOnWebsite = linkOnWebsite;
    }

    /**
     * Getter for <code>public.residential_complex.phone_number</code>.
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Setter for <code>public.residential_complex.phone_number</code>.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Getter for <code>public.residential_complex.delivery_year</code>.
     */
    public LocalDate getDeliveryYear() {
        return this.deliveryYear;
    }

    /**
     * Setter for <code>public.residential_complex.delivery_year</code>.
     */
    public void setDeliveryYear(LocalDate deliveryYear) {
        this.deliveryYear = deliveryYear;
    }

    /**
     * Getter for <code>public.residential_complex.status</code>.
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Setter for <code>public.residential_complex.status</code>.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Getter for <code>public.residential_complex.region</code>.
     */
    public Integer getRegion() {
        return this.region;
    }

    /**
     * Setter for <code>public.residential_complex.region</code>.
     */
    public void setRegion(Integer region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final ResidentialComplexEntity other = (ResidentialComplexEntity) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.city == null) {
            if (other.city != null)
                return false;
        }
        else if (!this.city.equals(other.city))
            return false;
        if (this.district == null) {
            if (other.district != null)
                return false;
        }
        else if (!this.district.equals(other.district))
            return false;
        if (this.numberOfBuildings == null) {
            if (other.numberOfBuildings != null)
                return false;
        }
        else if (!this.numberOfBuildings.equals(other.numberOfBuildings))
            return false;
        if (this.numberOfReadyBuildings == null) {
            if (other.numberOfReadyBuildings != null)
                return false;
        }
        else if (!this.numberOfReadyBuildings.equals(other.numberOfReadyBuildings))
            return false;
        if (this.builderId == null) {
            if (other.builderId != null)
                return false;
        }
        else if (!this.builderId.equals(other.builderId))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        if (this.description == null) {
            if (other.description != null)
                return false;
        }
        else if (!this.description.equals(other.description))
            return false;
        if (this.linkOnWebsite == null) {
            if (other.linkOnWebsite != null)
                return false;
        }
        else if (!this.linkOnWebsite.equals(other.linkOnWebsite))
            return false;
        if (this.phoneNumber == null) {
            if (other.phoneNumber != null)
                return false;
        }
        else if (!this.phoneNumber.equals(other.phoneNumber))
            return false;
        if (this.deliveryYear == null) {
            if (other.deliveryYear != null)
                return false;
        }
        else if (!this.deliveryYear.equals(other.deliveryYear))
            return false;
        if (this.status == null) {
            if (other.status != null)
                return false;
        }
        else if (!this.status.equals(other.status))
            return false;
        if (this.region == null) {
            if (other.region != null)
                return false;
        }
        else if (!this.region.equals(other.region))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.city == null) ? 0 : this.city.hashCode());
        result = prime * result + ((this.district == null) ? 0 : this.district.hashCode());
        result = prime * result + ((this.numberOfBuildings == null) ? 0 : this.numberOfBuildings.hashCode());
        result = prime * result + ((this.numberOfReadyBuildings == null) ? 0 : this.numberOfReadyBuildings.hashCode());
        result = prime * result + ((this.builderId == null) ? 0 : this.builderId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.linkOnWebsite == null) ? 0 : this.linkOnWebsite.hashCode());
        result = prime * result + ((this.phoneNumber == null) ? 0 : this.phoneNumber.hashCode());
        result = prime * result + ((this.deliveryYear == null) ? 0 : this.deliveryYear.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.region == null) ? 0 : this.region.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ResidentialComplexEntity (");

        sb.append(id);
        sb.append(", ").append(city);
        sb.append(", ").append(district);
        sb.append(", ").append(numberOfBuildings);
        sb.append(", ").append(numberOfReadyBuildings);
        sb.append(", ").append(builderId);
        sb.append(", ").append(name);
        sb.append(", ").append(description);
        sb.append(", ").append(linkOnWebsite);
        sb.append(", ").append(phoneNumber);
        sb.append(", ").append(deliveryYear);
        sb.append(", ").append(status);
        sb.append(", ").append(region);

        sb.append(")");
        return sb.toString();
    }
}