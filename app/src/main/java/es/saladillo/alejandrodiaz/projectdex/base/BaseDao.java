package es.saladillo.alejandrodiaz.projectdex.base;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

public interface BaseDao<T> {

    @Insert
    long insert(T model);

    @Update
    int update(T... model);

    @Delete
    int delete(T... model);

}