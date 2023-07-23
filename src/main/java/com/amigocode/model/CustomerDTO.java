package com.amigocode.model;

public class CustomerDTO {
    private Integer id;
    private String name;
    private String email;
    private Integer age;

    public CustomerDTO(Integer id,String name,String email,Integer age ){
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    
    }
    public CustomerDTO() {
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", age='" + getAge() + "'" +
            "}";
    }
}
