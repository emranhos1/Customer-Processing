package com.eh.cp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eh.cp.exception.ErrorResponse;
import com.eh.cp.helper.constants.CustomerConstants;
import com.eh.cp.helper.message.CustomerMessageConstants;
import com.eh.cp.payload.ApiResponse;
import com.eh.cp.service.IService.ICustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * This controller is to provide customer API
 *
 * @author MD. EMRAN HOSSAIN
 * @since 24 MAR, 2023
 * @version 1.1
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
public class CustomerController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Value("${file.exportDir}")
    private String exportDir;

    @Autowired
    ICustomerService iCustomerService;

    /**
     * This API is for store customer data in DB
     *
     * @author MD. EMRAN HOSSAIN
     * @return ResponseEntity - ResponseEntity
     * @since 24 MAR, 2023
     */
    @Operation(summary = CustomerConstants.CUSTOMER_SWAGGER_SUMMARY, description = CustomerConstants.CUSTOMER_SWAGGER_DESCRIPTION)
    @ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = CustomerMessageConstants.DATA_PROCESS_EN,
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = CustomerController.class)))),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = CustomerMessageConstants.DATA_PROCESS_FAILD_EN,
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))
            })
    @GetMapping(CustomerConstants.PARSE_API_ENDPOINT)
    public ResponseEntity<?> parseData(){
        try {
            long startTime = System.nanoTime();
            Boolean parsedData = iCustomerService.parseData();
            long endTime = System.nanoTime();
            LOG.info("Total Time in nanoseconds: {}", endTime - startTime);
            if(parsedData) {
                return ResponseEntity.ok(new ApiResponse(true, CustomerMessageConstants.DATA_PROCESS_EN, CustomerMessageConstants.DATA_PROCESS_BN, CustomerMessageConstants.DATA_PROCESS_EN, 1L));
            } else {
                return new ResponseEntity(new ErrorResponse(false, CustomerMessageConstants.DATA_PROCESS_FAILD_EN, CustomerMessageConstants.DATA_PROCESS_FAILD_EN, CustomerMessageConstants.DATA_PROCESS_FAILD_EN, null), HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
            return new ResponseEntity(new ErrorResponse(false, CustomerMessageConstants.DATA_PROCESS_FAILD_EN, CustomerMessageConstants.DATA_PROCESS_FAILD_EN, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This API is for export customer data from DB
     *
     * @author MD. EMRAN HOSSAIN
     * @return ResponseEntity - ResponseEntity
     * @since 25 MAR, 2023
     */
    @Operation(summary = CustomerConstants.EXPORT_SWAGGER_SUMMARY, description = CustomerConstants.EXPORT_SWAGGER_DESCRIPTION)
    @ApiResponses(value = {
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = CustomerMessageConstants.DATA_EXPORT_EN,
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = CustomerController.class)))),
                @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = CustomerMessageConstants.DATA_EXPORT_FAILD_EN,
                        content = @Content(array = @ArraySchema(schema = @Schema(implementation = ErrorResponse.class))))
            })
    @GetMapping(CustomerConstants.EXPORT_API_ENDPOINT)
    public ResponseEntity<?> exportData(){
        try {
            Boolean exprotData = iCustomerService.exprotData();
            if(exprotData) {
                return ResponseEntity.ok(new ApiResponse(true, CustomerMessageConstants.DATA_EXPORT_EN, CustomerMessageConstants.DATA_EXPORT_BN, "You can find those file in " + exportDir, 1L));
            } else {
                return new ResponseEntity(new ErrorResponse(false, CustomerMessageConstants.DATA_EXPORT_FAILD_EN, CustomerMessageConstants.DATA_EXPORT_FAILD_EN, CustomerMessageConstants.DATA_EXPORT_FAILD_EN, null), HttpStatus.OK);
            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
            return new ResponseEntity(new ErrorResponse(false, CustomerMessageConstants.DATA_EXPORT_FAILD_EN, CustomerMessageConstants.DATA_EXPORT_FAILD_EN, e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
