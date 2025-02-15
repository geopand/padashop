package gr.padashop.models;

public class CCType {
    private Long id;
    private String name;

    public CCType(){}

    public CCType(Long id) {
        this.id = id;
    }

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
}
