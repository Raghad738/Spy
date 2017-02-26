package raghad12_1_2016.spy.data;

/**
 * Created by user on 11/27/2016.
 */
public class Mytt
{
    private String id;
    private String title;
    private String phone;

    public Mytt(String id, String title, String phone) {
        this.id = id;
        this.title = title;
        this.phone = phone;
    }
    public Mytt(){}




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Mytt{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
