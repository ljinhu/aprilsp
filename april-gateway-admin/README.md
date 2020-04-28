#### 路由保存接口
POST  april/gatewayroute/save

请求报文示例：
~~~json
{
  "createdBy": "jimbolix",
  "createdTime": "2020-04-28 07:07:19.026Z",
  "description": "网关路由添加测试",
  "filters": [{"name":"RewritePath","args":{"_genkey_1":"/api/gateway/admin/(?<segment>.*)","_genkey_2":"/$\\{segment}"}}],
  "orders": 0,
  "predicates": [{"name":"Path","args":{"_genkey_1":"/api/gateway/admin/**"}}],
  "routeId": "gateway_admin_route",
  "status": "1",
  "updatedBy": "",
  "updatedTime": "2020-04-28 07:07:19.026Z",
  "uri": "lb://april-gateway-admin"
}
~~~
上面配置等同于在yaml做如下配置
~~~yaml
spring:
  cloud:
    gateway:
      routes:
      - id: gateway_admin_route
        uri: lb://april-gateway-admin
        predicates:
        - Path=/api/gateway/admin/**
        filters:
        - RewritePath=/api/gateway/admin/(?<segment>.*),/$\{segment}
~~~
**注意:**
filters的args中的key必须要按照_genkey_格式，否则spring cloud gateway会出错

FilterDefinition源码如下：
~~~java_holder_method_tree
public FilterDefinition(String text) {
		int eqIdx = text.indexOf('=');
		if (eqIdx <= 0) {
			setName(text);
			return;
		}
		setName(text.substring(0, eqIdx));

		String[] args = tokenizeToStringArray(text.substring(eqIdx + 1), ",");

		for (int i = 0; i < args.length; i++) {
			this.args.put(NameUtils.generateName(i), args[i]);
		}
	}
	
	--
	/**
    	 * Generated name prefix.
    	 */
    	public static final String GENERATED_NAME_PREFIX = "_genkey_";
    
    	public static String generateName(int i) {
    		return GENERATED_NAME_PREFIX + i;
    	}
~~~
predicates的args中的key也必须要按照_genkey_格式
PredicateDefinition源码如下
~~~java_holder_method_tree
public PredicateDefinition(String text) {
		int eqIdx = text.indexOf('=');
		if (eqIdx <= 0) {
			throw new ValidationException("Unable to parse PredicateDefinition text '"
					+ text + "'" + ", must be of the form name=value");
		}
		setName(text.substring(0, eqIdx));

		String[] args = tokenizeToStringArray(text.substring(eqIdx + 1), ",");

		for (int i = 0; i < args.length; i++) {
			this.args.put(NameUtils.generateName(i), args[i]);
		}
	}
~~~