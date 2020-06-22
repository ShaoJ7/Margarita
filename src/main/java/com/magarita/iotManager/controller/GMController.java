package com.magarita.iotManager.controller;

import com.magarita.iotManager.pojo.Location;
import com.magarita.iotManager.pojo.Employee;
import com.magarita.iotManager.pojo.Position;
import com.magarita.iotManager.pojo.PositionChange;
import com.magarita.iotManager.service.impl.CommonServiceImpl;
import com.magarita.iotManager.service.impl.GMServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GMController {

    @Autowired
    private GMServiceImpl gmServiceImpl;

    @Autowired
    private CommonServiceImpl commonServiceImpl;

    /**
     * 跳转到设备查询界面
     * @return
     */
    @GetMapping("/empsel")
    public String toSelEmpPage(HttpSession session) {
        List<Location> depts = commonServiceImpl.selAllDept();
        session.setAttribute("depts",depts);
        return "admin/empsel";
    }


    /**
     * 设备查询逻辑
     * @param username
     * @param realname
     * @param deptId
     * @param model
     * @return
     */
    @PostMapping("/empsel")
    public String selectEmpByName(@RequestParam(required = false) String username,
                                  @RequestParam(required = false) String realname,
                                  @RequestParam(required = false) String deptId,
                                  Model model, HttpSession session) {
        Position position = (Position) session.getAttribute("pos");
        if("".equals(username) || username == null) {
            username = null;
        }
        if("".equals(realname) || realname == null) {
            realname = null;
        }
        if("".equals(deptId) || deptId == null) {
            deptId = null;
        }
        Employee employee = new Employee();
        employee.setRealname(realname);
        employee.setUsername(username);
        List<Employee> emps = gmServiceImpl.selEmpByFuzzy(username,realname,deptId);
        Integer counts = gmServiceImpl.getTotalCountsByName(username, realname, deptId);
        List<Position> positions = new ArrayList<>();
        for (Employee emp:emps
             ) {
            Position pos = commonServiceImpl.selPosByEmp(emp);
            if(pos == null) {
                Position position1 = new Position();
                position1.setEmployee(emp);
                position1.setLocation(null);
                position1.setSalary(null);
                position1.setName(null);
                positions.add(position1);
            }
            positions.add(pos);
        }
        model.addAttribute("pos",positions);
        model.addAttribute("counts",counts);
        if(("HEAVEN").equals(position.getName())) {
            return "admin/empsel";
        } else if (("HYPER").equals(position.getName())) {
            return "personnel/emps";
        }
        return null;
    }

    /**
     * 跳转到企业概况界面(设备数量，部门数量，人均工资
     * @param model
     * @return
     */
    @GetMapping("/profile")
    public String toGeneralPage(Model model) {
        List<Integer> list = gmServiceImpl.companyProfile();
        model.addAttribute("profile",list);
        return "admin/profile";
    }

    /**
     * 跳转到管理员处理下线请求界面
     * @param model
     * @return
     */
    @GetMapping("/gmdealelr")
    public String toDealEmpLeaveRequestPage(Model model) {
        List<PositionChange> pcs = gmServiceImpl.selAllLeavePCByGMAgree();
        List<PositionChange> checked = new ArrayList<>();
        List<PositionChange> uncheck = new ArrayList<>();
        for (PositionChange pc:pcs
             ) {
            if (pc.getGmStatus() != null) {
                checked.add(pc);
            } else {
                uncheck.add(pc);
            }
        }
        model.addAttribute("checked",checked);
        model.addAttribute("uncheck",uncheck);
        return "admin/del";
    }

    /**
     * 跳转到管理员处理意见界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/gmdealelr/{id}")
    public String toAddLeaveComment(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id",id);
        return "admin/leavedeal";
    }

    /**
     * 处理下线请求
     * @param pcId
     * @param gmComment
     * @param gmStatus
     * @return
     */
    @PostMapping("/leavedeal")
    @ResponseBody
    public String dealLeaveRequestSuccess(Integer pcId, String gmComment, String gmStatus) {
        gmServiceImpl.gmDealEmpLeaveRequest(pcId, gmComment, gmStatus);
        return "处理成功";
    }

    /**
     * 跳转到管理员处理调岗请求界面
     * @param model
     * @return
     */
    @GetMapping("/gmdealepc")
    public String toDealEmpPositionChangePage(Model model) {
        List<PositionChange> pcs = gmServiceImpl.selAllPosChangeByGMAgree();
        List<PositionChange> checked = new ArrayList<>();
        List<PositionChange> uncheck = new ArrayList<>();
        for (PositionChange pc:pcs
        ) {
            if (pc.getGmStatus() != null) {
                checked.add(pc);
            } else {
                uncheck.add(pc);
            }
        }
        model.addAttribute("checked",checked);
        model.addAttribute("uncheck",uncheck);
        return "admin/dpc";
    }

    /**
     * 跳转到管理员处理意见界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/gmdealepc/{id}")
    public String toAddPosChangeComment(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id",id);
        return "admin/pcdeal";
    }

    /**
     * 处理下线请求
     * @param pcId
     * @param gmComment
     * @param gmStatus
     * @return
     */
    @PostMapping("/pcdeal")
    @ResponseBody
    public String dealPositionChangeRequestSuccess(Integer pcId, String gmComment, String gmStatus) {
        gmServiceImpl.gmDealEmpLeaveRequest(pcId, gmComment, gmStatus);
        return "处理成功";
    }
}
