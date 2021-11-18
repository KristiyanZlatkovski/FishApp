package com.tusofia.myapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tusofia.myapp.model.Water;
import com.tusofia.myapp.model.WaterProp;
import com.tusofia.myapp.repository.WaterProposalRep;


@Service
public class WaterProposalServiceImpl implements WaterProposalService {

    @Autowired
    private WaterProposalRep waterPropRep;


    @Override
    public void save(WaterProp waterProp) {
        waterPropRep.save(waterProp);

    }

    @Override
    public ArrayList<WaterProp> findAll() {

        return waterPropRep.findAll();
    }

    @Override
    public void deleteById(Long id) {
        waterPropRep.deleteById(id);

    }

    @Override
    public WaterProp findById(Long id) {

        return waterPropRep.getById(id);
    }

    @Override
    public Water proposalToRecord(WaterProp prop) {
        Water water = new Water();
        water.setLatitude(prop.getLatitude());
        water.setLongtitude(prop.getLongtitude());
        water.setName(prop.getName());
        water.setPath(prop.getPath());
        water.setInfo(prop.getWaterInfo());
        return water;
    }

}
