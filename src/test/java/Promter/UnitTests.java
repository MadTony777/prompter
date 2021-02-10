package Promter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;

public class UnitTests extends Template {

    @BeforeEach
    public void executedBeforeEach(TestInfo testInfo) {
        log.info("\n...Starting test: " + testInfo.getDisplayName());
    }

    @AfterEach
    public void executedAfterEach() {
        log.info("...End test\n\n");
    }


    @Test
    public void AskThePrompter_SystemID_DocNum_VehicleNum() throws Exception {
        List<String> logs = testCase(environment, "AskThePrompter_SystemID+DocNum+VehicleNum.json");
        assertThat(logs.get(2), containsString("PrompterService: REST request  Operation 'AskThePrompter' was call"));
        assertThat(logs.get(2), containsString("Outbound Message"));
        assertThat(logs.get(2), containsString("PrompterService: REST response Operation 'AskThePrompter' was complete"));
        assertThat(logs.get(2), containsString(getURLOisuu(environment)));
        assertThat(logs.get(2), containsString("<SystemID>BotMVR3839</SystemID>"));
        assertThat(logs.get(2), containsString("<DocNum>1218940</DocNum>"));
        assertThat(logs.get(2), containsString("<VehicleNum>М979СЕ68</VehicleNum>"));
    }

    @Test
    public void AskThePrompter_SystemID_DocNum() throws Exception {
        List<String> logs = testCase(environment, "AskThePrompter_SystemID+DocNum.json");
        assertThat(logs.get(2), containsString("PrompterService: REST request  Operation 'AskThePrompter' was call"));
        assertThat(logs.get(2), containsString("Outbound Message"));
        assertThat(logs.get(2), containsString("PrompterService: REST response Operation 'AskThePrompter' was complete"));
        assertThat(logs.get(2), containsString(getURLOisuu(environment)));
        assertThat(logs.get(2), containsString("<SystemID>BotMVR3839</SystemID>"));
        assertThat(logs.get(2), containsString("<DocNum>1218940</DocNum>"));
    }

    @Test
    public void AskThePrompter_SystemID_VehicleNum() throws Exception {
        List<String> logs = testCase(environment, "AskThePrompter_SystemID+VehicleNum.json");
        assertThat(logs.get(2), containsString("PrompterService: REST request  Operation 'AskThePrompter' was call"));
        assertThat(logs.get(2), containsString("Outbound Message"));
        assertThat(logs.get(2), containsString("PrompterService: REST response Operation 'AskThePrompter' was complete"));
        assertThat(logs.get(2), containsString(getURLOisuu(environment)));
        assertThat(logs.get(2), containsString("<SystemID>BotMVR3839</SystemID>"));
        assertThat(logs.get(2), containsString("<VehicleNum>М979СЕ68</VehicleNum>"));
    }

    @Test
    public void AskThePrompter_IncorrectRequest() throws Exception {
        List<String> logs = testCase(environment, "AskThePrompter_IncorrectRequest.json");
        assertThat(logs.get(0), containsString("javax.validation.ValidationException: Validation errors: Field [SystemID] is required"));
    }


    @Test
    public void AskThePrompterV2_SystemID_LossType_IdentifierLoss() throws Exception {
        List<String> logs = testCase(environment, "AskThePrompterV2_SystemID+LossType+IdentifierLoss.json");
        assertThat(logs.get(2), containsString("PrompterService: REST request  Operation 'AskThePrompterV2' was call"));
        assertThat(logs.get(2), containsString("Outbound Message"));
        assertThat(logs.get(2), containsString("PrompterService: REST response Operation 'AskThePrompterV2' was complete"));
        assertThat(logs.get(2), containsString(getURLOisuu(environment)));
        assertThat(logs.get(2), containsString("<SystemID>BotMVR3839</SystemID>"));
        assertThat(logs.get(2), containsString("<LossType>Policy</LossType>"));
        assertThat(logs.get(2), containsString("<IdentifierLoss>ССС0330931289</IdentifierLoss>"));
    }

    @Test
    public void AskThePrompterV2_SystemID_IdentifierLoss() throws Exception {
        List<String> logs = testCase(environment, "AskThePrompterV2_SystemID+IdentifierLoss.json");
        assertThat(logs.get(2), containsString("PrompterService: REST request  Operation 'AskThePrompterV2' was call"));
        assertThat(logs.get(2), containsString("Outbound Message"));
        assertThat(logs.get(2), containsString("PrompterService: 'AskThePrompterV2' catch exception: org.apache.cxf.binding.soap.SoapFault: Неизвестная ошибка. {WebСервис.Prompter.Модуль(25)}: Не указан тип идентификатора убытка.\\nпо причине:\\n{WebСервис.Prompter.Модуль(25)}: Не указан тип идентификатора убытка"));
        assertThat(logs.get(2), containsString("PrompterService: REST response Operation 'AskThePrompterV2' was complete"));
        assertThat(logs.get(2), containsString("Failed delivery"));
        assertThat(logs.get(2), containsString(getURLOisuu(environment)));
        assertThat(logs.get(2), containsString("<SystemID>BotMVR3839</SystemID>"));
        assertThat(logs.get(2), containsString("<IdentifierLoss>ССС0330931289</IdentifierLoss>"));
    }


    @Test
    public void  AskThePrompterV2_SystemID_LossType() throws Exception {
        List<String> logs = testCase(environment, "AskThePrompterV2_SystemID+LossType.json");
        assertThat(logs.get(2), containsString("PrompterService: REST request  Operation 'AskThePrompterV2' was call"));
        assertThat(logs.get(2), containsString("Outbound Message"));
        assertThat(logs.get(2), containsString("org.apache.cxf.binding.soap.SoapFault: Неизвестная ошибка. {WebСервис.Prompter.Модуль(1349)}: Не указаны ни номер убытка, ни госномер ТС"));
        assertThat(logs.get(2), containsString("PrompterService: REST response Operation 'AskThePrompterV2' was complete"));
        assertThat(logs.get(2), containsString("Failed delivery"));
        assertThat(logs.get(2), containsString(getURLOisuu(environment)));
        assertThat(logs.get(2), containsString("<SystemID>BotMVR3839</SystemID>"));
        assertThat(logs.get(2), containsString("<LossType>Policy</LossType>"));
    }

    @Test
    public void AskThePrompterV2_IncorrectRequest() throws Exception {
            List<String> logs = testCase(environment, "AskThePrompterV2_IncorrectRequest.json");
        assertThat(logs.get(0), containsString("javax.validation.ValidationException: Validation errors: Field [SystemID] is required"));
    }

}

