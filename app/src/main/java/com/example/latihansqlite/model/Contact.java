package com.example.latihansqlite.model;

public class Contact {
    private int _id;
    private String _name;
    private String _phone_number;

    public Contact() {
    }

    public Contact(int id, String name, String _phone_number) {
        this.set_id(id);
        this.set_name(name);
        this.set_phone_number(_phone_number);
    }

    public Contact(String name, String _phone_number) {
        this.set_name(name);
        this.set_phone_number(_phone_number);
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_phone_number() {
        return _phone_number;
    }

    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }
}