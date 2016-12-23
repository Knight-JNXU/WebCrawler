package code.service;

import code.dao.SearchDao;
import code.model.FilmModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Knight_JXNU on 2016/12/22.
 */
@Service
public class SearchService extends BaseService {
    @Autowired
    SearchDao searchDao;
    public List<FilmModel> getFilms(String name){
        return searchDao.getFilms(name);
    }
}
