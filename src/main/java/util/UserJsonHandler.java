package util;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class UserJsonHandler {

    private final ClassLoader classLoader = this.getClass().getClassLoader();

    private String getUsers() {
        try {
            final File file = new File(Objects.requireNonNull(classLoader.getResource("users.json")).getFile());
            final StringBuilder stringBuilder = new StringBuilder();
            final BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            br.close();
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String usersWithReversedNames() {
        // create object mapper
        ObjectMapper mapper = new ObjectMapper();
        // convert to json object
        try {
            JsonNode jsonNode = mapper.readTree(this.getUsers());
            jsonNode.forEach(user -> {
                String firstName = user.get("firstname").textValue();
                ((ObjectNode) user).put("firstname", this.reverseString(firstName));
                if(this.isPalindrome(firstName)) {
                    ((ObjectNode) user).put("palindrome", true); 
                }
            });
            return jsonNode.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String reverseString(String string) {
        // Revering the string using Built in reverse method of String Builder.
        String reversedString = new StringBuilder(string).reverse().toString().toLowerCase();
        return reversedString.substring(0, 1).toUpperCase() + reversedString.substring(1);
    }

    public boolean isPalindrome(String string) {
        // Check if palindrome and return status
        if (string.toLowerCase().equals(this.reverseString(string).toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    public String padNumberWithZeroes(int number) {
        // padding with zeros
        return String.format("%05d", number);
    }

    public int findNthLargestNumber(List<Integer> numbers, int nthLargestNumber) {
        Collections.sort(numbers);
        return numbers.get(numbers.size() - nthLargestNumber);
    }
}
