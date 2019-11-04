
package es.saladillo.alejandrodiaz.projectdex.data.remote.dto.types;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypesResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("damage_relations")
    @Expose
    private DamageRelations damageRelations;
    @SerializedName("game_indices")
    @Expose
    private List<GameIndex> gameIndices = null;
    @SerializedName("generation")
    @Expose
    private Generation_ generation;
    @SerializedName("move_damage_class")
    @Expose
    private MoveDamageClass moveDamageClass;
    @SerializedName("names")
    @Expose
    private List<Name> names = null;
    @SerializedName("pokemon")
    @Expose
    private List<Pokemon> pokemon = null;
    @SerializedName("moves")
    @Expose
    private List<Move> moves = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DamageRelations getDamageRelations() {
        return damageRelations;
    }

    public void setDamageRelations(DamageRelations damageRelations) {
        this.damageRelations = damageRelations;
    }

    public List<GameIndex> getGameIndices() {
        return gameIndices;
    }

    public void setGameIndices(List<GameIndex> gameIndices) {
        this.gameIndices = gameIndices;
    }

    public Generation_ getGeneration() {
        return generation;
    }

    public void setGeneration(Generation_ generation) {
        this.generation = generation;
    }

    public MoveDamageClass getMoveDamageClass() {
        return moveDamageClass;
    }

    public void setMoveDamageClass(MoveDamageClass moveDamageClass) {
        this.moveDamageClass = moveDamageClass;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

}
