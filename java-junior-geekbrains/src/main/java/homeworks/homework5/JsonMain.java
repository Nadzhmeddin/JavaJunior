package homeworks.homework5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonMain {
    public static void main(String[] args) throws JsonProcessingException {

        ListResponse listResponse = new ListResponse();

        User user1 = new User();
        user1.setLogin("anonymous");

        User user2 = new User();
        user2.setLogin("nagibator");

        User user3 = new User();
        user3.setLogin("admin");

        listResponse.setUsers(List.of(user1, user2, user3));


//        ListRequest listRequest = new ListRequest();
//        listRequest.setType("users");

        String s = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(listResponse);
        System.out.println(s);
    }
}
