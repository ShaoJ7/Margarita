package com.magarita.iotManager.controller;

import com.magarita.iotManager.pojo.*;
import com.magarita.iotManager.service.impl.CommonServiceImpl;
import com.magarita.iotManager.service.impl.PMServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class CommonController {

    @Autowired
    private CommonServiceImpl commonServiceImpl;

    @Autowired
    private PMServiceImpl pmServiceImpl;



    /**
     * 展示个设备信息
     * @return
     */
    @GetMapping("/info")
    public String showInfo(HttpSession session, Model model) {
        Position pos = (Position) session.getAttribute("pos");
        model.addAttribute("pos",pos);
        if("HEAVEN".equals(pos.getName())) {
            return "admin/info";
        } else if("HYPER".equals(pos.getName())){
            return "personnel/info";
        } else if(pos.getName().contains("SUPER")) {
            return "generalmanager/info";
        } else {
            return "employee/info";
        }
    }

    /**
     * 设备登录
     * @param username
     * @param deviceport
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(String username, String deviceport, Map<String, Object> map, HttpSession session) {

        Employee emp = commonServiceImpl.selEmpByUNamePwd(username, deviceport);
        if(emp != null) {
            Position pos = commonServiceImpl.selPosByEmp(emp);
            if(pos != null) {
                session.setAttribute("pos",pos);
                session.setAttribute("loginUser",pos.getEmployee().getRealname());
                session.setAttribute("loginPos",pos.getName());
                if("HEAVEN".equals(pos.getName())) {
                    return "admin/index";
                } else if("HYPER".equals(pos.getName())){
                    return "personnel/index";
                } else if(pos.getName().contains("SURPER")) {
                    return "generalmanager/index";
                } else {
                    return "employee/index";
                }
            } else {
                map.put("msg","设备未分配部门或设备已下线");
                return "login";
            }
        } else {
            map.put("msg","设备不存在或登录名或密码错误");
            return "login";
        }
    }

    /**
     * 安全退出
     * @param request
     * @return
     */
    @GetMapping("/quit")
    public String quit(HttpServletRequest request) {
        request.getSession().invalidate();
        return "login";
    }
    /**
     * 跳转到密码修改界面
     * @return
     */
    @GetMapping("/updpwd")
    public String toEditPwd(HttpSession session) {
        Position pos = (Position) session.getAttribute("pos");
        if("HEAVEN".equals(pos.getName())) {
            return "admin/editpwd";
        } else if("HYPER".equals(pos.getName())){
            return "personnel/editpwd";
        } else if(pos.getName().contains("SURPER")) {
            return "generalmanager/editpwd";
        } else {
            return "employee/editpwd";
        }
    }

    /**
     * 设备修改端口
     * @param oldpwd
     * @param newpwd
     * @param session
     * @return
     */
    @PostMapping("/updpwd")
    @ResponseBody
    public String editpwd(String oldpwd, String newpwd, HttpSession session) {
        Position pos = (Position) session.getAttribute("pos");
        Employee emp = pos.getEmployee();
        if(!emp.getDeviceport().equals(oldpwd)) {
            return "原端口填写错误";
        } else {
            emp.setDeviceport(newpwd);
            commonServiceImpl.updPwdByEmp(emp);
            return "端口修改成功,重新连接";
        }
    }

    /**
     * 跳转到备忘录界面
     * @return
     */
    @GetMapping("/memos")
    public String toMemoPage(HttpSession session,Model model){
        Position pos = (Position) session.getAttribute("pos");
        List<Memo> memos = commonServiceImpl.selAllMemoByEmpId(pos.getEmployee().getId());
        model.addAttribute("memos",memos);
        if("HEAVEN".equals(pos.getName())) {
            return "admin/memo";
        } else if("HYPER".equals(pos.getName())){
            return "personnel/memo";
        } else if(pos.getName().contains("SURPER")) {
            return "generalmanager/memo";
        } else {
            return "employee/memo";
        }
    }

    /**
     * 来到备忘录添加界面
     * @param session
     * @return
     */
    @GetMapping("/memo")
    public String toaddMemoPage(HttpSession session) {
        Position pos = (Position) session.getAttribute("pos");
        if("HEAVEN".equals(pos.getName())) {
            return "admin/addmemo";
        } else if("HYPER".equals(pos.getName())){
            return "personnel/addmemo";
        } else if(pos.getName().contains("SURPER")) {
            return "generalmanager/addmemo";
        } else {
            return "employee/addmemo";
        }
    }

    /**
     * 增加备忘录内容
     * @param content
     * @return
     */
    @PostMapping("/memo")
    public String addMemoByEmp(HttpSession session,String content) {
        Position pos = (Position) session.getAttribute("pos");
        Memo memo = new Memo();
        memo.setEmp(pos.getEmployee());
        memo.setContent(content);
        memo.setTime(new Date(System.currentTimeMillis()));
        commonServiceImpl.addMemoByEmp(memo);
        if("HEAVEN".equals(pos.getName())) {
            return "redirect:/memos";
        } else if("HYPER".equals(pos.getName())){
            return "redirect:/memos";
        } else if(pos.getName().contains("SURPER")) {
            return "redirect:/memos";
        } else {
            return "redirect:/memos";
        }
    }

    /**
     * 删除备忘录
     * @param id
     * @return
     */
    @DeleteMapping("/memo/{id}")
    public String delMemoByEmp(@PathVariable("id") Integer id) {
        commonServiceImpl.delMemoByEmp(id);
        return "redirect:/memos";
    }

    /**
     * 跳转到请假页面
     * @param session
     * @return
     */
    @GetMapping("/requests")
    public String toRequestsPage(HttpSession session,Model model) {
        Position pos = (Position) session.getAttribute("pos");
        List<Request> reqs = commonServiceImpl.selReqsByEmp(pos.getEmployee().getId());
        model.addAttribute("reqs",reqs);
        if("HYPER".equals(pos.getName())){
            return "personnel/request";
        } else if(pos.getName().contains("SURPER")) {
            return "generalmanager/request";
        } else {
            return "employee/request";
        }
    }

    /**
     * 前往请假添加页面
     * @param session
     * @return
     */
    @GetMapping("/request")
    public String toAddRequestPage(HttpSession session) {
        Position pos = (Position) session.getAttribute("pos");
        if("HYPER".equals(pos.getName())){
            return "personnel/addrequest";
        } else if(pos.getName().contains("SURPER")) {
            return "generalmanager/addrequest";
        } else {
            return "employee/addrequest";
        }
    }

    /**
     * 添加请假申请的逻辑
     * @param startTime
     * @param endTime
     * @param reason
     * @return
     */
    @PostMapping("/request")
    public String AddRequest(String startTime, String endTime, String reason, HttpSession session) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = sdf.parse(startTime);
        Date end = sdf.parse(endTime);
        Position pos = (Position) session.getAttribute("pos");
        Request request = new Request();
        request.setEmp(pos.getEmployee());
        request.setStartTime(start);
        request.setEndTime(end);
        request.setReason(reason);
        commonServiceImpl.addReqByEmp(request);
        if("HYPER".equals(pos.getName())){
            return "redirect:/requests";
        } else if(pos.getName().contains("SURPER")) {
            return "redirect:/requests";
        } else {
            return "redirect:/requests";
        }
    }
    /**
     * 撤回请假申请
     * @param id
     * @return
     */
    @DeleteMapping("/request/{id}")
    public String delReqByEmp(@PathVariable("id") Integer id) {
        commonServiceImpl.delReqByEmp(id);
        return "redirect:/requests";
    }

    /**
     * 前往处理请假申请界面
     * @param session
     * @return
     */
    @GetMapping("/disposes")
    public String toDisposePage(HttpSession session,Model model) {
        Position pos = (Position) session.getAttribute("pos");
        List<Request> reqs = commonServiceImpl.selAllReqByDeptId(pos.getLocation().getId());
        List<Request> unChecked = new ArrayList<>();
        List<Request> checked = new ArrayList<>();
        for (Request req:reqs
             ) {
            if("未处理".equals(req.getStatus().getValue())) {
                unChecked.add(req);
            } else {
                checked.add(req);
            }
        }
        model.addAttribute("unchecked",unChecked);
        model.addAttribute("checked",checked);
        if(("HYPER").equals(pos.getName())) {
            return "personnel/dispose";
        } else if (pos.getName().contains("SURPER")) {
            return "generalmanager/dispose";
        }
        return null;
    }

    /**
     * 同意请假申请
     * @param id
     * @return
     */
    @GetMapping("/agree/{id}")
    public String agree(@PathVariable("id") Integer id) {
        commonServiceImpl.updReqStatusById(Status.AGREE.getValue(),id);
        return "redirect:/disposes";
    }

    /**
     * 驳回请假申请
     * @param id
     * @return
     */
    @GetMapping("/disagree/{id}")
    public String disagree(@PathVariable("id") Integer id) {
        commonServiceImpl.updReqStatusById(Status.DISAGREE.getValue(),id);
        return "redirect:/disposes";
    }

    /**
     * 前往显示部门下所有设备页面
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/employee")
    public String showEmps(HttpSession session,Model model) {
        Position pos = (Position) session.getAttribute("pos");
        List<Position> list = commonServiceImpl.selPosByDeptId(pos.getLocation().getId());
        model.addAttribute("positions",list);
        if(("HYPER").equals(pos.getName())) {
            return "personnel/emp";
        } else if (pos.getName().contains("SURPER")) {
            return "generalmanager/emp";
        }
        return null;
    }

    /**
     * 跳转到发送信件界面
     * @return
     */
    @GetMapping("/send")
    public String toSendMsgPage(HttpSession session) {
        Position pos = (Position) session.getAttribute("pos");
        if("HEAVEN".equals(pos.getName())) {
            return "admin/send";
        } else if("HYPER".equals(pos.getName())){
            return "personnel/send";
        } else if(pos.getName().contains("SURPER")) {
            return "generalmanager/send";
        } else {
            return "employee/send";
        }
    }

    /**
     * 发送信件
     * @param username
     * @param content
     * @param session
     * @return
     */
    @PostMapping("/send")
    @ResponseBody
    public String sendMsg(String username, String content, HttpSession session) {
        Position pos = (Position) session.getAttribute("pos");
        if(commonServiceImpl.selEmpByUserName(username) == null) {
            return "IP地址填写错误";
        }
        Message msg = new Message();
        msg.setFromId(pos.getEmployee());
        msg.setContent(content);
        msg.setToId(commonServiceImpl.selEmpByUserName(username));
        msg.setStartTime(new Date(System.currentTimeMillis()));
        commonServiceImpl.addMsgByEmp(msg);
        return "信件发送成功";
    }

    /**
     * 跳转到收件箱界面
     * @param session
     * @return
     */
    @GetMapping("/receive")
    public String toShowMsgPage(HttpSession session,Model model) {
        Position pos = (Position) session.getAttribute("pos");
        //查询出发出的所有信件
        List<Message> fromMsg = commonServiceImpl.selAllMsgByFrom(pos.getEmployee().getId());
        //查询到接收到的所有信件
        List<Message> toMsg = commonServiceImpl.selAllMsgByTo(pos.getEmployee().getId());
        List<Message> unCheckedMsg = new ArrayList<>();
        List<Message> checkedMsg = new ArrayList<>();
        for (Message msg:toMsg
             ) {
            if(msg.getStatus().equals(Status.UNSETTLED)) {
                unCheckedMsg.add(msg);
            } else {
                checkedMsg.add(msg);
            }
        }
        model.addAttribute("uncheck",unCheckedMsg);
        model.addAttribute("check",checkedMsg);
        model.addAttribute("from",fromMsg);
        if("HEAVEN".equals(pos.getName())) {
            return "admin/receive";
        } else if("HYPER".equals(pos.getName())){
            return "personnel/receive";
        } else if(pos.getName().contains("SURPER")) {
            return "generalmanager/receive";
        } else {
            return "employee/receive";
        }
    }

    /**
     * 撤回信件
     * @param id
     * @return
     */
    @GetMapping("/recall/{id}")
    public String recall(@PathVariable("id") Integer id) {
        commonServiceImpl.delMsgById(id);
        return "redirect:/receive";
    }

    /**
     * 同意信件
     * @param id
     * @return
     */
    @GetMapping("/consent/{id}")
    public String consent(@PathVariable("id") Integer id) {
        commonServiceImpl.updMsgById(id,new Date(System.currentTimeMillis()),Status.AGREE.getValue());
        return "redirect:/receive";
    }

    /**
     * 驳回信件
     * @param id
     * @return
     */
    @GetMapping("/unconsent/{id}")
    public String unConsent(@PathVariable("id") Integer id) {
        commonServiceImpl.updMsgById(id,new Date(System.currentTimeMillis()),Status.DISAGREE.getValue());
        return "redirect:/receive";
    }

    /**
     * 跳转到下线申请界面
     * @param session
     * @return
     */
    @GetMapping("/empleave")
    public String toLeavePage(HttpSession session,Model model) {
        Position pos = (Position) session.getAttribute("pos");
        List<PositionChange> list = commonServiceImpl.selAllPosChangeByEmpId(pos.getEmployee().getId(),Type.LEAVE.getValue());
        for (PositionChange pc:list
             ) {
            pc.seteId(pos.getEmployee());
            pc.setpId(pos);
        }
        model.addAttribute("poscge",list);
        if("HYPER".equals(pos.getName())){
            return "personnel/empleave";
        } else if(pos.getName().contains("SURPER")) {
            return "generalmanager/empleave";
        } else {
            return "employee/empleave";
        }
    }

    /**
     * 设备下线
     * @param session
     * @param empReason
     * @return
     */
    @PostMapping("/empleave")
    public String empLeave(HttpSession session,String empReason,Model model) {
        Position pos = (Position) session.getAttribute("pos");
        PositionChange pc = new PositionChange();
        pc.setStartTime(new Date(System.currentTimeMillis()));
        pc.setType(Type.LEAVE);
        pc.setEmpReason(empReason);
        pc.seteId(pos.getEmployee());
        pc.setpId(pos);
        commonServiceImpl.empLeave(pc);
        return "redirect:/empleave";
    }

    /**
     * 删除职位申请
     * @param id
     * @return
     */
    @DeleteMapping("/empleave/{id}")
    public String delPosChange(@PathVariable("id") Integer id) {
        commonServiceImpl.delPosChange(id);
        return "redirect:/empleave";
    }

    /**
     * 跳转到职位调动界面
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/poschange")
    public String toPosChangePage(HttpSession session,Model model) {
        Position pos = (Position) session.getAttribute("pos");
        List<PositionChange> list = commonServiceImpl.selAllPosChangeByEmpId(pos.getEmployee().getId(),Type.CHANGE.getValue());
        List<Position> positions = pmServiceImpl.selEmptyPos();
        for (PositionChange pc:list
        ) {
            pc.seteId(pos.getEmployee());
            pc.setpId(pos);
            pc.settId(commonServiceImpl.selTIdByPCId(pc.getId()));
        }
        model.addAttribute("poscge",list);
        model.addAttribute("pos",positions);
        if("HYPER".equals(pos.getName())){
            return "personnel/poschange";
        } else if(pos.getName().contains("SURPER")) {
            return "generalmanager/poschange";
        } else {
            return "employee/poschange";
        }
    }
    /**
     * 设备调岗
     * @param session
     * @param empReason
     * @return
     */
    @PostMapping("/poschange")
    @ResponseBody
    public String empLeave(HttpSession session,String empReason,String deptId,Model model) {
        Position pos = (Position) session.getAttribute("pos");
        PositionChange pc = new PositionChange();
        pc.setStartTime(new Date(System.currentTimeMillis()));
        pc.setType(Type.CHANGE);
        pc.setEmpReason(empReason);
        pc.seteId(pos.getEmployee());
        pc.setpId(pos);
        pc.settId(commonServiceImpl.selPosById(Integer.parseInt(deptId)));
        commonServiceImpl.posChange(pc);
        return "调岗申请发布成功";
    }

    /**
     * 撤回职位调动申请
     * @param id
     * @return
     */
    @DeleteMapping("/poschange/{id}")
    public String delPosCha(@PathVariable("id") Integer id) {
        commonServiceImpl.delPosChange(id);
        return "redirect:/poschange";
    }

    /**
     * 前往处理设备下线申请界面
     * @param session
     * @return
     */
    @GetMapping("/dealempleave")
    public String toDealEmpLeaveRequestPage(HttpSession session,Model model) {
        Position pos = (Position) session.getAttribute("pos");
        List<PositionChange> pcLeave = commonServiceImpl.selAllLeavePCByDeptId(pos.getLocation().getId());
        List<PositionChange> checked = new ArrayList<>();
        List<PositionChange> uncheck = new ArrayList<>();
        for (PositionChange pc:pcLeave
             ) {
            if(pc.getDmStatus() != null) {
                checked.add(pc);
            } else {
                uncheck.add(pc);
            }
        }
        model.addAttribute("checked",checked);
        model.addAttribute("uncheck",uncheck);
        return "generalmanager/dealempleave";
    }

    /**
     * 跳转到部门设备下线申请处理填写意见
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/dealempleave/{id}")
    public String dealEmpLeave(@PathVariable("id") Integer id,Model model) {
        PositionChange pc = commonServiceImpl.selPCById(id);
        model.addAttribute("pc",pc);
        return "generalmanager/leavedeal";
    }

    /**
     * 部门SURPER处理设备下线请求
     * @param pcId
     * @param dmComment
     * @param dmStatus
     * @return
     */
    @PostMapping("/dealleave")
    @ResponseBody
    public String dealLeave(Integer pcId, String dmComment, String dmStatus) {
        commonServiceImpl.managerDealLeave(pcId, dmComment, dmStatus);
        return "提交成功";
    }

    /**
     * 前往处理设备调岗申请界面
     * @param session
     * @return
     */
    @GetMapping("/dealempposchge")
    public String toDealEmpChangePosRequestPage(HttpSession session,Model model) {
        Position pos = (Position) session.getAttribute("pos");
        List<PositionChange> pcChange = commonServiceImpl.selAllPosChangeByDeptId(pos.getLocation().getId());
        List<PositionChange> checked = new ArrayList<>();
        List<PositionChange> uncheck = new ArrayList<>();
        for (PositionChange pc:pcChange
        ) {
            if(pc.getDmStatus() != null) {
                checked.add(pc);
            } else {
                uncheck.add(pc);
            }
        }
        model.addAttribute("checked",checked);
        model.addAttribute("uncheck",uncheck);
        return "generalmanager/dealempposchge";
    }

    /**
     * 跳转到部门设备调岗申请处理填写意见
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/dealempposchge/{id}")
    public String dealEmpPosChange(@PathVariable("id") Integer id,Model model) {
        PositionChange pc = commonServiceImpl.selPCById(id);
        model.addAttribute("pc",pc);
        return "generalmanager/pchangedeal";
    }

    /**
     * 部门SURPER处理设备调岗请求
     * @param pcId
     * @param dmComment
     * @param dmStatus
     * @return
     */
    @PostMapping("/pchangedeal")
    @ResponseBody
    public String dealPChange(Integer pcId, String dmComment, String dmStatus) {
        commonServiceImpl.managerDealLeave(pcId, dmComment, dmStatus);
        return "提交成功";
    }

    /**
     * 跳转到考勤界面
     * @param session
     * @return
     */
    @GetMapping("/checking")
    public String toCheckPage(HttpSession session,Model model) {
        Position pos = (Position) session.getAttribute("pos");
        //首先进行上班打卡和下班打卡的判断
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String now = sdf.format(new Date(System.currentTimeMillis()));
        int nHour = Integer.parseInt(now.split(":")[0]);
        //取出设置的打卡时间
        CheckSetting checkSetting = commonServiceImpl.selCS();
        int mHour = Integer.parseInt(checkSetting.getMorningCheckLast().split(":")[0]);
        int eHour = Integer.parseInt(checkSetting.getEveningCheckFirst().split(":")[0]);
        if(nHour > mHour-2 && nHour < 12) {
            session.setAttribute("checkTime","上班打卡");
        } else if(nHour > 14 && nHour < eHour+2) {
            session.setAttribute("checkTime","下班打卡");
        } else {
            session.setAttribute("checkTime","未到打卡时间");
        }
        if("HYPER".equals(pos.getName())){
            return "personnel/checking";
        } else if(pos.getName().contains("SURPER")) {
            return "generalmanager/checking";
        } else {
            return "employee/checking";
        }
    }

    /**
     * 设备考勤逻辑处理
     * @param session
     * @return
     */
    @PostMapping("/empCheck")
    @ResponseBody
    public String empCheck(HttpSession session) {
        Position pos = (Position) session.getAttribute("pos");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date(System.currentTimeMillis());
        String nowDate = date.format(now);
        String nowTime = time.format(now);
        //取出设置的打卡时间
        CheckSetting checkSetting = commonServiceImpl.selCS();
        String setMorningTime = checkSetting.getMorningCheckLast();
        String setEveningTime = checkSetting.getEveningCheckFirst();
        int nowSecond = Integer.parseInt(nowTime.split(":")[0])*60*60
                +Integer.parseInt(nowTime.split(":")[1])*60
                +Integer.parseInt(nowTime.split(":")[2]);
        int setMorningSecond = Integer.parseInt(setMorningTime.split(":")[0])*60*60
                +Integer.parseInt(setMorningTime.split(":")[1])*60
                +Integer.parseInt(setMorningTime.split(":")[2]);
        int setEveningSecond = Integer.parseInt(setEveningTime.split(":")[0])*60*60
                +Integer.parseInt(setEveningTime.split(":")[1])*60
                +Integer.parseInt(setEveningTime.split(":")[2]);
        Checking check = new Checking();
        //首先判断设备是否已经打过卡
        if (session.getAttribute("checkTime").equals("上班打卡")) {
            if (commonServiceImpl.empMCheckStatus(pos.getEmployee().getId(),nowDate).equals("上班已打卡")) {
                return "上班已打卡，请勿重复打卡";
            } else {
                check.setEmp(pos.getEmployee());
                check.setMorningCheck(nowTime);
                check.setNowDate(nowDate);
                if(nowSecond <= setMorningSecond) {
                    check.setmState("正常");
                } else {
                    check.setmState("迟到");
                }
                commonServiceImpl.empMCheck(check);
                return "打卡成功";
            }
        } else if (session.getAttribute("checkTime").equals("下班打卡")) {
            if ((commonServiceImpl.empECheckStatus(pos.getEmployee().getId(),nowDate)).equals("下班已打卡")) {
                return "下班已打卡，请勿重复打卡";
            } else {
                check.setEmp(pos.getEmployee());
                check.setEveningCheck(nowTime);
                check.setNowDate(nowDate);
                if(nowSecond >= setEveningSecond) {
                    check.seteState("正常");
                } else {
                    check.seteState("早退");
                }

                commonServiceImpl.empECheck(check);
                return "打卡成功";
            }
        } else {
            return "未到打卡时间，请稍后打卡";
        }
    }

    /**
     * 考勤记录查询
     * @param startTime
     * @param endTime
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/selCheck")
    public String selCheck(@RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime, Model model, HttpSession session) {
        Position pos = (Position) session.getAttribute("pos");
        if("".equals(startTime) || startTime == null) {
            startTime = null;
        }
        if("".equals(endTime) || endTime == null) {
            endTime = null;
        }
        List<Checking> checkings = null;
        if(startTime == null) {
            checkings = commonServiceImpl.selCheckByEmpAndDay(pos.getEmployee().getId(), endTime);
        } else if (endTime == null){
            checkings = commonServiceImpl.selCheckByEmpAndDay(pos.getEmployee().getId(), startTime);
        } else {
            checkings = commonServiceImpl.selCheckByEmpAndDate(pos.getEmployee().getId(), startTime, endTime);
        }
        model.addAttribute("checks", checkings);
        if("HYPER".equals(pos.getName())){
            return "personnel/checking";
        } else if(pos.getName().contains("SURPER")) {
            return "generalmanager/checking";
        } else {
            return "employee/checking";
        }
    }

    /**
     * 跳转到查看工资界面
     * @param session
     * @param response
     * @param model
     * @return
     */
    @GetMapping("/mySalary")
    public String downloadFile(HttpSession session, HttpServletResponse response, Model model){
        String c = System.getProperty("user.dir");
        File fileMkDir = new File(c+"\\src\\main\\resources\\static\\uploads\\");
        String[] list = fileMkDir.list();
        model.addAttribute("fileNames",list);
        Position pos = (Position) session.getAttribute("pos");
        if("HYPER".equals(pos.getName())){
            return "personnel/salary";
        } else if(pos.getName().contains("SURPER")) {
            return "generalmanager/salary";
        } else {
            return "employee/salary";
        }
    }
}
