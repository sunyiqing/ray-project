package com.ray.service;

import com.github.pagehelper.PageHelper;
import com.ray.mapper.CityMapper;
import com.ray.model.City;
import java.util.*;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    public List<City> getAll(City city) {
        if (city.getPage() != null && city.getRows() != null) {
            PageHelper.startPage(city.getPage(), city.getRows());
        }
        return cityMapper.selectAll();
    }

    public City getById(Integer id) {
        return cityMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        cityMapper.deleteByPrimaryKey(id);
    }

    public void save(City country) {
        if (country.getId() != null) {
            cityMapper.updateByPrimaryKey(country);
        } else {
            cityMapper.insert(country);
        }
    }

    /**
     * 随机生成城市序列
     */
    public List<City> generateCityList(){
        List<City> result= Lists.newArrayList();
        List<City> cities=cityMapper.selectAll();
        int n=cities.size();
        Random random=new Random();
        boolean[] flag=new boolean[n];
        int randInt=0;
        for (int i = 0; i < n; i++) {
            do{
                randInt=random.nextInt(n);
            }while (flag[randInt]);

            flag[randInt]=true;
            result.add(cities.get(randInt));
        }
        if(result !=null && result.size()>0){
            result.add(result.get(0));
        }
        return result;
    }
}
