package es.saladillo.alejandrodiaz.projectdex.base;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.saladillo.alejandrodiaz.projectdex.data.remote.dto.pokemon.Type;

public class Converters {

    @TypeConverter
    public static List<Type> fromString(String value) {
        if (value == null) {
            return Collections.emptyList();
        }
        java.lang.reflect.Type listType = new TypeToken<List<Type>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(List<Type> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
