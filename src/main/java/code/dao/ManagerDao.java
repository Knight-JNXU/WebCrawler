package code.dao;

import code.model.BlogModel;
import org.springframework.stereotype.Repository;

/**
 * Created by Knight_JXNU on 2016/12/28.
 */
@Repository
public class ManagerDao extends BaseDao {
    public void insertBlog(BlogModel blogModel){
        super.set(Prefix+blogModel.getTitle(), jsonUtils.obj2Str(blogModel));
    }
}
