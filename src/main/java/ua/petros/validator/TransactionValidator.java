package ua.petros.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.petros.model.*;
import ua.petros.model.Currency;
import ua.petros.service.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Taras on 15.12.2019.
 */

@Component
public class TransactionValidator {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private BeneficiaryService beneficiaryService;

    public Map<String, String> validate(Transaction transaction){

        // Configure error messages.
        Map<String, String> messages = new HashMap<String, String>();

        // Check date.
        Date tradingDate = transaction.getTradingDate();
        if (tradingDate == null) {
            messages.put("date", "Please enter date");
        }

        // Check amount.
        BigDecimal amount = transaction.getAmount();
        if (amount == null) {
            messages.put("amount", "Please enter amount");
        }

        // Check donor/beneficiary
        if (transaction.getIsIncome()){
            // Check donor
            Member member = transaction.getMember();
            if (member == null) {
                messages.put("donor", "Donor is missing");
            }
        } else {
            // Check beneficiary.
            Beneficiary beneficiary = transaction.getBeneficiary();
            if (beneficiary == null) {
                messages.put("beneficiary", "Beneficiary is missing");
            }
        }

        // Check currency.
        Currency currency = transaction.getCurrency();
        if (currency == null) {
            messages.put("currency", "Please choose currency from the list");
        }

        // Check type.
        TransactionType transactionType = transaction.getTransactionType();
        if (transactionType == null) {
            messages.put("transactionType", "Please choose transaction type from the list");
        }

        return messages;
    }

}
