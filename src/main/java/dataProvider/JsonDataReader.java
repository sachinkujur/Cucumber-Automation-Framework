package dataProvider;

import com.google.gson.*;
import managers.FileReaderManager;
import testDataTypes.Customer;

import java.io.*;
import java.util.*;

public class JsonDataReader {
    private final String customerFilepath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath();
    private List<Customer> customerList;

    public JsonDataReader() {
        customerList = getCustomerData();
    }

    private List<Customer> getCustomerData() {
        Gson gson = new Gson();
        BufferedReader bufferReader = null;
        try {
            bufferReader = new BufferedReader(new FileReader(customerFilepath));
            Customer[] customers = gson.fromJson(bufferReader, Customer[].class);
            return Arrays.asList(customers);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Json file not found at path : " + customerFilepath);
        } finally {
            try {
                if (bufferReader != null) bufferReader.close();
            } catch (IOException ignore) {
            }
        }
    }

    public final Customer getCustomerByName(String customerName) {
        return customerList.stream().filter(x -> x.firstName.equalsIgnoreCase(customerName)).findAny().get();
    }

//    public final Customer getCustomerByName(String customerName) {
//        for (Customer customer : customerList) {
//            if (customer.firstName.equalsIgnoreCase(customerName)) return customer;
//        }
//        return null;
//    }


}
