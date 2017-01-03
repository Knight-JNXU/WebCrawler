package code.dao;

import code.model.BlogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Knight_JXNU on 2016/12/28.
 */
@Repository
public class ManagerDao extends BaseDao {
    @Autowired
    private EmailDao emailDao;

    /**
     * 单条数据插入
     * @param blogModel
     */
    public void insertBlog(BlogModel blogModel){
        super.set(BlogPrefix +blogModel.getTitle(), jsonUtils.obj2Str(blogModel));
    }

    /**
     * 多条数据插入
     * @param kes
     * @param values
     */
    public void insertBlogs(List<String> kes, List<String> values){
        super.setArray(BlogPrefix, kes, values);
    }

    public void sendEmail(){
        emailDao.sendHtmlEmail("CSDN 博客录入结束!");
    }
}
