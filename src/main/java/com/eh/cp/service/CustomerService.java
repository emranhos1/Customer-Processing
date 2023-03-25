package com.eh.cp.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eh.cp.helper.Validator;
import com.eh.cp.model.InvalidCustomer;
import com.eh.cp.model.ValidCustomer;
import com.eh.cp.repository.InvalidCustomerRepository;
import com.eh.cp.repository.ValidCustomerRepository;
import com.eh.cp.service.IService.ICustomerService;
/**
 * @author MD. EMRAN HOSSAIN
 * @since 24 MAR, 2023
 * @version 1.1
 */
@Service
public class CustomerService implements ICustomerService {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    @Value("${file.fileDir}")
    private String fileDir;

    @Value("${file.exportDir}")
    private String exportDir;

    @Autowired
    Validator validator;

    @Autowired
    ValidCustomerRepository validCustomerRepository;

    
    @Autowired
    InvalidCustomerRepository invalidCustomerRepository;

    /**
     * This method is for store customer data in DB
     *
     * @author MD. EMRAN HOSSAIN
     * @return ResponseEntity - ResponseEntity
     * @since 24 MAR, 2023
     */
    @Override
    public Boolean parseData() throws Exception {
        try {
            String userDirectory = System.getProperty("user.dir");
            FileReader fileReader = new FileReader(userDirectory + fileDir);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            List<ValidCustomer> validCustomers = new ArrayList<ValidCustomer>();
            List<InvalidCustomer> invalidCustomers = new ArrayList<InvalidCustomer>();

            Set<String> uniquePhone = new HashSet<String>();
            Set<String> uniqueEmail = new HashSet<String>();

            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                ValidCustomer validCustomer = new ValidCustomer();
                InvalidCustomer invalidCustomer = new InvalidCustomer();

                Boolean phoneSet = uniquePhone.add(values[5]);
                Boolean emailSet = uniqueEmail.add(values[6]);
                //checking email and phone no is unique or not
                if(!phoneSet && !emailSet) {
                    invalidCustomer.setName(values[0]);
                    invalidCustomer.setBranch(values[1]);
                    invalidCustomer.setCity(values[2]);
                    invalidCustomer.setState(values[3]);
                    invalidCustomer.setZipCode(values[4]);
                    invalidCustomer.setPhoneNo(values[5]);
                    invalidCustomer.setEmail(values[6]);
                    invalidCustomer.setIpAddress((values.length >= 8 && values[7] != null) ? values[7] : null);

                    invalidCustomers.add(invalidCustomer);
                } else {
                    // validate phone and email
                    if(validator.validatePhone(values[5]) && validator.validateEmail(values[6])) {
                        validCustomer.setName(values[0]);
                        validCustomer.setBranch(values[1]);
                        validCustomer.setCity(values[2]);
                        validCustomer.setState(values[3]);
                        validCustomer.setZipCode(values[4]);
                        validCustomer.setPhoneNo(values[5]);
                        validCustomer.setEmail(values[6]);
                        validCustomer.setIpAddress((values.length >= 8 && values[7] != null) ? values[7] : null);

                        validCustomers.add(validCustomer);
                    } else {
                        invalidCustomer.setName(values[0]);
                        invalidCustomer.setBranch(values[1]);
                        invalidCustomer.setCity(values[2]);
                        invalidCustomer.setState(values[3]);
                        invalidCustomer.setZipCode(values[4]);
                        invalidCustomer.setPhoneNo(values[5]);
                        invalidCustomer.setEmail(values[6]);
                        invalidCustomer.setIpAddress((values.length >= 8 && values[7] != null) ? values[7] : null);

                        invalidCustomers.add(invalidCustomer);
                    }
                }
            }
            bufferedReader.close();

            validCustomerRepository.saveAll(validCustomers);
            invalidCustomerRepository.saveAll(invalidCustomers);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * This API is for export customer data from DB
     *
     * @author MD. EMRAN HOSSAIN
     * @return ResponseEntity - ResponseEntity
     * @since 25 MAR, 2023
     */
    @Override
    public Boolean exprotData() throws Exception {
        try {
            List<ValidCustomer> validCustomers = validCustomerRepository.findAll();

            int rowcount = 0;
            int filecount = 1;
    
            String userDirectory = System.getProperty("user.dir");
            String exportPath = userDirectory + exportDir;

            // make directory if not exists
            File directory = new File(exportPath);
            if (!directory.exists()) {
                directory.mkdirs(); 
            }

            FileWriter writer = new FileWriter(exportPath + "data_" + filecount + ".txt");
            for(ValidCustomer validCustomer : validCustomers) {
                // write each row of data to the file
                String line = validCustomer.getName() + "," + validCustomer.getBranch() + "," + validCustomer.getCity() + "," + validCustomer.getState()+ "," + validCustomer.getZipCode()+ "," + validCustomer.getPhoneNo()+ "," + validCustomer.getEmail()+ "," + validCustomer.getIpAddress()+ "\n";

                writer.write(line);
                rowcount++;

                // if the row count is 10k, create a new file and reset the row counter
                if (rowcount == 10000) {
                    writer.close();
                    filecount++;
                    writer = new FileWriter(exportPath + "data_" + filecount + ".txt");
                    rowcount = 0;
                }
            }

            // close the last file
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
