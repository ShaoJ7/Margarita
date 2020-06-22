package com.magarita.iotManager.service.impl;

import com.magarita.iotManager.mapper.GMMapper;
import com.magarita.iotManager.pojo.Deal;
import com.magarita.iotManager.pojo.Employee;
import com.magarita.iotManager.pojo.PositionChange;
import com.magarita.iotManager.pojo.Type;
import com.magarita.iotManager.service.GMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GMServiceImpl implements GMService {

    @Autowired
    private GMMapper gmMapper;


    @Override
    public List<Employee> selEmpByFuzzy(String username, String realname, String deptId) {
        return gmMapper.selEmpByFuzzy(username,realname,deptId);
    }

    @Override
    public Integer getTotalCountsByName(String username, String realname, String deptId) {
        return gmMapper.getTotalCountsByName(username,realname,deptId);
    }

    @Override
    public List<Integer> companyProfile() {
        List<Integer> list = new ArrayList<>();
        Integer depts = gmMapper.selCountByAllDept();
        Integer emps = gmMapper.selCountByAllEmp();
        Integer empAge = gmMapper.selEmpCountByAvgAge();
        Integer empMAN = gmMapper.selEmpCountByCategory("控制");
        Integer empWOMAN = gmMapper.selEmpCountByCategory("设备");
        list.add(depts);
        list.add(emps);
        list.add(empAge);
        list.add(empMAN);
        list.add(empWOMAN);
        return list;
    }

    @Override
    public List<PositionChange> selAllLeavePCByGMAgree() {
        List<PositionChange> pcs = gmMapper.selAllPCAfterPMDeal(Deal.AGREE.getValue(), Type.LEAVE.getValue());
        for (PositionChange pc:pcs
             ) {
            pc.seteId(gmMapper.selEIdById(pc.getId()));
            pc.setpId(gmMapper.selPIdById(pc.getId()));
        }
        return pcs;
    }

    @Override
    public List<PositionChange> selAllPosChangeByGMAgree() {
        List<PositionChange> pcs = gmMapper.selAllPCAfterPMDeal(Deal.AGREE.getValue(), Type.CHANGE.getValue());
        for (PositionChange pc:pcs
             ) {
            pc.seteId(gmMapper.selEIdById(pc.getId()));
            pc.setpId(gmMapper.selPIdById(pc.getId()));
            pc.settId(gmMapper.selTIdById(pc.getId()));
        }
        return pcs;
    }

    @Override
    public int gmDealEmpLeaveRequest(Integer pcId, String gmComment, String gmStatus) {
        return gmMapper.updPCByGm(pcId, gmComment, gmStatus);
    }
}
