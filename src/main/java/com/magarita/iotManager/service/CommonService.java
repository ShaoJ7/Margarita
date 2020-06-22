package com.magarita.iotManager.service;

import com.magarita.iotManager.pojo.*;

import java.util.Date;
import java.util.List;

public interface CommonService {

    /**
     * 设备登录系统
     * @param username
     * @param deviceport
     * @return
     */
    public Employee selEmpByUNamePwd(String username, String deviceport);

    /**
     * 根据设备查询出设备职位
     * @param employee
     * @return
     */
    public Position selPosByEmp(Employee employee);

    /**
     * 设备修改登录密码
     * @param employee
     * @return
     */
    public int updPwdByEmp(Employee employee);


    /**
     * 查询所有部门
     * @return
     */
    List<Location> selAllDept();

    /**
     * 根据id查询出所属的备忘录
     * @param id
     * @return
     */
    List<Memo> selAllMemoByEmpId(Integer id);

    /**
     * 设备添加备忘录内容
     * @param memo
     * @return
     */
    int addMemoByEmp(Memo memo);

    /**
     * 设备删除备忘录内容
     * @param id
     * @return
     */
    int delMemoByEmp(Integer id);

    /**
     * 根据设备id查询请假申请表
     * @param id
     * @return
     */
    List<Request> selReqsByEmp(Integer id);

    /**
     * 添加请假请求
     * @param request
     * @return
     */
    int addReqByEmp(Request request);

    /**
     * 撤回请假申请
     * @param id
     * @return
     */
    int delReqByEmp(Integer id);

    /**
     * 根据部门id查询请假申请
     * @param id
     * @return
     */
    List<Request> selAllReqByDeptId(Integer id);

    /**
     * 处理请假申请
     * @param status
     * @param id
     * @return
     */
    int updReqStatusById(String status, Integer id);

    /**
     * 通过部门id查询出部门下所有设备的职位信息
     * @param id
     * @return
     */
    List<Position> selPosByDeptId(Integer id);

    /**
     * 设备添加信件
     * @param message
     * @return
     */
    int addMsgByEmp(Message message);

    /**
     * 根据设备IP地址查出设备信息
     * @param username
     * @return
     */
    Employee selEmpByUserName(String username);

    /**
     * 查询设备发送的所有信件
     * @param id
     * @return
     */
    List<Message> selAllMsgByFrom(Integer id);

    /**
     * 查询设备接收到的所有信件
     * @param id
     * @return
     */
    List<Message> selAllMsgByTo(Integer id);

    /**
     * 撤回信件
     * @param id
     * @return
     */
    Integer delMsgById(Integer id);

    /**
     * 处理信件
     * @param id
     * @return
     */
    Integer updMsgById(Integer id, Date endTime, String status);

    /**
     * 设备下线
     * @param positionChange
     * @return
     */
    Integer empLeave(PositionChange positionChange);

    /**
     * 根据设备id查询出所有的职位变动表
     * @param id
     * @return
     */
    List<PositionChange> selAllPosChangeByEmpId(Integer id,String type);

    /**
     * 根据职位申请id删除职位
     * @param id
     * @return
     */
    int delPosChange(Integer id);

    /**
     * 通过职位变更表id和类型查找职位id
     * @param id
     * @param type
     * @return
     */
    Integer selPosIdByPCId(Integer id,String type);

    /**
     * 通过职位id查询出职位信息
     * @param id
     * @return
     */
    public Position selPosById(Integer id);

    /**
     * 设备调岗
     * @param positionChange
     * @return
     */
    public int posChange(PositionChange positionChange);

    /**
     * 根据职位变更表id查询出调往部署信息
     * @param id
     * @return
     */
    public Position selTIdByPCId(Integer id);

    /**
     * 根据部门id查询出部门设备发送的下线请求
     * @param id
     * @return
     */
    public List<PositionChange> selAllLeavePCByDeptId(Integer id);

    /**
     * 根据部门id查询出部门设备发送的职位变更请求
     * @param id
     * @return
     */
    public List<PositionChange> selAllPosChangeByDeptId(Integer id);

    /**
     * 通过职位变更表id查询职位变更信息
     * @param id
     * @return
     */
    public PositionChange selPCById(Integer id);

    /**
     * 部门SURPER处理设备下线请求
     * @param pcId
     * @param dmComment
     * @param dmStatus
     * @return
     */
    public int managerDealLeave(Integer pcId, String dmComment, String dmStatus);

    /**
     * 查看打卡时间
     * @return
     */
    public CheckSetting selCS();

    /**
     * 判断设备上班是否已打卡
     * @return
     */
    public String empMCheckStatus(Integer eId, String nowDate);

    /**
     * 判断设备下班是否已打卡
     * @param eId
     * @param nowDate
     * @return
     */
    public String empECheckStatus(Integer eId, String nowDate);

    /**
     * 设备上班考勤
     * @param check
     * @return
     */
    public int empMCheck(Checking check);

    /**
     * 设备下班考勤
     * @param check
     * @return
     */
    public int empECheck(Checking check);

    /**
     * 设备id查询错所有的考勤记录
     * @param eId
     * @return
     */
    public List<Checking> selAllCheckByEid(Integer eId);

    /**
     * 设备查询一天考勤记录
     * @param eId
     * @param nowDate
     * @return
     */
    public List<Checking> selCheckByEmpAndDay(Integer eId, String nowDate);

    /**
     * 设备查询一段时间考勤记录
     * @param eId
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Checking> selCheckByEmpAndDate(Integer eId, String startTime, String endTime);
}
