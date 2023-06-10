package cn.stylefeng.guns.modular.pateint_health_manager.controller;

import cn.stylefeng.guns.modular.system.model.DoctorPoint;
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
import cn.stylefeng.guns.modular.system.model.PatientHealth;
import cn.stylefeng.guns.modular.pateint_health_manager.service.IPatientHealthService;

/**
 * 健康信息管理控制器
 *
 * @author fengshuonan
 * @Date 2018-12-29 16:51:07
 */
@Controller
@RequestMapping("/patientHealth")
public class PatientHealthController extends BaseController {

    private String PREFIX = "/pateint_health_manager/patientHealth/";

    @Autowired
    private IPatientHealthService patientHealthService;

    /**
     * 跳转到健康信息管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "patientHealth.html";
    }

    /**
     * 跳转到添加健康信息管理
     */
    @RequestMapping("/patientHealth_add")
    public String patientHealthAdd() {
        return PREFIX + "patientHealth_add.html";
    }

    /**
     * 跳转到修改健康信息管理
     */
    @RequestMapping("/patientHealth_update/{patientHealthId}")
    public String patientHealthUpdate(@PathVariable Integer patientHealthId, Model model) {
        PatientHealth patientHealth = patientHealthService.selectById(patientHealthId);
        model.addAttribute("item",patientHealth);
        LogObjectHolder.me().set(patientHealth);
        return PREFIX + "patientHealth_edit.html";
    }

    /**
     * 获取健康信息管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if (ToolUtil.isNotEmpty(condition)) return patientHealthService.selectList(new EntityWrapper<PatientHealth>().like("patient_idcard", "%" + condition + "%"));
        else return patientHealthService.selectList(null);
    }

    /**
     * 新增健康信息管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PatientHealth patientHealth) {
        patientHealthService.insert(patientHealth);
        return SUCCESS_TIP;
    }

    /**
     * 删除健康信息管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer patientHealthId) {
        patientHealthService.deleteById(patientHealthId);
        return SUCCESS_TIP;
    }

    /**
     * 修改健康信息管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PatientHealth patientHealth) {
        patientHealthService.updateById(patientHealth);
        return SUCCESS_TIP;
    }

    /**
     * 健康信息管理详情
     */
    @RequestMapping(value = "/detail/{patientHealthId}")
    @ResponseBody
    public Object detail(@PathVariable("patientHealthId") Integer patientHealthId) {
        return patientHealthService.selectById(patientHealthId);
    }
}
