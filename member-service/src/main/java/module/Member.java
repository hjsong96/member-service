package module;

import lombok.Data;

@Data
public class Member {
    private Long id;
    private String name;
    private Integer age;

    public Member() {}

    public Member(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Member(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
