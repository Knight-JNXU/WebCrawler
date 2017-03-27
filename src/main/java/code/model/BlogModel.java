package code.model;

/**
 * Created by Knight_JXNU on 2016/12/28.
 * 这个是博客的model
 */
public class BlogModel extends BaseModel {
    private String title;
    private String url;
    private String author;

    public BlogModel(){

    }

    public BlogModel(String title, String url, String author) {
        this.title = title;
        this.url = url;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
