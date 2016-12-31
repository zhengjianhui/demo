package demo.swagger;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by zhengjianhui on 16/10/2.
 *
 *
 */
@EnableSwagger2
public class SwaggerConfing {

    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)//<3>
                .select()//<4>
                .apis(RequestHandlerSelectors.any())//<5>
                .paths(PathSelectors.any())//<6>
                .build()//<7>
                .pathMapping("/rest/")//<8>
                .produces(Sets.newHashSet("application/json")) //Or whatever default value(s)
                .consumes(Sets.newHashSet("application/json")) //Or whatever default value(s)
                .protocols(Sets.newHashSet("http"))
                .apiInfo(getApiInfo())
//          .directModelSubstitute(LocalDate.class,
//              String.class)//<9>
                .genericModelSubstitutes(ResponseEntity.class)
//          .alternateTypeRules(
//              newRule(typeResolver.resolve(DeferredResult.class,
//                      typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
//                  typeResolver.resolve(WildcardType.class)))//<10>
                .useDefaultResponseMessages(true)//<11>
//          .globalResponseMessage(RequestMethod.GET,//<12>
//              newArrayList(new ResponseMessageBuilder()
//                  .code(500)
//                  .message("500 message")
//                  .responseModel(new ModelRef("Error"))//<13>
//                  .build()))
//          .securitySchemes(newArrayList(apiKey()))//<14>
//          .securityContexts(newArrayList(securityContext()))//<15>
//          .enableUrlTemplating(true)//<21>
                ;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo("郑建辉的测试api", "uvo项目组郑建辉个人", "0.5", "", "江苏优沃科技有限公司", "©有近邻™", "http://www.uvoplus.com");
    }
}
