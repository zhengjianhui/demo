package demo.domain.mongo;

import org.springframework.data.annotation.Id;


/**
 * 使用@Document注解指明一个领域对象将被持久化到MongoDB中。@Id注解identifies。
 */
public class AbstractDocument {
    @Id
    private Long id;

    /**
     * Returns the identifier of the document.
     * 
     * @return the id
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
            return false;
        }

        AbstractDocument that = (AbstractDocument) obj;

        return this.id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

}
