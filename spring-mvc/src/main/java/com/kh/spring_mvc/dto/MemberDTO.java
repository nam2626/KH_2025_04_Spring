package com.kh.spring_mvc.dto;

public class MemberDTO {
    private String id;
    private String password;
    private String name;
    private int age;

    public MemberDTO(String id, String password, String name, int age) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public MemberDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
