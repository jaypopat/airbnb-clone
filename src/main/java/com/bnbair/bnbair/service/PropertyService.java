package com.bnbair.bnbair.service;

import com.bnbair.bnbair.domain.Property;
import com.bnbair.bnbair.exception.PropertyNotFoundException;
import com.bnbair.bnbair.repository.PropertyRepository;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getAllProperties() {
        return (List<Property>) propertyRepository.findAll();
    }

    public Property getPropertyById(Long id) throws PropertyNotFoundException {
        return propertyRepository.findById(id)
                .orElseThrow(() -> new PropertyNotFoundException("Property not found with id: " + id));
    }

    public List<Property> getPropertyByCountry(String country) {
        return propertyRepository.findByCountry(country);
    }

    public List<Property> getPropertyByPriceBetween(float low, float high) {
        return propertyRepository.findPropertyByPriceBetween(low, high);
    }

    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Property updateProperty(Long id, Property propertyDetails) throws PropertyNotFoundException {
        Property property = getPropertyById(id);

        property.setName(propertyDetails.getName());
        property.setAddress(propertyDetails.getAddress());
        property.setCountry(propertyDetails.getCountry());
        property.setOwner(propertyDetails.getOwner());
        property.setPricePerNight(propertyDetails.getPricePerNight());
        property.setRating(propertyDetails.getRating());
        property.setType(propertyDetails.getType());
        property.setDescription(propertyDetails.getDescription());
        return propertyRepository.save(property);
    }

    public void deleteProperty(Long id) throws PropertyNotFoundException {
        Property property = getPropertyById(id);
        propertyRepository.delete(property);
    }
}
