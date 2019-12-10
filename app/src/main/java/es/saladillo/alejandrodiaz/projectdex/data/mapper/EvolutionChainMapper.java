package es.saladillo.alejandrodiaz.projectdex.data.mapper;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.data.local.model.EvoChain;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.evolution.Chain;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.evolution.EvolutionDetail;
import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.evolution.EvolvesTo;
import es.saladillo.alejandrodiaz.projectdex.utils.StringUtils;

public class EvolutionChainMapper {

    public static List<EvoChain> map(Chain chain) {
        List<EvoChain> evoChain = new ArrayList<>();
        String imgUrl = "http://www.pokestadium.com/assets/img/sprites/official-art/%s.png";
        String[] idUrl = chain.getSpecies().getUrl().split("/");
        String name = chain.getSpecies().getName();
        String imgUrlC = String.format(imgUrl, StringUtils.pokemonNameSprite(name));
        int id = Integer.parseInt(idUrl[idUrl.length - 1]);

        evoChain.add(new EvoChain(id, imgUrlC, name, new EvolutionDetail()));

        for(EvolvesTo evoTo: chain.getEvolvesTo()) {
            idUrl = evoTo.getSpecies().getUrl().split("/");
            id = Integer.parseInt(idUrl[idUrl.length - 1]);
            name = evoTo.getSpecies().getName();
            imgUrlC = String.format(imgUrl, StringUtils.pokemonNameSprite(name));

            evoChain.add(new EvoChain(id, imgUrlC, name, evoTo.getEvolutionDetails().get(0)));
            for(EvolvesTo evoTo2: evoTo.getEvolvesTo()) {
                idUrl = evoTo2.getSpecies().getUrl().split("/");
                id = Integer.parseInt(idUrl[idUrl.length - 1]);
                name = evoTo2.getSpecies().getName();
                imgUrlC = String.format(imgUrl, StringUtils.pokemonNameSprite(name));

                evoChain.add(new EvoChain(id, imgUrlC, name, evoTo2.getEvolutionDetails().get(0)));
            }
        }

        return evoChain;
    }

}
