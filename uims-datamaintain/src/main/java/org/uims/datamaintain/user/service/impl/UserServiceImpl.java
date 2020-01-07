package org.uims.datamaintain.user.service.impl;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uims.common.exception.ApiException;
import org.uims.common.service.SimpleGetListPage;
import org.uims.common.service.SimpleInsertOneService;
import org.uims.common.util.LogUtil;
import org.uims.datamaintain.user.dao.UserInfoDao;
import org.uims.datamaintain.user.dto.UserInfoDo;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service(value = "userService")
public class UserServiceImpl implements SimpleInsertOneService<UserInfoDo>
    ,SimpleGetListPage<UserInfoDo> {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public int insertOne(UserInfoDo userInfoDo) {
        return userInfoDao.insertOne(userInfoDo);
    }

    @Override
    public List<UserInfoDo> getListPage(){
        Map<String,Object> map = new HashMap<>();
        map.put("startNo",0);
        map.put("endNo",10);
        List<UserInfoDo> users = userInfoDao.getListPage(map);
        exportExcel(users);
        return users;
    }

    private void exportExcel(List<UserInfoDo> users){
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("testSheet");
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell = null;

        int rowNO = 0;
        int cellNo = 0;

        String[] title = {"id","姓名","性别","年龄","工作"};
        for (int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
        }

        for (UserInfoDo item : users){
            cellNo = 0;
            rowNO++;
            row = sheet.createRow(rowNO);

            cell = row.createCell(cellNo++);
            cell.setCellValue(item.getId());

            cell = row.createCell(cellNo++);
            cell.setCellValue(item.getFullName());

            cell = row.createCell(cellNo++);
            cell.setCellValue(item.getSex());

            cell = row.createCell(cellNo++);
            cell.setCellValue(item.getAge());

            cell = row.createCell(cellNo++);
            cell.setCellValue(item.getJob());
        }

        File file = new File("d:/test.xlsx");
        FileOutputStream outputStream = null;
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                LogUtil.getLogger(this.getClass()).error("创建excel文件失败");
            }
        }else{

            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                LogUtil.getLogger(this.getClass()).error("文件未找到");
            }


            try {
                wb.write(outputStream);
            } catch (IOException e) {
                LogUtil.getLogger(this.getClass()).error("写文件到磁盘发生错误");
            }finally {
                try {
                    wb.close();
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }
    }

}
