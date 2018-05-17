package com.aimprosoft.yesipov.hibernate.dao;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ContactHobbyDetailEntityPK implements Serializable {
    private int contactId;
    private String hobbyId;

    @Column(name = "contact_id", nullable = false)
    @Id
    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    @Column(name = "hobby_id", nullable = false, insertable = false, updatable = false, length = 20)
    @Id
    public String getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(String hobbyId) {
        this.hobbyId = hobbyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactHobbyDetailEntityPK that = (ContactHobbyDetailEntityPK) o;

        if (contactId != that.contactId) return false;
        if (hobbyId != null ? !hobbyId.equals(that.hobbyId) : that.hobbyId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contactId;
        result = 31 * result + (hobbyId != null ? hobbyId.hashCode() : 0);
        return result;
    }
}
