package ru.netology;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class App {
    
    public static void main( String[] args ) {

        readJson();
        jsonToList(readJson());

    }

    public static String readJson(){
        try (BufferedReader br = new BufferedReader(new FileReader("data.json"))){

            String data;
            while ((data = br.readLine()) != null){
                return data;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void jsonToList(String jsonData){
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
        System.out.println(employees);
    }

}
