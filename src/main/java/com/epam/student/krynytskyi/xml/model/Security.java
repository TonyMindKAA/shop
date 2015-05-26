package com.epam.student.krynytskyi.xml.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "security")
@XmlAccessorType(XmlAccessType.FIELD)
public class Security {
    @XmlElement(name = "role")
    private List<RoleXml> roleXmls;

    public List<RoleXml> getRoleXmls() {
        return roleXmls;
    }

    public Security setRoleXmls(List<RoleXml> roleXmls) {
        this.roleXmls = roleXmls;
        return this;
    }
}
