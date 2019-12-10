
package es.saladillo.alejandrodiaz.projectdex.data.remote.dto.evolution;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;


public class EvolutionDetail {

    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("held_item")
    @Expose
    private HeldItem heldItem;
    @SerializedName("item")
    @Expose
    private Item item;
    @SerializedName("known_move")
    @Expose
    private KnownMoveType knownMove;
    @SerializedName("known_move_type")
    @Expose
    private KnownMoveType knownMoveType;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("min_affection")
    @Expose
    private Integer minAffection;
    @SerializedName("min_beauty")
    @Expose
    private Integer minBeauty;
    @SerializedName("min_happiness")
    @Expose
    private Integer minHappiness;
    @SerializedName("min_level")
    @Expose
    private Integer minLevel;
    @SerializedName("needs_overworld_rain")
    @Expose
    private Boolean needsOverworldRain;
    @SerializedName("party_species")
    @Expose
    private PartyType partySpecies;
    @SerializedName("party_type")
    @Expose
    private PartyType partyType;
    @SerializedName("relative_physical_stats")
    @Expose
    private Object relativePhysicalStats;
    @SerializedName("time_of_day")
    @Expose
    private String timeOfDay;
    @SerializedName("trade_species")
    @Expose
    private Object tradeSpecies;
    @SerializedName("trigger")
    @Expose
    private Trigger trigger;
    @SerializedName("turn_upside_down")
    @Expose
    private Boolean turnUpsideDown = false;

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public HeldItem getHeldItem() {
        return heldItem;
    }

    public void setHeldItem(HeldItem heldItem) {
        this.heldItem = heldItem;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public KnownMoveType getKnownMove() {
        return knownMove;
    }

    public void setKnownMove(KnownMoveType knownMove) {
        this.knownMove = knownMove;
    }

    public KnownMoveType getKnownMoveType() {
        return knownMoveType;
    }

    public void setKnownMoveType(KnownMoveType knownMoveType) {
        this.knownMoveType = knownMoveType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getMinAffection() {
        return minAffection;
    }

    public void setMinAffection(Integer minAffection) {
        this.minAffection = minAffection;
    }

    public Integer getMinBeauty() {
        return minBeauty;
    }

    public void setMinBeauty(Integer minBeauty) {
        this.minBeauty = minBeauty;
    }

    public Integer getMinHappiness() {
        return minHappiness;
    }

    public void setMinHappiness(Integer minHappiness) {
        this.minHappiness = minHappiness;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public Boolean getNeedsOverworldRain() {
        return needsOverworldRain;
    }

    public void setNeedsOverworldRain(Boolean needsOverworldRain) {
        this.needsOverworldRain = needsOverworldRain;
    }

    public PartyType getPartySpecies() {
        return partySpecies;
    }

    public void setPartySpecies(PartyType partySpecies) {
        this.partySpecies = partySpecies;
    }

    public PartyType getPartyType() {
        return partyType;
    }

    public void setPartyType(PartyType partyType) {
        this.partyType = partyType;
    }

    public Object getRelativePhysicalStats() {
        return relativePhysicalStats;
    }

    public void setRelativePhysicalStats(Object relativePhysicalStats) {
        this.relativePhysicalStats = relativePhysicalStats;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public Object getTradeSpecies() {
        return tradeSpecies;
    }

    public void setTradeSpecies(Object tradeSpecies) {
        this.tradeSpecies = tradeSpecies;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public Boolean getTurnUpsideDown() {
        return turnUpsideDown;
    }

    public void setTurnUpsideDown(Boolean turnUpsideDown) {
        this.turnUpsideDown = turnUpsideDown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EvolutionDetail)) return false;
        EvolutionDetail that = (EvolutionDetail) o;
        return Objects.equals(gender, that.gender) &&
                Objects.equals(heldItem, that.heldItem) &&
                Objects.equals(item, that.item) &&
                Objects.equals(knownMove, that.knownMove) &&
                Objects.equals(knownMoveType, that.knownMoveType) &&
                Objects.equals(location, that.location) &&
                Objects.equals(minAffection, that.minAffection) &&
                Objects.equals(minBeauty, that.minBeauty) &&
                Objects.equals(minHappiness, that.minHappiness) &&
                Objects.equals(minLevel, that.minLevel) &&
                Objects.equals(needsOverworldRain, that.needsOverworldRain) &&
                Objects.equals(partySpecies, that.partySpecies) &&
                Objects.equals(partyType, that.partyType) &&
                Objects.equals(relativePhysicalStats, that.relativePhysicalStats) &&
                Objects.equals(timeOfDay, that.timeOfDay) &&
                Objects.equals(tradeSpecies, that.tradeSpecies) &&
                Objects.equals(trigger, that.trigger) &&
                Objects.equals(turnUpsideDown, that.turnUpsideDown);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, heldItem, item, knownMove, knownMoveType, location, minAffection, minBeauty, minHappiness, minLevel, needsOverworldRain, partySpecies, partyType, relativePhysicalStats, timeOfDay, tradeSpecies, trigger, turnUpsideDown);
    }
}
