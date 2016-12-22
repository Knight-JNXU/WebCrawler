package code.dao;

import code.model.FilmModel;
import org.springframework.stereotype.Repository;

/**
 * Created by Knight_JXNU on 2016/12/19.
 */
public class ManagerDao extends BaseDao {
    public void insertFilm(FilmModel filmModel){
        super.set(filmModel.getName(), filmModel.getUrl());
    }
}
