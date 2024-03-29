package es.saladillo.alejandrodiaz.projectdex.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import es.saladillo.alejandrodiaz.projectdex.base.Converters;
import es.saladillo.alejandrodiaz.projectdex.data.local.model.TeamDataB;

@Database(entities = {TeamDataB.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "projecdex-database";

    public abstract TeamDao teamDao();

    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance =
                            Room.databaseBuilder(
                                    context.getApplicationContext(), AppDatabase.class,
                                    DATABASE_NAME).build();
                }
            }
        }
        return instance;
    }
}
