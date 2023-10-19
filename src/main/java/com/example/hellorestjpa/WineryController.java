package com.example.hellorestjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.util.List;

@RestController
@RequestMapping("/winery")
public class WineryController {
    @Autowired
    WineryRepository wineryRepository;

    @GetMapping("")
    List<Winery> getAll() {
        return wineryRepository.findAll();
    }

    @GetMapping("/{id}")
    Winery retrieve(@PathVariable Integer id) throws Exception {
        return wineryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
    }

    @PostMapping("")
    ResponseEntity<Winery> create(@RequestBody Winery winery) {
        winery.setId(null);
        Winery saved = wineryRepository.save(winery);
        UriComponents uriComponents = MvcUriComponentsBuilder.fromMethodName(WineryController.class, "retrieve", saved.getId())
                .buildAndExpand();
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", uriComponents.encode().toUriString())
                .body(saved);
    }

}
