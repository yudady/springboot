package com.betmatrix.theonex.dao.br.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.betmatrix.theonex.App;
import com.betmatrix.theonex.dao.br.model.BrMatchMarketArgument;

@RunWith(SpringJUnit4ClassRunner.class) //
@SpringBootTest(classes = App.class)
public class BrMatchMarketArgumentMapperTest {

	@Autowired
	BrMatchMarketArgumentMapper bBrMatchMarketArgumentMapper;

	@Test
	public void findByMarketId() throws Exception {
		List<BrMatchMarketArgument> list = bBrMatchMarketArgumentMapper.findByMarketId(15505280L);
        System.out.println(list.size());
        System.out.println(list.size());
        System.out.println(list.size());
        System.out.println(list.size());
        System.out.println(list.size());
        System.out.println(list.size());
        System.out.println(list.size());
        System.out.println(list.size());
        bBrMatchMarketArgumentMapper.deleteByMatchMarketArgumentId(1);

	}

}