package es.saladillo.alejandrodiaz.projectdex.data.local.model;

public class Team {

    private long id;
    private String teamName;
    private PokemonTeam pokemon1;
    private PokemonTeam pokemon2;
    private PokemonTeam pokemon3;
    private PokemonTeam pokemon4;
    private PokemonTeam pokemon5;
    private PokemonTeam pokemon6;

    public Team(long id, String teamName) {
        this.id = id;
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public PokemonTeam getPokemon1() {
        return pokemon1;
    }

    public void setPokemon1(PokemonTeam pokemon1) {
        this.pokemon1 = pokemon1;
    }

    public PokemonTeam getPokemon2() {
        return pokemon2;
    }

    public void setPokemon2(PokemonTeam pokemon2) {
        this.pokemon2 = pokemon2;
    }

    public PokemonTeam getPokemon3() {
        return pokemon3;
    }

    public void setPokemon3(PokemonTeam pokemon3) {
        this.pokemon3 = pokemon3;
    }

    public PokemonTeam getPokemon4() {
        return pokemon4;
    }

    public void setPokemon4(PokemonTeam pokemon4) {
        this.pokemon4 = pokemon4;
    }

    public PokemonTeam getPokemon5() {
        return pokemon5;
    }

    public void setPokemon5(PokemonTeam pokemon5) {
        this.pokemon5 = pokemon5;
    }

    public PokemonTeam getPokemon6() {
        return pokemon6;
    }

    public void setPokemon6(PokemonTeam pokemon6) {
        this.pokemon6 = pokemon6;
    }
}
