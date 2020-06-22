package com.magarita.iotManager.service;

import com.magarita.iotManager.pojo.*;

import java.util.List;

/**
 * HEAVEN接口
 */
public interface GMService {


    /**
     * 条件查询
     * @return
     */
    public List<Employee> selEmpByFuzzy(String username, String realname, String deptId);

    /**
     *条件查询的记录数
     */
    public Integer getTotalCountsByName(String username, String realname, String deptId);

    /**
     * 获得企业概况
     * @return
     */
    public List<Integer> companyProfile();

    /**
     * 查询到所有经过部门SURPER同意的下线请求
     * @return
     */
    List<PositionChange> selAllLeavePCByGMAgree();

    /**
     * 查询到所有经过部门SURPER同意的调岗请求
     * @return
     */
    List<PositionChange> selAllPosChangeByGMAgree();

    /**
     * 管理员处理设备请假申请
     * @param pcId
     * @param gmComment
     * @param gmStatus
     * @return
     */
    int gmDealEmpLeaveRequest(Integer pcId, String gmComment, String gmStatus);


}











