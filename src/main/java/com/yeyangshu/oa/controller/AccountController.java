package com.yeyangshu.oa.controller;

import com.github.pagehelper.PageInfo;
import com.yeyangshu.oa.RespStat;
import com.yeyangshu.oa.entity.Account;
import com.yeyangshu.oa.entity.SystemConfig;
import com.yeyangshu.oa.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 用户账号相关处理，登录等功能
 *
 * @author yeyangshu
 * @version 1.0
 * @date 2020/8/26 22:52
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    SystemConfig systemConfig;

    /**
     * 登录页面，异步验证登录信息
     *
     * @return login.html
     */
    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("systemConfig", systemConfig);
        return "account/login";
    }

    /**
     * 异步登录功能
     * 1.直接返回是否登录成功的结果
     * 2.返回Account对象，对象是空的，在controller里做业务逻辑
     *
     * @return
     */
    @RequestMapping("/validateAccount")
    @ResponseBody
    public String validateAccount(String loginName, String password, HttpServletRequest request) {
        System.out.println("loginName：" + loginName + "，password：" + password);
        Account account = accountService.findByLoginNameAndPassword(loginName, password);
        if (account == null) {
            return "登录失败";
        } else {
            // 用户登陆成功，将登录的account写到session里面，可以在不同的controller或者前端页面使用
            request.getSession().setAttribute("account", account);
            return "success";
        }
    }

    /**
     * 退出登录功能，退出后跳转
     *
     * @param request
     * @return
     */
    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute("account");
        return "index";
    }

    /**
     * 人员管理-用户列表
     * 将所有人员信息取出存至session，显示在/account/list中
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize, Model model) {
        System.out.println("pageNum:" + pageNum + "，pageSize：" + pageSize);
        PageInfo<Account> page = accountService.findByPage(pageNum, pageSize);
        model.addAttribute("page", page);
        return "account/list";
    }


    /**
     * 异步删除用户操作
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    @ResponseBody
    public RespStat deleteById(int id) {
        System.out.println("deleteById：" + id);
        // 标记用户是否删除
        return accountService.deleteById(id);
    }

    /**
     * 跳转个人资料页面
     *
     * @return
     */
    @RequestMapping("/profile")
    public String profile() {
        return "account/profile";
    }

    /**
     * 更新个人资料
     * @param filename
     * @param password
     * @return
     */
    @RequestMapping("/updateProfile")
    public String fileUpload(MultipartFile filename, String password, HttpServletRequest request) {
        // 不要从表单中获取用户信息，表单可能会伪造数据
        Account account = (Account)request.getSession().getAttribute("account");
        try {
            // 定位当前项目路径
            // File path = new File(ResourceUtils.getURL("classpath:").getPath());
            // File upload = new File(path.getAbsolutePath(), "static/uploads/");
            // filename.transferTo(new File(upload + "/" + filename.getOriginalFilename()));

            // 文件转存+文件重名
            filename.transferTo(new File("d:/study/project/springboot-oa/uploads/" + filename.getOriginalFilename()));

            account.setPassword(password);
            account.setLocation(filename.getOriginalFilename());
            accountService.update(account);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "account/profile";
    }
}