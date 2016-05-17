package com.protobuftest.protobuf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created: 2016/5/12.
 * Author: Qiannan Lu
 */
public class Person implements Serializable {
    private static final long serialVersionUID = -9037361065151578803L;

    private String name;
    private int id;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private List<Phone> phoneList = new ArrayList<>();

    public void addPhone(Phone phone) {
        phoneList.add(phone);
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }

    enum PhoneType {
        MOBILE(0),
        HOME(1),
        WORK(2);

        private int value;

        PhoneType(int i) {
            this.value = i;
        }

    }

    static class Phone implements Serializable {
        private static final long serialVersionUID = -7287327470957843683L;
        PhoneType type;
        String num;

        public Phone(String num, PhoneType type) {
            this.type = type;
            this.num = num;
        }

        public PhoneType getType() {
            return type;
        }

        public void setType(PhoneType type) {
            this.type = type;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}

class Country {
    private String name;
    private String code;
    private int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}