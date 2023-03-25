package com.eh.cp.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/**
 * This class for Parse Data from text file
 *
 * @author MD. EMRAN HOSSAIN
 * @since 24 MAR, 2023
 * @version 1.1
*/
@Component
public class Validator {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(Validator.class);

    /**
     * This method return is phone number is validate or not FOR US
     *
     * @author MD. EMRAN HOSSAIN
     * @param phoneNo - String
     * @return isValid - boolean
     * @since 24 MAR, 2023
     */
    public Boolean validatePhone(String phoneNo){
        String phoneRegex = "^\\(?(\\d{3})\\)?[-.\\s]?(\\d{3})[-.\\s]?(\\d{4})$";
        return phoneNo.matches(phoneRegex);
    }

    /**
     * This method return is email is validate or not
     *
     * @author MD. EMRAN HOSSAIN
     * @param email - String
     * @return isValid - boolean
     * @since 24 MAR, 2023
     */
    public Boolean validateEmail(String email){
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
