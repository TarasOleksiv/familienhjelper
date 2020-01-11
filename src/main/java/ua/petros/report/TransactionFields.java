package ua.petros.report;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Taras on 10.01.2020.
 */
public class TransactionFields extends AbstractFields {

    // "Project", "Date", "Amount",
	//			"Currency", "Amount NOK", "From Donor", "To Beneficiary", "Transaction Type"

    // "project", "tradingDate", "amount",
	//			"currency", "amountNOK", "member", "beneficiary", "transactionType"

    @Override
    Map<String, String> initMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("Project", "Project");
        map.put("Date", "tradingDate");
        map.put("Amount", "Amount");
        map.put("Currency", "Currency");
        map.put("Amount NOK", "AmountNOK");
        map.put("From Donor", "member");
        map.put("To Beneficiary", "Beneficiary");
        map.put("Transaction Type", "TransactionType");
        map.put("Description", "Description");
        return Collections.unmodifiableMap(map);
    }
}
