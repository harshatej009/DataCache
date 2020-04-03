package harsh.rane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DataCacheMain {

	public static void main(String[] args)
	{
		SpringApplication.run(DataCacheMain.class, args);
	}
}
