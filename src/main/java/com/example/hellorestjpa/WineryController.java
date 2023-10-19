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

    @PutMapping("/{id}")
    Winery update(@PathVariable Integer id, @RequestBody Winery winery) throws Exception {
        Winery found = wineryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
        // ...
        winery.setId(id);
        Winery saved = wineryRepository.save(winery);
        return saved;
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Winery> delete(@PathVariable Integer id) {
        Winery found = wineryRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "entity "+id+" not found"));
        wineryRepository.delete(found);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PatchMapping("/{id}")
    Winery partialUpdate(@PathVariable Integer id, @RequestBody Winery winery) throws Exception {
        Winery found = wineryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
        // ...
        if (winery.getName() != null) {
            found.setName(winery.getName());
        }
        Winery saved = wineryRepository.save(found);
        return saved;
    }

}
