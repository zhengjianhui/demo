package demo.config;

import org.springframework.core.NestedRuntimeException;


public class ConfigurationException extends NestedRuntimeException implements ExceptionInfoGetter{

	private static final long serialVersionUID = 1L;
	
	public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

	@Override
	public ExceptionInfo getInfo() {
		return null;
	}
}
