package ru.netology;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    
    public static void main( String[] args ) {

//        List<Employee> list = jsonToList(readJson());
//        System.out.println(list);
        List<Employee> employees = jsonTList(readJson());
        System.out.println(employees);

    }

    public static String readJson(){
        try (BufferedReader br = new BufferedReader(new FileReader("data.json"))){

            String data;
            while ((data = br.readLine()) != null)return data;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Employee> jsonToList(String jsonData){
        List<Employee> employees = new ArrayList<>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(jsonData).getAsJsonArray();
        for (JsonElement jsonElement: jsonArray){
            JsonObject jsonObject = gson.fromJson(jsonElement,JsonObject.class);
            employees.add(new Employee(jsonObject.get("id").getAsLong(),
                    jsonObject.get("firstName").getAsString(),
                    jsonObject.get("lastName").getAsString(),
                    jsonObject.get("country").getAsString(),
                    jsonObject.get("age").getAsInt()));
        }
        return employees;
    }

    public static List<Employee> jsonTList(String jsonData){
        Type itemsListType = new TypeToken<List<Employee>>() {}.getType();
        GsonBuilder gsonBuilder= new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            return Arrays.asList(gson.fromJson(jsonData, Employee.class));
        }catch (Exception e){
        }
        try {
            return gson.fromJson(jsonData, itemsListType);
        }catch (Exception e){
        }
        return null;
    }

}
