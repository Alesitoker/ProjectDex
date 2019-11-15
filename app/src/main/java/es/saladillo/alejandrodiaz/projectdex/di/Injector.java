package es.saladillo.alejandrodiaz.projectdex.di;

import es.saladillo.alejandrodiaz.projectdex.data.Repository;
import es.saladillo.alejandrodiaz.projectdex.data.RepositoryImpl;
import es.saladillo.alejandrodiaz.projectdex.data.remote.PokeApiImpl;

public class Injector {

    private Injector() {}

    public static Repository provideRepository() {
        return RepositoryImpl.getInstance(providePokeApi());
    }

    private static PokeApiImpl providePokeApi() {
        return PokeApiImpl.getInstance();
    }
}
