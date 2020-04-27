package com.example.demo;

import com.codingapi.simplemybatis.page.PageList;
import com.codingapi.simplemybatis.query.QueryBuilder;
import com.codingapi.simplemybatis.tree.TreeList;
import com.example.demo.entity.Demo;
import com.example.demo.entity.State;
import com.example.demo.mapper.DemoMapper;
import com.example.demo.view.DemoView;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
@Slf4j
class DemoApplicationTests {

    @Autowired
    private DemoMapper demoMapper;


    @Test
    void allTest() {
        Demo demo = createDemo();
        Assert.isTrue(demoMapper.save(demo)>0,"保存失败");
        Assert.isTrue(demo.getId()>0,"保存失败");

        demo.setMyName("new name ");
        Assert.isTrue(demoMapper.update(demo)>0,"修改失败");
        Assert.isTrue(demoMapper.getById(demo.getId()).getMyName().equals(demo.getMyName()),"getById 查询失败");

        Assert.isTrue(demoMapper.deleteById(demo.getId())>0,"删除失败");

        List<Demo> list = new ArrayList<>();
        list.add(createDemo());
        list.add(createDemo());

        Assert.isTrue(demoMapper.saveAll(list)==2,"批量保存失败");
        Assert.isTrue(demoMapper.findAll().size()>0,"查询失败");
        Assert.isTrue(demoMapper.deleteAll(list)>0,"批量删除失败");

        Demo newDemo = createDemo();
        demoMapper.save(newDemo);

        Assert.isTrue(demoMapper.delete(newDemo)>0,"删除失败");
        demoMapper.saveAll(list);

        List<Long> ids = new ArrayList<>();
        for(Demo item:list){
            ids.add(item.getId());
        }

        Assert.isTrue(demoMapper.deleteAllById(ids)>0,"批量Id删除失败");


    }

    private Demo createDemo(){
        Demo demo = new Demo();
        demo.setMyName(String.valueOf(System.currentTimeMillis()));
        demo.setModule(UUID.randomUUID().toString());
        demo.setName(UUID.randomUUID().toString());
        demo.setFlag(false);
        demo.setState(State.CREATE);
        demo.setSuperId(1L);
        return demo;
    }


    @Test
    void myTest(){
        Demo demo = createDemo();
        demoMapper.save(demo);
    }

    @Test
    void tree(){
        List<TreeList<Demo>> treeLists =  demoMapper.tree( demoMapper::findAll,0L);
        log.info("treeLists:{}",treeLists);
    }


    @Test
    void pageList(){
        PageList<Demo> pageList =  demoMapper.page(1, 5, demoMapper::findAll);
        log.info("page:{}",pageList);
    }


    @Test
    void getById(){
        Demo list =
                demoMapper.getById(1);
        log.info("list:{}",list);
    }

    @Test
    void viewList(){
        Object state = null;
        List<DemoView> list =
                demoMapper.queryView(
                        DemoView.class,
                        QueryBuilder.Build()
                                .select("select * from t_demo d left join t_refrigerator r on d.id = r.ID ")
                                .where()
                                .condition("d.id between #{small} and #{larger}", Map.of("small",1,"larger",10))
                                .and()
                                .condition("r.state = #{state}",state)
                                .and()
                                .condition("d.id in (${ids})",1,2,3,4,5,6,7,8,9,10)
                                .and()
                                .condition("d.name like '%${name}%'","")
                                .orderBy("d.id desc")
                                .builder());
        log.info("list:{}",list);
    }

    @Test
    void queryList(){
        //select * from t_demo where name = '123'
        List<Demo> list = demoMapper.query(QueryBuilder.Build().where().condition("name=#{name}","123").builder());
        log.info("list:{}",list);
    }


    @Test
    public void saveAndGetId(){
        Demo demo =  createDemo();
        demoMapper.save(demo);
        System.out.println(demo.getId());
    }

    @Test
    public void update(){
        Demo demo =  new Demo();
        demo.setId(1L);
        demo.setMyName("!@3123");
        demoMapper.update(demo);
        System.out.println(demo.getId());
    }

}
