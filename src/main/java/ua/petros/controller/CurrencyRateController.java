package ua.petros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.petros.model.CurrencyRate;
import ua.petros.service.CurrencyRateService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Created by Taras on 02.01.2020.
 */

@Controller
public class CurrencyRateController {

    @Autowired
    private CurrencyRateService currencyRateService;

    // Show all currency rates
    @RequestMapping(value = "/currencyrates", method = {RequestMethod.GET})
    public String showCurrencyRates(Model model) {
        model.addAttribute("list", currencyRateService.getAll());
        return "currencyRatesList";
    }

    //Show form to edit currency rate
    @RequestMapping(value = "/currencyrates/{currencyRateId}/edit", method = {RequestMethod.GET})
    public String showEditCurrencyRateForm(Model model, @PathVariable("currencyRateId") String currencyRateId) {
        model.addAttribute("currencyRate", currencyRateService.getById(UUID.fromString(currencyRateId)));
        return "currencyRateEdit";
    }

    // Edit currency rate
    @RequestMapping(value = "/currencyrates/{currencyRateId}", method = {RequestMethod.PUT})
    public String editCurrencyRate(Model model,
                                   @ModelAttribute("rate") String rate,
                                   @ModelAttribute("currencyRateId") String currencyRateId){

        CurrencyRate currencyRate = new CurrencyRate();
        CurrencyRate currencyRateDB = currencyRateService.getById(UUID.fromString(currencyRateId));
        currencyRate.setId(UUID.fromString(currencyRateId));
        currencyRate.setSourceCurrency(currencyRateDB.getSourceCurrency());
        currencyRate.setTargetCurrency(currencyRateDB.getTargetCurrency());
        currencyRate.setRate(new BigDecimal(rate));
        currencyRateService.save(currencyRate);
        return "redirect:/currencyrates/";
    }
}
