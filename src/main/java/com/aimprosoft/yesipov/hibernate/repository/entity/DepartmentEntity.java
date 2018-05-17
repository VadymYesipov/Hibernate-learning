package com.aimprosoft.yesipov.hibernate.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "department", schema = "example", catalog = "example")
public class DepartmentEntity {
    private int id;
    private String originalName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "original_name", nullable = false, length = 255)
    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentEntity that = (DepartmentEntity) o;

        if (id != that.id) return false;
        if (originalName != null ? !originalName.equals(that.originalName) : that.originalName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (originalName != null ? originalName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "id=" + id +
                ", originalName='" + originalName + '\'' +
                '}';
    }
}
