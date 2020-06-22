package com.magarita.iotManager.mapper;

import com.magarita.iotManager.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface CommonMapper {

    /**
     * 设备登录
     * @param username
     * @param deviceport
     * @return
     */
    @Select("select * from employee where username=#{username} and deviceport=#{deviceport}")
    public Employee selEmpByUNamePwd(String username, String deviceport);

    /**
     * 根据职工信息查询出职位名称
     * @param employee
     * @return
     */
    @Select("select * from position where e_id=#{id}")
    public Position selPosByEmp(Employee employee);

    /**
     * 根据职位id查询出设备id
     * @param id
     * @return
     */
    @Select("select e_id from position where id=#{id}")
    public Integer selEmpIdByPosId(Integer id);

    /**
     * 根据职位id查询出部门id
     * @param id
     * @return
     */
    @Select("select d_id from position where id=#{id}")
    public int selDeptIdByPosId(Integer id);

    /**
     * 根据id查询设备信息
     * @param id
     * @return
     */
    @Select("select * from employee where id=#{id}")
    public Employee selEmpById(Integer id);

    /**
     * 根据部门id查询部门
     * @param id
     * @return
     */
    @Select("select * from location where id=#{id}")
    public Location selDeptById(Integer id);

    /**
     * 设备修改登录密码
     * @param employee
     * @return
     */
    @Update("update employee set deviceport=#{deviceport} where id=#{id}")
    public int updPwdByEmp(Employee employee);

    /**
     * 查询出所有的部门
     */
    @Select("select * from location")
    public List<Location> selAllDept();

    /**
     * 按部门名称查找部门信息
     * @param name
     * @return
     */
    @Select("select * from location where name=#{name}")
    public Location selDeptByName(String name);

    /**
     * 根据设备id查询出备忘录
     * @param id
     * @return
     */
    @Select("select * from memo where e_id=#{id} order by id desc")
    public List<Memo> selAllMemoByEmpId(Integer id);

    /**
     * 设备添加备忘录
     * @param
     * @return
     */
    @Insert("insert into memo(content,time,e_id) values(#{content},#{time},#{id})")
    public int addMemo(String content, Date time, Integer id);

    /**
     * 设备删除备忘录
     * @param id
     * @return
     */
    @Delete("delete from memo where id=#{id}")
    public int delMemo(Integer id);

    /**
     * 根据设备id查询出请假申请表
     * @param id
     * @return
     */
    @Select("select * from request where e_id=#{id}")
    public List<Request> selReqsByEmp(Integer id);

    /**
     * 设备发送请假请求
     * @param startTime
     * @param endTime
     * @param reason
     * @param id
     * @return
     */
    @Insert("insert into request(start_time,end_time,reason,e_id) values(#{startTime},#{endTime},#{reason},#{id})")
    public int addReqByEmpId(Date startTime, Date endTime, String reason, Integer id);

    /**
     * 设备撤回请假申请
     * @param id
     * @return
     */
    @Delete("delete from request where id = #{id}")
    public int delReqById(Integer id);

    /**
     * 通过部门id查询出部门下的所有设备信息
     * @param id
     * @return
     */
    @Select("select * from employee where id in (select e_id from position where d_id=#{id})")
    public List<Employee> selAllEmpByDept(Integer id);

    /**
     * 查询部门下设备提交的所有请假申请
     * @param id
     * @return
     */
    @Select("select * from request where e_id in (select e_id from position where d_id=#{id})")
    public List<Request> selAllReqByDeptId(Integer id);

    /**
     * 通过请求id查询出设备的id
     * @param id
     * @return
     */
    @Select("select * from employee where id in (select e_id from request where id=#{id})")
    public Employee selEmpIdByReqId(Integer id);

    /**
     * 通过id查询出请求信息
     * @param id
     * @return
     */
    @Select("select * from request where id=#{id}")
    public Request selReqById(Integer id);

    /**
     * 处理请假申请
     * @param status
     * @param id
     * @return
     */
    @Update("update request set status=#{status} where id=#{id}")
    public int updReqStatusById(String status,Integer id);

    /**
     * 发送邮件
     * @param from
     * @param to
     * @param content
     * @param startTime
     * @return
     */
    @Insert("insert into message values(default,#{from},#{to},#{content},#{startTime},null,default)")
    public int addMsgByEmp(Integer from, Integer to, String content, Date startTime);

    /**
     * 根据设备IP地址查出设备信息
     * @param username
     * @return
     */
    @Select("select * from employee where username=#{username}")
    public Employee selEmpByUserName(String username);

    /**
     * 通过设备id查询出发出的所有信件
     * @param id
     * @return
     */
    @Select("select * from message where from_id=#{id}")
    public List<Message> selMsgsByFrom(Integer id);

    /**
     * 通过设备id查询出其他设备发送来的信件
     * @param id
     * @return
     */
    @Select("select * from message where to_id=#{id}")
    public List<Message> selMsgsByTo(Integer id);

    /**
     * 通过设备id和信件状态查询发送来的信件
     * @param id
     * @param status
     * @return
     */
    @Select("select * from message where to_id=#{id} and status=#{status}")
    public List<Message> selMsgsByToStatus(Integer id, String status);

    /**
     * 通过信件id查询出发送设备id
     * @param id
     * @return
     */
    @Select("select from_id from message where id=#{id}")
    public Integer selFromIdByMsgId(Integer id);

    /**
     * 通过信件id查询出收件设备id
     * @param id
     * @return
     */
    @Select("select to_id from message where id=#{id}")
    public Integer selToIdByMsgId(Integer id);

    /**
     * 撤回一个信件
     * @param id
     * @return
     */
    @Delete("delete from message where id=#{id}")
    public int delMsgById(Integer id);

    /**
     * 处理信件
     * @param id
     * @param status
     * @return
     */
    @Update("update message set status=#{status},end_time=#{endTime} where id=#{id}")
    public int updMsgById(Integer id,String status,Date endTime);

    /**
     * 设备下线申请
     * @param startTime
     * @param type
     * @param empReason
     * @param eId
     * @param pId
     * @return
     */
    @Insert("insert into position_change(start_time,type,emp_reason,e_id,p_id) " +
            "values(#{startTime},#{type},#{empReason},#{eId},#{pId})")
    public int empLeavePos(Date startTime, String type, String empReason, Integer eId, Integer pId);

    /**
     * 根据职工id和请求类型查询出设备发出的所有下线申请
     * @param id
     * @return
     */
    @Select("select * from position_change where e_id=#{id} and type=#{type}")
    public List<PositionChange> selAllPosLeaveByEmpIdAndType(Integer id,String type);

    /**
     * 删除职位申请
     * @param id
     * @return
     */
    @Delete("delete from position_change where id=#{id}")
    public int delPosChange(Integer id);

    /**
     * 通过职位变更表id和类型查找职位id
     * @param id
     * @param type
     * @return
     */
    @Select("select p_id from position_change where id=#{id} and type=#{type}")
    public Integer selPosIdByPCId(Integer id,String type);

    /**
     * 通过职位id查询出职位信息
     * @param id
     * @return
     */
    @Select("select * from position where id=#{id}")
    public Position selPosById(Integer id);

    /**
     * 设备调岗申请
     * @param startTime
     * @param type
     * @param empReason
     * @param eId
     * @param pId
     * @return
     */
    @Insert("insert into position_change(start_time,type,emp_reason,e_id,p_id,t_id) " +
            "values(#{startTime},#{type},#{empReason},#{eId},#{pId},#{tId})")
    public int empChangePos(Date startTime, String type, String empReason, Integer eId, Integer pId, Integer tId);

    /**
     * 通过职位变更表id查询出调往部署id
     * @param id
     * @return
     */
    @Select("select t_id from position_change where id=#{id}")
    public int selTIdByPCId(Integer id);

    /**
     * 通过职位变更表id查询出调往设备id
     * @param id
     * @return
     */
    @Select("select e_id from position_change where id=#{id}")
    public int selEidByPCId(Integer id);

    /**
     * 通过职位变更表id查询出调往所在职位id
     * @param id
     * @return
     */
    @Select("select p_id from position_change where id=#{id}")
    public int selPidByPCId(Integer id);

    /**
     * 通过职位变更表id查询职位变更信息
     * @param id
     * @return
     */
    @Select("select * from position_change where id=#{id}")
    public PositionChange selPCById(Integer id);

    /**
     * 查询部门下设备提交的所有部署申请
     * @param id
     * @return
     */
    @Select("select * from position_change where e_id in (select e_id from position where d_id=#{id}) and type=#{type}")
    public List<PositionChange> selAllPCByDeptId(Integer id, String type);

    /**
     * 部门SURPER处理设备提交的下线请求
     * @param pcId
     * @param dmStatus
     * @param dmComment
     * @return
     */
    @Update("update position_change set dm_status=#{dmStatus},dm_comment=#{dmComment} where id=#{pcId}")
    public int updPCByDM(Integer pcId, String dmStatus, String dmComment);

    /**
     * 查询出考勤设置时间
     * @return
     */
    @Select("select * from check_setting where id=1")
    public CheckSetting selCS();

    /**
     * 根据设备id和考勤日期查询出考勤记录
     * @param eId
     * @return
     */
    @Select("select * from checking where e_id=#{eId} and now_date=#{now_date}")
    public Checking selCheckByEid(Integer eId, String now_date);

    /**
     * 添加设备考勤记录
     * @param eId
     * @param nowDate
     * @return
     */
    @Insert("insert into checking(e_id,now_date) values(#{eId},#{nowDate})")
    public int addCheckByEid(Integer eId, String nowDate);

    /**
     * 设备上班考勤
     * @param eId
     * @param nowDate
     * @param morningCheck
     * @param mState
     * @return
     */
    @Update("update checking set morning_check=#{morningCheck},m_state=#{mState} where e_id=#{eId} and now_date=#{nowDate}")
    public int updMCheckByEid(Integer eId, String nowDate, String morningCheck, String mState);

    /**
     * 设备下班考勤
     * @param eId
     * @param nowDate
     * @param eveningCheck
     * @param eState
     * @return
     */
    @Update("update checking set evening_check=#{eveningCheck},e_state=#{eState} where e_id=#{eId} and now_date=#{nowDate}")
    public int updECheckByEid(Integer eId, String nowDate, String eveningCheck, String eState);

    /**
     * 通过设备id查询出设备的所有考勤记录
     * @param eId
     * @return
     */
    @Select("select * from checking where e_id=#{eId} order by id desc")
    public List<Checking> selAllCheckByEid(Integer eId);

    /**
     * 设备查询考勤记录
     * @param eId
     * @param nowDate
     * @return
     */
    @Select("<script>" +
            "select * from checking" +
            "<where>" +
            "   <if test='eId != null'>" +
            "       and e_id = #{eId}" +
            "   </if> " +
            "   <if test='nowDate != null'>" +
            "       and now_date = #{nowDate}" +
            "   </if> " +
            "</where>" +
            "</script>")
    public List<Checking> selCheckByEmp(Integer eId, String nowDate);

    /**
     * 根据时间查询考勤记录
     * @param eId
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("select * from checking where now_date between #{startTime} and #{endTime} and e_id=#{eId}")
    public List<Checking> selCheckByDate(Integer eId, String startTime, String endTime);
}
