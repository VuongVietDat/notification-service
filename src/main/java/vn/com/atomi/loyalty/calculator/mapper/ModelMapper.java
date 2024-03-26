package vn.com.atomi.loyalty.calculator.mapper;

import java.time.LocalDateTime;
import java.util.List;
import org.mapstruct.*;
import vn.com.atomi.loyalty.calculator.dto.output.*;
import vn.com.atomi.loyalty.calculator.enums.ApprovalStatus;

/**
 * @author haidv
 * @version 1.0
 */
@Mapper
public interface ModelMapper {

  default String getApprover(ApprovalStatus approvalStatus, String updateBy) {
    return ApprovalStatus.RECALL.equals(approvalStatus)
            || ApprovalStatus.WAITING.equals(approvalStatus)
        ? null
        : updateBy;
  }

  default LocalDateTime getApproveDate(ApprovalStatus approvalStatus, LocalDateTime updateAt) {
    return ApprovalStatus.RECALL.equals(approvalStatus)
            || ApprovalStatus.WAITING.equals(approvalStatus)
        ? null
        : updateAt;
  }

  @Named("findDictionaryName")
  default String findDictionaryName(
      String code, @Context List<DictionaryOutput> dictionaryOutputs) {
    if (code == null || dictionaryOutputs == null) {
      return null;
    }
    for (DictionaryOutput value : dictionaryOutputs) {
      if (value != null && code.equals(value.getCode())) {
        return value.getName();
      }
    }
    return null;
  }
}
