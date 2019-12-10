package es.saladillo.alejandrodiaz.projectdex.data.local.model;

import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.evolution.EvolutionDetail;

public class EvoChain {

    private int id;
    private String imgUrl;
    private String name;
    private EvolutionDetail evolutionDetail;

    public EvoChain(int id, String imgUrl, String name, EvolutionDetail evolutionDetail) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.name = name;
        this.evolutionDetail = evolutionDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EvolutionDetail getEvolutionDetail() {
        return evolutionDetail;
    }

    public void setEvolutionDetail(EvolutionDetail evolutionDetail) {
        this.evolutionDetail = evolutionDetail;
    }
}
