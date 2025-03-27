

import java.io.*;
import java.nio.file.*;
import java.security.*;
import org.json.JSONObject;

public class JsonMd5Hash {
    public static void main(String[] args) {
        try {
           
            String jsonString = new String(Files.readAllBytes(Paths.get("input.json")));
            JSONObject jsonObject = new JSONObject(jsonString);

            
            JSONObject student = jsonObject.getJSONObject("student");
            String firstName = student.getString("first_name").toLowerCase().replace(" ", "");
            String rollNumber = student.getString("roll_number").toLowerCase().replace(" ", "");

           
            String concatenatedString = firstName + rollNumber;

            
            String md5Hash = generateMD5Hash(concatenatedString);

           
            Files.write(Paths.get("output.txt"), md5Hash.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateMD5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 Algorithm not found.");
        }
    }
}
