package Promter;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Requests extends BaseClass {

    static List<String> request(String environment, String fileName) throws IOException {
        String requestBody = new String(Files.readAllBytes(Paths.get(paths + fileName)), StandardCharsets.UTF_8);

        String cid = String.valueOf(UUID.randomUUID());
        List<String> result = new ArrayList<>();
        Response response = RestAssured.given().log().all()
                .header("Content-Type", "application/json")
//                .auth().basic(username, password)
                .headers("X-VSK-CorrelationId", cid)
                .body(requestBody)
                .post(getURL(environment, fileName))
                .then()
//                .statusCode(200)
                .extract()
                .response();
        String serviceResponse = response.body().asString();
        result.add(0, serviceResponse);
        assertREST(fileName, serviceResponse);
        System.out.println("Responce is: " + serviceResponse);
        result.add(1, cid);
        return result;
    }




}
