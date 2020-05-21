package com.xinhai.demo01.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinhai.demo01.bean.RespResult;
import com.xinhai.demo01.bean.User;
import com.xinhai.demo01.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    
    
    @GetMapping("/{id}")
    public RespResult selectOne(@PathVariable(value = "id") Integer id){
        User user = userService.selectById(id);
        return RespResult.success("查询用户成功",1,user);
    }

    @GetMapping("/list")
    public RespResult selectAll(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userService.selectAll();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        long total = userPageInfo.getTotal();
        List<User> userList = userPageInfo.getList();
        return RespResult.success("查询用户列表成功",(int)total, userList);
    }

    @PostMapping("/add")
    public RespResult add(User user){
        Boolean b = userService.insert(user);
        if (b){
            return RespResult.success("添加用户成功");
        }
        return RespResult.error("添加用户失败");
    }

    @PutMapping("/update")
    public RespResult update(User user){
        Boolean b = userService.update(user);
        if (b){
            return RespResult.success("修改用户成功");
        }
        return RespResult.error("修改用户失败");
    }

    @DeleteMapping("/delete/{id}")
    public RespResult delete(@PathVariable(value = "id") Integer id){
        Boolean b = userService.delete(id);
        if (b){
            return RespResult.success("删除用户成功");
        }
        return RespResult.error("删除用户失败");
    }

    @DeleteMapping("/delete")
    public RespResult batchDelete(String idsStr){
        String[] idsArray = idsStr.split(",");
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < idsArray.length; i++) {
            ids.add(Integer.parseInt(idsArray[i]));
        }
        Boolean b = userService.batchDelete(ids);
        if (b){
            return RespResult.success("删除用户成功");
        }
        return RespResult.error("删除用户失败");
    }

    /**
     * Excel表格导出接口
     * http://localhost:8080/ExcelDownload
     * @param response response对象
     * @throws IOException 抛IO异常
     */
    @RequestMapping("/ExcelDownload")
    public void excelDownload(HttpServletResponse response) throws IOException {
        List<User> users = userService.selectAll();

        //表头数据
        String[] header = {"ID", "姓名",  "年龄","性别", "邮箱"};
//        String[] header = {"ID", "姓名", "性别", "年龄", "地址", "分数"};

        //数据内容
//        String[] student1 = {"1", "小红", "女", "23", "成都青羊区", "96"};
//        String[] student2 = {"2", "小强", "男", "26", "成都金牛区", "91"};
//        String[] student3 = {"3", "小明", "男", "28", "成都武侯区", "90"};

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("用户表");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);

        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);


        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
        }

        for (int i = 0; i < users.size(); i++) {
            HSSFRow row1 = sheet.createRow(i+1);
            User user = users.get(i);
            String[] userArray = {user.getId().toString(),user.getUsername(),user.getAge().toString(),user.getGender()==1?"男":"女",user.getEmail()};
            for (int j = 0; j < userArray.length; j++) {
                HSSFCell cell = row1.createCell(j);
                HSSFRichTextString text = new HSSFRichTextString(userArray[j]);
                cell.setCellValue(text);
            }
        }
//        //在表中存放查询到的数据放入对应的列
//        for (Teacher teacher : classmateList) {
//            HSSFRow row1 = sheet.createRow(rowNum);
//            row1.createCell(0).setCellValue(teacher.getTno());
//            row1.createCell(1).setCellValue(teacher.getTname());
//            row1.createCell(2).setCellValue(teacher.getType());
//            row1.createCell(3).setCellValue(teacher.getTpassword());
//            rowNum++;
//        }
//        //模拟遍历结果集，把内容加入表格
//        //模拟遍历第一个学生
//        HSSFRow row1 = sheet.createRow(1);
//        for (int i = 0; i < student1.length; i++) {
//            HSSFCell cell = row1.createCell(i);
//            HSSFRichTextString text = new HSSFRichTextString(student1[i]);
//            cell.setCellValue(text);
//        }
//
//        //模拟遍历第二个学生
//        HSSFRow row2 = sheet.createRow(2);
//        for (int i = 0; i < student2.length; i++) {
//            HSSFCell cell = row2.createCell(i);
//            HSSFRichTextString text = new HSSFRichTextString(student2[i]);
//            cell.setCellValue(text);
//        }
//
//        //模拟遍历第三个学生
//        HSSFRow row3 = sheet.createRow(3);
//        for (int i = 0; i < student3.length; i++) {
//            HSSFCell cell = row3.createCell(i);
//            HSSFRichTextString text = new HSSFRichTextString(student3[i]);
//            cell.setCellValue(text);
//        }

        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //这后面可以设置导出Excel的名称，此例中名为student.xls
        response.setHeader("Content-disposition", "attachment;filename=user.xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }
}
