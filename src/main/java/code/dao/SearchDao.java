package code.dao;

import code.model.BlogModel;
import code.model.ShRdModel;
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
        Set<String> names = redisTemplate.keys(BlogPrefix+"*"+title+"*");
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
        String result = (String) get(BlogSearchPrefix+title);
        if(result==null){
            super.set(BlogSearchPrefix+title, jsonUtils.obj2Str(new ShRdModel(title, 1)));
        }else{
            ShRdModel shRdModel = jsonUtils.str2Obj(result, ShRdModel.class);
            shRdModel.addNum();
            set(BlogSearchPrefix+title, jsonUtils.obj2Str(shRdModel));
        }
    }
}
