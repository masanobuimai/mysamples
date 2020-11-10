package com.example.demo;

import com.example.demo.domain.City;
import com.example.demo.domain.CityMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HelloController {
    private final CityMapper cityMapper;

    private final SqlSession sqlSession;

    public HelloController(CityMapper cityMapper, SqlSession sqlSession) {
        this.cityMapper = cityMapper;
        this.sqlSession = sqlSession;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", "Hello世界!!");
        return "index";
    }

    @GetMapping({"/list", "/list/{lang}"})
    @ResponseBody
    public List<City> list(@PathVariable(name = "lang", required = false) String lang) {
        return cityMapper.findByLanguage(lang);
    }
}
