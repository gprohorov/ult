package pro.shag.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Student {
    @Id
    private String id;
    private String name;
    private Group group;
    private int mark;
    private boolean active;

    public Student(String name, Group group) {
        this.name = name;
        this.group = group;
    }

    public Student(String id, String name, Group group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    public Student() {
    }

    public Student(String name, Group group, int mark) {
        this.name = name;
        this.group = group;
        this.mark = mark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", group=" + group +
                ", mark=" + mark +
                '}';
    }
}

