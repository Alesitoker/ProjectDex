package es.saladillo.alejandrodiaz.projectdex.di;

import android.content.Context;

import es.saladillo.alejandrodiaz.projectdex.data.DataBaseRepository;
import es.saladillo.alejandrodiaz.projectdex.data.DataBaseRepositoryImpl;
import es.saladillo.alejandrodiaz.projectdex.data.Repository;
import es.saladillo.alejandrodiaz.projectdex.data.RepositoryImpl;
import es.saladillo.alejandrodiaz.projectdex.data.local.AppDatabase;
import es.saladillo.alejandrodiaz.projectdex.data.local.TeamDao;
import es.saladillo.alejandrodiaz.projectdex.data.remote.PokeApiImpl;

public class Injector {

    private Injector() {}

    public static DataBaseRepository provideDataBaseRepository(Context context) {
        return DataBaseRepositoryImpl.getInstance(provideTeamDataBDao(context));
    }

    private static TeamDao provideTeamDataBDao(Context context) {
        return AppDatabase.getInstance(context.getApplicationContext()).teamDao();
    }

    public static Repository provideRepository() {
        return RepositoryImpl.getInstance(providePokeApi());
    }

    private static PokeApiImpl providePokeApi() {
        return PokeApiImpl.getInstance();
    }
}
