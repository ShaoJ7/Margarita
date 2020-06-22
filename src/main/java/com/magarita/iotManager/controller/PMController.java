package com.magarita.iotManager.controller;

import com.magarita.iotManager.pojo.*;
import com.magarita.iotManager.service.impl.CommonServiceImpl;
import com.magarita.iotManager.service.impl.PMServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 人事部门SURPERcontroller
 */

@Controller
public class PMController {

    @Autowired
    private CommonServiceImpl commonServiceImpl;

    @Autowired
    private PMServiceImpl pmServiceImpl;
    /**
     * 跳转到企业设备管理界面
     * @return
     */
    @GetMapping("/emps")
    public String toEmpsPage(HttpSession session) {
        List<Location> depts = commonServiceImpl.selAllDept();
        session.setAttribute("depts",depts);
        return "personnel/emps";
    }

    /**
     * 跳转到设备修改界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/emp/{id}")
    public String toEmpEditPage(@PathVariable("id") Integer id, Model model) {
        Position position = pmServiceImpl.selPosByEmpId(id);
        model.addAttribute("pos",position);
        return "personnel/empedit";
    }

    /**
     * 修改设备信息
     * @param username 设备IP地址
     * @param realname
     * @param category
     * @param address
     * @param email
     * @param birth
     * @param nation
     * @param bankcard
     * @param telphone
     * @param age
     * @param identity
     * @param education
     * @param experience
     * @return
     */
    @PutMapping("/emp")
    @ResponseBody
    public String EmpEdit(String id,String username,String realname,String category,String address,String email,String birth,String nation,
                          String bankcard,String telphone,String age,String identity,String education,String experience) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Employee employee = new Employee();
        employee.setId(Integer.parseInt(id));
        employee.setUsername(username);
        employee.setRealname(realname);
        employee.setCategory(category);
        employee.setAddress(address);
        employee.setEmail(email);
        try {
            employee.setBirth(sdf.parse(birth));
        } catch (ParseException e) {
            return "生产日期格式有误";
        }
        employee.setNation(nation);
        employee.setBankcard(bankcard);
        employee.setTelphone(telphone);
        employee.setAge(Integer.parseInt(age));
        employee.setIdentity(identity);
        employee.setEducation(education);
        employee.setExperience(experience);
        System.out.println(employee);
        pmServiceImpl.updEmpById(employee);
        return "修改成功";
    }


    /**
     * 跳转到部门管理界面
     * @param model
     * @return
     */
    @GetMapping("/depts")
    public String toDeptPage(Model model) {
        List<Position> positions = pmServiceImpl.selAllPos();
        model.addAttribute("positions",positions);
        List<Location> depts = commonServiceImpl.selAllDept();
        model.addAttribute("depts",depts);
        return "personnel/depts";
    }

    /**
     * 跳转到部门添加界面
     * @return
     */
    @GetMapping("/dept")
    public String toAddDeptPage() {
        return "personnel/adddept";
    }

    /**
     * 部门添加
     * @return
     */
    @PostMapping("/dept")
    @ResponseBody
    public String addDept(String name, String comment) {
        List<Location> depts = commonServiceImpl.selAllDept();
        for (Location dept:depts
             ) {
            if(dept.getName().equals(name)) {
                return "部门名称已存在";
            }
        }
        Location location = new Location();
        location.setName(name);
        location.setComment(comment);
        pmServiceImpl.addDept(location);
        return "部门添加成功";
    }

    /**
     * 前往部门修改界面
     * @param id
     * @return
     */
    @GetMapping("/dept/{id}")
    public String toEditDeptPage(@PathVariable("id") Integer id, Model model) {
        Location location = pmServiceImpl.selDeptById(id);
        System.out.println(location);
        model.addAttribute("dept",location);
        return "personnel/editdept";
    }

    /**
     * 修改一个部门
     * @param location
     * @return
     */
    @PutMapping("/dept")
    @ResponseBody
    public String editDept(Location location) {
        List<Location> depts = commonServiceImpl.selAllDept();
        depts.remove(pmServiceImpl.selDeptById(location.getId()));
        for (Location dept:depts
             ) {
            if (dept.getName().equals(location.getName())) {
                return "部门名称已存在";
            }
        }
        pmServiceImpl.updDeptById(location);
        return "部门修改成功";
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @DeleteMapping("/dept/{id}")
    public String delDept(@PathVariable("id") Integer id) {
        Location dept = pmServiceImpl.selDeptById(id);
        pmServiceImpl.delDeptById(dept);
        return "redirect:/depts";
    }

    /**
     * 根据id修改职位信息
     * @param id
     * @return
     */
    @GetMapping("/pos/{id}")
    public String toEidtPositionPage(@PathVariable("id") Integer id,Model model) {
        Position position = pmServiceImpl.selPosById(id);
        model.addAttribute("pos",position);
        return "personnel/editpos";
    }

    /**
     * 修改职位信息
     * @param name
     * @param salary
     * @return
     */
    @PutMapping("/pos")
    @ResponseBody
    public String editPosition(Integer id,String name, String salary) {
        List<Position> positions = pmServiceImpl.selAllPos();
        positions.remove(pmServiceImpl.selPosById(id));
        for (Position position:positions
             ) {
            if(position.getName().equals(name)) {
                return "职位已存在,添加职位失败";
            }
        }
        Position position = new Position();
        position.setId(id);
        position.setName(name);
        position.setSalary(Double.parseDouble(salary));
        pmServiceImpl.updPosById(position);
        return "职位添加成功";
    }

    /**
     * 删除职位
     * @param id
     * @return
     */
    @DeleteMapping("/pos/{id}")
    public String delPos(@PathVariable("id") Integer id) {
        Position position = pmServiceImpl.selPosById(id);
        pmServiceImpl.delPositionById(position);
        return "redirect:/depts";
    }

    /**
     * 跳转到职位添加界面
     * @return
     */
    @GetMapping("/pos")
    public String toAddPosPage(Model model) {
        model.addAttribute("depts",commonServiceImpl.selAllDept());
        return "personnel/addpos";
    }

    /**
     * 添加职位
     * @param name
     * @param salary
     * @param deptId
     * @return
     */
    @PostMapping("/pos")
    @ResponseBody
    public String addPos(String name, String salary, String deptId) {
        List<Position> positions = pmServiceImpl.selAllPos();
        for (Position pos:positions
             ) {
            if(pos.getName().equals(name)) {
                return "职位名称已存在，添加职位失败";
            }
        }
        Position pos = new Position();
        pos.setName(name);
        pos.setSalary(Double.parseDouble(salary));
        pos.setLocation(pmServiceImpl.selDeptById(Integer.parseInt(deptId)));
        pmServiceImpl.addPos(pos);
        return "职位添加成功";
    }


    /**
     * 跳转到设备上线界面
     * @return
     */
    @GetMapping("/entry")
    public String toAddEmpPage() {
        return "personnel/entry";
    }

    /**
     * 添加上线设备信息
     * @param username
     * @param realname
     * @param deviceport
     * @param category
     * @param address
     * @param email
     * @param birth
     * @param nation
     * @param bankcard
     * @param telphone
     * @param age
     * @param identity
     * @param education
     * @param experience
     * @return
     */
    @PostMapping("/entry")
    @ResponseBody
    public String addEmp(String username, String realname, String deviceport, String category, String address, String email, String birth, String nation,
                         String bankcard, String telphone, String age, String identity, String education, String experience) throws ParseException {
        Employee employee1 = commonServiceImpl.selEmpByUserName(username);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(employee1 != null) {
            return "该设备IP地址已存在";
        }
        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setRealname(realname);
        employee.setDeviceport(deviceport);
        employee.setCategory(category);
        employee.setAddress(address);
        employee.setEmail(email);
        employee.setBirth(sdf.parse(birth));
        employee.setNation(nation);
        employee.setBankcard(bankcard);
        employee.setTelphone(telphone);
        employee.setAge(Integer.parseInt(age));
        employee.setIdentity(identity);
        employee.setEducation(education);
        employee.setExperience(experience);
        pmServiceImpl.addEmp(employee);
        return "设备基本信息添加成功";
    }

    /**
     * 跳转到职工分配部署界面
     * @param model
     * @return
     */
    @GetMapping("/transfer")
    public String toTranFerPage(Model model) {
        List<Position> positions = pmServiceImpl.selEmptyPos();
        model.addAttribute("pos",positions);
        List<Employee> employees = pmServiceImpl.selNOPosEmp();
        model.addAttribute("emps",employees);
        return "personnel/transfer";
    }

    /**
     * 跳转到职位分配界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/transfer/{id}")
    public String toAddPosToEmpPage(@PathVariable("id") Integer id,Model model) {
        List<Position> positions = pmServiceImpl.selEmptyPos();
        Employee employee = pmServiceImpl.selEmpById(id);
        model.addAttribute("emp",employee);
        model.addAttribute("pos",positions);
        return "personnel/job";
    }

    /**
     * 给上线设备添加职位
     * @param id
     * @param posId
     * @return
     */
    @PutMapping("/transfer")
    @ResponseBody
    public String updPosByEmpId(Integer id,Integer posId) {
        pmServiceImpl.updPosEId(posId,id);
        return "添加职位成功";
    }

    /**
     * 跳转到HYPER处理设备下线请求界面
     * @param model
     * @return
     */
    @GetMapping("/pmdelr")
    public String showAllLeaveAfterGMCheck(Model model) {
        List<PositionChange> pcs = pmServiceImpl.selAllLeaveAfterGMAgree();
        List<PositionChange> checked = new ArrayList<>();
        List<PositionChange> uncheck = new ArrayList<>();
        for (PositionChange pc:pcs
             ) {
            if(pc.getEffectTime() != null) {
                checked.add(pc);
            } else {
                uncheck.add(pc);
            }
        }
        model.addAttribute("checked",checked);
        model.addAttribute("uncheck",uncheck);
        return "personnel/pmdpcr";
    }

    /**
     * 跳转到下线生效时间处理界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/pmdelr/{id}")
    public String toDealLeavePage(@PathVariable("id") Integer id, Model model) {
        pmServiceImpl.pmDealEmpLeave(id,new Date(System.currentTimeMillis()));
        return "redirect:/pmdelr";
    }

    /**
     * 跳转到HYPER处理设备部署调动请求界面
     * @param model
     * @return
     */
    @GetMapping("/pmepcr")
    public String showAllPCAfterGMCheck(Model model) {
        List<PositionChange> pcs = pmServiceImpl.selAllPCAfterGMAgree();
        List<PositionChange> checked = new ArrayList<>();
        List<PositionChange> uncheck = new ArrayList<>();
        for (PositionChange pc:pcs
        ) {
            if(pc.getEffectTime() != null) {
                checked.add(pc);
            } else {
                uncheck.add(pc);
            }
        }
        model.addAttribute("checked",checked);
        model.addAttribute("uncheck",uncheck);
        return "personnel/pmepcr";
    }

    /**
     * 跳转到部署调动生效时间处理界面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/pmepcr/{id}")
    public String toDealPCPage(@PathVariable("id") Integer id, Model model) {
        pmServiceImpl.pmDealEmpPosChange(id,new Date(System.currentTimeMillis()));
        return "redirect:/pmepcr";
    }

    /**
     * 跳转到查询设备请假界面
     * @param model
     * @return
     */
    @GetMapping("/ers")
    public String toShowEmpsRequests(Model model) {
        List<Request> requests = pmServiceImpl.selAllReqsByDMAgree();
        model.addAttribute("reqs",requests);
        return "personnel/empsreqs";
    }

//    /**
//     * 跳转到查询设备考勤界面
//     * @param model
//     * @return
//     */
//    @GetMapping("/ecs")
//    public String toShowEmpsChecks(Model model) {
//        List<Check> checks = pmServiceImpl.selAllCheck();
//        model.addAttribute("checks",checks);
//        return "personnel/empschecks";
//    }

    /**
     * 跳转到考勤时间设置界面
     * @return
     */
    @GetMapping("/checkTimeSet")
    public String toSettingCheckTimePage(Model model) {
        model.addAttribute("cse",pmServiceImpl.selCS());
        return "personnel/checksetting";
    }

    /**
     * HYPER设置考勤时间
     * @param morning_check_time_last
     * @param evening_check_time_first
     * @return
     */
    @PostMapping("/checkTimeSet")
    public String gmSettingCheckTime(String morning_check_time_last, String evening_check_time_first) throws ParseException {
        pmServiceImpl.gmSettingCheckTiming(morning_check_time_last, evening_check_time_first);
        return "redirect:/checkTimeSet";
    }

    /**
     * 跳转到设备出勤界面
     * @return
     */
    @GetMapping("/attendance")
    public String toAttendancePage() {
        return "personnel/attendance";
    }

    /**
     * 查询出设备出勤情况
     * @param startTime
     * @param endTime
     * @param username
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/attendance")
    public String selCheck(@RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime,
                           @RequestParam(required = false) String username, Model model, HttpSession session) {
        Position pos = (Position) session.getAttribute("pos");
        if("".equals(startTime) || startTime == null) {
            startTime = null;
        }
        if("".equals(endTime) || endTime == null) {
            endTime = null;
        }
        if("".equals(username) || username == null) {
            username = null;
        }
        List<Checking> checkings = null;
        List<Request> requests = null;
        Integer eId = pmServiceImpl.selEmpIdByEmpUsername(username);
        if(startTime == null) {
            checkings = pmServiceImpl.selChecksByPersonnelDay(eId,endTime);
            requests = pmServiceImpl.selRequestsByPersonnelDay(eId,endTime,Status.AGREE.getValue());
        } else if (endTime == null){
            checkings = pmServiceImpl.selChecksByPersonnelDay(eId,startTime);
            requests = pmServiceImpl.selRequestsByPersonnelDay(eId,startTime,Status.AGREE.getValue());
        } else {
            checkings = pmServiceImpl.selChecksByPersonnelDate(eId,startTime,endTime);
            requests = pmServiceImpl.selRequestsByPersonnelDate(eId,startTime,endTime,Status.AGREE.getValue());
        }
        model.addAttribute("checks", checkings);
        model.addAttribute("requests",requests);
        return "personnel/attendance";
    }

    /**
     * 跳转到文件上传界面
     * @return
     */
    @GetMapping("/salary")
    public String toUploadSalaryFilePage() {
        return "personnel/upload";
    }

    /**
     * 实现文件上传
     * @param file
     * @return
     */
    @PostMapping("/salary")
    @ResponseBody
    public String fileUpload(@RequestParam("fileName") MultipartFile file){
        if(file.isEmpty()){
            return "文件为空，上传文件失败";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        String c = System.getProperty("user.dir");
        File fileMkDir = new File(c+"\\src\\main\\resources\\static\\uploads");
        File dest = new File(fileMkDir.getPath() + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "文件上传成功";
//            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "上传过程中出现异常，请重新上传";
//            return null;
        }
    }
}







