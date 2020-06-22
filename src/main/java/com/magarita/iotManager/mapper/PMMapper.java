package com.magarita.iotManager.mapper;

import com.magarita.iotManager.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface PMMapper {

    /**
     * 根据设备id更新设备信息
     * @param employee
     * @return
     */
    @Update("update employee set realname=#{realname},category=#{category},address=#{address},email=#{email},birth=#{birth},nation=#{nation}," +
            "bankcard=#{bankcard},telphone=#{telphone},age=#{age},identity=#{identity},education=#{education},experience=#{experience}" +
            " where id=#{id}")
    int updEmpById(Employee employee);

    /**
     * 查询出所有的职位信息
     * @return
     */
    @Select("select * from position")
    List<Position> selAllPos();

    /**
     * 根据设备IP地址查询设备id
     * @param username
     * @return
     */
    @Select("select id from employee where username=#{username}")
    Integer selEmpIdByEmpUsername(String username);

    /**
     * 添加一个部门
     * @param location
     * @return
     */
    @Insert("insert into location values(default,#{name},#{comment})")
    int addDept(Location location);

    /**
     * 通过部门id查询出部门信息
     * @param id
     * @return
     */
    @Select("select * from location where id=#{id}")
    Location selDeptById(Integer id);

    /**
     * 通过id修改部门
     * @param location
     * @return
     */
    @Update("update location set name=#{name},comment=#{comment} where id=#{id}")
    int updDeptById(Location location);

    /**
     * 根据id删除部门
     * @param location
     * @return
     */
    @Delete("delete from location where id=#{id}")
    public int delDeptById(Location location);

    /**
     * 根据id删除职位
     * @param position
     * @return
     */
    @Delete("delete from position where id=#{id}")
    public int delPosById(Position position);

    /**
     * 根据id查询出职位信息
     * @param id
     * @return
     */
    @Select("select * from position where id=#{id}")
    public Position selPosById(Integer id);

    /**
     * 根据职位id修改职位信息
     * @param position
     * @return
     */
    @Update("update position set name=#{name},salary=#{salary} where id=#{id}")
    public int updPosById(Position position);

    /**
     * 增加一条职位信息
     * @param name
     * @param salary
     * @param d_id
     * @return
     */
    @Insert("insert into position values(default,#{name},#{salary},#{d_id},null)")
    public int addPos(String name, Double salary, Integer d_id);

    /**
     * 添加一条上线设备基本信息
     * @param employee
     * @return
     */
    @Insert("insert into employee values(default,#{username},#{deviceport},#{realname},#{category},#{address},#{email},#{birth},#{nation}" +
            ",#{bankcard},#{telphone},#{age},#{identity},#{education},#{experience},default)")
    public int addEmp(Employee employee);

    /**
     * 查询出所有的空缺职位
     * @return
     */
    @Select("select * from position where e_id is null")
    public List<Position> selEmptyPos();

    /**
     * 查询出所有的在职设备id
     * @return
     */
    @Select("select e_id from position")
    public List<Integer> selAllEmpIdWithPos();

    /**
     * 查询出所有的设备
     * @return
     */
    @Select("select * from employee")
    public List<Employee> selAllEmp();

    /**
     * 根据设备id查询出设备信息
     * @param id
     * @return
     */
    @Select("select * from employee where id=#{id}")
    public Employee selEmpById(Integer id);

    /**
     * 更新职位
     * @param id
     * @param eId
     * @return
     */
    @Update("update position set e_id=#{eId} where id=#{id}")
    public int updPosEId(Integer id,Integer eId);

    /**
     * 根据HEAVEN处理结果和申请类型查找职位调动信息
     * @param gmStatus
     * @param type
     * @return
     */
    @Select("select * from position_change where gm_status=#{gmStatus} and type=#{type}")
    public List<PositionChange> selAllPCAfterGM(String gmStatus, String type);

    /**
     * HYPER处理设备职位请求
     * @param pcId
     * @param effectTime
     * @return
     */
    @Update("update position_change set effect_time=#{effectTime} where id=#{pcId}")
    public  int updPCByPM(Integer pcId, Date effectTime);

    /**
     * 根据设备id删除设备信息
     * @param id
     * @return
     */
    @Update("update employee set isdel=1 where id=#{id}")
    public int delEmpById(Integer id);

    /**
     * 根据职位id删除职位表中的设备信息
     * @param id
     * @return
     */
    @Update("update position set e_id=null where id=#{id}")
    public int updPosEIdById(Integer id);

    /**
     * 根据职位表id更新设备职位id
     * @param id 原职位id
     * @param eId 新职位id
     * @return
     */
    @Update("update position set e_id=#{eId} where id=#{id}")
    public int updeIdById(Integer id,Integer eId);

    /**
     * HYPER查询设备的请假申请
     * @param status
     * @return
     */
    @Select("select * from request where status=#{status}")
    public List<Request> selReqByStatus(String status);

    /**
     * HYPER查询出所有的考勤记录
     * @return
     */
    @Select("select * from checking")
    public List<Checking> selAllChecks();

    /**
     * HYPER设置考勤时间段
     * @param morningCheckLast
     * @param eveningCheckFirst
     * @return
     */
    @Update("update check_setting set morning_check_last=#{morningCheckLast},evening_check_first=#{eveningCheckFirst} where id=1")
    public Integer updCheckSetting(String morningCheckLast, String eveningCheckFirst);

    /**
     * 查看考勤时间设置
     * @return
     */
    @Select("select * from check_setting where id=1")
    public CheckSetting selCS();

    /**
     * 通过考勤表id查询出设备id
     * @param id
     * @return
     */
    @Select("select * from employee where id in (select e_id from checking where id=#{id})")
    public Employee selEmpIdByCheckId(Integer id);

    /**
     * HYPER查询考勤记录
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
    public List<Checking> selCheckByPersonnelDay(Integer eId, String nowDate);

    /**
     * 根据时间查询考勤记录
     * @param eId
     * @param startTime
     * @param endTime
     * @return
     */

    @Select("<script>" +
            "select * from checking" +
            "<where>" +
            "   <if test='startTime != null and endTime != null'>" +
            "       and now_date between #{startTime} and #{endTime}" +
            "   </if> " +
            "   <if test='eId != null'>" +
            "       and e_id = #{eId}" +
            "   </if> " +
            "</where>" +
            "</script>")
    public List<Checking> selCheckByPersonnelDate(Integer eId, String startTime, String endTime);

    /**
     * HYPER查询请假记录
     * @param eId
     * @param nowDate
     * @return
     */
    @Select("<script>" +
            "select * from request" +
            "<where>" +
            "   <if test='eId != null'>" +
            "       and e_id = #{eId}" +
            "   </if> " +
            "   <if test='nowDate != null'>" +
            "       and start_time like '${nowDate}%'" +
            "   </if> " +
            "   <if test='status != null'>" +
            "       and status = #{status}" +
            "   </if> " +
            "</where>" +
            "</script>")
    public List<Request> selRequestByPersonnelDay(Integer eId, String nowDate, String status);

    /**
     * 根据时间查询考勤记录
     * @param eId
     * @param startTime
     * @param endTime
     * @return
     */

    @Select("<script>" +
            "select * from request" +
            "<where>" +
            "   <if test='startTime != null and endTime != null'>" +
            "       and start_time between #{startTime} and #{endTime}" +
            "   </if> " +
            "   <if test='eId != null'>" +
            "       and e_id = #{eId}" +
            "   </if> " +
            "   <if test='status != null'>" +
            "       and status = #{status}" +
            "   </if> " +
            "</where>" +
            "</script>")
    public List<Request> selRequestByPersonnelDate(Integer eId, String startTime, String endTime, String status);

}





