package com.nagel.task.controller;


import com.nagel.task.model.City;
import com.nagel.task.model.CityReq;
import com.nagel.task.model.PageResponse;
import com.nagel.task.model.security.CustomUserDetails;
import com.nagel.task.model.security.EnumRole;
import com.nagel.task.services.CityService;
import com.nagel.task.services.CityUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController implements CityApi{

    @Autowired
    CityService cityService;


    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse> retrieveCities(@RequestParam(value="page", defaultValue="1") final Integer page, final HttpServletRequest request) {
        Long pageNum = Long.valueOf(page);
        PageResponse pageResponse = cityService.finadAll(pageNum);

        List<GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().collect(Collectors.toList());

        if (roles.get(0).getAuthority().equals("ROLE_ALLOW_EDIT")) {
            pageResponse.setAllowEdit(Boolean.TRUE);
        }

        return new ResponseEntity<PageResponse>(pageResponse, HttpStatus.OK);

    }

    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/cities/city", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResponse> retreiveCity(@RequestParam(value="name", defaultValue="") final String name) {
        PageResponse pageResponse = cityService.getByCityName(name);
        List<GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().collect(Collectors.toList());
        if (roles.get(0).equals("ROLE_ALLOW_EDIT")) {
            pageResponse.setAllowEdit(Boolean.TRUE);
        }
        return new ResponseEntity<PageResponse>(pageResponse, HttpStatus.OK);
    }



    @Override
    @ResponseBody
     @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(path = "/cities/city", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> updateCity(@RequestBody CityReq cityReq) {
        City city = City.builder().id(Long.valueOf(cityReq.getId())).name(cityReq.getName()).photo(cityReq.getPhoto()).build();
        Long id = cityService.updateCity(city);
        System.out.print(city.getId());

        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }

}
