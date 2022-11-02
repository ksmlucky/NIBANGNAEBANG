package com.footprints.authservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="business-service")
public interface BusinessServiceClient {

//    @GetMapping("/api/select/{memberId}")
//    PaintingDtoDataResponse getSelectedPaintingList(@PathVariable Long memberId);
}