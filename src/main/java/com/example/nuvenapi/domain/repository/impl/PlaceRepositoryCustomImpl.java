package com.example.nuvenapi.domain.repository.impl;

import com.example.nuvenapi.domain.entity.Place;
import com.example.nuvenapi.domain.repository.PlaceRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class PlaceRepositoryCustomImpl implements PlaceRepositoryCustom {

    private EntityManager entityManager;

    public PlaceRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Place> getByName(String name) {

        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Place> query = criteriaBuilder.createQuery(Place.class);
        Root<Place> place = query.from(Place.class);
        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            predicates.add(criteriaBuilder.like(place.get("name"), "%" + name + "%"));
        }

        if (!predicates.isEmpty()) {
            query.where(predicates.stream().toArray(Predicate[]::new));
        }

        TypedQuery<Place> queryResult = this.entityManager.createQuery(query);

        return queryResult.getResultList();
    }

    @Override
    public List<Place> readAllSorted() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Place> query = criteriaBuilder.createQuery(Place.class);
        Root<Place> place = query.from(Place.class);

        query.orderBy(criteriaBuilder.asc(place.get("createdAt")));

        TypedQuery<Place> queryResult = this.entityManager.createQuery(query);
        return queryResult.getResultList();
    }
}
