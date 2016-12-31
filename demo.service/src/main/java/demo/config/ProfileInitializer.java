package demo.config;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.CollectionUtils;

public class ProfileInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		logger.info("In ProfileInitializer.initialize ... ...");
		ConfigurableEnvironment configEnv = applicationContext.getEnvironment();
		
		Configuration profileConfig = SystemConfig.subset("profiles");
		Set<String> profiles = profileConfig.getKeys();
		
		if(CollectionUtils.isEmpty(profiles)) {
			return;
		}
		
		for (String profile : profiles) {
			if(profileConfig.getBoolean(profile,false)) {
				configEnv.addActiveProfile(profile);
				logger.info(profile +" profile is actived");
			}
		}
	}
}
