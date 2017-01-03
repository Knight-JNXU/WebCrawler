package code.dao;

import code.model.BlogModel;
import code.model.ShRdModel;
import code.model.StaticModel;
import code.utils.CsdnBlogCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        emailDao.sendHtmlEmail("CSDN 博客录入结束!<br>"+
                "录入数据 <b>"+ CsdnBlogCrawler.inputTimes+"</b> 条!<br>"+
                "耗时 <b>"+(StaticModel.endTime-StaticModel.startTime)+"</b> 毫秒");
    }

    public List<ShRdModel> getRecords(){
        List<ShRdModel> list = new ArrayList<ShRdModel>();
        Set<String> names = redisTemplate.keys(BlogSearchPrefix+"*");
        for(String k : names){
            String rulst = (String) get(k);
            ShRdModel temp = jsonUtils.str2Obj(rulst, ShRdModel.class);
            list.add(temp);
        }
        return list;
    }
}
