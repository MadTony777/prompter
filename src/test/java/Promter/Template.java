package Promter;


import java.io.IOException;
import java.util.List;

import static Promter.Kibana.searchByCID;

public class Template extends Requests {
    public static List<String> testCase(String environment, String fileName) throws IOException {
        List<String> responce = request(environment, fileName);
        String CID= responce.get(1);
        String logs = searchByCID(environment, CID, fileName);
        responce.add(2, logs);
        return responce;
    }
}
