package com.tommy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.tommy.model.EsMatchCompetitor;
import com.tommy.model.EsMatchMarketArgument;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.Aggregations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tommy.model.EsMatchInfoDoc;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.rangeQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@RunWith(SpringJUnit4ClassRunner.class) //
@SpringBootTest(classes = App.class)
public class Test01ElasticSearch {

	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;

	@Test
	public void init() throws Exception {
		System.out.println(elasticsearchTemplate.getElasticsearchConverter());
	}

	@Test
	public void test01Save() throws Exception {
		EsMatchInfoDoc doc = new EsMatchInfoDoc();
		Date now = new Date();
		doc.setMatchId(now.getTime());
		doc.setMatchTitle("title-" + now);
		doc.setMatchTime(now);

        List<EsMatchCompetitor> esMatchCompetitors = new ArrayList<>();

        List<EsMatchMarketArgument> esMatchMarketArguments = new ArrayList<>();

		//
		IndexQuery indexQuery = new IndexQueryBuilder().withObject(doc).build();
		elasticsearchTemplate.index(indexQuery);

	}

    @Test
    public void test01Read() throws Exception {
        BoolQueryBuilder boolQueryBuilder = boolQuery().
                must(rangeQuery("matchTime").gte("20180101010101").lt("20580101010101").format("yyyyMMddHHmmss"))
                ;

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withPageable(PageRequest.of(0, 10))
                .build();

        AggregatedPage<EsMatchInfoDoc> esMatchInfoDocs = elasticsearchTemplate.queryForPage(searchQuery, EsMatchInfoDoc.class);
        System.out.println(esMatchInfoDocs.getContent().size());
        System.out.println(esMatchInfoDocs.getContent().size());
        System.out.println(esMatchInfoDocs.getContent().size());
        esMatchInfoDocs.stream().forEach(System.out::println);
    }



}
