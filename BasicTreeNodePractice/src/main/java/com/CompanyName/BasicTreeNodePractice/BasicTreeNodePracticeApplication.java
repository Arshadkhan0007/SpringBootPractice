package com.CompanyName.BasicTreeNodePractice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class BasicTreeNodePracticeApplication{

//    private final ObjectMapper mapper;
//
//    public BasicTreeNodePracticeApplication(ObjectMapper mapper) {
//        this.mapper = mapper;
//    }

    public static void main(String[] args) {
		SpringApplication.run(BasicTreeNodePracticeApplication.class, args);
	}

//    public void run(String... args) throws JsonProcessingException {
//        //Building TreeNode
//        //Create root object {}
//        ObjectNode root = mapper.createObjectNode();
//
//        //Add simple fields
//        root.put("id", 101);
//        root.put("name", "Conor Mcgregor");
//
//        //Add an array
//        ArrayNode skills = mapper.createArrayNode();
//        skills.add("Existing");
//        skills.add("Thinking");
//        skills.add("Moj Masti");
//        root.set("skills", skills);
//
//        //Add a nested object
//        ObjectNode address = mapper.createObjectNode();
//        address.put("city", "Hyderabad");
//        address.put("zip", 100001);
//        root.set("address", address);
//
//        String jsonString = mapper.writerWithDefaultPrettyPrinter()
//                .writeValueAsString(root);
//
//        System.out.println(jsonString);
//
//        //Reading TreeNode
//        //Accessing simple fields
//        int id = root.get("id").asInt();
//        String name = root.get("name").asText();
//
//        //Accessing array nodes
//        List<String> skillsList = new ArrayList<>();
//        JsonNode skillsNode = root.get("skills");
//        for(JsonNode skill : skillsNode){
//            skillsList.add(skill.asText());
//        }
//
//        //Accessing object nodes
//        String city = root.get("address").get("city").asText();
//        String zip = root.get("address").get("zip").asText();
//
//        System.out.println("Id : " + id
//                + "\nname : " + name
//                + "\nskills : " + skillsList
//                + "\naddress : " + city + ", " + zip);
//    }

}
