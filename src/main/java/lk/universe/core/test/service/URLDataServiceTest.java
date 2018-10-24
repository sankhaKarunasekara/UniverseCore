package lk.universe.core.test.service;

import lk.universe.core.domain.URLData;
import lk.universe.core.enums.ExtractMethod;
import lk.universe.core.enums.State;
import lk.universe.core.service.URLDataService;

public class URLDataServiceTest {

    public static void main(String[] args){

        URLDataService urlDataService = new URLDataService();
        String centralBankUrl = "https://www.cbsl.gov.lk/cbsl_custom/charts/usd/oneyear.php";
        URLData urlData = new URLData("CentralBank",centralBankUrl,ExtractMethod.ONE, State.LIVE);
        urlDataService.createURLData(centralBankUrl,"ONE","Google");
    }

}
