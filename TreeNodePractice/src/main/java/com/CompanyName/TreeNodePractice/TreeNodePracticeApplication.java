package com.CompanyName.TreeNodePractice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TreeNodePracticeApplication implements CommandLineRunner {

    private final ObjectMapper mapper;

    public TreeNodePracticeApplication(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public static void main(String[] args) {
		SpringApplication.run(TreeNodePracticeApplication.class, args);
	}

    public void run(String... args){

        //Building Json
        //Creating root
        ObjectNode root = mapper.createObjectNode();

        //Adding simple fields
        root.put("Id", 1001);
        root.put("Name", "Alex");

        //Adding an array
        ArrayNode skills = mapper.createArrayNode();
        skills.add("Moj");
        skills.add("Masti");
        skills.add("Maze karna");
        root.set("Skills", skills);

        //Adding an object
        ObjectNode address = mapper.createObjectNode();
        address.put("City", "Hyderabad");
        address.put("Pincode", 100009);
        root.set("Address", address);

        try {
            String json = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(root);
            System.out.println(json);

            //Iterating through json
            JsonNode jsonNode = mapper.readTree(json);

//            System.out.println(jsonNode.get("Id"));
//            System.out.println(jsonNode.get("Skills"));
//            System.out.println(jsonNode.get("Address"));
//            System.out.println(jsonNode.get("Skills").get(2));
//            System.out.println(jsonNode.get("Address").get("City"));
//
//            System.out.println(jsonNode.path("City").asInt(0));
//
//            System.out.println("Id is Integer? " + jsonNode.get("Id").isInt());
//            System.out.println("Skills is an array? " + jsonNode.get("Skills").isArray());
//            System.out.println("Address is an object? " + jsonNode.get("Address").isObject());

//            Iterator<Map.Entry<String, JsonNode>> iterator = jsonNode.fields();
//            while(iterator.hasNext()){
//                Map.Entry<String, JsonNode> fields = iterator.next();
//                System.out.println(fields.getKey() + " : " + fields.getValue());
//            }
//
//            Iterator<String> iterator1 = jsonNode.fieldNames();
//            while(iterator1.hasNext()){
//                System.out.println(iterator1.next());
//            }
//
//            Iterator<JsonNode> iterator2 = jsonNode.get("Skills").elements();
//            while(iterator2.hasNext()){
//                System.out.println(iterator2.next());
//            }

            json = jsonNode.toPrettyString();
//            System.out.println(json);

//            JsonNode addressNodeCopy = jsonNode.get("Address").deepCopy();
//            Iterator<Map.Entry<String, JsonNode>> iterator = addressNodeCopy.fields();
//            while(iterator.hasNext()){
//                Map.Entry<String, JsonNode> map = iterator.next();
//                System.out.println(map.getKey() + " : " + map.getValue());
//            }

            Person p = new Person();
            p.Id = 1001;
            p.Name = "Alex";
            String[] arr = {"Moj", "Masti", "Maze karna"};
            p.Skills = arr;
            Address address1 = new Address();
            address1.City = "Hyderabad";
            address1.PinCode = 100009;
            p.Address = address1;

            System.out.println(jsonNode.equals(p));

        } catch (JsonProcessingException ex){
            System.out.println("An error has occurred while processing json");
        }

    }

}

