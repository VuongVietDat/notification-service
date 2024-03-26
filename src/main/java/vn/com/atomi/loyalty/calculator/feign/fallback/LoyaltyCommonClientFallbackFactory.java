package vn.com.atomi.loyalty.calculator.feign.fallback;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import vn.com.atomi.loyalty.base.data.ResponseData;
import vn.com.atomi.loyalty.base.exception.BaseException;
import vn.com.atomi.loyalty.base.exception.CommonErrorCode;
import vn.com.atomi.loyalty.calculator.dto.output.DictionaryOutput;
import vn.com.atomi.loyalty.calculator.enums.Status;
import vn.com.atomi.loyalty.calculator.feign.LoyaltyCommonClient;

/**
 * @author haidv
 * @version 1.0
 */
@Component
@Slf4j
public class LoyaltyCommonClientFallbackFactory implements FallbackFactory<LoyaltyCommonClient> {
  @Override
  public LoyaltyCommonClient create(Throwable cause) {
    log.error("An exception occurred when calling the LoyaltyCommonClient", cause);

    return new LoyaltyCommonClient() {
      @Override
      public ResponseData<List<DictionaryOutput>> getDictionaries(
          String requestId, String type, Status status) {
        throw new BaseException(CommonErrorCode.EXECUTE_THIRTY_SERVICE_ERROR, cause);
      }
    };
  }
}
