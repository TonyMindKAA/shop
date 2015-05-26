package com.epam.student.krynytskyi.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "role")
@XmlAccessorType(XmlAccessType.FIELD)
public class RoleXml {
    private String role;
    private List<String> urls;
    private String redirectTo;

    public String getRedirectTo() {
        return redirectTo;
    }

    public RoleXml setRedirectTo(String redirectTo) {
        this.redirectTo = redirectTo;
        return this;
    }

    public String getRole() {
        return role;
    }

    public RoleXml setRole(String role) {
        this.role = role;
        return this;
    }

    public List<String> getUrls() {
        return urls;
    }

    public RoleXml setUrls(List<String> urls) {
        this.urls = urls;
        return this;
    }
}
