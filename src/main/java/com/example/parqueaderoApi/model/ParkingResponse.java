package com.example.parqueaderoApi.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingResponse {
  
  private Long id;
  private Boolean status;
  
}
