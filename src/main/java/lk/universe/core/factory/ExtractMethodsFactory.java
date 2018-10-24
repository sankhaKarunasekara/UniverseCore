package lk.universe.core.factory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExtractMethodsFactory {

    public  ExtractMethodsFactory(){
    }

    public Map<Date,Double> getMapfromDataString(String s) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();
        Double price;

        Map<Date,Double> returnMap = new HashMap<Date,Double>();
        String[] items = s.split(" ");
        int size = items.length;

        if(size%2==0) {
            for (int i=0; i<size; i=i+2) {
                date = dateFormat.parse(items[i]);
                price = Double.parseDouble(items[i+1]);
                returnMap.put(date,price);
            }
        }else{
            throw new IllegalArgumentException("number of items in the array is not even: "+size);
        }
        return returnMap;
    }

    public Map<String,String>getStringMapfromDataString(String s) throws ParseException {

        Map<String,String> returnMap = new HashMap<String,String>();
        String[] items = s.split(" ");
        int size = items.length;
        String key = "";
        String value = "";

        if(size%2==0) {
            for (int i=0; i<size; i=i+2) {
                key = items[i];
                value = items[i+1];
                returnMap.put(key,value);
            }
        }else{
            throw new IllegalArgumentException("number of items in the array is not even: "+size);
        }
        return returnMap;
    }
}
