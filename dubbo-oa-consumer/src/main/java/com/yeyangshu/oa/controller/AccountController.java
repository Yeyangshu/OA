package com.yeyangshu.oa.controller;

import com.github.pagehelper.PageInfo;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.yeyangshu.oa.RespStat;
import com.yeyangshu.oa.entity.Account;
import com.yeyangshu.oa.entity.SystemConfig;
import com.yeyangshu.oa.service.IAccountService;
import org.apache.commons.io.FilenameUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @Reference(version = "1.0.0")
    IAccountService accountService;

    @Autowired
    SystemConfig systemConfig;

    @Autowired
    FastFileStorageClient fileStorageClient;

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
     * FastDFS 上传文件
     *
     * @param filename
     * @param password
     * @return
     */
    @RequestMapping("/updateProfile")
    public String fileUpload(MultipartFile filename, String password, HttpServletRequest request) {
        // 不要从表单中获取用户信息，表单可能会伪造数据
        Account account = (Account) request.getSession().getAttribute("account");
        try {
            // 元数据
            Set<MetaData> metaDataSet = new HashSet<MetaData>();
            metaDataSet.add(new MetaData("Author", "yeyangshu"));
            metaDataSet.add(new MetaData("CreateDate", new Date().toString()));

            try {
                StorePath uploadFile = null;
                uploadFile = fileStorageClient.uploadImageAndCrtThumbImage(filename.getInputStream(), filename.getSize(), FilenameUtils.getExtension(filename.getOriginalFilename()), metaDataSet);
                // 下载文件
                System.out.println("uploadFile.getPath()：" + uploadFile.getPath());

                account.setPassword(password);
                account.setLocation("http://192.168.163.111/" + uploadFile.getFullPath());
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            accountService.update(account);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "account/profile";
    }

    /**
     * FastDFS 下载文件
     *
     * @param resp
     * @return
     */
    @RequestMapping("/down")
    @ResponseBody
    public ResponseEntity<byte[]> down(HttpServletResponse resp) {

        DownloadByteArray cb = new DownloadByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "aaa.xx");
        byte[] bs = fileStorageClient.downloadFile("group1", "M00/00/00/wKiWDV0vAb-AcOaYABf1Yhcsfws9181.xx", cb);

        return new ResponseEntity<>(bs,headers, HttpStatus.OK);
    }
}