package com.itheima.solr;

import com.itheima.pojo.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-solr.xml"})
public class SolrDemo {

    @Resource
    private SolrTemplate solrTemplate;

    @Test
    public void testSaveOrUpdate(){
        Item item = new Item();
        item.setBrand("华为");
        item.setCategory("手机");
        item.setGoodsId(1L);
        item.setId(1L);
        item.setImage("http://ip:port/image/xxx.jpg");
        item.setPrice(new BigDecimal("99998"));
        item.setSeller("传智小店");
        item.setTitle("小米8SE 32G内存 4G全网通");
        solrTemplate.saveBean(item,5000);
        //solrTemplate.commit();
    }
    @Test
    public void testFindById(){
        Item item = solrTemplate.getById(1, Item.class);
        System.out.println(item);
    }

    @Test
    public void testFindByQuery(){
        Criteria criteria = new Criteria("item_title");
        criteria= criteria.contains("小米");
        SimpleQuery query = new SimpleQuery(criteria);
        ScoredPage<Item> items = solrTemplate.queryForPage(query, Item.class);
        System.out.println("总条数="+items.getTotalPages());
        for (Item item : items) {
            System.out.println(item);
        }
    }
    @Test
    public void testDeteleById() {
        solrTemplate.deleteById("1");
        solrTemplate.commit();
    }

    }

