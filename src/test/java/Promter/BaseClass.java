package Promter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class BaseClass {
    private String arg = System.getProperty("arg", "stage");
    public String environment = arg;

    private static String testPromterUrl = "http://esb-test01.vsk.ru:8181/cxf/rest/api/v1/prompter/AskThePrompter";
    private static String stagePromterUrl = "http://esb-stage.vsk.ru:8501/cxf/rest/api/v1/prompter/AskThePrompter";
    private static String testPromterV2Url = "http://esb-test01.vsk.ru:8181/cxf/rest/api/v1/prompter/AskThePrompterV2";
    private static String stagePromterV2Url = "http://esb-stage.vsk.ru:8501/cxf/rest/api/v1/prompter/AskThePrompterV2";
    private static String testOisuuUrl = "http://oisuu-app-test.vsk.ru/oisuu/ws/Prompter";
    private static String stageOisuuUrl = "http://oisuu-app-stage.vsk.ru/oisuu/ws/Prompter";
    public static final String paths = "src/test/java/promter/Examples/";
//    public static final String username = "WebUser";
//    public static final String password = "WebUser";
    public static Logger log = LoggerFactory.getLogger(UnitTests.class);



    static String getURL(String environment, String fileName) {
        String url = "";
        switch (environment) {
            case "stage":
                switch (fileName) {
                    case "AskThePrompter_SystemID+DocNum+VehicleNum.json":
                    case "AskThePrompter_SystemID+DocNum.json":
                    case "AskThePrompter_SystemID+VehicleNum.json":
                    case "AskThePrompter_IncorrectRequest.json":
                        url = stagePromterUrl;
                        break;
                    case "AskThePrompterV2_SystemID+LossType+IdentifierLoss.json":
                    case "AskThePrompterV2_SystemID+IdentifierLoss.json":
                    case "AskThePrompterV2_SystemID+LossType.json":
                    case "AskThePrompterV2_IncorrectRequest.json":
                        url = stagePromterV2Url;
                }
                break;
            case "test":
                switch (fileName) {
                    case "AskThePrompter_SystemID+DocNum+VehicleNum.json":
                    case "AskThePrompter_SystemID+DocNum.json":
                    case "AskThePrompter_SystemID+VehicleNum.json":
                    case "AskThePrompter_IncorrectRequest.json":
                        url = testPromterUrl;
                        break;
                    case "AskThePrompterV2_SystemID+LossType+IdentifierLoss.json":
                    case "AskThePrompterV2_SystemID+IdentifierLoss.json":
                    case "AskThePrompterV2_SystemID+LossType.json":
                    case "AskThePrompterV2_IncorrectRequest.json":
                        url = testPromterV2Url;
                }
        }
        return url;
    }


    static String getURLkibana(String environment) {
        String urlKibanaSearch = "";
        switch (environment) {
            case "stage":
                urlKibanaSearch = "esb-stage";
                break;
            case "test":
                urlKibanaSearch = "esb-test";
                break;
        }
        return urlKibanaSearch;
    }


    static String getURLOisuu(String environment) {
        String urlOisuu = "";
        switch (environment) {
            case "stage":
                urlOisuu = stageOisuuUrl;
                break;
            case "test":
                urlOisuu = testOisuuUrl;
                break;
        }
        return urlOisuu;
    }


    static String getSearchingLog(String fileName) {
        String log = "";
        switch (fileName) {
            case "AskThePrompter_SystemID+DocNum+VehicleNum.json":
            case "AskThePrompter_SystemID+DocNum.json":
            case "AskThePrompter_SystemID+VehicleNum.json":
                log= "PrompterService: REST response Operation 'AskThePrompter' was complete";
                break;
            case "AskThePrompterV2_SystemID+LossType+IdentifierLoss.json":
                log= "PrompterService: REST response Operation 'AskThePrompterV2' was complete";
                break;
            case "AskThePrompterV2_SystemID+IdentifierLoss.json":
            case "AskThePrompterV2_SystemID+LossType.json":
                log= "Failed delivery";
                break;
        }
        return log;
    }


    static void assertREST(String fileName, String serviceResponse) {
        switch (fileName) {
            case "AskThePrompter_SystemID+DocNum+VehicleNum.json":
            case "AskThePrompter_SystemID+DocNum.json":
            case "AskThePrompter_SystemID+VehicleNum.json":
                assertThat(serviceResponse, containsString("return"));
                assertThat(serviceResponse, containsString("Prompter"));
                assertThat(serviceResponse, containsString("mainDocNum"));
                assertThat(serviceResponse, containsString(":1218940"));
                assertThat(serviceResponse, containsString("mainVehicleNum"));
                assertThat(serviceResponse, containsString("М979СЕ68"));
                assertThat(serviceResponse, containsString("process"));
                break;
            case "AskThePrompterV2_SystemID+LossType+IdentifierLoss.json":
                assertThat(serviceResponse, containsString("return"));
                assertThat(serviceResponse, containsString("Prompter"));
                assertThat(serviceResponse, containsString("mainDocNum"));
                assertThat(serviceResponse, containsString(":3445241"));
                assertThat(serviceResponse, containsString("mainVehicleNum"));
                assertThat(serviceResponse, containsString("К552СХ22"));
                assertThat(serviceResponse, containsString("process"));
                break;
            case "AskThePrompterV2_SystemID+IdentifierLoss.json":
                assertThat(serviceResponse, containsString("org.apache.cxf.binding.soap.SoapFault: Неизвестная ошибка. {WebСервис.Prompter.Модуль(25)}: Не указан тип идентификатора убытка.\n" +
                        "по причине:\n" +
                        "{WebСервис.Prompter.Модуль(25)}: Не указан тип идентификатора убытка."));
                break;
            case "AskThePrompterV2_SystemID+LossType.json":
                assertThat(serviceResponse, containsString("org.apache.cxf.binding.soap.SoapFault: Неизвестная ошибка. {WebСервис.Prompter.Модуль(1349)}: Не указаны ни номер убытка, ни госномер ТС."));
        }
    }
}
