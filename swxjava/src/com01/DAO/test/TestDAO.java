package com01.DAO.test;

import com01.DAO.dao.ActorDAO;
import com01.jdbcChi.Druid_DButils.Actor;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestDAO {
    @Test
    public void testActorDAO() {
        ActorDAO actorDAO = new ActorDAO();
        String sql = "select * from actor where id>=?";
        List<Actor> actors = actorDAO.queryMulti(sql, Actor.class, 1);
        for (Actor actor : actors) {
            System.out.println(actor);
        }
    }

    @Test
    public void testActorDAOSimple() {
        ActorDAO actorDAO = new ActorDAO();
        String sql = "select * from actor where id=?";
        Actor actor = actorDAO.querySingle(sql, Actor.class, 4);
        System.out.println(actor);
    }

    @Test
    public void testOnlyRow() {
        ActorDAO actorDAO = new ActorDAO();
        String sql = "select name from actor where id=?";
        String name = (String) actorDAO.queryScalar(sql, 4);
        System.out.println(name);
    }

    @Test
    public void testUpdate() {
        ActorDAO actorDAO = new ActorDAO();
        String sql = "insert into actor values(null,?,?,?,?)";
        int update = actorDAO.update(sql, "大傻逼", "男", "1999-12-1", "23456");
        System.out.println(update > 0 ? "成功" : "失败");
    }
}
