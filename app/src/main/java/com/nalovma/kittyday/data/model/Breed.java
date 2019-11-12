package com.nalovma.kittyday.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Breed  {

    @SerializedName("adaptability")
    @Expose
    private Integer adaptability;
    @SerializedName("affection_level")
    @Expose
    private Integer affectionLevel;
    @SerializedName("alt_names")
    @Expose
    private String altNames;
    @SerializedName("cfa_url")
    @Expose
    private String cfaUrl;
    @SerializedName("child_friendly")
    @Expose
    private Integer childFriendly;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("country_codes")
    @Expose
    private String countryCodes;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("dog_friendly")
    @Expose
    private Integer dogFriendly;
    @SerializedName("energy_level")
    @Expose
    private Integer energyLevel;
    @SerializedName("experimental")
    @Expose
    private Integer experimental;
    @SerializedName("grooming")
    @Expose
    private Integer grooming;
    @SerializedName("hairless")
    @Expose
    private Integer hairless;
    @SerializedName("health_issues")
    @Expose
    private Integer healthIssues;
    @SerializedName("hypoallergenic")
    @Expose
    private Integer hypoallergenic;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("indoor")
    @Expose
    private Integer indoor;
    @SerializedName("intelligence")
    @Expose
    private Integer intelligence;
    @SerializedName("lap")
    @Expose
    private Integer lap;
    @SerializedName("life_span")
    @Expose
    private String lifeSpan;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("natural")
    @Expose
    private Integer natural;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("rare")
    @Expose
    private Integer rare;
    @SerializedName("rex")
    @Expose
    private Integer rex;
    @SerializedName("shedding_level")
    @Expose
    private Integer sheddingLevel;
    @SerializedName("short_legs")
    @Expose
    private Integer shortLegs;
    @SerializedName("social_needs")
    @Expose
    private Integer socialNeeds;
    @SerializedName("stranger_friendly")
    @Expose
    private Integer strangerFriendly;
    @SerializedName("suppressed_tail")
    @Expose
    private Integer suppressedTail;
    @SerializedName("temperament")
    @Expose
    private String temperament;
    @SerializedName("vcahospitals_url")
    @Expose
    private String vcahospitalsUrl;
    @SerializedName("vetstreet_url")
    @Expose
    private String vetstreetUrl;
    @SerializedName("vocalisation")
    @Expose
    private Integer vocalisation;
    @SerializedName("weight")
    @Expose
    private Weight weight;
    @SerializedName("wikipedia_url")
    @Expose
    private String wikipediaUrl;
    @SerializedName("bidability")
    @Expose
    private Integer bidability;
    @SerializedName("cat_friendly")
    @Expose
    private Integer catFriendly;

    public Integer getAdaptability() {
        return adaptability;
    }

    public Integer getAffectionLevel() {
        return affectionLevel;
    }

    public String getAltNames() {
        return altNames;
    }

    public String getCfaUrl() {
        return cfaUrl;
    }

    public Integer getChildFriendly() {
        return childFriendly;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryCodes() {
        return countryCodes;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDogFriendly() {
        return dogFriendly;
    }

    public Integer getEnergyLevel() {
        return energyLevel;
    }

    public Integer getExperimental() {
        return experimental;
    }

    public Integer getGrooming() {
        return grooming;
    }

    public Integer getHairless() {
        return hairless;
    }

    public Integer getHealthIssues() {
        return healthIssues;
    }

    public Integer getHypoallergenic() {
        return hypoallergenic;
    }

    public String getId() {
        return id;
    }

    public Integer getIndoor() {
        return indoor;
    }

    public Integer getIntelligence() {
        return intelligence;
    }

    public Integer getLap() {
        return lap;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public String getName() {
        return name;
    }

    public Integer getNatural() {
        return natural;
    }

    public String getOrigin() {
        return origin;
    }

    public Integer getRare() {
        return rare;
    }

    public Integer getRex() {
        return rex;
    }

    public Integer getSheddingLevel() {
        return sheddingLevel;
    }

    public Integer getShortLegs() {
        return shortLegs;
    }

    public Integer getSocialNeeds() {
        return socialNeeds;
    }

    public Integer getStrangerFriendly() {
        return strangerFriendly;
    }

    public Integer getSuppressedTail() {
        return suppressedTail;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getVcahospitalsUrl() {
        return vcahospitalsUrl;
    }

    public String getVetstreetUrl() {
        return vetstreetUrl;
    }

    public Integer getVocalisation() {
        return vocalisation;
    }

    public Weight getWeight() {
        return weight;
    }

    public String getWikipediaUrl() {
        return wikipediaUrl;
    }

    public Integer getBidability() {
        return bidability;
    }

    public Integer getCatFriendly() {
        return catFriendly;
    }
}