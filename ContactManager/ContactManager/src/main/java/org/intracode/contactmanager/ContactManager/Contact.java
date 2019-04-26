package org.intracode.contactmanager.ContactManager;

/**
 * Created by cwright70 on 4/20/18.
 */

public class Contact {

    private String _name, _phone, _email, _adress;

    public Contact (String name, String phone, String email , String adress) {

        _name=name;
        _phone=phone;
        _email = email;
        _adress = adress;


    }

    public String get_Name () {
return _name;

    }

    public  String get_Phone () {


        return _phone;
    }

    public String get_Email () {
        return _email;
    }

    public String get_Adress () {
        return _adress;
    }
}
