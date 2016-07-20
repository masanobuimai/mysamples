package service;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Hello {
  @WebMethod
  public String sayHello(String name) {
    return "Hello, " + name;
  }
}