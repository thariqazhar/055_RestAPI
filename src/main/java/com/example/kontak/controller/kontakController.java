package com.example.kontak.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.kontak.model.kontakRepo;
import com.example.kontak.model.kontak;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/kontak")
public class kontakController {

    @Autowired
    private kontakRepo kontakRepo;

    @GetMapping
    public ResponseEntity<List<kontak>> getAllKontak() {
        try {
            List<kontak> kontakList = kontakRepo.findAll()
                    .stream()
                    .sorted(Comparator.comparing(k -> k.getNama().toLowerCase())) // Sort by name (case-insensitive)
                    .collect(Collectors.toList());

            if (kontakList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(kontakList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<kontak> getAllKontakbyid(@PathVariable("id") int id) {
        try {

            Optional<kontak> tutorialData = kontakRepo.findById(id);
            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<kontak> createKontak(@RequestBody kontak kontak_) {
        try {
            kontak _kontak = kontakRepo.save(new kontak(kontak_.getId(), kontak_.getNama(), kontak_.getEmail(), kontak_.getNohp()));
            return new ResponseEntity<>(_kontak, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") int id) {
        try {
            kontakRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<kontak> updateTutorial(@PathVariable("id") int id, @RequestBody kontak kontak_) {
        Optional<kontak> tutorialData = kontakRepo.findById(id);

        if (tutorialData.isPresent()) {

            kontak _kontak = tutorialData.get();
            _kontak.setId(kontak_.getId());
            _kontak.setNama(kontak_.getNama());
            _kontak.setEmail(kontak_.getEmail());
            _kontak.setNohp(kontak_.getNohp());
            return new ResponseEntity<>(kontakRepo.save(_kontak), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
