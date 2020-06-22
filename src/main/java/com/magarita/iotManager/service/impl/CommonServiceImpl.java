package com.magarita.iotManager.service.impl;

import com.magarita.iotManager.mapper.CommonMapper;
import com.magarita.iotManager.pojo.*;
import com.magarita.iotManager.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;


    @Override
    public Employee selEmpByUNamePwd(String username, String deviceport) {
        return commonMapper.selEmpByUNamePwd(username, deviceport);
    }

    @Override
    public Position selPosByEmp(Employee employee) {
        Position position = commonMapper.selPosByEmp(employee);
        if(position == null) {
            return null;
        }
        Employee emp = commonMapper.selEmpById(commonMapper.selEmpIdByPosId(position.getId()));
        Location dept = commonMapper.selDeptById(commonMapper.selDeptIdByPosId(position.getId()));
        position.setLocation(dept);
        position.setEmployee(emp);
        return position;
    }

    @Override
    public int updPwdByEmp(Employee employee) {
        return commonMapper.updPwdByEmp(employee);
    }

    @Override
    public List<Location> selAllDept() {
        return commonMapper.selAllDept();
    }

    @Override
    public List<Memo> selAllMemoByEmpId(Integer id) {
        return commonMapper.selAllMemoByEmpId(id);
    }

    @Override
    public int addMemoByEmp(Memo memo) {
        return commonMapper.addMemo(memo.getContent(),memo.getTime(),memo.getEmp().getId());
    }

    @Override
    public int delMemoByEmp(Integer id) {
        return commonMapper.delMemo(id);
    }

    @Override
    public List<Request> selReqsByEmp(Integer id) {
        return commonMapper.selReqsByEmp(id);
    }

    @Override
    public int addReqByEmp(Request request) {
        return commonMapper.addReqByEmpId(request.getStartTime(),request.getEndTime(),request.getReason(),request.getEmp().getId());
    }

    @Override
    public int delReqByEmp(Integer id) {
        return commonMapper.delReqById(id);
    }

    @Override
    public List<Request> selAllReqByDeptId(Integer id) {
        List<Request> requests = commonMapper.selAllReqByDeptId(id);
        for (Request req:requests
             ) {
            req.setEmp(commonMapper.selEmpIdByReqId(req.getId()));
        }
        return requests;
    }

    @Override
    public int updReqStatusById(String status, Integer id) {
        return commonMapper.updReqStatusById(status,id);
    }

    @Override
    public List<Position> selPosByDeptId(Integer id) {
        List<Employee> emps = commonMapper.selAllEmpByDept(id);
        List<Position> list = new ArrayList<>();
        for (Employee employee:emps
             ) {
            Position position = commonMapper.selPosByEmp(employee);
            Employee emp = commonMapper.selEmpById(commonMapper.selEmpIdByPosId(position.getId()));
            Location dept = commonMapper.selDeptById(commonMapper.selDeptIdByPosId(position.getId()));
            position.setLocation(dept);
            position.setEmployee(emp);
            list.add(position);
        }

        return list;
    }

    @Override
    public int addMsgByEmp(Message message) {
        return commonMapper.addMsgByEmp(message.getFromId().getId(),message.getToId().getId(),message.getContent(),message.getStartTime());
    }

    @Override
    public Employee selEmpByUserName(String username) {
        return commonMapper.selEmpByUserName(username);
    }

    @Override
    public List<Message> selAllMsgByFrom(Integer id) {
        List<Message> fromMsg = commonMapper.selMsgsByFrom(id);
        for (Message from:fromMsg
             ) {
            from.setFromId(commonMapper.selEmpById(commonMapper.selFromIdByMsgId(from.getId())));
            from.setToId(commonMapper.selEmpById(commonMapper.selToIdByMsgId(from.getId())));
        }
        return fromMsg;
    }

    @Override
    public List<Message> selAllMsgByTo(Integer id) {
        List<Message> toMsg = commonMapper.selMsgsByTo(id);
        for (Message to:toMsg
        ) {
            to.setFromId(commonMapper.selEmpById(commonMapper.selFromIdByMsgId(to.getId())));
            to.setToId(commonMapper.selEmpById(commonMapper.selToIdByMsgId(to.getId())));
        }
        return toMsg;
    }

    @Override
    public Integer delMsgById(Integer id) {
        return commonMapper.delMsgById(id);
    }

    @Override
    public Integer updMsgById(Integer id, Date endTime, String status) {
        return commonMapper.updMsgById(id,status,endTime);
    }

    @Override
    public Integer empLeave(PositionChange positionChange) {
        return commonMapper.empLeavePos(positionChange.getStartTime(),positionChange.getType().getValue(),
                positionChange.getEmpReason(),positionChange.geteId().getId(),positionChange.getpId().getId());
    }

    @Override
    public List<PositionChange> selAllPosChangeByEmpId(Integer id,String type) {
        return commonMapper.selAllPosLeaveByEmpIdAndType(id,type);
    }

    @Override
    public int delPosChange(Integer id) {
        return commonMapper.delPosChange(id);
    }

    @Override
    public Integer selPosIdByPCId(Integer id, String type) {
        return commonMapper.selPosIdByPCId(id, type);
    }

    @Override
    public Position selPosById(Integer id) {
        return commonMapper.selPosById(id);
    }

    @Override
    public int posChange(PositionChange positionChange) {
        System.out.println(positionChange);
        return commonMapper.empChangePos(positionChange.getStartTime(),
                positionChange.getType().getValue(),positionChange.getEmpReason(),positionChange.geteId().getId(),
                positionChange.getpId().getId(),positionChange.gettId().getId());
    }

    @Override
    public Position selTIdByPCId(Integer id) {
        return commonMapper.selPosById(commonMapper.selTIdByPCId(id));
    }

    @Override
    public List<PositionChange> selAllLeavePCByDeptId(Integer id) {
        List<PositionChange> pcs = commonMapper.selAllPCByDeptId(id,Type.LEAVE.getValue());
        for (PositionChange pc:pcs
             ) {
            pc.seteId(commonMapper.selEmpById(commonMapper.selEidByPCId(pc.getId())));
            pc.setpId(commonMapper.selPosById(commonMapper.selPidByPCId(pc.getId())));
        }
        return pcs;
    }

    @Override
    public List<PositionChange> selAllPosChangeByDeptId(Integer id) {
        List<PositionChange> pcs = commonMapper.selAllPCByDeptId(id, Type.CHANGE.getValue());
        for (PositionChange pc:pcs
             ) {
            pc.seteId(commonMapper.selEmpById(commonMapper.selEidByPCId(pc.getId())));
            pc.setpId(commonMapper.selPosById(commonMapper.selPidByPCId(pc.getId())));
            pc.settId(commonMapper.selPosById(commonMapper.selTIdByPCId(pc.getId())));
        }
        return pcs;
    }

    @Override
    public PositionChange selPCById(Integer id) {
        return commonMapper.selPCById(id);
    }

    @Override
    public int managerDealLeave(Integer pcId, String dmComment, String dmStatus) {
        return commonMapper.updPCByDM(pcId, dmStatus, dmComment);
    }

    @Override
    public CheckSetting selCS() {
        return commonMapper.selCS();
    }

    @Override
    public String empMCheckStatus(Integer eid, String nowDate) {
        Checking check = commonMapper.selCheckByEid(eid, nowDate);
        if (check == null) {
            commonMapper.addCheckByEid(eid, nowDate);
            return "上班未打卡";
        } else {
            if (check.getMorningCheck() == null) {
                return "上班未打卡";
            } else {
                return "上班已打卡";
            }
        }
    }

    @Override
    public String empECheckStatus(Integer eId, String nowDate) {
        Checking check = commonMapper.selCheckByEid(eId, nowDate);
        if (check == null) {
            commonMapper.addCheckByEid(eId, nowDate);
            return "下班未打卡";
        } else {
            if(check.getEveningCheck() == null) {
                return "下班未打卡";
            } else {
                return "下班已打卡";
            }
        }
    }

    @Override
    public int empMCheck(Checking check) {
        return commonMapper.updMCheckByEid(check.getEmp().getId(),check.getNowDate(),check.getMorningCheck(),check.getmState());
    }

    @Override
    public int empECheck(Checking check) {
        return commonMapper.updECheckByEid(check.getEmp().getId(),check.getNowDate(),check.getEveningCheck(),check.geteState());
    }

    @Override
    public List<Checking> selAllCheckByEid(Integer eId) {
        return commonMapper.selAllCheckByEid(eId);
    }

    @Override
    public List<Checking> selCheckByEmpAndDay(Integer eId, String nowDate) {
        return commonMapper.selCheckByEmp(eId, nowDate);
    }

    @Override
    public List<Checking> selCheckByEmpAndDate(Integer eId, String startTime, String endTime) {
        return commonMapper.selCheckByDate(eId, startTime, endTime);
    }


}
