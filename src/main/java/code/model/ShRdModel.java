package code.model;

/**
 * Created by Knight_JXNU on 2017/1/3.
 * 这个是用来保存用户搜索记录的model
 */
public class ShRdModel extends BaseModel{
    private String shTitle;
    private int num=0;

    public ShRdModel(String title, int num) {
        this.shTitle = title;
        this.num = num;
    }

    public String getShTitle() {
        return shTitle;
    }

    public void setShTitle(String shTitle) {
        this.shTitle = shTitle;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void addNum(){
        num++;
    }
}
