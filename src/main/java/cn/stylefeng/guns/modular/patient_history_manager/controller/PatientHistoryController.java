package cn.stylefeng.guns.modular.patient_history_manager.controller;

import cn.stylefeng.guns.modular.system.model.PatientInfo;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.util.ToolUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import cn.stylefeng.guns.modular.system.model.PatientHistory;
import cn.stylefeng.guns.modular.patient_history_manager.service.IPatientHistoryService;

/**
 * 就诊历史管理控制器
 *
 * @author fengshuonan
 * @Date 2018-12-29 17:16:39
 */
@Controller
@RequestMapping("/patientHistory")
public class PatientHistoryController extends BaseController {

    private String PREFIX = "/patient_history_manager/patientHistory/";

    @Autowired
    private IPatientHistoryService patientHistoryService;

    /**
     * 跳转到就诊历史管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "patientHistory.html";
    }

    /**
     * 跳转到添加就诊历史管理
     */
    @RequestMapping("/patientHistory_add")
    public String patientHistoryAdd() {
        return PREFIX + "patientHistory_add.html";
    }

    /**
     * 跳转到修改就诊历史管理
     */
    @RequestMapping("/patientHistory_update/{patientHistoryId}")
    public String patientHistoryUpdate(@PathVariable Integer patientHistoryId, Model model) {
        PatientHistory patientHistory = patientHistoryService.selectById(patientHistoryId);
        model.addAttribute("item",patientHistory);
        LogObjectHolder.me().set(patientHistory);
        return PREFIX + "patientHistory_edit.html";
    }

    /**
     * 获取就诊历史管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if (ToolUtil.isNotEmpty(condition)) return patientHistoryService.selectList(new EntityWrapper<PatientHistory>().like("patient_idcard", "%" + condition + "%"));
        else return patientHistoryService.selectList(null);
    }

    /**
     * 新增就诊历史管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PatientHistory patientHistory) {
        patientHistoryService.insert(patientHistory);
        return SUCCESS_TIP;
    }

    /**
     * 删除就诊历史管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer patientHistoryId) {
        patientHistoryService.deleteById(patientHistoryId);
        return SUCCESS_TIP;
    }

    /**
     * 修改就诊历史管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PatientHistory patientHistory) {
        patientHistoryService.updateById(patientHistory);
        return SUCCESS_TIP;
    }

    /**
     * 就诊历史管理详情
     */
    @RequestMapping(value = "/detail/{patientHistoryId}")
    @ResponseBody
    public Object detail(@PathVariable("patientHistoryId") Integer patientHistoryId) {
        return patientHistoryService.selectById(patientHistoryId);
    }
}
