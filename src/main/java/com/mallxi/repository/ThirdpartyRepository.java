package com.mallxi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.mallxi.beandata.ThirdParty;

@RestResource(exported = false) // 禁止暴露REST
public interface ThirdpartyRepository extends CrudRepository<ThirdParty, String> {

}
