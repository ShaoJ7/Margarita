package com.magarita.iotManager.service.impl;

import com.magarita.iotManager.mapper.CommonMapper;
import com.magarita.iotManager.mapper.PMMapper;
import com.magarita.iotManager.pojo.*;
import com.magarita.iotManager.service.PMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class
PMServiceImpl implements PMService {

    @Autowired
    private CommonMapper commonMapper;

    @Autowired
    private PMMapper pmMapper;

    @Autowired
    private CommonServiceImpl commonServiceImpl;

    @Override
    public Position selPosByEmpId(Integer id) {
        Employee emp = commonMapper.selEmpById(id);
        Position position = commonServiceImpl.selPosByEmp(emp);
        if(position == null) {
            Position pos = new Position();
            pos.setEmployee(emp);
            return pos;
        }
        return position;
    }

    @Override
    public int updEmpById(Employee employee) {
        return pmMapper.updEmpById(employee);
    }

    @Override
    public List<Position> selAllPos() {
        List<Position> list = pmMapper.selAllPos();
        for (Position pos: list
             ) {
            if(commonMapper.selEmpIdByPosId(pos.getId()) == null) {
                pos.setEmployee(null);
            } else {
                int empId = commonMapper.selEmpIdByPosId(pos.getId());
                Employee employee = commonMapper.selEmpById(empId);
                pos.setEmployee(employee);
            }
            int deptId = commonMapper.selDeptIdByPosId(pos.getId());
            Location location = commonMapper.selDeptById(deptId);
            pos.setLocation(location);
        }
        return list;
    }

    @Override
    public int addDept(Location location) {
        return pmMapper.addDept(location);
    }

    @Override
    public Location selDeptById(Integer id) {
        return pmMapper.selDeptById(id);
    }

    @Override
    public int updDeptById(Location location) {
        return pmMapper.updDeptById(location);
    }

    @Override
    public Integer selEmpIdByEmpUsername(String username) {
        return pmMapper.selEmpIdByEmpUsername(username);
    }

    @Override
    public int delDeptById(Location location) {
        List<Position> positions = commonServiceImpl.selPosByDeptId(location.getId());
        Iterator<Position> it = positions.listIterator();
        while (it.hasNext()) {
            pmMapper.delPosById(it.next());
        }
        return pmMapper.delDeptById(location);
    }

    @Override
    public Position selPosById(Integer id) {
        return pmMapper.selPosById(id);
    }

    @Override
    public int updPosById(Position position) {
        return pmMapper.updPosById(position);
    }

    @Override
    public int delPositionById(Position position) {
        return pmMapper.delPosById(position);
    }

    @Override
    public int addPos(Position position) {
        return pmMapper.addPos(position.getName(),position.getSalary(),position.getLocation().getId());
    }

    @Override
    public int addEmp(Employee employee) {
        return pmMapper.addEmp(employee);
    }

    @Override
    public List<Position> selEmptyPos() {
        List<Position> positions = pmMapper.selEmptyPos();
        for (Position pos:positions
             ) {
            pos.setLocation(commonMapper.selDeptById(commonMapper.selDeptIdByPosId(pos.getId())));
        }
        return positions;
    }

    @Override
    public List<Employee> selNOPosEmp() {
        List<Integer> list = pmMapper.selAllEmpIdWithPos();
        List<Employee> employees = pmMapper.selAllEmp();
        List<Employee> emps = new ArrayList<>();
        for (Employee emp:employees
             ) {
            if(!list.contains(emp.getId())) {
                emps.add(emp);
            }
        }

        return emps;
    }

    @Override
    public Employee selEmpById(Integer id) {
        return pmMapper.selEmpById(id);
    }

    @Override
    public Integer updPosEId(Integer id, Integer eId) {
        return pmMapper.updPosEId(id, eId);
    }

    @Override
    public List<PositionChange> selAllLeaveAfterGMAgree() {
        List<PositionChange> pcs = pmMapper.selAllPCAfterGM(Deal.AGREE.getValue(), Type.LEAVE.getValue());
        for (PositionChange pc:pcs
             ) {
            pc.seteId(commonMapper.selEmpById(commonMapper.selEidByPCId(pc.getId())));
            pc.setpId(commonMapper.selPosById(commonMapper.selPidByPCId(pc.getId())));
        }
        return pcs;
    }

    @Override
    public List<PositionChange> selAllPCAfterGMAgree() {
        List<PositionChange> pcs = pmMapper.selAllPCAfterGM(Deal.AGREE.getValue(),Type.CHANGE.getValue());
        for (PositionChange pc:pcs
             ) {
            pc.seteId(commonMapper.selEmpById(commonMapper.selEidByPCId(pc.getId())));
            pc.setpId(commonMapper.selPosById(commonMapper.selPidByPCId(pc.getId())));
            pc.settId(commonMapper.selPosById(commonMapper.selTIdByPCId(pc.getId())));
        }
        return pcs;
    }

    @Override
    public int pmDealEmpLeave(Integer pcId, Date effectTime) {
        pmMapper.delEmpById(commonMapper.selEidByPCId(pcId));
        pmMapper.updPosEIdById(commonMapper.selPidByPCId(pcId));
        return pmMapper.updPCByPM(pcId, effectTime);
    }

    @Override
    public int pmDealEmpPosChange(Integer pcId, Date effectTime) {
        pmMapper.updPosEIdById(commonMapper.selPidByPCId(pcId));
        pmMapper.updeIdById(commonMapper.selTIdByPCId(pcId),commonMapper.selEidByPCId(pcId));
        return pmMapper.updPCByPM(pcId,effectTime);
    }

    @Override
    public List<Request> selAllReqsByDMAgree() {
        return pmMapper.selReqByStatus(Status.AGREE.getValue());
    }

    @Override
    public List<Checking> selAllCheck() {
        return pmMapper.selAllChecks();
    }

    @Override
    public int gmSettingCheckTiming(String morningCheckLast, String eveningCheckFirst) {
        return pmMapper.updCheckSetting(morningCheckLast, eveningCheckFirst);
    }

    @Override
    public CheckSetting selCS() {
        return pmMapper.selCS();
    }

    @Override
    public List<Checking> selChecksByPersonnelDay(Integer eId, String nowDate) {
        List<Checking> checkings = pmMapper.selCheckByPersonnelDay(eId, nowDate);
        for (Checking check:checkings
             ) {
            Employee employee = pmMapper.selEmpIdByCheckId(check.getId());
            check.setEmp(employee);
        }
        return checkings;
    }

    @Override
    public List<Checking> selChecksByPersonnelDate(Integer eId, String startTime, String endTime) {
        List<Checking> checkings = pmMapper.selCheckByPersonnelDate(eId, startTime, endTime);
        for (Checking check:checkings
             ) {
            Employee employee = pmMapper.selEmpIdByCheckId(check.getId());
            check.setEmp(employee);
        }
        return checkings;
    }

    @Override
    public List<Request> selRequestsByPersonnelDay(Integer eId, String nowDate, String status) {
        List<Request> requests = pmMapper.selRequestByPersonnelDay(eId, nowDate, status);
        for (Request request:requests
             ) {
            Employee employee = commonMapper.selEmpIdByReqId(request.getId());
            request.setEmp(employee);
        }
        return requests;
    }

    @Override
    public List<Request> selRequestsByPersonnelDate(Integer eId, String startTime, String endTime, String status) {
        List<Request> requests = pmMapper.selRequestByPersonnelDate(eId, startTime, endTime, status);
        for (Request request:requests
             ) {
            Employee employee = commonMapper.selEmpIdByReqId(request.getId());
            request.setEmp(employee);
        }
        return requests;
    }
}
