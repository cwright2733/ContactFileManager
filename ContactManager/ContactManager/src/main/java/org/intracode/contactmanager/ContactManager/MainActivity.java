package org.intracode.contactmanager.ContactManager;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText name, phone, emailadress, postaladress;
    List<Contact> Contacts = new ArrayList<>();
    ListView contactListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.contactname);
        phone = (EditText) findViewById(R.id.phonenumber);
        emailadress = (EditText) findViewById(R.id.email);
        postaladress = (EditText) findViewById(R.id.adress);
        contactListView = (ListView) findViewById(R.id.listview);


        TabHost tabHost = (TabHost) findViewById(R.id.Host);
// activate host
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("creator");
        tabSpec.setContent(R.id.tabCreator);
        tabSpec.setIndicator("Creator");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("list");
        tabSpec.setContent(R.id.tabContactList);
        tabSpec.setIndicator("List");
        tabHost.addTab(tabSpec);



        final Button addBtn = (Button) findViewById(R.id.addcontact);

       addBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                addContact(name.getText().toString(), phone.getText().toString(), emailadress.getText().toString(), postaladress.getText().toString());
                populateList();
                Toast.makeText(getApplication(), name.getText().toString() + "has been a added to your Contacts! ", Toast.LENGTH_SHORT).show();
            }
            });

                name.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        addBtn.setEnabled(!name.getText().toString().trim().isEmpty());
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
            }
            private void populateList() {
        ArrayAdapter<Contact>adapter = new ContactListAdapter();
        contactListView.setAdapter(adapter);
            }

            private void addContact(String name, String phone, String email, String adress) {
                Contacts.add(new Contact(name, phone, email, adress));


            }

    private class ContactListAdapter extends ArrayAdapter<Contact> {
        public ContactListAdapter() {
            super(MainActivity.this, R.layout.listview_item, Contacts);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);

            Contact currentContact = Contacts.get(position);

            TextView name = (TextView) view.findViewById(R.id.contactName);
            name.setText(currentContact.get_Name());
            TextView phone = (TextView) view.findViewById(R.id.phoneNumber);
            phone.setText(currentContact.get_Phone());
            TextView email = (TextView) view.findViewById(R.id.emailAdress);
            email.setText(currentContact.get_Email());
            TextView adress = (TextView) view.findViewById(R.id.cAdress);
            adress.setText(currentContact.get_Adress());

            return view;

        }
    }
}
