package com.epam.student.krynytskyi.entity;

import java.util.Date;

public class User {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String img;
    private String id;
    private Role role;
    private int isBan;
    private int errorEntry;
    private int timeBan;
    private Date dateBan;


    public int getIsBan() {
        return isBan;
    }

    public void setIsBan(int isBan) {
        this.isBan = isBan;
    }

    public int getErrorEntry() {
        return errorEntry;
    }

    public void setErrorEntry(int errorEntry) {
        this.errorEntry = errorEntry;
    }

    public int getTimeBan() {
        return timeBan;
    }

    public User setTimeBan(int timeBan) {
        this.timeBan = timeBan;
        return this;
    }

    public Date getDateBan() {
        return dateBan;
    }

    public void setDateBan(Date dateBan) {
        this.dateBan = dateBan;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String userId) {
        this.id = userId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", img='").append(img).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", role=").append(role);
        sb.append(", isBan=").append(isBan);
        sb.append(", errorEntry=").append(errorEntry);
        sb.append(", timeBan=").append(timeBan);
        sb.append(", dateBan=").append(dateBan);
        sb.append('}');
        return sb.toString();
    }
}
