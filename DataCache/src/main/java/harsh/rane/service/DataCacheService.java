package harsh.rane.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import harsh.rane.model.Mapping;

@Component
public class DataCacheService {

	private Map<String, Mapping> mappingdao = new HashMap<>();

	@PostConstruct
	private void Postconstruct() {
		updateCache();
	}

	public void updateCache() {
		String line = "";
		AWSCredentials credentials = new BasicAWSCredentials("AKIARPSBRILU7DV34PFL",
				"04LnSCxGfL1gPt5SjqbvvHbQkjr8PR2kxfuGZyCW");

		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();

		System.err.println("S3 Call");
		S3Object s3object = s3client.getObject("carefirst-nexus-project", "lambdaMapping.csv");
		S3ObjectInputStream inputStream = s3object.getObjectContent();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			while ((line = br.readLine()) != null) {
				String[] attribute = line.split(",");
				Mapping map = new Mapping(attribute[0], attribute[1], attribute[2], attribute[3]);
				mappingdao.put(map.getId(), map);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Collection<Mapping> getMappings() throws InterruptedException {
		return mappingdao.values();
	}

	public Mapping getById(String key) {
		return mappingdao.get(key);
	}
	
	public String findByQuery(String parameter)
	{
		
		List<Mapping> result=mappingdao.entrySet().stream()
				.filter(map->map.getValue().getUpdatedBy().equals(parameter))
				.map(x->x.getValue()).collect(Collectors.toList());
		
		List<String> sourceList=mappingdao.entrySet().stream()
				.filter(map->map.getValue().getUpdatedBy().equals(parameter))
				.map(x->x.getValue().getSource()).collect(Collectors.toList());
				
		System.err.println(result);
		System.err.println(sourceList);
		return "OK";
	}
	//Date
}
