package com.example.hellorestjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/winery")
public class WineryController {
    @Autowired
    WineryRepository wineryRepository;

    @GetMapping("/")
    List<Winery> getAll() {
        return wineryRepository.findAll();
    }

}
