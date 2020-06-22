package com.magarita.iotManager.mapper;

import com.magarita.iotManager.pojo.Employee;
import com.magarita.iotManager.pojo.Position;
import com.magarita.iotManager.pojo.PositionChange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * HEAVENmapper
 */
@Mapper
public interface GMMapper {

    /**
     * 条件查询
     * @param
     * @return
     */
    @Select("<script>" +
            "select * from employee" +
            "<where>" +
            "   <if test='username != null'>" +
            "       and username like '%${username}%'" +
            "   </if>" +
            "   <if test='realname != null'>" +
            "       and realname like '%${realname}%'" +
            "   </if>" +
            "   <if test='deptId != null'>" +
            "       and id in (select e_id from position where d_id=#{deptId})" +
            "   </if>" +
            "</where>" +
            "</script>")
    public List<Employee> selEmpByFuzzy(String username, String realname, String deptId);


    /**
     * 条件记录总数
     * @param
     * @return
     */
    @Select("<script>" +
            "select count(*) from employee" +
            "<where>" +
            "   <if test='username != null'>" +
            "       and username like '%${username}%'" +
            "   </if> " +
            "   <if test='realname != null'>" +
            "       and realname like '%${realname}%'" +
            "   </if>" +
            "   <if test='deptId != null'>" +
            "       and id in (select e_id from position where d_id=#{deptId})" +
            "   </if>" +
            "</where>" +
            "</script>")
    public Integer getTotalCountsByName(String username, String realname, String deptId);

    /**
     * 查询总的设备数
     * @return
     */
    @Select("select count(*) from employee")
    public Integer selCountByAllEmp();

    /**
     * 根据类别查询职工
     * @param category
     * @return
     */
    @Select("select count(*) from employee where category=#{category}")
    public Integer selEmpCountByCategory(String category);

    /**
     * 查询职工平均使用年限
     * @return
     */
    @Select("select avg(age) from employee")
    public Integer selEmpCountByAvgAge();

    /**
     * 查询部门数
     * @return
     */
    @Select("select count(*) from location")
    public Integer selCountByAllDept();

    /**
     * 查询到经过部门SURPER处理过的职位请求
     * @param dmStatus
     * @return
     */
    @Select("select * from position_change where dm_status=#{dmStatus} and type=#{type}")
    public List<PositionChange> selAllPCAfterPMDeal(String dmStatus,String type);

    /**
     * 根据职位变更表id查询出设备信息
     * @param id
     * @return
     */
    @Select("select * from employee where id in (select e_id from position_change where id=#{id})")
    public Employee selEIdById(Integer id);

    /**
     * 根据职位变更表id查询出职位信息
     * @param id
     * @return
     */
    @Select("select * from position where id in (select p_id from position_change where id=#{id})")
    public Position selPIdById(Integer id);

    /**
     * 根据职位变更表id查询出职位信息
     * @param id
     * @return
     */
    @Select("select * from position where id in (select t_id from position_change where id=#{id})")
    public Position selTIdById(Integer id);

    /**
     * 管理员处理设备下线请求
     * @param pcId
     * @param gmComment
     * @param gmStatus
     * @return
     */
    @Update("update position_change set gm_comment=#{gmComment},gm_status=#{gmStatus} where id=#{pcId}")
    public Integer updPCByGm(Integer pcId, String gmComment, String gmStatus);

}
