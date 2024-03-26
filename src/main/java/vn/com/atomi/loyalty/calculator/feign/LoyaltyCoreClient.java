package vn.com.atomi.loyalty.calculator.feign;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import vn.com.atomi.loyalty.base.constant.RequestConstant;
import vn.com.atomi.loyalty.base.data.ResponseData;
import vn.com.atomi.loyalty.calculator.dto.input.TransactionInput;
import vn.com.atomi.loyalty.calculator.feign.fallback.LoyaltyCoreClientFallbackFactory;

/**
 * @author haidv
 * @version 1.0
 */
@FeignClient(
    name = "loyalty-core-service",
    url = "${custom.properties.loyalty-core-service-url}",
    fallbackFactory = LoyaltyCoreClientFallbackFactory.class)
public interface LoyaltyCoreClient {

  @Operation(summary = "Api (nội bộ) tiêu điểm")
  @PostMapping("/internal/transactions-minus")
  ResponseData<Void> executeTransactionMinus(
      @RequestHeader(RequestConstant.REQUEST_ID) String requestId,
      @RequestBody TransactionInput transactionInput);
}
