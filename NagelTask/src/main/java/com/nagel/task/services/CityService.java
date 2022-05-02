package com.nagel.task.services;


import com.nagel.task.model.City;
import com.nagel.task.model.CityCsvData;
import com.nagel.task.model.PageResponse;
import com.nagel.task.repository.CityRepository;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CityService implements ICityService{

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    CityRepository cityRepository;

    public void processCityCsv() {
        List<City> cityList = new ArrayList<City>();
        Resource resource = resourceLoader.getResource("classpath:" + "assets/cities.csv");

        try {
            cityList = CityUtilities.loadCsv(resource.getInputStream());
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        if (!cityList.isEmpty()) {
            saveCities(cityList);
        }

    }

    private void saveCities(final List<City> cityList) {
        cityRepository.saveAll(cityList);
    }

    public PageResponse finadAll(final Long pageNum) {

        long offSet = (CityUtilities.PAGE_SIZE * pageNum) - CityUtilities.PAGE_SIZE;
        offSet = offSet + 1;
        final Long size = cityRepository.count();
        long end = pageNum * CityUtilities.PAGE_SIZE;
        List<City> cities = cityRepository.citiesByRange(offSet, end);
        double totalPages = Math.ceil((double) size / 10);

        PageResponse pageResponse = formatResponse(size, cities, totalPages, pageNum);
        return pageResponse;
    }

    private PageResponse formatResponse(final Long size, final List<City> cities, final double totalPages, final Long pageNum) {

        PageResponse pageResponse = new PageResponse();
        pageResponse.setTotalCount(size);
        pageResponse.getCityList().addAll(cities);
        pageResponse.setTotalPages((long)totalPages);
        pageResponse.setCurrPage(pageNum);

        if (pageNum > 1) {
            StringBuilder prevurl = new StringBuilder(CityUtilities.URL);
            long prevPage =  pageNum - 1;
            prevurl.append("?page="+prevPage);
            try {
                pageResponse.setPrevUrl(new URL(prevurl.toString()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        if (pageNum <100) {
            StringBuilder nexturl = new StringBuilder(CityUtilities.URL);
            long nextPage =  pageNum + 1;
            nexturl.append("?page="+nextPage);
            try {
                pageResponse.setNextUrl(new URL(nexturl.toString()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return pageResponse;
    }

    public Long updateCity(final City city) {
        Optional<City> cityDatabse = cityRepository.findById(city.getId());

        if(cityDatabse.isPresent()) {
            if (city.getName() != null && !city.getName().equals(" ") && !city.getName().equals(cityDatabse.get().getName())) {
                cityDatabse.get().setName(city.getName());
            }
            if (city.getPhoto() != null && !city.getPhoto().equals(" ") && !city.getPhoto().equals(cityDatabse.get().getPhoto())) {
                cityDatabse.get().setPhoto(city.getPhoto());
            }
        }

        cityRepository.save(cityDatabse.get());
        return cityDatabse.get().getId();
    }


    public PageResponse getByCityName(final String name) {
        PageResponse pageResponse = new PageResponse();
        List<City> cities = new ArrayList<>();
        if (name != null) {
            cities = cityRepository.retrieveCity(name);
        }
        pageResponse.getCityList().addAll(cities);
        return pageResponse;
    }
}
