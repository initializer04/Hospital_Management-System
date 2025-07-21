package com.deepakcode.hello.dto;

import java.util.List;

import com.deepakcode.hello.entity.BillItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillRequestDTO {
   @NotNull(message = "Patient ID is required")
  private Long patientId;

  @NotNull(message = "Bill items are required")
  @Size(min = 1, message = "At least one bill item is required")
  private List<BillItem> items;

  // getters and setters
}
