package es.saladillo.alejandrodiaz.projectdex.data.local.model;

import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.HeldItem;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.Type;

class PokemonTeam {

    private int id;
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
}
