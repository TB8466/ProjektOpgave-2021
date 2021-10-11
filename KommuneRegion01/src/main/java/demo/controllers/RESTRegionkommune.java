package demo.controllers;

import demo.model.Kommune;
import demo.model.Region;
import demo.repository.KommuneRepository;
import demo.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RESTRegionkommune {

    @Autowired
    RegionRepository regionRepository;

    @Autowired
    KommuneRepository kommuneRepository;

    @PostMapping(value ="/region", consumes = "application/json")
    public ResponseEntity<Region> newRegion(@RequestBody Region region){

        regionRepository.save(region);
        return new ResponseEntity<Region>(region, HttpStatus.CREATED);
    }

    @GetMapping("/regioner")
    public List<Region> allRegioner(){ return regionRepository.findAll(); }

    @PostMapping(value ="/kommune", consumes = "application/json")
    public ResponseEntity<Kommune> newKommune(@RequestBody Kommune kommune){

        kommuneRepository.save(kommune);
        return new ResponseEntity<Kommune>(kommune, HttpStatus.CREATED);
    }

    @GetMapping("/kommuner")
    public List<Kommune> allKommuner(){ return kommuneRepository.findAll(); }

}
