package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.model.AddUserCase;
import com.tester.model.User;
import com.tester.model.gsqg;
import com.tester.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserTest {


    @Test(dependsOnGroups = "loginTrue",description = "添加用户接口接口")
    public void addUser() throws IOException, InterruptedException {

        SqlSession session = DatabaseUtil.getSqlSession();
        AddUserCase addUserCase = session.selectOne("addUserCase",1);
        System.out.println(addUserCase.toString());
        System.out.println(TestConfig.addUserUrl);



        //下边的代码为写完接口的测试代码
//        String result = getResult(addUserCase);

        /**
         * 可以先讲
         */
        //查询用户看是否添加成功
//        Thread.sleep(5000);
        User user = session.selectOne("addUser",addUserCase);

//        Thread.sleep(5000);
        System.out.println(user.toString());
        String result = getResult(addUserCase);
        Thread.sleep(5000);


        //处理结果，就是判断返回结果是否符合预期
        Assert.assertEquals(addUserCase.getExpected(),result);


    }


    private String getResult(AddUserCase addUserCase) throws IOException {
        //下边的代码为写完接口的测试代码
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        String param = new String();
        param = "canSubmit=0&buyApplyDto.buyApply.id=&GLOBAL_SIGNCONTROL=&ASSIGN_MANAGER=&ASSIGN_SIGNTIME=&global_scg=&buyApplyDto.workflowActivity.id=&buyApplyDto.showId=&buyApplyDto.attachmentUnit.id=&buyApplyDto.buyApply.status=&buyApplyDto.buyApply.publishStatus=&buyApplyDto.assignManagerNode=&buyApplyDto.buyApply.buyReplyCreateFlag=&GLOBAL_SHENGCAI=&GLOBAL_FENMANAGER=&GLOBAL_SHEGNCAIBU=&GLOBAL_BUMENSHENGPI=&GLOBAL_BIANZHI=&GLOBAL_FENMANAGERSHENGPI=&GLOBAL_FENZONGMANAGERSHENGPI=&GLOBAL_CAIGOUQIANBAO=&GLOBAL_FENMANAGER1=&URL=%2FriskControlAction.do%3Fmethod%3DgetPrepareList%26masterId%3D%26showId%3D&buyApplyDto.buyApply.code=&buyApplyDto.buyApply.isBillType=0&buyApplyDto.buyApply.name="+ gsqg.qgName+"&buyApplyDto.buyApply.fundTypeFirst=1&buyApplyDto.materialCate.id=1&buyApplyDto.buyApply.orgBelong=1&buyApplyDto.buyApply.orgBelong=1&buyApplyDto.buyApply.secondCateType=&buyApplyDto.buyApply.secondCateType=&buyApplyDto.buyApply.fundTypeThird=&buyApplyDto.buyApply.fundTypeThird=&buyApplyDto.project.code=&buyApplyDto.project.id=&buyApplyDto.project.name=&buyApplyDto.buyApply.buyApplyType=0&buyApplyDto.buyApply.buyDealMode=1&buyApplyDto.buyApply.clientBase=&buyApplyDto.buyApply.clientBase=&buyApplyDto.buyApply.productSource=&buyApplyDto.buyApply.productSource=&buyApplyDto.buyApply.requireFinishDate=2018-10-20&buyApplyDto.buyApply.fileNumId=&buyApplyDto.buyApply.fileNum=&buyApplyDto.buyApply.fileNum=&buyApplyDto.buyApply.stockItemMemo=&buyApplyDto.buyApply.stockProviderRange=&buyApplyDto.buyApply.judgeResult=&buyApplyDto.buyApply.stockSchemeMemo=&buyApplyDto.buyApply.description=&buyApplyDto.buyApply.applyDealMemo=&buyApplyDto.buyApply.applyDealOther=&buyApplyDto.applyDealUser.id=&buyApplyDto.applyDealUser.name=&buyApplyDto.buyApply.applyDealUserPhone=&buyApplyDto.creator.id=3650&buyApplyDto.creator.name=%D5%C5%BA%A3%BE%FC&buyApplyDto.buyApply.phoneOfOriginator=13919260880&buyApplyDto.unit.id=1&buyApplyDto.unit.name=%CA%A1%B9%AB%CB%BE&buyApplyDto.department.id=25&buyApplyDto.department.name=%B2%C9%B9%BA%B2%BF&buyApplyDto.buyApply.createDate=2018-10-20&buyApplyDto.buyApply.finishDate=";
//        JSONObject param = new JSONObject();
//        param.put("userName",addUserCase.getUserName());
//        param.put("password",addUserCase.getPassword());
//        param.put("sex",addUserCase.getSex());
//        param.put("age",addUserCase.getAge());
//        param.put("permission",addUserCase.getPermission());
//        param.put("isDelete",addUserCase.getIsDelete());
        //设置请求头信息 设置header
        post.setHeader("content-type","application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //设置cookies
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.store);
        //声明一个对象来进行响应结果的存储
        String result;
        //执行post方法
        HttpResponse response = TestConfig.defaultHttpClient.execute(post);
        //获取响应结果
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        return result;
    }



}
