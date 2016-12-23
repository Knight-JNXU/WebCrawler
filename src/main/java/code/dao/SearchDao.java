package code.dao;

import code.model.FilmModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Knight_JXNU on 2016/12/22.
 */
@Repository
public class SearchDao extends BaseDao{
    public List<FilmModel> getFilms(String name){
        Set<String> names = redisTemplate.keys("*"+name+"*");
        List<FilmModel> list = new ArrayList<FilmModel>();
        for(String k : names){
            String u = (String) get(k);
            FilmModel temp = new FilmModel(k, u);
            list.add(temp);
        }
        return list;
    }
}
