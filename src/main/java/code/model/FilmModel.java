package code.model;

/**
 * Created by Knight_JXNU on 2016/11/25.
 */
public class FilmModel extends BaseModel{
    private String name;
    private String url;

    public FilmModel() {
    }

    public FilmModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
