package com.magarita.iotManager.service;

import com.magarita.iotManager.pojo.*;

import java.util.Date;
import java.util.List;

/**
 * HYPERservice
 */
public interface PMService {

    /**
     * 通过设备id查询出设备的职位信息
     * @param id
     * @return
     */
    public Position selPosByEmpId(Integer id);

    /**
     * 更新设备信息
     * @param employee
     * @return
     */
    public int updEmpById(Employee employee);

    /**
     * 查询出所有的职位信息
     * @return
     */
    public List<Position> selAllPos();

    /**
     * 添加一个部门
     * @param location
     * @return
     */
    int addDept(Location location);

    /**
     * 通过部门id查询出一个部门信息
     * @param id
     * @return
     */
    public Location selDeptById(Integer id);

    /**
     * 通过部门id修改部门
     * @param location
     * @return
     */
    int updDeptById(Location location);

    /**
     * 通过设备IP地址查询设备id
     * @param username
     * @return
     */
    Integer selEmpIdByEmpUsername(String username);

    /**
     * 删除一个部门
     * @param location
     * @return
     */
    int delDeptById(Location location);

    /**
     * 根据id查询出职位信息
     * @param id
     * @return
     */
    Position selPosById(Integer id);

    /**
     * 根据id修改职位信息
     * @param position
     * @return
     */
    int updPosById(Position position);

    /**
     * 根据id删除职位信息
     * @param position
     * @return
     */
    int delPositionById(Position position);

    /**
     * 添加一条职位信息
     * @param position
     * @return
     */
    int addPos(Position position);

    /**
     * 添加一条新上线设备信息
     * @param employee
     * @return
     */
    int addEmp(Employee employee);

    /**
     * 查询出空缺职位信息
     * @return
     */
    List<Position> selEmptyPos();

    /**
     * 查询出没有职位的设备
     * @return
     */
    List<Employee> selNOPosEmp();

    /**
     * 根据设备id查询出设备信息
     * @param id
     * @return
     */
    Employee selEmpById(Integer id);

    /**
     * 给上线设备分配职位
     * @param id
     * @param eId
     * @return
     */
    Integer updPosEId(Integer id, Integer eId);

    /**
     * 查出经过HEAVEN同意的下线请求
     * @return
     */
    public List<PositionChange> selAllLeaveAfterGMAgree();

    /**
     * 查找经过HEAVEN同意的调岗请求
     * @return
     */
    public List<PositionChange> selAllPCAfterGMAgree();

    /**
     * HYPER处理设备下线
     * @param pcId
     * @param effectTime
     * @return
     */
    public int pmDealEmpLeave(Integer pcId, Date effectTime);

    /**
     * HYPER处理设备调岗
     * @param pcId
     * @param effectTime
     * @return
     */
    public int pmDealEmpPosChange(Integer pcId, Date effectTime);

    /**
     * HYPER设备请假情况
     * @return
     */
    public List<Request> selAllReqsByDMAgree();

    /**
     * HYPER查看设备考勤状况
     * @return
     */
    public List<Checking> selAllCheck();

    /**
     * @param morningCheckLast
     * @param eveningCheckFirst
     * @return
     */
    int gmSettingCheckTiming(String morningCheckLast, String eveningCheckFirst);

    /**
     * 查询考勤时间设置
     * @return
     */
    CheckSetting selCS();

    /**
     * HYPER查询某一天的考勤记录
     * @param eId
     * @param nowDate
     * @return
     */
    List<Checking> selChecksByPersonnelDay(Integer eId, String nowDate);


    /**
     * HYPER查询某一段时间的考勤记录
     * @param eId
     * @param startTime
     * @param endTime
     * @return
     */
    List<Checking> selChecksByPersonnelDate(Integer eId, String startTime, String endTime);

    /**
     * HYPER查询某一天的请假记录
     * @param eId
     * @param nowDate
     * @return
     */
    List<Request> selRequestsByPersonnelDay(Integer eId, String nowDate, String status);


    /**
     * HYPER查询某一段时间的请假记录
     * @param eId
     * @param startTime
     * @param endTime
     * @return
     */
    List<Request> selRequestsByPersonnelDate(Integer eId, String startTime, String endTime, String status);

}
