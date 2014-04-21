package esc.plugins.dal;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import esc.plugins.Contact;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex
 */
public class FakeDataAccessLayer implements IDataAccessLayer {
    @Override
    public List<Contact> searchContacts(String text, boolean onlyActive) {
        Type arrayType = new TypeToken<ArrayList<Contact>>(){}.getType();
        Gson gson = new Gson();

        return gson.fromJson("[{\"contactID\":1,\"name\":\"Alexander Grafl\",\"title\":\"Dr.\",\"firstName\":" +
                "\"Alexander\",\"lastName\":\"Grafl\",\"suffix\":\"Msc\",\"birthDate\":\"Mar 28, 2014 12:00:0" +
                "0 AM\",\"address\":\"Bergengasse 6/5/14 1220 Wien\",\"invoiceAddress\":\"Bergengasse 6/5/14 " +
                "1220 Wien\",\"shippingAddress\":\"Bergengasse 6/5/14 1220 Wien\",\"isActive\":false},{\"cont" +
                "actID\":2,\"name\":\"Grafl GmbH\",\"uid\":1,\"address\":\"Bergengasse 6/5/14 1220 Wien\",\"i" +
                "nvoiceAddress\":\"Bergengasse 6/5/14 1220 Wien\",\"shippingAddress\":\"Bergengasse 6/5/14 12" +
                "20 Wien\",\"isActive\":false}]", arrayType);

    }
}