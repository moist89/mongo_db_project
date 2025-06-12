package com.tutorial.mongo_db_project.service;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class OrderAnalyticsService{
    private MongoTemplate mongoTemplate;
    
        public Double getAverageTransitionTime(String fromStatus, String toStatus) {

        MatchOperation match = Aggregation.match(Criteria.where("statusHistory.status")
                .all(List.of(fromStatus, toStatus)));

        ProjectionOperation project = Aggregation.project()
                .and("statusHistory").as("statusHistory");

        AggregationOperation computeDifference = context -> new Document("$project",
            new Document("diffInSeconds",
                new Document("$let",
                    new Document("vars", new Document()
                        .append("from", new Document("$first", new Document("$filter", new Document()
                            .append("input", "$statusHistory")
                            .append("as", "s")
                            .append("cond", new Document("$eq", List.of("$$s.status", fromStatus))))))
                        .append("to", new Document("$first", new Document("$filter", new Document()
                            .append("input", "$statusHistory")
                            .append("as", "s")
                            .append("cond", new Document("$eq", List.of("$$s.status", toStatus))))))
                    )
                    .append("in", new Document("$subtract", List.of("$$to.timestamp", "$$from.timestamp")))
                )
            )
        );

        GroupOperation avg = Aggregation.group().avg("diffInSeconds").as("averageDiffInMillis");

        Aggregation aggregation = Aggregation.newAggregation(match, project, computeDifference, avg);

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "orders", Document.class);
        Document result = results.getUniqueMappedResult();

        if (result == null) return null;

        long avgMillis = result.getLong("averageDiffInMillis");
        return avgMillis / 1000.0; 
    }
    
    public List<Document> getTop5ProductsByChannel(String channel) {

        MatchOperation matchChannel = Aggregation.match(Criteria.where("channel").is(channel));
    
        UnwindOperation unwindItems = Aggregation.unwind("items");
    
        GroupOperation groupBySku = Aggregation.group("items.sku", "items.name")
            .sum("items.quantity").as("totalSold");
    
        SortOperation sort = Aggregation.sort(Sort.by(Sort.Direction.DESC, "totalSold"));
    
        LimitOperation limit = Aggregation.limit(5);
    
        ProjectionOperation project = Aggregation.project()
            .and("_id.sku").as("sku")
            .and("_id.name").as("name")
            .and("totalSold").as("totalSold");
    
        Aggregation aggregation = Aggregation.newAggregation(
            matchChannel, unwindItems, groupBySku, sort, limit, project
        );
    
        return mongoTemplate.aggregate(aggregation, "orders", Document.class).getMappedResults();
    }
}