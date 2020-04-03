package harsh.rane.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import harsh.rane.model.Mapping;
import harsh.rane.service.DataCacheService;
import harsh.rane.service.ValidateService;

@RestController
public class DataCacheController {

	@Autowired
	ValidateService validateService;
	
	@Autowired
	DataCacheService mappingCache;
	
	
	@GetMapping(path="/validate")
	public String Check()
	{
		return "SUCCESS"+validateService.getValidate();
	}
	
	@GetMapping(path="/getMappings")
	public Collection<Mapping> getMappings() throws InterruptedException
	{
		return mappingCache.getMappings();
	}
	
	@GetMapping(path="/refreshCache")
	public String refreshCache()
	{
		mappingCache.updateCache();
		return "Updated";
	}
	
	@GetMapping(path="/getMapping/{key}")
	public Mapping getMapping(@PathVariable String key)
	{
		return mappingCache.getById(key);
	}
	
	@GetMapping(path="/findAdmins/{value}")
	public String findAdmins(@PathVariable String value)
	{
		return mappingCache.findByQuery(value);
	}
}
