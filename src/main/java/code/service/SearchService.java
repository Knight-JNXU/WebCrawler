package code.service;

import code.Model.BlogModel;
import code.dao.SearchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Knight_JXNU on 2016/12/29.
 */
@Service
public class SearchService extends BaseService {
    @Autowired
    SearchDao searchDao;
    public List<BlogModel> getBlogs(String title){
        return searchDao.getBlogs(title);
    }
}
