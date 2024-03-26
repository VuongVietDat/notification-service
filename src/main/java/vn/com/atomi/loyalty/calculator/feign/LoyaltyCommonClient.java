package vn.com.atomi.loyalty.calculator.feign;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import vn.com.atomi.loyalty.base.constant.RequestConstant;
import vn.com.atomi.loyalty.base.data.ResponseData;
import vn.com.atomi.loyalty.calculator.dto.output.DictionaryOutput;
import vn.com.atomi.loyalty.calculator.enums.Status;
import vn.com.atomi.loyalty.calculator.feign.fallback.LoyaltyCommonClientFallbackFactory;

/**
 * @author haidv
 * @version 1.0
 */
@FeignClient(
    name = "loyalty-common-service",
    url = "${custom.properties.loyalty-common-service-url}",
    fallbackFactory = LoyaltyCommonClientFallbackFactory.class)
public interface LoyaltyCommonClient {

  @Operation(summary = "Api (nội bộ) lấy danh sách cấu hình đang hiệu lực")
  @GetMapping("/internal/dictionaries")
  ResponseData<List<DictionaryOutput>> getDictionaries(
      @RequestHeader(RequestConstant.REQUEST_ID) String requestId,
      @RequestParam(required = false) String type,
      @RequestParam(required = false) Status status);
}
