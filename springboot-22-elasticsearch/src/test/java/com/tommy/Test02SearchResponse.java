package com.tommy;

import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) //
@SpringBootTest(classes = App.class)
public class Test02SearchResponse {

	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;

	@Test
	public void index() throws Exception {
		Client client = elasticsearchTemplate.getClient();

		String id = "" + new Date().getTime();
		XContentBuilder builder = XContentFactory.jsonBuilder()//
			.startObject()//
			.field("id", id)//
			.field("user", "kimchy")//
			.field("postDate", new Date())//
			.field("age", 16)//
			.field("message", "trying out Elasticsearch")//
			.endObject();

		IndexResponse response = client.prepareIndex("twitter1", "doc", id).setSource(builder).get();
		System.out.println(response.getIndex());
		System.out.println(response.toString());
	}

	@Test
	public void search() throws Exception {
		Client client = elasticsearchTemplate.getClient();
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.query(QueryBuilders.termQuery("user", "kimchy"));
		sourceBuilder.from(0);
		sourceBuilder.size(5);

		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("twitter1");
		searchRequest.source(sourceBuilder);

		ActionFuture<SearchResponse> search = client.search(searchRequest);
		SearchResponse searchResponse = search.actionGet();
		System.out.println("******************");
		System.out.println(searchResponse);
		System.out.println(searchResponse.status());
		Iterator<SearchHit> iterator = searchResponse.getHits().iterator();
		while (iterator.hasNext()) {
			SearchHit searchHit = iterator.next();
			System.out.println(searchHit.getId() + "   " + searchHit.getSourceAsString());
		}
		System.out.println("******************");
	}

	@Test
	public void searchAsync() throws Exception {
		Client client = elasticsearchTemplate.getClient();
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.query(QueryBuilders.termQuery("user", "kimchy"));
		sourceBuilder.from(0);
		sourceBuilder.size(5);

		SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("twitter1");
		searchRequest.source(sourceBuilder);

		ActionListener<SearchResponse> listener = new ActionListener<SearchResponse>() {

			@Override
			public void onResponse(SearchResponse searchResponse) {
				System.out.println("******************");
				System.out.println(searchResponse);
				System.out.println(searchResponse.status());
				Iterator<SearchHit> iterator = searchResponse.getHits().iterator();
				while (iterator.hasNext()) {
					SearchHit searchHit = iterator.next();
					System.out.println(searchHit.getId() + " " + searchHit.getSourceAsString());
				}
				System.out.println("******************");
			}

			@Override
			public void onFailure(Exception e) {
				e.printStackTrace();
			}
		};
		client.search(searchRequest, listener);

		TimeUnit.SECONDS.sleep(5);
	}
}
