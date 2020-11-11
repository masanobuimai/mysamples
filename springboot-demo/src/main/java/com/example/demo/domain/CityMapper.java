package com.example.demo.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CityMapper {
  List<City> findByLanguage(@Param("language") String language);
}
