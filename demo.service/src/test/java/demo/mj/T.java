package demo.mj;

/**
 * Created by zhengjianhui on 16/11/9.
 */
public class T {

    Long id;

    String name ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "T{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
