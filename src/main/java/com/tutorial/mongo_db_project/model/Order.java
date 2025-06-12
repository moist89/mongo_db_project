package  com.tutorial.mongo_db_project.model;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public  class Order{
@Id
private String id ; 

private String clientId ; 

private String channel ; 

private Instant createdAt ; 

private List<StatusHistory> statusHistories ; 
private List<Item> items ; 

}