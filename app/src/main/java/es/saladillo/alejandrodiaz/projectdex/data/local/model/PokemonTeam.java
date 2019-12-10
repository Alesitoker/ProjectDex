package es.saladillo.alejandrodiaz.projectdex.data.local.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.HeldItem;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.Type;

class PokemonTeam {

    private int id;
    private int teamPosition;
    private String name;
    private String nickName;
    private String category;
    private List<Type> types = null;
    private String imgUrl;
    private String[] moveSet = new String[4];
    private byte level = 1;
    private String nature;
    private HeldItem heldItem;

    public PokemonTeam(int id, String name, String category, List<Type> types, String imgUrl) {
        this.id = id;
        this.name = name;
        this.nickName = name;
        this.category = category;
        this.types = types;
        this.imgUrl = imgUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String[] getMoveSet() {
        return moveSet;
    }

    public void setMoveSet(String[] moveSet) {
        this.moveSet = moveSet;
    }

    public byte getLevel() {
        return level;
    }

    public void setLevel(byte level) {
        this.level = level;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public HeldItem getHeldItem() {
        return heldItem;
    }

    public void setHeldItem(HeldItem heldItem) {
        this.heldItem = heldItem;
    }

    public int getTeamPosition() {
        return teamPosition;
    }

    public void setTeamPosition(int teamPosition) {
        this.teamPosition = teamPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PokemonTeam)) return false;
        PokemonTeam that = (PokemonTeam) o;
        return id == that.id &&
                teamPosition == that.teamPosition &&
                level == that.level &&
                Objects.equals(name, that.name) &&
                Objects.equals(nickName, that.nickName) &&
                Objects.equals(category, that.category) &&
                Objects.equals(types, that.types) &&
                Objects.equals(imgUrl, that.imgUrl) &&
                Arrays.equals(moveSet, that.moveSet) &&
                Objects.equals(nature, that.nature) &&
                Objects.equals(heldItem, that.heldItem);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, teamPosition, name, nickName, category, types, imgUrl, level, nature, heldItem);
        result = 31 * result + Arrays.hashCode(moveSet);
        return result;
    }
}
