package com.edudev.controller;

import com.edudev.dto.AlertResponse;
import com.edudev.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService service;

    @GetMapping("/low-stock")
    public List<AlertResponse> getLowStockAlerts() {
        return service.getLowStockAlerts();
    }

}
