package code.dao;

import code.model.BlogModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Knight_JXNU on 2016/12/29.
 */
@Repository
public class SearchDao extends BaseDao {
    /**
     * 获取博客
     * @param title
     * @return
     */
    public List<BlogModel> getBlogs(String title){
        insertShRd(title);
        Set<String> names = redisTemplate.keys("blog:*"+title+"*");
        List<BlogModel> list = new ArrayList<BlogModel>();
        for(String k : names){
            String rulst = (String) get(k);
            BlogModel temp = jsonUtils.str2Obj(rulst, BlogModel.class);
            list.add(temp);
        }
        return list;
    }

    /**
     * 插入搜索记录
     * @param title
     */
    private void insertShRd(String title){
        String num = (String) get(BlogSearchPrefix+title);
        if(num==null){
            set(BlogSearchPrefix+title, "1");
        }else{
            set(BlogSearchPrefix+title, (Integer.valueOf(num)+1+""));
        }
    }
}
