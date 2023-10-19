package com.example.hellorestjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

    @GetMapping("/{id}")
    Winery retrieve(@PathVariable Integer id) throws Exception {
        return wineryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
    }

}
