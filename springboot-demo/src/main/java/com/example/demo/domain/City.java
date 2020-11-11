package com.example.demo.domain;

public class City {
  private Long id;
  private String name;
  private String country;
  private String airport;
  private String language;
  private String countryISOCode;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getAirport() {
    return airport;
  }

  public void setAirport(String airport) {
    this.airport = airport;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getCountryISOCode() {
    return countryISOCode;
  }

  public void setCountryISOCode(String countryISOCode) {
    this.countryISOCode = countryISOCode;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("City{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", country='").append(country).append('\'');
    sb.append(", airport='").append(airport).append('\'');
    sb.append(", language='").append(language).append('\'');
    sb.append(", countryISOCode='").append(countryISOCode).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
