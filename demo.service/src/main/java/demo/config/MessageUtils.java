package demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;

public class MessageUtils implements MessageSourceAware {

	private static MessageSourceAccessor accessor;
	
	public static String getMessage(String code, Object... args) {
		return accessor.getMessage(code, args);
	}
	
	@Override
	public void setMessageSource(MessageSource messageSource) {
		MessageUtils.accessor = new MessageSourceAccessor(messageSource);
	}

}
